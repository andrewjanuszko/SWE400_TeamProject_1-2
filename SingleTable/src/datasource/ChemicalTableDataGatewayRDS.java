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

  @Override
  public ChemicalTableDataGatewayRDS getAll() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type <> 0)";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getElements() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.ELEMENT.getIntValue() + " OR Chemical.type = " + ChemicalEnum.METAL.getIntValue() + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getMetals() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.METAL.getIntValue() + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getCompounds() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.COMPOUND.getIntValue() + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getBases() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.BASE.getIntValue() + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS getAcids() {
    querySQL += "SELECT * FROM Chemical WHERE (Chemical.type = " + ChemicalEnum.ACID.getIntValue() + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByNameLike(String nameLike) {
    querySQL += " AND (Chemical.name LIKE '%" + nameLike + "%')";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByInventory(double inventory) {
    querySQL += " AND (Chemical.inventory = " + inventory + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByInventoryBetween(double min, double max) {
    querySQL += " AND (Chemical.inventory BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicNumber(int atomicNumber) {
    querySQL += " AND (Chemical.atomicNumber = " + atomicNumber + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicNumberBetween(int min, int max) {
    querySQL += " AND (Chemical.atomicNumber BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicMass(double atomicMass) {
    querySQL += " AND (Chemical.atomicMass = " + atomicMass + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAtomicMassBetween(double min, double max) {
    querySQL += " AND (Chemical.atomicMass BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByDissolvedBy(int acidID) {
    querySQL += " AND (Chemical.dissolvedBy = " + acidID + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAcidAmount(double acidAmount) {
    querySQL += " AND (Chemical.acidAmount = " + acidAmount + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterByAcidAmountBetween(double min, double max) {
    querySQL += " AND (Chemical.acidAmount BETWEEN " + min + " AND " + max + ")";
    return this;
  }

  @Override
  public ChemicalTableDataGatewayRDS filterBySolute(int soluteID) {
    querySQL += " AND (Chemical.solute = " + soluteID + ")";
    return this;
  }

  @Override
  public List<ChemicalDTO> executeQuery() throws DatabaseException {
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(querySQL + ";");
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
  }

  @Override
  public List<ChemicalDTO> getAllWithLowInventory() throws DatabaseException {
    List<ChemicalDTO> lowChemicals = new ArrayList<>();
    lowChemicals.addAll(getElementsWithLowInventory());
    lowChemicals.addAll(getBasesWithLowInventory());
    lowChemicals.addAll(getAcidsWithLowInventory());
    return lowChemicals;
  }

  @Override
  public List<ChemicalDTO> getElementsWithLowInventory() throws DatabaseException {
    return getElements().filterByInventoryBetween(0, 20).executeQuery();
  }

  @Override
  public List<ChemicalDTO> getMetalsWithLowInventory() throws DatabaseException {
    return getMetals().filterByInventoryBetween(0, 20).executeQuery();
  }

  @Override
  public List<ChemicalDTO> getBasesWithLowInventory() throws DatabaseException {
    return getBases().filterByInventoryBetween(0, 40).executeQuery();
  }

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