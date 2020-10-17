package model;

import java.util.ArrayList;
import java.util.List;

import dataDTO.ChemicalDTO;
import dataENUM.ChemicalEnum;
import datasource.ChemicalRowDataGatewayRDS;
import datasource.ChemicalTableDataGatewayRDS;
import datasource.DatabaseException;

public class BaseDataMapper implements BaseDataMapperInterface {

  private ChemicalTableDataGatewayRDS chemicalTableDataGateway;

  /**
   * 
   */
  @Override
  public Base create(String name, double inventory, int solute) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(ChemicalEnum.BASE.getIntValue(), name, inventory, 0,
          0, 0, 0, solute);
      return new Base(row.getID(), name, inventory, solute);
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to create a Base.", e);
    }
  }

  /**
   * 
   */
  @Override
  public Base read(int id) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(id);
      return new Base(row.getID(), row.getName(), row.getInventory(), row.getSolute());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to read a Base with ID '" + id + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public void update(Base base) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(base.getID());
      row.setName(base.getName());
      row.setInventory(base.getInventory());
      row.setSolute(base.getSolute());
      row.update();
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to update a Base with ID '" + base.getID() + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public void delete(Base base) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(base.getID());
      row.delete();
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to delete a Base with ID '" + base.getID() + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Base> getAll() throws DomainModelException {
    try {
      return convertToBase(chemicalTableDataGateway.getBases().executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Bases.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Base> filterByWildCardName(String wildCard) throws DomainModelException {
    try {
      return convertToBase(chemicalTableDataGateway.getBases().filterByWildCardName(wildCard).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Bases with name '" + wildCard + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Base> filterByInventory(double inventory) throws DomainModelException {
    try {
      return convertToBase(chemicalTableDataGateway.getBases().filterByInventoryValue(inventory).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Bases with inventory of '" + inventory + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Base> filterByInventoryRange(double min, double max) throws DomainModelException {
    try {
      return convertToBase(chemicalTableDataGateway.getBases().filterByInventoryRange(min, max).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException(
          "Failed to get all Bases with inventory between '" + min + "' < x < '" + max + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Base> filterBySolute(int chemicalID) throws DomainModelException {
    try {
      return convertToBase(chemicalTableDataGateway.getBases().filterBySolute(chemicalID).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Bases with solute '" + chemicalID + "'.", e);
    }
  }

  /**
   * Converts an ArrayList of ChemicalDTOs from the DB to Base objects
   * 
   * @param chemicalDTOs
   * @return
   */
  private List<Base> convertToBase(List<ChemicalDTO> chemicals) throws DomainModelException {
    ArrayList<Base> bases = new ArrayList<Base>();
    for (ChemicalDTO chemical : chemicals) {
      bases.add(new Base(chemical.getID(), chemical.getName(), chemical.getInventory(), chemical.getSolute()));
    }
    return bases;
  }

}
