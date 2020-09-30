package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * BaseRowDataGatewayRDS
 * @author Isabella Boone, Kim O'Neill
 */
public class BaseRowDataGatewayRDS implements BaseRowDataGateway {
  int baseId, solute;
  String name, inhabits; 
  
  /**
   * Create table
   */
  public BaseRowDataGatewayRDS() {
    createTable();
  }
  
  /**
   * Constructor BaseRowDataGateway, search for existing Base via id 
   * @param id
   */
  public BaseRowDataGatewayRDS(int id) throws SQLException, DatabaseException {
    createTable();
    // Select statements
    String getBase = new String("SELECT * FROM Base WHERE baseId = " + id + ";"),
        getChem = new String("SELECT * FROM Chemical INNER JOIN Base ON Chemical.chemicalId = " + id + ";");

    // Get acid information
    Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
    ResultSet rs = statement.executeQuery(getBase);
    rs.next(); // Get result
    this.solute = rs.getInt("solute");

    // Get chemical information
    rs = statement.executeQuery(getChem);
    rs.next();
    this.name = rs.getString("name");
    this.inhabits = rs.getString("inhabits");
    this.baseId = id;

  }
  
  /**
   * AcidRowDataGateway constructor for creating a new base
   * @param id
   * @param solute
   * @param name
   * @param inhabits
   */
  public BaseRowDataGatewayRDS(int id, int solute, String name, String inhabits) {   
    createTable();
    
    try {
      // Insert chemical
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      
      // Insert Base
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
    
    // Set instance variables
    this.baseId = id;
    this.solute = solute;
    this.name = name;
    this.inhabits = inhabits;
  }
  
  /**
   * Create base and chemical table if they do not already exist.
   */
  public void createTable() {
    String createChem = "CREATE TABLE IF NOT EXISTS Chemical" + "(" + "chemicalId INT NOT NULL, " + "name VARCHAR(20), "
        + "inhabits VARCHAR(20), " + "PRIMARY KEY (chemicalId)" + ");",
        createBase = "CREATE TABLE IF NOT EXISTS Base" + "(" + "baseId INT NOT NULL, "
            + "solute VARCHAR(20), " 
            + "FOREIGN KEY(baseId) REFERENCES Chemical(chemicalId)" + ");";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(createChem);
      statement.executeUpdate(createBase);
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to create base and/or chemical table");
    }
  }
  
  /**
   * Drop the base table if it exists.
   */
  public void dropTableBase() {
    String dropTable = "DROP TABLE IF EXISTS Base;";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
      statement.executeUpdate(dropTable);
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error dropping base table");
    }
  }
  
  /**
   * Drop the chemical table if it exists.
   */
  public void dropTableChemical() {
    String dropTable = "DROP TABLE IF EXISTS Chemical;";
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
   * Drop base and all tables connected (base & chemical)
   */
  public void dropAllTables() {
    dropTableBase();
    dropTableChemical();
  }
  
  /**
   * Delete a base from both chemical and base tables.
   */
  public void delete() {
    String deleteChemical = "DELETE FROM Chemical WHERE ChemicalId = " + baseId + ";",
        deleteBase = "DELETE FROM Base WHERE baseId = " + baseId + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      
      statement.executeUpdate(deleteBase);
      statement.executeUpdate(deleteChemical);
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error deleting base " + baseId);
    }
  }
  
  /**
   * Update the database.
   */
  public void update() {
    String updateChemicalSQL = "UPDATE Chemical SET chemicalId = ?, name = ?, inhabits = ? WHERE chemicalID = " + baseId
        + ";", updateBaseSQL = "UPDATE Base SET baseId = ?, solute = ? WHERE baseId = " + baseId + ";";

    try {
      // Chemical
      PreparedStatement chem = DatabaseManager.getSingleton().getConnection().prepareStatement(updateChemicalSQL);
      chem.setInt(1, baseId);
      chem.setString(2, name);
      chem.setString(3, inhabits);

      chem.execute();
      
      // Base
      PreparedStatement acid = DatabaseManager.getSingleton().getConnection().prepareStatement(updateBaseSQL);
      acid.setInt(1, baseId);
      acid.setInt(2, solute);
      
      acid.execute();
      
      
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to update base");
    }
  }
  
  /**
   * 
   */
  public List<BaseRowDataGatewayRDS> findSet(int solute) {
    List<BaseRowDataGatewayRDS> results = new ArrayList<>(); 
    
    try {
      String sql = "SELECT * FROM Base WHERE solute = " + solute + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      
      while(rs.next()) {
        int sol = rs.getInt("baseId");
        BaseRowDataGatewayRDS id = new BaseRowDataGatewayRDS(sol); 
        results.add(id);
      }
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    return results;
  }
  
  /**
   * Get solute.
   */
  @Override
  public int getSolute() {
    return solute;
  }

  /** 
   * Set solute.
   */
  @Override
  public void setSolute(int newSolute) {
    this.solute = newSolute;
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
   */
  @Override
  public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Get inhabits.
   */
  @Override
  public String getInhabits() {
    return inhabits;
  }

  /**
   * Set inhabits.
   */
  @Override
  public void setInhabits(String newInhabits) {
    this.inhabits = newInhabits;
  }

}
