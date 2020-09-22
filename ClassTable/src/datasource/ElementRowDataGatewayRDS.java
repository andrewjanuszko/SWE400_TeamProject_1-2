package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ElementRowDataGatewayRDS implements ElementRowDataGateway {
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
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;"); 
      statement.executeUpdate(dropTable);
      
      // Create tables
      statement.executeUpdate(createTable);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getAtomicNumber(int id) {
    int num = -1;
    String sql = "SELECT atomicNumber FROM Element where elementId = " + id + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      num = rs.getInt("atomicNumber"); // Get atomic number from atomicNumber column
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch name of element");
    }
    return num;
  }

  @Override
  public int getAtomicMass(int id) {
    int mass = -1;
    String sql = "SELECT atomicMass FROM Element where elementId = " + id + ";";
        
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      mass = rs.getInt("atomicMass"); // Get atomic mass from atomicMass column
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch name of element");
    }
    return mass;
  }

  @Override
  public String getName(int id) {
    String name = "";
    String sql =
        "SELECT Chemical.name FROM Chemical INNER JOIN Element ON Chemical.chemicalId = " + id + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      name = rs.getString("name"); // Get name from "name" column
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch name of element");
    }
    return name;
  }

  @Override
  public String getInhabits(int id) {
    String inhabits = "";
    String sql = new String(
        "SELECT Chemical.inhabits FROM Chemical INNER JOIN Element ON Chemical.chemicalId = " + id + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql); 
      rs.next();
      inhabits = rs.getString("inhabits");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to fetch inhabits of element");
    }
    
    return inhabits;
  }

  @Override
  public void insert(int id, int atomicNum, int atomicMass) {
    try {
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Element (elementId, atomicNumber, atomicMass)" 
              + "VALUES (?, ?, ?);");
      
      insert.setInt(1, id);
      insert.setInt(2, atomicNum);
      insert.setInt(2, atomicMass);

      insert.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert");
    } 
  }
  
}
