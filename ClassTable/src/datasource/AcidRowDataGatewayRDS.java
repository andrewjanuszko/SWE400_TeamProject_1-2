package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AcidRowDataGatewayRDS implements AcidRowDataGateway {
  private int acidId, solute;
  private String name, inhabits;
  
  /**
   * Constructor used for resetting Acid/Chemical tables.
   * Delete all tables, and then make them again.
   */
  public AcidRowDataGatewayRDS() {
    dropTableAcid(); // Drop Acid and Chemical table.
    createTable(); // re-create Acid and Chemical table.
  }
  
  /**
   * Constructor used to find an existing Acid.
   * @param id of the acid to find
   */
  public AcidRowDataGatewayRDS(int id) {
    // Statements to find existing acid/chemical and collect their information.
    String getAcid = new String("SELECT * FROM Acid WHERE acidId = " + id + ";"),
        getChem = new String("SELECT * FROM Chemical INNER JOIN Acid ON Chemical.chemicalId = " + id + ";");
    
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
      this.acidId = id; 
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch ID");
    }
  }
  
  /**
   * AcidRowDataGateway constructor for creating a new acid.
   * @param id of acid to insert
   * @param solute of acid to insert
   * @param name of acid to insert
   * @param inhabits of acid to isnert
   */
  public AcidRowDataGatewayRDS(int id, int solute, String name, String inhabits) {
    createTable();
    try {
      // Insert chemical 
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      
      // Insert Acid
      PreparedStatement insertAcid = DatabaseManager.getSingleton().getConnection()
        .prepareStatement("INSERT INTO Acid (acidId, solute)" + "VALUES (?, ?);");
      insertAcid.setInt(1, id); // Set acid id
      insertAcid.setInt(2, solute); // set solute id
      
      insertChemical.execute(); // Insert chemical
      insertAcid.execute();  // Insert acid
      
      this.acidId = id;
      this.solute = solute; 
      this.name = name;
      this.inhabits = inhabits;
      
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert acid through constructor");
    }
  }

  /**
   * Create acid and chemical table if they do not already exist. 
   */
  public void createTable() {
    String createChem = "CREATE TABLE IF NOT EXISTS Chemical" + "(" + "chemicalId INT NOT NULL, " + "name VARCHAR(20), "
        + "inhabits VARCHAR(20), " + "PRIMARY KEY (chemicalId)" + ");",
        createAcid = "CREATE TABLE IF NOT EXISTS Acid" + "(acidId INT NOT NULL, " + 
        "solute INT, " + "FOREIGN KEY(acidId) REFERENCES Chemical(chemicalId)" + ");";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(createChem);
      statement.executeUpdate(createAcid);
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to create acid and/or chemical table");
    }
  }
  
	/**
	 * Drop the acid table if it exists.
	 */
	public void dropTableAcid() {
	  String dropTable = "DROP TABLE IF EXISTS Acid";
	  try {
	    Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
	    statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
	    statement.executeUpdate(dropTable);
	  } catch (SQLException | DatabaseException e) {
	    e.printStackTrace();
	    System.out.println("Error dropping acid table");
	  }
	}
	
	/**
	 * Drop the chemical table if it exists.
	 */
	public void dropTableChemical() {
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
	 * Drop acid and all tables connected (acid & chemical)
	 */
	public void dropAllTables() {
	  dropTableAcid();
	  dropTableChemical();
	}
	
	/**
	 * Delete an acid from both acid and chemical tables
	 */
  @Override
  public void delete() {
    String deleteChemical = "DELETE FROM Chemical WHERE ChemicalId = " + acidId + ";",
        deleteAcid = "DELETE FROM Acid WHERE AcidId = " + acidId + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      
      statement.executeUpdate(deleteAcid);
      statement.executeUpdate(deleteChemical);
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error deleting acid " + acidId);
    }
  }
  
  /**
   * Insert a new acid.
   * @param id to insert
   * @param solute to insert
   * @param name to insert
   * @param inhabits to insert
   */
  public void insert(int id, int solute, String name, String inhabits) {
    try {
      // Insert chemical 
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      
      // Insert Acid
      PreparedStatement insertAcid = DatabaseManager.getSingleton().getConnection()
        .prepareStatement("INSERT INTO Acid (acidId, solute)" + "VALUES (?, ?);");
      insertAcid.setInt(1, id); // Set acid id
      insertAcid.setInt(2, solute); // set solute id
      
      insertChemical.execute(); // Insert chemical
      insertAcid.execute();  // Insert acid
      
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Return solute of acid
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
   * Return name of acid.
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
   * Return inhabits of acid
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

  /**
   * Fetch a new acid.
   * @param newId to fetch
   * @throws DatabaseException 
   * @throws SQLException 
   */
  public void fetch(int newId) throws SQLException, DatabaseException {
    String getAcid = new String("SELECT * FROM Acid WHERE acidId = " + newId + ";"),
        getChem = new String("SELECT * FROM Chemical INNER JOIN Acid ON Chemical.chemicalId = " + newId + ";");
    
    // Temporary variables
    String tmpName, tmpInh;
    int tmpSol; 
    
      // Get acid information
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement(); 
      ResultSet rs = statement.executeQuery(getAcid); 
      rs.next(); // Get result 
      tmpSol = rs.getInt("solute"); 
      
      // Get chemical information 
      rs = statement.executeQuery(getChem); 
      rs.next(); 
      tmpName = rs.getString("name"); 
      tmpInh = rs.getString("inhabits"); 
      
      // If we get to this point without errors, then it is safe to update our variables
      this.name = tmpName;
      this.solute = tmpSol;
      this.inhabits = tmpInh;
      
    
  }
  
  /**
   * Update the database.
   */
  @Override
  public void update() {
    String updateChemicalSQL = "UPDATE Chemical SET chemicalId = ?, name = ?, inhabits = ? WHERE chemicalID = " + acidId + ";";
    String updateAcidSQL = "UPDATE Acid SET acidId = ?, solute = ? WHERE acidId = " + acidId + ";";
    
    try {
      // Chemical
      PreparedStatement chem = DatabaseManager.getSingleton().getConnection().prepareStatement(updateChemicalSQL);
      chem.setInt(1, acidId);
      chem.setString(2, name);
      chem.setString(3, inhabits);

      chem.execute();
      
      // Acid
      PreparedStatement acid = DatabaseManager.getSingleton().getConnection().prepareStatement(updateAcidSQL);
      acid.setInt(1, acidId);
      acid.setInt(2, solute);
      
      acid.execute();
      
      
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to update acid");
    }

  }
	
  /**
   * Find set 
   * @param dissolvedById
   * @return
   */
  public static List<AcidRowDataGatewayRDS> findSet(int soluteId) {
    List<AcidRowDataGatewayRDS> results = new ArrayList<>();
    try {
      String sql = "SELECT * FROM Acid WHERE solute = "+ soluteId + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while(rs.next()) {
        AcidRowDataGatewayRDS soluteIds = new AcidRowDataGatewayRDS(rs.getInt("acidId"));
          results.add(soluteIds);
      }
    } catch (SQLException | DatabaseException e) {
      //
    }
    return results;
  }
}
