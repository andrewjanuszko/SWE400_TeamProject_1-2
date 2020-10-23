package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class ElementTDGRDS implements ElementTDG {
  String sql = "SELECT * FROM Element INNER JOIN Chemical WHERE (Element.elementId = Chemical.chemicalId)";
  private static ElementTDGRDS singleton;

  public ElementTDGRDS() {
    // TODO Auto-generated constructor stub
  }

  public static ElementTDGRDS getSingleton() {
    if (singleton == null) {
      singleton = new ElementTDGRDS();
    }
    return singleton;
  }

  public ElementTDGRDS getAllElements() {
    sql = "SELECT * FROM Element INNER JOIN Chemical WHERE (Element.elementId = Chemical.chemicalId)";
    return getSingleton();
  }

  public ElementDTO getDTO(int id) throws DatabaseException {
    String sql = "SELECT * FROM Element INNER JOIN Chemical ON Element.elementId = Chemical.chemicalId AND elementId = "
        + id + ";";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next();

      return new ElementDTO(id, rs.getInt("atomicNum"), rs.getDouble("atomicMass"), rs.getString("name"),
          rs.getDouble("inventory"));

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("No entry with id " + id);
      throw new DatabaseException("No entry with id " + id, e);
    }
  }

  public ElementTDGRDS filterByName(String name) {
    sql += " AND (Chemical.name LIKE '%" + name + "%') ";
    return getSingleton();
  }

  public ElementTDGRDS filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ")";
    return getSingleton();
  }

  public ElementTDGRDS filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ")";
    return getSingleton();
  }

  public ElementTDGRDS filterByAtomicMass(double atomicMass) {
    sql += " AND (Element.atomicMass = " + atomicMass + ")";
    return getSingleton();
  }

  public ElementTDGRDS filterByAtomicMassRange(double high, double low) {
    sql += " AND (Element.atomicMass BETWEEN " + low + " AND " + high + ")";
    return getSingleton();
  }

  public ElementTDGRDS filterByAtomicNumber(int atomicNumber) {
    sql += " AND (Element.atomicNumber = " + atomicNumber + ")";
    return getSingleton();
  }

  public ElementTDGRDS filterByAtomicNumberRange(int high, int low) {
    sql += " AND (Element.atomicNumber BETWEEN " + low + " AND " + high + ")";
    return getSingleton();
  }
  
  public List<ElementDTO> executeQuery() throws DatabaseException {
    List<ElementDTO> listDTO = new ArrayList<>();
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(this.sql + ";");
      ResultSet results = statement.executeQuery();

      while (results.next()) {
        int id = results.getInt("chemicalId");
        int atomicNum = results.getInt("atomicNumber");
        double atomicMass = results.getDouble("atomicMass");
        String name = results.getString("name");
        double inventory = results.getDouble("inventory");
        ElementDTO element = new ElementDTO(id, atomicNum, atomicMass, name, inventory);
        listDTO.add(element);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
    sql = "SELECT * FROM Element INNER JOIN Chemical WHERE (Element.elementId = Chemical.chemicalId)";
    return listDTO;
  }

  public static void delete(int i) {
    String sqlElement = "DELETE FROM Element WHERE elementId = " + i + ";";
    String sqlChem = "DELETE FROM Chemical WHERE chemicalId = " + i + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(sqlElement);
      statement.executeUpdate(sqlChem);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Problem deleting Element with id " + i);
    }
  }
  
  
}
