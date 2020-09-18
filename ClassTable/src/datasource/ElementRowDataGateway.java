package ClassTable.src.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ClassTable.src.datasource.DatabaseManager;

public class ElementRowDataGateway {
  public void createTableElement() {
    String dropTable = "DROP TABLE IF EXISTS Element;";
    String createTable = "CREATE TABLE Element" + "(" 
        + "elementId INT NOT NULL, "
        + "atomicNumber INT, " 
        + "atomicMass DOUBLE, " 
        + "FOREIGN KEY(elementId) REFERENCES Chemical(chemicalId)"
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
  
  public String getName(int elementId) {
    String name = ""; 
    String sql = new String("SELECT Chemical.name FROM Chemical INNER JOIN Element ON Chemical.chemicalId = "+ elementId + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next();
      name = rs.getString("name");
    } catch (Exception e) { 
      
    }
    return name;
  }
  
  public void insertElement(int id, int atmoicNumber, int atmoicMass) {
    String insert = "INSERT INTO Element (acidId, atomicNumber, atomicMass) VALUES (" 
        + id + ", " + atmoicNumber + ", " + atmoicMass + ");"; 
    try  {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeQuery(insert);
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    
  }
}
