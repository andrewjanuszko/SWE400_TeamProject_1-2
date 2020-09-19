package datasource;

import java.sql.Statement;

public class CompoundTableDataGateway {

  public void createTableCompound() {
    String dropTable = "DROP TABLE IF EXISTS Compound;";
    String createTable = "CREATE TABLE Compound" + "(" 
        + "compoundId INT NOT NULL, "
        + "madeOf VARCHAR(20), " 
        + "FOREIGN KEY(compoundId) REFERENCES Chemical(chemicalId)"
        + ");"; // Not sure how to store madeOf (list of elements)
    
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
