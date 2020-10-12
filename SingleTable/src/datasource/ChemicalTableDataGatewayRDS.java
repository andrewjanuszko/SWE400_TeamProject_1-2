package datasource;

import dataDTO.ChemicalDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements a ChemicalTableDataGateway.
 * 
 * @author andrewjanuszko
 */
public class ChemicalTableDataGatewayRDS implements ChemicalTableDataGateway {

  private static ChemicalTableDataGateway singletonInstance;
  
  private static String querySQL = "";
  

  /**
   * Retrieves a Singleton instance of ChemicalTableDataGateway. Creates a new one
   * if it does not exist.
   * 
   * @return a Singleton instance of ChemicalTableDataGateway.
   */
  public static synchronized ChemicalTableDataGateway getSingletonInstance() {
    if (singletonInstance == null) {
      singletonInstance = new ChemicalTableDataGatewayRDS();
    }
    return singletonInstance;
  }

  /**
   * Converts a query to a list of ChemicalDTOs.
   * 
   * @param statement the query on the database.
   * @return a list of ChemicalDTOs.
   * @throws DatabaseException when things go wrong.
   */
  private ArrayList<ChemicalDTO> convertToDTO(PreparedStatement statement) throws DatabaseException {
    ArrayList<ChemicalDTO> listDTO = new ArrayList<ChemicalDTO>();
    try {
      ResultSet results = statement.executeQuery();
      querySQL = "";
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

  @Override
  public ChemicalTableDataGatewayRDS getAll() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type <> 0)";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getElements() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = 1 OR Chemical.type = 2)";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getMetals() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = 2)";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getCompounds() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = 3)";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getBases() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = 4)";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getAcids() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = 5)";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByWildCardName(String wildCard) {
    querySQL += " AND (Chemical.name LIKE '%" + wildCard + "%')";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByInventoryValue(double inventoryValue) {
    querySQL += " AND (Chemical.inventory = " + inventoryValue + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByInventoryRange(double min, double max) {
    querySQL += " AND (Chemical.inventory BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicNumberValue(int atomicNumberValue) {
    querySQL += " AND (Chemical.atomicNumber = " + atomicNumberValue + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicNumberRange(int min, int max) {
    querySQL += " AND (Chemical.atomicNumber BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicMassValue(double atomicMassValue) {
    querySQL += " AND (Chemical.atomicMass = " + atomicMassValue + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicMassRange(double min, double max) {
    querySQL += " AND (Chemical.atomicMass BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByDissolvedBy(int dissolvedByID) {
    querySQL += " AND (Chemical.dissolvedBy = " + dissolvedByID + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByMolesValue(double molesValue) {
    querySQL += " AND (Chemical.moles = " + molesValue + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByMolesRange(double min, double max) {
    querySQL += " AND (Chemical.moles BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterBySolute(int soluteID) {
    querySQL += " AND (Chemical.solute = " + soluteID + ")";
    return this;
  }

  @Override
  public ArrayList<ChemicalDTO> filterElementsWithLowInventory() throws DatabaseException {
    querySQL = "SELECT * FROM Chemical WHERE (Chemical.type = 1 OR Chemical.type = 2) AND (Chemical.inventory < 20)";
    return executeQuery();
  }

  @Override
  public ArrayList<ChemicalDTO> filterBasesWithLowInventory() throws DatabaseException {
    querySQL = "SELECT * FROM Chemical WHERE (Chemical.type = 4) AND (Chemical.inventory < 40)";
    return executeQuery();
  }

  @Override
  public ArrayList<ChemicalDTO> filterAcidsWithLowInventory() throws DatabaseException {
    try {
      ArrayList<ChemicalDTO> resultSet = new ArrayList<ChemicalDTO>();
      ArrayList<ChemicalDTO> acids = getSingletonInstance().getAcids().executeQuery();
      for (ChemicalDTO acid : acids) {
        System.out.println(acid.getName() + " " + acid.getInventory());
        ArrayList<ChemicalDTO> metals = getSingletonInstance().getMetals().filterByDissolvedBy(acid.getChemicalID()).executeQuery();
        double totalMolesNeeded = 0.0;
        for (ChemicalDTO metal : metals) {
          System.out.println(metal.getName() + " " + metal.getMoles());
          totalMolesNeeded += metal.getMoles();
        }
        if (acid.getInventory() < totalMolesNeeded) {
          resultSet.add(acid);
        }
      }
      return resultSet;
    } catch(DatabaseException e) {
      throw new DatabaseException("Failed to fetch acids with low inventory.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> filterAllWithLowInventory() throws DatabaseException {
    ArrayList<ChemicalDTO> lowChemicals = new ArrayList<ChemicalDTO>();
    lowChemicals.addAll(filterElementsWithLowInventory());
    lowChemicals.addAll(filterBasesWithLowInventory());
    lowChemicals.addAll(filterAcidsWithLowInventory());
    return lowChemicals;
  }

  @Override
  public ArrayList<ChemicalDTO> executeQuery() throws DatabaseException {
    try {
      System.out.println(querySQL);
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(querySQL + ";");
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
  }
  
}