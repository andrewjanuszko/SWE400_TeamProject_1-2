package ClassTable.src.datasource;

import java.sql.SQLException;
import java.sql.Statement;

public class BaseRowDataGateway {
  
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
      statement.executeUpdate(dropTable);
      // Create new Monitorings Table
      statement.executeUpdate(createTable);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  public void insertBase(int id, String solute) {
    String insert = "INSERT INTO Base (baseId, solute) VALUES (" 
        + id + ", " + solute + ");"; 
    try  {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeQuery(insert);
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    
  }
}
