package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dataDTO.ChemicalDTO;
import dataDTO.ElementCompoundDTO;

/**
 * The RDS version of the gateway for CompoundMadeFromElement.
 * 
 * @author andrewjanuszko
 */
public class ElementCompoundTableDataGatewayRDS implements ElementCompoundTableDataGateway {

  private static ElementCompoundTableDataGateway singletonInstance;

  /**
   * Get the singleton instance of the RDS gateway.
   * 
   * @return the singleton instance.
   */
  public static synchronized ElementCompoundTableDataGateway getSingletonInstance() {
    if (singletonInstance == null) {
      singletonInstance = new ElementCompoundTableDataGatewayRDS();
    }
    return singletonInstance;
  }
  
  private ElementCompoundTableDataGatewayRDS() {
    try {
      createTable();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Drop the CompoundMadeFromElement table if it already exists, then recreate it
   * as an empty table.
   * 
   * @throws DatabaseException when something goes really wrong.
   */
  public void createTable() throws DatabaseException {
    
    String createTableSQL = "CREATE TABLE IF NOT EXISTS ElementCompound (" + "compoundID INTEGER NOT NULL, "
        + "elementID INTEGER NOT NULL, " + "FOREIGN KEY ElementCompound(compoundID) REFERENCES Chemical(id), "
        + "FOREIGN KEY ElementCompound(elementID) REFERENCES Chemical(id));";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(createTableSQL);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to create ElementCompound table.", e);
    }
  }
  
  public void dropTable() throws DatabaseException {
    String dropTableSQL = "DROP TABLE IF EXISTS ElementCompound";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(dropTableSQL);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to drop ElementCompound table.", e);
    }
  }

  /**
   * Creates a new row in the CompoundMadeFromElement table.
   * 
   * @param compoundID the id of the compound.
   * @param elementID  the id of the element.
   * @throws DatabaseException when insertion fails.
   */
  public void createRow(int compoundID, int elementID) throws DatabaseException {
    String insertSQL = "INSERT INTO ElementCompound SET compoundID = ?, elementID = ?;";
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(insertSQL);
      statement.setInt(1, compoundID);
      statement.setInt(2, elementID);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseException("Failed to create row in CompoundMadeFromElement table.", e);
    }
  }

  /**
   * @see datasource.CompoundMadeFromElementTableDataGateway#updateRow(long,
   *      long).
   */
  @Override
  public void updateRow(int oldCompoundID, int oldElementID, int newCompoundID, int newElementID)
      throws DatabaseException {
    String updateSQL = "UPDATE ElementCompound SET compoundID = ?, elementID = ? WHERE compoundID = ? and elementID = ?;";
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(updateSQL);
      statement.setInt(1, newCompoundID);
      statement.setInt(2, newElementID);
      statement.setInt(3, oldCompoundID);
      statement.setInt(4, oldElementID);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseException("Failed to update row in CompoundMadeFromElement table.", e);
    }
  }

  /**
   * @see datasource.findElementsByCompoundID(int compoundID).
   */
  @Override
  public ElementCompoundDTO findElementsByCompoundID(int compoundID) throws DatabaseException {
    try {
      String selectSQL = "SELECT * FROM Chemical WHERE Chemical.id IN (SELECT elementID FROM ElementCompound WHERE ElementCompound.compoundID = ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
      statement.setInt(1, compoundID);
      List<ChemicalDTO> elements = convertToDTO(statement);
      return new ElementCompoundDTO(compoundID, elements);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to convert query to DTO.", e);
    }
  }

  /**
   * @see datasource.findCompoundsByElementID(int elementID).
   */
  @Override
  public ElementCompoundDTO findCompoundsByElementID(int elementID) throws DatabaseException {
    try {
      String selectSQL = "SELECT * FROM Chemical WHERE Chemical.id IN (SELECT compoundID FROM ElementCompound WHERE ElementCompound.elementID = ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
      statement.setInt(1, elementID);
      List<ChemicalDTO> compounds = convertToDTO(statement);
      return new ElementCompoundDTO(elementID, compounds);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to convert query to DTO.", e);
    }
  }

  /**
   * Converts a query to a list of ChemicalDTOs.
   * 
   * @param statement the query on the database.
   * @return a list of ChemicalDTOs.
   * @throws DatabaseException when things go wrong.
   */
  private List<ChemicalDTO> convertToDTO(PreparedStatement statement) throws DatabaseException {
    List<ChemicalDTO> listDTO = new ArrayList<>();
    try {
      ResultSet results = statement.executeQuery();
      while (results.next()) {
        int chemicalID = results.getInt("chemicalID");
        int type = results.getInt("type");
        String name = results.getString("name");
        double inventory = results.getDouble("inventory");
        int atomicNumber = results.getInt("atomicNumber");
        double atomicMass = results.getDouble("atomicMass");
        int dissolvedBy = results.getInt("dissolvedBy");
        double moles = results.getDouble("moles");
        int solute = results.getInt("solute");
        ChemicalDTO chemical = new ChemicalDTO(chemicalID, type, name, inventory, atomicNumber, atomicMass, dissolvedBy,
            moles, solute);
        listDTO.add(chemical);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to convert query to DTO.", e);
    }
    return listDTO;
  }

  /**
   * Delete a row from the table.
   */
  @Override
  public void delete(int compoundID, int elementID) throws DatabaseException {
    String deleteSQL = "DELETE FROM ElementCompound WHERE compoundID = ? and elementID = ?;";
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(deleteSQL);
      statement.setInt(1, compoundID);
      statement.setInt(2, elementID);
      statement.execute();
    } catch (SQLException e) {
      throw new DatabaseException("Could not delete compound " + compoundID + " with element " + elementID + ".", e);
    }
  }

  /**
   * this is for testing only.
   * 
   * @throws DatabaseException
   */
  @Override
  public void resetData() throws DatabaseException {
    singletonInstance = null;
  }

}
