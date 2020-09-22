package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseRowDataGatewayRDS implements BaseRowDataGateway {

  public void createTableBase() {
    String dropTable = "DROP TABLE IF EXISTS Base;";
    String createTable = "CREATE TABLE Base" + "(" 
        + "baseId INT NOT NULL, "
        + "solute VARCHAR(20), " 
        + "FOREIGN KEY(baseId) REFERENCES Chemical(chemicalId)"
        + ");"; // Not sure how to store solute, is a chemical
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      // Drop the table if exists first
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;"); 
      statement.executeUpdate(dropTable);
      statement.executeUpdate(createTable);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  

  /**
   * Get solute from a given id.
   * 
   * @param id get
   */
  @Override
  public int getSoluteId(int id) {
    int solute = 0;
    String sql = new String("SELECT * FROM Base WHERE baseId = " + id + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      solute = rs.getInt("solute"); // Get name from "name" column
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch solute id");
    }
    return solute;
  }

  /**
   * Get the name of the acid
   * 
   * @param id to fetch name of
   */
  @Override
  public String getName(int id) {
    String name = "";
    String sql =
        "SELECT Chemical.name FROM Chemical INNER JOIN Base ON Chemical.chemicalId = " + id + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      name = rs.getString("name"); // Get name from "name" column
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch name of acid");
    }
    return name;
  }

  /**
   * Get inhabits from compoundID
   * 
   * @param compoundId to find inhabits from
   */
  @Override
  public String getInhabits(int id) {
    String inhabits = "";
    String sql = new String(
        "SELECT Chemical.inhabits FROM Chemical INNER JOIN Base ON Chemical.chemicalId = " + id + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql); 
      rs.next();
      inhabits = rs.getString("inhabits");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch inhabits of acid");
    }
    
    return inhabits;
  }
  @Override
  public void insert(int baseId, int solute, String name, String inhabits) {
    
    try {
      PreparedStatement insertChemical =  DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, baseId);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Base (baseId, solute)" + "VALUES (?, ?);");
      insert.setInt(1, baseId);
      insert.setInt(2, solute);

      insertChemical.execute();
      insert.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }
}
