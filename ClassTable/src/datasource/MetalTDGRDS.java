package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class MetalTDGRDS implements MetalTDG {
  String sql = "SELECT * FROM Metal INNER JOIN Chemical ON Metal.metalId = Chemical.chemicalId "
      + "INNER JOIN Element ON Element.elementId = Metal.metalId ";
  private static MetalTDGRDS singleton;

  public static MetalTDGRDS getSingleton() {
    if (singleton == null) {
      singleton = new MetalTDGRDS();
    }
    return singleton;
  }

  public List<MetalDTO> executeQuery() throws DatabaseException {
    List<MetalDTO> listDTO = new ArrayList<>();
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(this.sql + ";");
      ResultSet results = statement.executeQuery();

      while (results.next()) {
        int id = results.getInt("metalId");
        int dissolvedBy = results.getInt("dissolvedBy");
        int atomicNum = results.getInt("atomicNumber");
        double moles = results.getDouble("moles");
        double atomicMass = results.getDouble("atomicMass");
        String name = results.getString("name");
        double inventory = results.getDouble("inventory");
        MetalDTO metal = new MetalDTO(id, dissolvedBy, atomicNum, atomicMass, moles, name, inventory);
        listDTO.add(metal);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }

    sql = "SELECT * FROM Metal INNER JOIN Chemical ON Metal.metalId = Chemical.chemicalId "
        + "INNER JOIN Element ON Element.elementId = Metal.metalId ";

    return listDTO;
  }

  @Override
  public MetalTDGRDS getAllMetals() {
    sql = "SELECT * FROM Metal INNER JOIN Chemical ON Metal.metalId = Chemical.chemicalId "
        + "INNER JOIN Element ON Element.elementId = Metal.metalId ";
    return getSingleton();
  }

  public MetalTDGRDS filterByName(String name) {
    sql += " AND (Chemical.name LIKE '%" + name + "%')";
    return getSingleton();
  }

  public MetalTDGRDS filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ")";
    return getSingleton();
  }

  public MetalTDGRDS filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ")";
    return getSingleton();
  }

  public MetalTDGRDS filterByAtomicMass(double atomicMass) {
    sql += " AND (Element.atomicMass = " + atomicMass + ")";
    return getSingleton();
  }

  public MetalTDGRDS filterByAtomicMassRange(double high, double low) {
    sql += " AND (Element.atomicMass BETWEEN " + low + " AND " + high + ")";
    return getSingleton();
  }

  public MetalTDGRDS filterByAtomicNumber(int atomicNumber) {
    sql += " AND (Element.atomicNumber = " + atomicNumber + ")";
    return getSingleton();
  }

  public MetalTDGRDS filterByAtomicNumberRange(int high, int low) {
    sql += " AND (Element.atomicNumber BETWEEN '" + low + "' AND '" + high + "')";
    return getSingleton();
  }

  public MetalTDGRDS filterByDissolvedBy(int dissolvedBy) {
    sql += " AND (Metal.dissolvedBy = " + dissolvedBy + ")";
    return getSingleton();
  }

  public MetalTDGRDS filterByMoles(double moles) {
    sql += " AND (Metal.moles = " + moles + ")";
    return getSingleton();
  }

  public MetalTDGRDS filterByMolesRange(double high, double low) {
    sql += " AND (Metal.moles BETWEEN " + low + " AND " + high + ")";
    return getSingleton();
  }

  public static void delete(int i) {
    String sqlMetal = "DELETE FROM Metal WHERE metalId = " + i + ";";
    String sqlElement = "DELETE FROM Element WHERE elementId = " + i + ";";
    String sqlChem = "DELETE FROM Chemical WHERE chemicalId = " + i + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(sqlMetal);
      statement.executeUpdate(sqlElement);
      statement.executeUpdate(sqlChem);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Problem deleting Metal with id " + i);
    }
  }

  public static void create(int id, int dissolvedBy, double moles, int atomicNumber, double atomicMass, String name,
      double inventory) {

    String createChemical = "INSERT INTO Chemical (chemicalId, name, inventory) VALUES (?, ?, ?);",
        createElement = "INSERT INTO Element (elementId, atomicNumber, atomicMass) VALUES (?, ?, ?);",
        createMetal = "INSERT INTO Metal (metalId, dissolvedBy, moles) VALUES (?, ?, ?);";

    try {
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(createChemical),
          insertElement = DatabaseManager.getSingleton().getConnection().prepareStatement(createElement),
          insertMetal = DatabaseManager.getSingleton().getConnection().prepareStatement(createMetal);
      
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setDouble(3, inventory);
      
      insertElement.setInt(1, id);
      insertElement.setInt(2, atomicNumber);
      insertElement.setDouble(3, atomicMass);
      
      insertMetal.setInt(1, id);
      insertMetal.setInt(2, dissolvedBy);
      insertMetal.setDouble(3, moles);
      
      insertChemical.execute();
      insertElement.execute();
      insertMetal.execute();
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to create metal");
    }
  }

}
