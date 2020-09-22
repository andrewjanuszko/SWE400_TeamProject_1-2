package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MetalRowDataGatewayRDS implements MetalRowDataGateway {

  public void createTableMetal() {
    String dropTable = "DROP TABLE IF EXISTS Metal;";
    String createTable = "CREATE TABLE Metal" + "(" 
        + "metalId INT NOT NULL, "
        + "dissolvedBy INT," 
        + "FOREIGN KEY(dissolvedBy) REFERENCES Acid(acidId),"
        + "FOREIGN KEY(metalId) REFERENCES Chemical(chemicalId)"
        + ");"; 
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      // Drop the table if exists first
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;"); 
      statement.executeUpdate(dropTable);
      // Create new Monitorings Table
      statement.executeUpdate(createTable);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getName(int id) {
    String name = "";
    String sql =
        "SELECT Chemical.name FROM Chemical INNER JOIN Metal ON Chemical.chemicalId = " + id + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      name = rs.getString("name"); // Get name from "name" column
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch name of metal");
    }
    return name;
  }

  @Override
  public String getInhabits(int id) {
    String inhabits = "";
    String sql = new String(
        "SELECT Chemical.inhabits FROM Chemical INNER JOIN Metal ON Chemical.chemicalId = " + id + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql); 
      rs.next();
      inhabits = rs.getString("inhabits");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch inhabits of Metal");
    }
    
    return inhabits;
  }

  @Override
  public int getDissolvedBy(int id) {
    int dissolvedBy = -1;
    String sql = new String(
        "SELECT dissolvedBy FROM Metal WHERE metalId = " + id + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql); 
      rs.next();
      dissolvedBy = rs.getInt("dissolvedBy");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch dissolvedBy of metal");
    }
    
    return dissolvedBy;
  }

  @Override
  public void insert(int id, int dissolvedBy) {
    try {
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Metal (metalId, dissolvedBy)" + " VALUES (?, ?);");
      insert.setInt(1, id); // Set chemicalId
      insert.setInt(2, dissolvedBy); // Set name

      insert.execute(); // Insert

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert metal");
    } 
  }
}
