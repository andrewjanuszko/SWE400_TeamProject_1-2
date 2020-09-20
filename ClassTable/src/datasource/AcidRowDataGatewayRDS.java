package datasource;

import java.sql.ResultSet;
import java.sql.Statement;

public class AcidRowDataGatewayRDS implements AcidRowDataGateway {

  @Override
  public void createTableAcid() {
    String dropTable = "DROP TABLE IF EXISTS Acid;";
    String createTable = "CREATE TABLE Acid" + "(" + "acidId INT NOT NULL, " + "solute VARCHAR(20), "
        + "FOREIGN KEY(acidId) REFERENCES Chemical(chemicalId)" + ");";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      // Drop the table if exists first
      statement.executeUpdate(dropTable);
      // Create new Monitorings Table
      statement.executeUpdate(createTable);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Get solute from a given id. 
   */
  @Override
  public int getSoluteId(int id) {
    int solute = 0;
    String sql = new String("SELECT * FROM Acid WHERE acidId = " + id + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      solute = rs.getInt("solute"); // Get name from "name" column
    } catch (Exception e) { 
      e.printStackTrace();
    }
    return solute;
  }
  
  
  @Override
  public String getName(int id) {
    String name = ""; 
    String sql = new String("SELECT Chemical.name FROM Chemical INNER JOIN Acid ON Chemical.chemicalId = " + id + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      name = rs.getString("name"); // Get name from "name" column
    } catch (Exception e) { 
      e.printStackTrace();
    }
    return name;
  }
  /**
   * Get inhabits from compoundID
   * @param compoundId to find inhbaits from
   */
  @Override
  public String getInhabits(int id) {
    String inhabits = ""; 
    String sql = new String("SELECT Chemical.inhabits FROM Chemical INNER JOIN Compound ON Chemical.chemicalId = " + id + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next();
      inhabits = rs.getString("inhabits");
    } catch (Exception e) { 
      e.printStackTrace();
    }
    return inhabits;
  }
}
