package datasource;

import dataDTO.ChemicalDTO;
import dataENUM.ChemicalEnum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements a ChemicalTableDataGateway.
 * 
 * @author andrewjanuszko
 */
public class ChemicalTableDataGatewayRDS extends ChemicalTableDataGateway {

  private static ChemicalTableDataGateway singletonInstance = null;

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
  
  private ChemicalTableDataGatewayRDS() {
    
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
      querySQL = "";
      while (results.next()) {
        int id = results.getInt("id");
        int type = results.getInt("type");
        String name = results.getString("name");
        double inventory = results.getDouble("inventory");
        int atomicNumber = results.getInt("atomicNumber");
        double atomicMass = results.getDouble("atomicMass");
        int dissolvedBy = results.getInt("dissolvedBy");
        double acidAmount = results.getDouble("acidAmount");
        int solute = results.getInt("solute");
        ChemicalDTO chemical = new ChemicalDTO(id, type, name, inventory, atomicNumber, atomicMass, dissolvedBy,
            acidAmount, solute);
        listDTO.add(chemical);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to convert query to DTO.", e);
    }
    return listDTO;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getAll().
   */
  @Override
  public ChemicalTableDataGateway getAll() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type <> 0)";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getElements().
   */
  @Override
  public ChemicalTableDataGateway getElements() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.ELEMENT.getIntValue() + " OR Chemical.type = " + ChemicalEnum.METAL.getIntValue() + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getMetals().
   */
  @Override
  public ChemicalTableDataGateway getMetals() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.METAL.getIntValue() + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getCompounds().
   */
  @Override
  public ChemicalTableDataGateway getCompounds() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.COMPOUND.getIntValue() + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getBases().
   */
  @Override
  public ChemicalTableDataGateway getBases() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.BASE.getIntValue() + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getAcids().
   */
  @Override
  public ChemicalTableDataGateway getAcids() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.ACID.getIntValue() + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByNameLike(String nameLike).
   */
  @Override
  public ChemicalTableDataGateway filterByNameLike(String nameLike) {
    querySQL += " AND (Chemical.name LIKE '%" + nameLike + "%')";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByInventory(double inventory).
   */
  @Override
  public ChemicalTableDataGateway filterByInventory(double inventory) {
    querySQL += " AND (Chemical.inventory = " + inventory + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByInventoryBetween(double min, double max).
   */
  @Override
  public ChemicalTableDataGateway filterByInventoryBetween(double min, double max) {
    querySQL += " AND (Chemical.inventory BETWEEN " + min + " AND " + max + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAtomicNumber(int atomicNumber).
   */
  @Override
  public ChemicalTableDataGateway filterByAtomicNumber(int atomicNumber) {
    querySQL += " AND (Chemical.atomicNumber = " + atomicNumber + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAtomicNumberBetween(int min, int max).
   */
  @Override
  public ChemicalTableDataGateway filterByAtomicNumberBetween(int min, int max) {
    querySQL += " AND (Chemical.atomicNumber BETWEEN " + min + " AND " + max + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay# filterByAtomicMass(double atomicMass).
   */
  @Override
  public ChemicalTableDataGateway filterByAtomicMass(double atomicMass) {
    querySQL += " AND (Chemical.atomicMass = " + atomicMass + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAtomicMassBetween(double min, double max).
   */
  @Override
  public ChemicalTableDataGateway filterByAtomicMassBetween(double min, double max) {
    querySQL += " AND (Chemical.atomicMass BETWEEN " + min + " AND " + max + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByDissolvedBy(int acidID).
   */
  @Override
  public ChemicalTableDataGateway filterByDissolvedBy(int acidID) {
    querySQL += " AND (Chemical.dissolvedBy = " + acidID + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAcidAmount(double acidAmount).
   */
  @Override
  public ChemicalTableDataGateway filterByAcidAmount(double acidAmount) {
    querySQL += " AND (Chemical.acidAmount = " + acidAmount + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAcidAmountBetween(double min, double max).
   */
  @Override
  public ChemicalTableDataGateway filterByAcidAmountBetween(double min, double max) {
    querySQL += " AND (Chemical.acidAmount BETWEEN " + min + " AND " + max + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterBySolute(int soluteID).
   */
  @Override
  public ChemicalTableDataGateway filterBySolute(int soluteID) {
    querySQL += " AND (Chemical.solute = " + soluteID + ")";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#executeQuery().
   */
  @Override
  public List<ChemicalDTO> executeQuery() throws DatabaseException {
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(querySQL + ";");
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getAllWithLowInventory().
   */
  @Override
  public List<ChemicalDTO> getAllWithLowInventory() throws DatabaseException {
    List<ChemicalDTO> lowChemicals = new ArrayList<>();
    lowChemicals.addAll(getElementsWithLowInventory());
    lowChemicals.addAll(getBasesWithLowInventory());
    lowChemicals.addAll(getAcidsWithLowInventory());
    return lowChemicals;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getElementsWithLowInventory().
   */
  @Override
  public List<ChemicalDTO> getElementsWithLowInventory() throws DatabaseException {
    return getElements().filterByInventoryBetween(0, 20).executeQuery();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getMetalsWithLowInventory().
   */
  @Override
  public List<ChemicalDTO> getMetalsWithLowInventory() throws DatabaseException {
    return getMetals().filterByInventoryBetween(0, 20).executeQuery();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getBasesWithLowInventory().
   */
  @Override
  public List<ChemicalDTO> getBasesWithLowInventory() throws DatabaseException {
    return getBases().filterByInventoryBetween(0, 40).executeQuery();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getAcidsWithLowInventory().
   */
  @Override
  public List<ChemicalDTO> getAcidsWithLowInventory() throws DatabaseException {
    List<ChemicalDTO> acids = getAcids().executeQuery();
    for (ChemicalDTO acid : acids) {
      int acidAmountNeeded = 0;
      List<ChemicalDTO> metals = getMetals().filterByDissolvedBy(acid.getID()).executeQuery();
      for (ChemicalDTO metal : metals) {
        acidAmountNeeded += metal.getAcidAmount();
      }
      if (acid.getInventory() >= acidAmountNeeded) {
        acids.remove(acid);
      }
    }
    return acids;
  }

}