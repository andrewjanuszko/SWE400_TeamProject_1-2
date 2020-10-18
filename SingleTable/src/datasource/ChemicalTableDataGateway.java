package datasource;

import dataDTO.ChemicalDTO;
import java.util.List;

/**
 * Table Data Gateway for Chemical.
 * 
 * @author andrewjanuszko
 */
public interface ChemicalTableDataGateway {

  /**
   * Get all Chemicals.
   * @return all Chemicals.
   */
  public ChemicalTableDataGatewayRDS getAll();
  
  /**
   * Get all Elements.
   * @return all Elements.
   */
  public ChemicalTableDataGatewayRDS getElements();
  
  /**
   * Get all Metals.
   * @return all Metals.
   */
  public ChemicalTableDataGatewayRDS getMetals();
  
  /**
   * Get all Compounds.
   * @return all Compounds.
   */
  public ChemicalTableDataGatewayRDS getCompounds();
  
  /**
   * Get all Bases.
   * @return all Bases.
   */
  public ChemicalTableDataGatewayRDS getBases();
  
  /**
   * Get all Acids.
   * @return all Acids.
   */
  public ChemicalTableDataGatewayRDS getAcids();
  
  /**
   * Get all Chemicals with a name like String.
   * @param nameLike the name we are looking for.
   * @return all Chemicals with a name like String.
   */
  public ChemicalTableDataGatewayRDS filterByNameLike(String nameLike);
  
  /**
   * 
   * @param inventoryValue
   * @return
   */
  public ChemicalTableDataGatewayRDS filterByInventory(double inventoryValue);

  /**
   * 
   * @param min
   * @param max
   * @return
   */
  public ChemicalTableDataGatewayRDS filterByInventoryBetween(double min, double max);
  
  /**
   * 
   * @param atomicNumberValue
   * @return
   */
  public ChemicalTableDataGatewayRDS filterByAtomicNumber(int atomicNumberValue);
  
  /**
   * 
   * @param min
   * @param max
   * @return
   */
  public ChemicalTableDataGatewayRDS filterByAtomicNumberBetween(int min, int max);
  
  /**
   * 
   * @param atomicMassValue
   * @return
   */
  public ChemicalTableDataGatewayRDS filterByAtomicMass(double atomicMassValue);
  
  /**
   * 
   * @param min
   * @param max
   * @return
   */
  public ChemicalTableDataGatewayRDS filterByAtomicMassBetween(double min, double max);
  
  /**
   * 
   * @param dissolvedByID
   * @return
   */
  public ChemicalTableDataGatewayRDS filterByDissolvedBy(int dissolvedByID);
  
  /**
   * 
   * @param acidAmount
   * @return
   */
  public ChemicalTableDataGatewayRDS filterByAcidAmount(double acidAmount);
  
  /**
   * 
   * @param min
   * @param max
   * @return
   */
  public ChemicalTableDataGatewayRDS filterByAcidAmountBetween(double min, double max);
  
  /**
   * 
   * @param soluteID
   * @return
   */
  public ChemicalTableDataGatewayRDS filterBySolute(int soluteID);
  
  /**
   * 
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> executeQuery() throws DatabaseException;
  
  /**
   * 
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getAllWithLowInventory() throws DatabaseException;
  
  /**
   * 
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getElementsWithLowInventory() throws DatabaseException;
  
  /**
   * 
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getMetalsWithLowInventory() throws DatabaseException;
  
  /**
   * 
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getBasesWithLowInventory() throws DatabaseException;
  
  /**
   * 
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getAcidsWithLowInventory() throws DatabaseException;

}
