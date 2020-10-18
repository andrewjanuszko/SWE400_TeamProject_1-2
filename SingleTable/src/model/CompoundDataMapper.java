package model;

import java.util.ArrayList;
import java.util.List;

import dataDTO.ChemicalDTO;
import dataENUM.ChemicalEnum;
import datasource.ChemicalRowDataGatewayRDS;
import datasource.ChemicalTableDataGatewayRDS;
import datasource.DatabaseException;
import datasource.ElementCompoundTableDataGatewayRDS;

/**
 * A mapper for Compound objects.
 * 
 * @author andrewjanuszko
 *
 */
public class CompoundDataMapper implements CompoundDataMapperInterface {

  private ChemicalTableDataGatewayRDS chemicalTableDataGateway;
  private ElementCompoundTableDataGatewayRDS ecTableDataGateway;

  /**
   * Empty constructor for CompoundDataMapper.
   */
  public CompoundDataMapper() {
    // EMPTY.
  }

  /**
   * @see model.CompoundDataMapperInterface#create(String, double, List).
   */
  @Override
  public Compound create(String name, double inventory, List<Element> madeOf) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(ChemicalEnum.COMPOUND.getIntValue(), name,
          inventory, 0, 0, 0, 0, 0);
      final int compoundID = row.getID();
      for (Element element : madeOf) {
        row = new ChemicalRowDataGatewayRDS(element.getID());
        ecTableDataGateway.createRow(compoundID, row.getID());
      }
      return new Compound(row.getID(), name, inventory, madeOf);
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to create Compound.", e);
    }
  }

  /**
   * @see model.CompoundDataMapperInterface#read(int).
   */
  @Override
  public Compound read(int id) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(id);
      List<ChemicalDTO> elements = ecTableDataGateway.findElementsByCompoundID(id).getRelations();
      List<Element> madeOf = new ArrayList<>();
      for (ChemicalDTO element : elements) {
        madeOf.add(new Element(element.getID(), element.getName(), element.getInventory(), element.getAtomicNumber(),
            element.getAtomicMass()));
      }
      return new Compound(row.getID(), row.getName(), row.getInventory(), madeOf);
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to read Compound with ID '" + id + "'.", e);
    }
  }

  /**
   * @see model.CompoundDataMapperInterface#update(Compound).
   */
  @Override
  public void update(Compound compound) throws DomainModelException {
    try {
      List<Element> elements = new ElementDataMapper().filterByPartOfCompound(compound.getID());
      for (Element element : elements) {
        ecTableDataGateway.delete(compound.getID(), element.getID());
      }
      for (Element element : compound.getMadeOf()) {
        ecTableDataGateway.createRow(compound.getID(), element.getID());
      }
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(compound.getID());
      row.setName(compound.getName());
      row.setInventory(compound.getInventory());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to update Compound with ID '" + compound.getID() + "'.", e);
    }
  }

  /**
   * @see model.CompoundDataMapperInterface#delete(Compound).
   */
  @Override
  public void delete(Compound compound) throws DomainModelException {
    try {
      List<Element> elements = new ElementDataMapper().filterByPartOfCompound(compound.getID());
      for (Element element : elements) {
        ecTableDataGateway.delete(compound.getID(), element.getID());
      }
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(compound.getID());
      row.delete();
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to delete Compound with ID '" + compound.getID() + "'.", e);
    }
  }

  /**
   * @see model.CompoundDataMapperInterface#getAll().
   */
  @Override
  public List<Compound> getAll() throws DomainModelException {
    try {
      return convertToCompound(chemicalTableDataGateway.getCompounds().executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Compounds.", e);
    }
  }

  /**
   * @see model.CompoundDataMapperInterface#filterByWildCardName(String).
   */
  @Override
  public List<Compound> filterByNameLike(String nameLike) throws DomainModelException {
    try {
      return convertToCompound(chemicalTableDataGateway.getCompounds().filterByNameLike(nameLike).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Compounds with a partial name of '" + nameLike + "'.", e);
    }
  }

  /**
   * @see model.CompoundDataMapperInterface#filterByInventory(double).
   */
  @Override
  public List<Compound> filterByInventory(double inventory) throws DomainModelException {
    try {
      return convertToCompound(chemicalTableDataGateway.getCompounds().filterByInventory(inventory).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Compounds with an inventory of '" + inventory + "'.", e);
    }
  }

  /**
   * @see model.CompoundDataMapperInterface#filterByInventoryRange(double,
   *      double).
   */
  @Override
  public List<Compound> filterByInventoryBetween(double min, double max) throws DomainModelException {
    try {
      return convertToCompound(
          chemicalTableDataGateway.getCompounds().filterByInventoryBetween(min, max).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException(
          "Failed to get all Compounds with an inventory between  '" + min + "' < x < '" + max + "'.", e);
    }
  }

  /**
   * @see model.CompoundDataMapperInterface#filterByMadeOf(int).
   */
  @Override
  public List<Compound> filterByMadeOf(int elementID) throws DomainModelException {
    try {
      return convertToCompound(ecTableDataGateway.findCompoundsByElementID(elementID).getRelations());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to get all Compounds that contain Element '" + elementID + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Compound> filterByLowInventory() throws DomainModelException {
    List<Compound> compounds = getAll();
    for (Compound compound : compounds) {
      int elementInventoryNeeded = 0;
      List<Element> elements = new ElementDataMapper().filterByPartOfCompound(compound.getID());
      for (Element element : elements) {
        elementInventoryNeeded += element.getInventory();
      }
      if (compound.getInventory() >= elementInventoryNeeded) {
        compounds.remove(compound);
      }
    }
    return compounds;
  }

  /**
   * Converts ChemicalDTOs to a List of Compounds.
   * 
   * @param chemicals the ChemicalDTOs to convert.
   * @return a List of Compounds.
   * @throws DomainModelException when things go wrong.
   */
  private List<Compound> convertToCompound(List<ChemicalDTO> chemicals) throws DomainModelException {
    try {
      List<Compound> compounds = new ArrayList<>();
      for (ChemicalDTO dto : chemicals) {
        ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(dto.getID());
        List<Element> madeOf = new ElementDataMapper().filterByPartOfCompound(row.getID());
        compounds.add(new Compound(row.getID(), row.getName(), row.getInventory(), madeOf));
      }
      return compounds;
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to convert ChemicalDTO to Compound.", e);
    }
  }

}
