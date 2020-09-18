package ClassTable.src.datasource;

import java.sql.Statement;

public class MetalRowDataGateway {
  
  public void createTableMetal() {
    String dropTable = "DROP TABLE IF EXISTS Metal;";
    String createTable = "CREATE TABLE Metal" + "(" 
        + "metalId INT NOT NULL, "
        + "dissolvedBy VARCHAR(20) , " 
        + "FOREIGN KEY(dissolvedBy) REFERENCES Acid(acidId),"
        + "FOREIGN KEY(metalId) REFERENCES Chemical(chemicalId)"
        + ");"; // Unsure of how to store dissolvedBy, is an acid
    
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
