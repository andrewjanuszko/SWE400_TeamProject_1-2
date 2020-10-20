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
public class ChemicalTableDataGatewayRDS implements ChemicalTableDataGateway {

  private static ChemicalTableDataGatewayRDS singletonInstance;

  private static String querySQL = "";

  /**
   * Retrieves a Singleton instance of ChemicalTableDataGateway. Creates a new one
   * if it does not exist.
   * 
   * @return a Singleton instance of ChemicalTableDataGateway.
   */
  public static synchronized ChemicalTableDataGatewayRDS getSingletonInstance() {
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
  public ChemicalTableDataGatewayRDS getAll() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type <> 0)";
    return getSingletonInstance();
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getElements().
   */
  @Override
  public ChemicalTableDataGatewayRDS getElements() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.ELEMENT.getIntValue() + " OR Chemical.type = " + ChemicalEnum.METAL.getIntValue() + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getMetals().
   */
  @Override
  public ChemicalTableDataGatewayRDS getMetals() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.METAL.getIntValue() + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getCompounds().
   */
  @Override
  public ChemicalTableDataGatewayRDS getCompounds() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.COMPOUND.getIntValue() + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getBases().
   */
  @Override
  public ChemicalTableDataGatewayRDS getBases() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.BASE.getIntValue() + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#getAcids().
   */
  @Override
  public ChemicalTableDataGatewayRDS getAcids() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.ACID.getIntValue() + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByNameLike(String nameLike).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByNameLike(String nameLike) {
    querySQL += " AND (Chemical.name LIKE '%" + nameLike + "%')";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByInventory(double inventory).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByInventory(double inventory) {
    querySQL += " AND (Chemical.inventory = " + inventory + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByInventoryBetween(double min, double max).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByInventoryBetween(double min, double max) {
    querySQL += " AND (Chemical.inventory BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAtomicNumber(int atomicNumber).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicNumber(int atomicNumber) {
    querySQL += " AND (Chemical.atomicNumber = " + atomicNumber + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAtomicNumberBetween(int min, int max).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicNumberBetween(int min, int max) {
    querySQL += " AND (Chemical.atomicNumber BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay# filterByAtomicMass(double atomicMass).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicMass(double atomicMass) {
    querySQL += " AND (Chemical.atomicMass = " + atomicMass + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAtomicMassBetween(double min, double max).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicMassBetween(double min, double max) {
    querySQL += " AND (Chemical.atomicMass BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByDissolvedBy(int acidID).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByDissolvedBy(int acidID) {
    querySQL += " AND (Chemical.dissolvedBy = " + acidID + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAcidAmount(double acidAmount).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByAcidAmount(double acidAmount) {
    querySQL += " AND (Chemical.acidAmount = " + acidAmount + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterByAcidAmountBetween(double min, double max).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterByAcidAmountBetween(double min, double max) {
    querySQL += " AND (Chemical.acidAmount BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  /**
   * @see datasource.ChemicalTableDataGateWay#filterBySolute(int soluteID).
   */
  @Override
  public ChemicalTableDataGatewayRDS filterBySolute(int soluteID) {
    querySQL += " AND (Chemical.solute = " + soluteID + ")";
    return this;
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