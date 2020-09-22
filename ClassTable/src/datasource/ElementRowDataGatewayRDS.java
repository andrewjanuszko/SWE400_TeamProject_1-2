package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ElementRowDataGatewayRDS implements ElementRowDataGateway {
  /**
   * Create element table
   */
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
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to create/drop element table");
    }
  }

  /**
   * Get atomic number of element from a given id
   * 
   * @param id to search for atomic number of
   */
  @Override
  public int getAtomicNumber(int id) {
    int num = -1;
    String sql = "SELECT atomicNumber FROM Element where elementId = " + id + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      num = rs.getInt("atomicNumber"); // Get atomic number from atomicNumber column
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch name of element");
    }
    return num;
  }

  /**
   * Get atomic mass of element from a given id
   * 
   * @param to search for atomic mass of
   */
  @Override
  public int getAtomicMass(int id) {
    int mass = -1;
    String sql = "SELECT atomicMass FROM Element where elementId = " + id + ";";
        
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next(); // Get result
      mass = rs.getInt("atomicMass"); // Get atomic mass from atomicMass column
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch name of element");
    }
    return mass;
  }

  /**
   * Get name of element
   * 
   * @param id to search for name of
   */
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
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch name of element");
    }
    return name;
  }

  /**
   * Get inhabits of a element
   * @param id to search for inhabits of
   */
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
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch inhabits of element");
    }
    
    return inhabits;
  }

  /**
   * Insert an element into the table
   * @param id to insert
   * @param atomicNum to insert
   * @param atomicMass to insert
   */
  @Override
  public void insert(int id, int atomicNum, int atomicMass, String name, String inhabits) {
    try {
      PreparedStatement insertChemical =  DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Element (elementId, atomicNumber, atomicMass)" 
              + "VALUES (?, ?, ?);");
      
      insert.setInt(1, id);
      insert.setInt(2, atomicNum);
      insert.setInt(3, atomicMass);

      insertChemical.execute();
      insert.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert");
    } 
  }
  
}
