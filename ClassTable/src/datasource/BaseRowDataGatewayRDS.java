package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseRowDataGatewayRDS implements BaseRowDataGateway {
  int baseId, solute;
  String name, inhabits; 
  
  /**
   * Constructor BaseRowDataGateway, search for existing Base via id 
   * @param id
   */
  public BaseRowDataGatewayRDS(int id) {
    this.baseId = id; 
    
    // Select statements
    String getAcid = new String("SELECT * FROM Base WHERE acidId = " + id + ";"),
        getChem = new String("SELECT * FROM Chemical INNER JOIN Base ON Chemical.chemicalId = " + id + ";");
    
    try {
      // Get acid information
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement(); 
      ResultSet rs = statement.executeQuery(getAcid); 
      rs.next(); // Get result 
      this.solute = rs.getInt("solute"); 
      
      // Get chemical information 
      rs = statement.executeQuery(getChem); 
      rs.next(); 
      this.name = rs.getString("name"); 
      this.inhabits = rs.getString("inhabits"); 
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch ID");
    }
  }
  
  /**
   * AcidRowDataGateway constructor for creating a new base
   * @param id
   * @param solute
   * @param name
   * @param inhabits
   */
  public void BaseRowDataGateway(int id, int solute, String name, String inhabits) {
    // Set instance variables
    this.baseId = id;
    this.solute = solute;
    this.name = name;
    this.inhabits = inhabits;
    
    // Insert Base
    try {
      // Insert chemical
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      // Insert Acid
      PreparedStatement insertAcid = DatabaseManager.getSingleton().getConnection()
        .prepareStatement("INSERT INTO Base (baseId, solute)" + "VALUES (?, ?);");
      insertAcid.setInt(1, id); // Set acid id
      insertAcid.setInt(2, solute); // set solute id
      
      insertChemical.execute(); // Insert chemical
      insertAcid.execute();  // Insert acid
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert acid through constructor");
    }
  }
  
  /**
   * create base table
   */
  public void createTableBase() {
    String dropTable = "DROP TABLE IF EXISTS Base;";
    String createTable = "CREATE TABLE Base" + "(" 
        + "baseId INT NOT NULL, "
        + "solute VARCHAR(20), " 
        + "FOREIGN KEY(baseId) REFERENCES Chemical(chemicalId)"
        + ");"; // Not sure how to store solute, is a chemical
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      // Drop the table if exists first
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;"); 
      statement.executeUpdate(dropTable);
      statement.executeUpdate(createTable);
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Get solute
   */
  @Override
  public int getSolute() {
    return solute;
  }


  /** 
   * Set solute
   */
  @Override
  public void setSolute(int newSolute) {
    this.solute = newSolute;
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
   */
  @Override
  public void setName(String newName) {
    this.name = newName;
  }


  /**
   * Get inhabits
   */
  @Override
  public String getInhabits() {
    return inhabits;
  }


  /**
   * Set inhabits
   */
  @Override
  public void setInhabits(String newInhabits) {
    this.inhabits = newInhabits;
  }
}
