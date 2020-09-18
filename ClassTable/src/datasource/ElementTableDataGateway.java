package ClassTable.src.datasource;

import java.sql.Statement;

public class ElementTableDataGateway {
  public void createTableElement() {
    String dropTable = "DROP TABLE IF EXISTS Element;";
    String createTable = "CREATE TABLE Element" + "(" + "chemicalId INT NOT NULL, "
        + "atomicNumber INT, " + "atomicMass DOUBLE) " + ");";
    
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
