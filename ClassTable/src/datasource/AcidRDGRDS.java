package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AcidRowDataGatewayRDS 
 * @author Isabella Boone, Kim O'Neill
 */
public class AcidRDGRDS implements AcidRDG {
  private int acidId, solute;
  private String name, inhabits;
  
  /**
   * Create tables
   */
  public AcidRDGRDS() {
  }
  
  /**
   * Constructor used to find an existing Acid.
   * @param id of the acid to find
   */
  public AcidRDGRDS(int id) throws SQLException, DatabaseException {
    // Statements to find existing acid/chemical and collect their information.
    String getAcid = new String("SELECT * FROM Acid WHERE acidId = " + id + ";"),
        getChem = new String("SELECT * FROM Chemical WHERE chemicalId = " + id + ";");

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

  }
  
  /**
   * AcidRowDataGateway constructor for creating a new acid.
   * @param id of acid to insert
   * @param solute of acid to insert
   * @param name of acid to insert
   * @param inhabits of acid to insert
   */
  public AcidRDGRDS(int id, int solute, String name, String inhabits) {
    try {
      // Insert chemical 
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + " VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      
      // Insert Acid
      PreparedStatement insertAcid = DatabaseManager.getSingleton().getConnection()
        .prepareStatement("INSERT INTO Acid (acidId, solute)" + " VALUES (?, ?);");
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
	 * Delete an acid from both acid and chemical tables
	 */
  @Override
  public void delete() {
    String deleteChemical = "DELETE FROM Chemical WHERE ChemicalId = " + acidId + ";",
        deleteAcid = "DELETE FROM Acid WHERE AcidId = " + acidId + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
      statement.executeUpdate(deleteAcid);
      statement.executeUpdate(deleteChemical);
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error deleting acid " + acidId);
    }
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
   * Find a set of ids that are dissolved by the same solute
   * @param solute
   * @return
   */
  public List<AcidRDGRDS> findSet(int solute) {
    List<AcidRDGRDS> results = new ArrayList<>();
    try {
      String sql = "SELECT * FROM Acid WHERE solute = "+ solute + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      
      while(rs.next()) {
        int sol = rs.getInt("acidId");
        AcidRDGRDS id = new AcidRDGRDS(sol);
        results.add(id);
      }
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();

    }
    return results;
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
	
}
