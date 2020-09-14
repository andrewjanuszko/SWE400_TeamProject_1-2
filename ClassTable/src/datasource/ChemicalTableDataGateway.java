package ClassTable.src.datasource;

import java.sql.SQLException;
import java.sql.Statement;

public class ChemicalTableDataGateway {

  public void createTableChemcial() {
    String dropTable = "DROP TABLE IF EXISTS Chemical;";
    String createTable = "CREATE TABLE Chemical" + "(" + "chemicalID INT NOT NULL, "
        + "name VARCHAR(20), " + "inhabits VARCHAR(20) " + ");";
    
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
