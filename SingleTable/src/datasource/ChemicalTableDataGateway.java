package datasource;

import dataDTO.ChemicalDTO;
import java.util.ArrayList;

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
  
  public ArrayList<ChemicalDTO> filterAllWithLowInventory() throws DatabaseException;
  
  public ArrayList<ChemicalDTO> filterElementsWithLowInventory() throws DatabaseException;
  
  public ArrayList<ChemicalDTO> filterBasesWithLowInventory() throws DatabaseException;
  
  public ArrayList<ChemicalDTO> filterAcidsWithLowInventory() throws DatabaseException;
  
  public ArrayList<ChemicalDTO> executeQuery() throws DatabaseException;

}
