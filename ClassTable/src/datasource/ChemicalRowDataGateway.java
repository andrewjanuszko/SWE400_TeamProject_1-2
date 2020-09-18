package ClassTable.src.datasource;

import java.sql.SQLException;
import java.sql.Statement;

public class ChemicalRowDataGateway {

  public void createTableChemcial() {
    String dropTable = "DROP TABLE IF EXISTS Chemical;";
    String createTable = "CREATE TABLE Chemical" + "(" + "chemicalID INT NOT NULL, "
        + "name VARCHAR(20), " + "inhabits VARCHAR(20), " + "PRIMARY KEY (chemicalID)"+ ");";
    
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
  
  
  
  
}
