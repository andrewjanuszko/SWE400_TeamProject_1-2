package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompoundsMadeOfTableDataGatewayRDS implements CompoundsMadeOfTableDataGateway {

  @Override
  public void createTableDataMadeOf() {
    String dropTable = "DROP TABLE IF EXISTS CompoundTableDataMadeOf;";
    String createTable = "CREATE TABLE CompoundTableDataMadeOf (" + 
        "compoundId INT NOT NULL, " +
        "c_elementId INT NOT NULL, " + 
        "FOREIGN KEY (compoundId) REFERENCES chemical(chemicalId)," +
        "FOREIGN KEY (c_elementId) REFERENCES Element(elementId)";
    
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
   * Get all compoundId's of elementId
   * @param elementId to search for
   */
  @Override
  public List<Integer> getCompoundId(int elementId) {
    String sql = "SELECT * FROM CompoundMadeFromElement WHERE elementId = " + elementId + ";";
    List<Integer> compounds = new ArrayList<>();
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      // While there are still results to search through
      while(rs.next()) {
        // Add each result to compound list
        compounds.add(rs.getInt("compoundId"));
      }
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    return compounds;
  }

  /**
   * Get all elementId's of of compoundId
   * @param compoundId to search for
   */
  @Override
  public List<Integer> getElementId(int compoundId) {
    String sql = "SELECT * FROM CompoundMadeFromElement WHERE compoundId = " + compoundId + ";";
    List<Integer> compounds = new ArrayList<>();
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      // While there are still results to search through
      while(rs.next()) {
        // Add each result to compound list
        compounds.add(rs.getInt("elementId"));
      }
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    return compounds;
  }

  /** Insert a given compoundId and elementId into the CompoundsMadeOfElement table. 
   * @param compoundId to insert
   * @param elementId to insert
   */
  @Override
  public void insert(int compoundId, int elementId) {
    try {
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO CompoundsMadeOfElement (compoundId, madeOf)" + "VALUES (?, ?);");
      insert.setInt(1, compoundId); // Set compoundId
      insert.setInt(2, elementId); // Set elementId

      insert.execute(); // Insert into table
      
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }

  /** Get compound name from compoundId
   * @param compoundId compoundId of id searching for
   */
  @Override
  public String getCompoundName(int compoundId) {
    String name = ""; 
    String sql = new String("SELECT Chemical.name FROM Chemical INNER JOIN Compound ON Chemical.chemicalId = " + compoundId + ";");
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
  
  @Override
  /** Get inhabits from the Chemical table of a given chemicalId
   * @param chemicalId to search for
   */
  public String getInhabits(int compoundId) {
    String inhabits = ""; 
    String sql = new String("SELECT Chemical.inhabits FROM Chemical INNER JOIN Compound ON Chemical.chemicalId = " + compoundId + ";");
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
