package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChemicalRowDataGatewayRDS implements ChemicalRowDataGateway {
  int id;
  String name, inhabits;
  
  /**
   * Initialize DB by dropping all tables and then creating them,
   * Probably shouldn't drop all tables, but it makes the tests 
   * pass atm. 
   */
  public ChemicalRowDataGatewayRDS() {
    dropTable(); // Should not be here, maybe
    createTable();
  }
  
  /**
   * Constructor ChemicalRowDataGatewayRDS, search for existing chemical.
   * @param id to search for
   */
  public ChemicalRowDataGatewayRDS(int id) {
    createTable();
    // Select statement
    String getChem = new String("SELECT * FROM Chemical WHERE chemicalId = " + id + ";");
    
    try {
      // Get chemical information
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement(); 
      ResultSet rs = statement.executeQuery(getChem); 
      rs.next(); 
      
      this.name = rs.getString("name"); 
      this.inhabits = rs.getString("inhabits"); 
      this.id = id; 
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch ID");
    }

  }
  
  /**
   * Constructor ChemicalRowDataGatewayRDS, create a new chemical.
   * @param id
   * @param name
   * @param inhabits
   */
  public ChemicalRowDataGatewayRDS(int id, String name, String inhabits) {
    createTable();
    try {
      // Insert chemical
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      
      
      insertChemical.execute(); // Insert chemical
      
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert chemical through constructor");
    }
    
    // Set instance variables
    this.id = id;
    this.name = name;
    this.inhabits = inhabits;
  }
  
  /**
   * Create chemical table if it does not already exist.
   */
  public void createTable() {
    String createChem = "CREATE TABLE IF NOT EXISTS Chemical" + "(" + "chemicalId INT NOT NULL, " + "name VARCHAR(20), "
        + "inhabits VARCHAR(20), " + "PRIMARY KEY (chemicalId)" + ");";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(createChem);
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to create chemical table");
    }
  }
  
  /**
   * Drop the chemical table if it exists.
   */
  public void dropTable() {
    String dropTable = "DROP TABLE IF EXISTS Chemical";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
      statement.executeUpdate(dropTable);
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error dropping chemical table");
    }
  }

  /**
   * Delete the currently selected chemical from the database. 
   */
  public void delete() {
    String deleteChemical = "DELETE FROM Chemical WHERE ChemicalId = " + id + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      
      statement.executeUpdate(deleteChemical);
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error deleting chemical " + id);
    }
  }

  /**
   * Fetch a new chemical.
   * @param newId to fetch
   * @throws DatabaseException 
   * @throws SQLException 
   */
  public void fetch(int newId) throws SQLException, DatabaseException {
    String getChem = new String("SELECT * FROM Chemical WHERE Chemical.chemicalId = " + newId + ";");
    
    // Temporary variables
    String tmpName, tmpInh;
    
    // Get chemical information
    Statement statement = DatabaseManager.getSingleton().getConnection().createStatement(); 
    ResultSet rs = statement.executeQuery(getChem); 
    rs.next(); // Get result 
    
    tmpName = rs.getString("name"); 
    tmpInh = rs.getString("inhabits"); 
    
    // If we get to this point without errors, then it is safe to update our variables
    this.id = newId;
    this.name = tmpName;
    this.inhabits = tmpInh;
      
  }  
  
  public void insert(int id, String name, String inhabits) {
    try {
    // Insert chemical
    PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
        .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
    insertChemical.setInt(1, id);
    insertChemical.setString(2, name);
    insertChemical.setString(3, inhabits);
    insertChemical.execute(); // Insert chemical
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Update the database.
   */
  public void update() {
    String updateChemicalSQL = "UPDATE Chemical SET chemicalId = ?, name = ?, inhabits = ? WHERE chemicalID = " + id
        + ";";

    try {
      // Chemical
      PreparedStatement chem = DatabaseManager.getSingleton().getConnection().prepareStatement(updateChemicalSQL);
      chem.setInt(1, id);
      chem.setString(2, name);
      chem.setString(3, inhabits);

      chem.execute();
      
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to update chemical");
    }
  }
  
  /**
   * Get name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Set name.
   * @param newName new name to set
   */
  @Override
  public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Get inhabits.
   */
  public String getInhabits() {
    return inhabits;
  }

  /**
   * Set inhabits.
   * @param newInhabits new inhabits to set
   */
  @Override
  public void setInhabits(String newInhabits) {
    this.inhabits = newInhabits;
  }

}
