package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcidRowDataGatewayRDS implements AcidRowDataGateway {
  private int acidId, solute;
  private String name, inhabits;
  
  /**
   * AcidRowDataGatewayRDS constructor, search for specific existing acid via id
   * @param id
   */
  public AcidRowDataGatewayRDS(int id) {
    this.acidId = id; 
    
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
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch ID");
    }
  }
  
  /**
   * AcidRowDataGateway constructor for creating a new acid
   * @param id
   * @param solute
   * @param name
   * @param inhabits
   */
  public AcidRowDataGatewayRDS(int id, int solute, String name, String inhabits) {
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
      System.out.println("Failed to insert acid through constructor");
    }
  }
  
  /**
   * Create acid table.
   */
	@Override
	public void createTableAcid() {
		String dropTable = "DROP TABLE IF EXISTS Acid;";
		String createTable = "CREATE TABLE Acid" + "(acidId INT NOT NULL, " + 
		"solute INT, " + "FOREIGN KEY(acidId) REFERENCES Chemical(chemicalId)" + ");";
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			
			// Drop the table if exists first
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
			statement.executeUpdate(dropTable);
			
			statement.executeUpdate(createTable);
			
		} catch (Exception e) {
		  // If the drop or create failed
			e.printStackTrace();
			System.out.println("Failed to create acid table.");
		}
	}

	/**
	 * Create acid table if it does not exist
	 */
	public void dropTableAcid() {
	  String dropTables
	  
	  statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
    statement.executeUpdate(dropTable);
	}
	/**
	 * Delete an acid from both acid and chemical tables
	 * @param id
	 */
  @Override
  public void delete(int id) {
    String deleteChemical = "DELETE FROM Chemical WHERE ChemicalId = " + id + ";",
        deleteAcid = "DELETE FROM Acid WHERE AcidId = " + id + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      
      statement.executeUpdate(deleteAcid);
      statement.executeUpdate(deleteChemical);
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error deleting acid " + id);
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
   * Update the database entry
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
	
}
