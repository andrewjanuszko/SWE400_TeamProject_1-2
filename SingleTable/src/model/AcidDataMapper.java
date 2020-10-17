package model;

import java.util.ArrayList;
import java.util.List;

import dataDTO.ChemicalDTO;
import dataENUM.ChemicalEnum;
import datasource.ChemicalRowDataGatewayRDS;
import datasource.ChemicalTableDataGatewayRDS;
import datasource.DatabaseException;

/**
 * 
 * @author andrewjanuszko
 *
 */
public class AcidDataMapper implements AcidDataMapperInterface {

  private ChemicalTableDataGatewayRDS chemicalTableDataGateway;
  
  public AcidDataMapper() {}

  /**
   * 
   */
  @Override
  public Acid create(String name, double inventory, List<Metal> dissolves, int solute) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(ChemicalEnum.ACID.getIntValue(), name, inventory, 0, 0, 0, 0, solute);
      final int acidID = row.getID();
      for (Metal metal : dissolves) {
        row = new ChemicalRowDataGatewayRDS(metal.getID());
        row.setDissolvedBy(acidID);
        row.update();
      }
      return new Acid(acidID, name, inventory, dissolves, solute);
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to create an Acid object.", e);
    }

  }
  
  /**
   * 
   */
  @Override
  public Acid read(int id) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(id);
      List<Metal> dissolves = new MetalDataMapper().filterByDissolvedBy(id);
      return new Acid(row.getID(), row.getName(), row.getInventory(), dissolves, row.getSolute());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to read an Acid with ID '" + id + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public void update(Acid acid) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(acid.getID());
      row.setName(acid.getName());
      row.setInventory(acid.getInventory());
      row.setSolute(acid.getSolute());
      row.update();
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to update an Acid with ID '" + acid.getID() + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public void delete(Acid acid) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(acid.getID());
      List<Metal> dissolves = new MetalDataMapper().filterByDissolvedBy(acid.getID());
      for (Metal metal : dissolves) {
        row = new ChemicalRowDataGatewayRDS(metal.getID());
        row.setDissolvedBy(0);
        row.update();
      }
      row.delete();
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to delete an Acid with ID '" + acid.getID() + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Acid> getAll() throws DomainModelException {
    try {
      return convertToAcid(chemicalTableDataGateway.getAcids().executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Acids.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Acid> filterByWildCardName(String wildCard) throws DomainModelException {
    try {
      return convertToAcid(chemicalTableDataGateway.getAcids().filterByWildCardName(wildCard).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Acids with name '" + wildCard + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Acid> filterByInventory(double inventory) throws DomainModelException {
    try {
      return convertToAcid(chemicalTableDataGateway.getAcids().filterByInventoryValue(inventory).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Acids with inventory of '" + inventory + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Acid> filterByInventoryRange(double min, double max) throws DomainModelException {
    try {
      return convertToAcid(chemicalTableDataGateway.getAcids().filterByInventoryRange(min, max).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Acids with inventory between '" + min + "' < x < '" + max + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Acid> filterBySolute(int chemicalID) throws DomainModelException {
    try {
      return convertToAcid(chemicalTableDataGateway.getAcids().filterBySolute(chemicalID).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Acids with solute '" + chemicalID + "'.", e);
    }
  }
  
  /**
   * 
   * @param chemicals
   * @return
   * @throws DomainModelException
   */
  private List<Acid> convertToAcid(List<ChemicalDTO> chemicals) throws DomainModelException {
    List<Acid> acids = new ArrayList<>();
    for (ChemicalDTO dto : chemicals) {
      List<Metal> dissolves = new MetalDataMapper().filterByDissolvedBy(dto.getID());
      acids.add(new Acid(dto.getID(), dto.getName(), dto.getInventory(), dissolves, dto.getSolute()));
    }
    return acids;
  }

}
