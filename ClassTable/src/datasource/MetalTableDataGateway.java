package ClassTable.src.datasource;

import java.sql.Statement;

public class MetalTableDataGateway {
  
  public void createTableMetal() {
    String dropTable = "DROP TABLE IF EXISTS Metal;";
    String createTable = "CREATE TABLE Metal" + "(" + "chemicalId INT NOT NULL, "
        + "dissolvedBy VARCHAR(20) " + ");"; // Unsure of how to store dissolvedBy, is an acid
    
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
