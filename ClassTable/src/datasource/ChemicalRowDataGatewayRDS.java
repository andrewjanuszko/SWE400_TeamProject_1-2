package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChemicalRowDataGatewayRDS implements ChemicalRowDataGateway {
  int id;
  String name, inhabits;
  
  /** 
   * Create Chemical table 
   */
  public void createTableChemical() {
    String dropTable = "DROP TABLE IF EXISTS Chemical;";
    String createTable = "CREATE TABLE Chemical" + "(" + "chemicalId INT NOT NULL, " + "name VARCHAR(20), "
        + "inhabits VARCHAR(20), " + "PRIMARY KEY (chemicalId)" + ");";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      // Drop the table if exists first
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;"); 
      statement.executeUpdate(dropTable);
      // Create table
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
      statement.executeUpdate(createTable);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * ChemicalRowDataGatewayRDS constructor, Search for existing chemical via id
   * @param id
   */
  public ChemicalRowDataGatewayRDS(int id) {
    this.id = id; 
    
    String getChem = new String("SELECT * FROM Chemical WHERE chemicalId = " + id + ";");
    
    try {
      // Get acid information
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement(); 
      ResultSet rs = statement.executeQuery(getChem); 
      rs.next(); 
      this.name = rs.getString("name"); 
      this.inhabits = rs.getString("inhabits"); 
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch ID & retrieve values");
    }
  }
  
  /**
   * ChemicalRowDataGatewayRDS constructor, create new chemical
   * @param id of new id
   * @param name of new id
   * @param inhabits of new id
   */
  public ChemicalRowDataGatewayRDS(int id, String name, String inhabits) {
    // Set instance variables
    this.id = id;
    this.name = name;
    this.inhabits = inhabits;
    
    // Attempt to add chemical
    try {
      PreparedStatement insert= DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      
      insert.setInt(1, id);
      insert.setString(2, name);
      insert.setString(3, inhabits);
      
      insert.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert new chemical");
    }
    
  }
  
  /**
   * Get name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Set name
   * @param newName new name to set
   */
  @Override
  public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Get inhabits
   */
  public String getInhabits() {
    return inhabits;
  }

  /**
   * Set inhabits
   * @param newInhabits new inhabits to set
   */
  @Override
  public void setInhabits(String newInhabits) {
    this.inhabits = newInhabits;
  }

}
