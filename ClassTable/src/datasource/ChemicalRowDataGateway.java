package datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import datasource.DatabaseManager;

public class ChemicalRowDataGateway {

  public void createTableChemcial() {
    String dropTable = "DROP TABLE IF EXISTS Chemical;";
    String createTable = "CREATE TABLE Chemical" + "(" + "chemicalId INT NOT NULL, " + "name VARCHAR(20), "
        + "inhabits VARCHAR(20), " + "PRIMARY KEY (chemicalId)" + ");";

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

  public String getName(int chemicalId) {
    String name = ""; 
    String sql = new String("SELECT * FROM Chemical WHERE chemicalId = " + chemicalId + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next();
      name = rs.getString("name");
    } catch (Exception e) { 
      
    }
    return name;
  }

  public String getInhabits(int chemicalId) {
    String inhabits = ""; 
    String sql = new String("SELECT * FROM Chemical WHERE chemicalId = " + chemicalId + ";");
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next();
      inhabits = rs.getString("inhabits");
    } catch (Exception e) { 
      
    }
    return inhabits;
  }
  
  public void insertChemical(int id, String name, String inhabits) {
    
  }
  
}
