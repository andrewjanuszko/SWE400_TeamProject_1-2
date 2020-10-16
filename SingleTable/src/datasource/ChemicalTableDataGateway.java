package datasource;

import dataDTO.ChemicalDTO;
import java.util.List;

/**
 * Table Data Gateway for Chemical.
 * 
 * @author andrewjanuszko
 */
public interface ChemicalTableDataGateway {

  public ChemicalTableDataGatewayRDS getAll();
  
  public ChemicalTableDataGatewayRDS getElements();
  
  public ChemicalTableDataGatewayRDS getMetals();
  
  public ChemicalTableDataGatewayRDS getCompounds();
  
  public ChemicalTableDataGatewayRDS getBases();
  
  public ChemicalTableDataGatewayRDS getAcids();
  
  public ChemicalTableDataGatewayRDS filterByWildCardName(String wildCard);
  
  public ChemicalTableDataGatewayRDS filterByInventoryValue(double inventoryValue);

  public ChemicalTableDataGatewayRDS filterByInventoryRange(double min, double max);
  
  public ChemicalTableDataGatewayRDS filterByAtomicNumberValue(int atomicNumberValue);
  
  public ChemicalTableDataGatewayRDS filterByAtomicNumberRange(int min, int max);
  
  public ChemicalTableDataGatewayRDS filterByAtomicMassValue(double atomicMassValue);
  
  public ChemicalTableDataGatewayRDS filterByAtomicMassRange(double min, double max);
  
  public ChemicalTableDataGatewayRDS filterByDissolvedBy(int dissolvedByID);
  
  public ChemicalTableDataGatewayRDS filterByMolesValue(double molesValue);
  
  public ChemicalTableDataGatewayRDS filterByMolesRange(double min, double max);
  
  public ChemicalTableDataGatewayRDS filterBySolute(int soluteID);
  
  public List<ChemicalDTO> executeFindAllWithLowInventory() throws DatabaseException;
  
  public List<ChemicalDTO> executeFindElementsWithLowInventory() throws DatabaseException;
  
  public List<ChemicalDTO> executeFindBasesWithLowInventory() throws DatabaseException;
  
  public List<ChemicalDTO> executeFindAcidsWithLowInventory() throws DatabaseException;
  
  public List<ChemicalDTO> executeQuery() throws DatabaseException;

}
