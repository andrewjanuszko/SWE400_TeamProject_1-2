package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChemicalRowDataGatewayRDS implements ChemicalRowDataGateway {
  
  /** 
   * Create Chemical table 
   */
  public void createTableChemcial() {
    String dropTable = "DROP TABLE IF EXISTS Chemical;";
    String createTable = "CREATE TABLE Chemical" + "(" + "chemicalId INT NOT NULL, " + "name VARCHAR(20), "
        + "inhabits VARCHAR(20), " + "PRIMARY KEY (chemicalId)" + ");";

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

  /** Get name from Chemical table of a given chemicalId.
   * @param chemicalId to search for
   */
  public String getName(int chemicalId) {
    String name = ""; 
    String sql = new String("SELECT * FROM Chemical WHERE chemicalId = " + chemicalId + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      name = rs.getString("name");
    } catch (Exception e) { 
      e.printStackTrace();
    }
    return name;
  }

  /** Get inhabits from the Chemical table of a given chemicalId
   * @param chemicalId to search for
   */
  public String getInhabits(int chemicalId) {
    String inhabits = "",; 
    String sql = new String("SELECT * FROM Chemical WHERE chemicalId = " + chemicalId + ";");
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
  
  /** Insert a given chemicalId, name and inhabits into the Chemical table. 
   * @param chemicalId to insert
   * @param name to insert
   * @param inhabits to insert
   */
  @Override
  public void insert(int chemicalId, String name, String inhabits) {
    try {
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insert.setInt(1, chemicalId); // Set chemicalId
      insert.setString(2, name); // Set name
      insert.setString(3, inhabits); // Set inhabits

      insert.execute(); // Insert
      
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }

}
