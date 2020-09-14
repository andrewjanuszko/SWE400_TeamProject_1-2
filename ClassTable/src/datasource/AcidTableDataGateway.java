package ClassTable.src.datasource;

import java.sql.Statement;

public class AcidTableDataGateway {
  
  public void createTableBase() {
    String dropTable = "DROP TABLE IF EXISTS Acid;";
    String createTable = "CREATE TABLE Acid" + "(" + "chemicalID INT NOT NULL, "
        + "solute VARCHAR(20), "   // Not sure how to store solute, is a chemical
        + "dissolves VARCHAR(20) " // Not sure how to store dissolves, is list of chemical 
        + ");";
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
