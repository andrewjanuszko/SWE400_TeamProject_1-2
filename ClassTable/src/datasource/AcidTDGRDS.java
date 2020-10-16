package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class AcidTDGRDS implements AcidTDG {
  String sql = "SELECT * FROM Acid INNER JOIN Chemical WHERE Acid.acidId = Chemical.chemicalId";
  private static AcidTDGRDS singleton;

  public AcidTDGRDS() {

  }

  public static AcidTDGRDS getSingleton() {
    if (singleton == null) {
      singleton = new AcidTDGRDS();
    }
    return singleton;
  }

  public static void delete(int acidId) {
    String deleteChemical = "DELETE FROM Chemical WHERE ChemicalId = " + acidId + ";",
        deleteAcid = "DELETE FROM Acid WHERE AcidId = " + acidId + ";";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();

      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
      statement.executeUpdate(deleteAcid);
      statement.executeUpdate(deleteChemical);
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error deleting acid " + acidId);
    }
  }

  public static ArrayList<MetalDTO> getMetals(int acidId) {
    String sqlMetal = "SELECT * FROM Metal INNER JOIN Chemical WHERE dissolvedBy = " + acidId + ";";
    ArrayList<MetalDTO> metals = new ArrayList<>();
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlMetal);

      while (rs.next()) {
        String sqlElement = "SELECT * FROM Element WHERE elementId = " + rs.getInt("metalId") + ";";
        ResultSet rsElement = statement.executeQuery(sqlElement);
        rsElement.next();
        metals.add(new MetalDTO(rs.getInt("metalId"), rs.getInt("dissolvedBy"), rsElement.getInt("atomicNumber"),
            rsElement.getDouble("atomicMass"), rs.getInt("moles"), rs.getString("name"), rs.getDouble("inventory")));

      }
    } catch (SQLException | DatabaseException e) {

    }
    return metals;
  }

  /**
   * Get all acids in the database.
   */
  public AcidTDGRDS getAllAcids() {
    sql = "SELECT * FROM Acid INNER JOIN Chemical WHERE Acid.acidId = Chemical.chemicalId";
    return getSingleton();
  }

  public AcidTDGRDS filterByName(String name) {
    sql += " AND (Chemical.name LIKE '%" + name + "%') ";
    System.out.println(sql);
    return getSingleton();
  }

  public AcidTDGRDS filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ") ";
    System.out.println(sql);
    return getSingleton();
  }

  public AcidTDGRDS filterBySolute(int solute) {
    sql += " AND (Acid.solute = " + solute + ") ";
    System.out.println(sql);
    return singleton;
  }

  public AcidTDGRDS filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ") ";
    System.out.println(sql);
    return getSingleton();
  }

  public List<AcidDTO> executeQuery() throws DatabaseException {
    List<AcidDTO> listDTO = new ArrayList<>();
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(this.sql + ";");
      try {
        ResultSet results = statement.executeQuery();

        while (results.next()) {
          int acidId = results.getInt("acidId");
          int soluteId = results.getInt("solute");
          String name = results.getString("name");
          double inventory = results.getDouble("inventory");
          AcidDTO acid = new AcidDTO(acidId, soluteId, name, inventory);
          listDTO.add(acid);
        }

        // Reset sql string
        sql = "SELECT * FROM Acid INNER JOIN Chemical WHERE Acid.acidId = Chemical.chemicalId ";
      } catch (SQLException e) {
        throw new DatabaseException("Failed to convert query to DTO.", e);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
    return listDTO;
  }
}