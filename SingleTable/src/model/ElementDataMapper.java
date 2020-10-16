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
 * 
 * @author andrewjanuszko
 *
 */
public class ElementDataMapper implements ElementDataMapperInterface {

  private ChemicalTableDataGatewayRDS chemicalTableDataGateway;
  private ElementCompoundTableDataGatewayRDS ecTableDataGateway;

  /**
   * 
   */
  @Override
  public Element create(String name, double inventory, int atomicNumber, double atomicMass)
      throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(ChemicalEnum.ELEMENT.getIntValue(), name, inventory,
          atomicNumber, atomicMass, 0, 0, 0);
      return new Element(row.getID(), name, inventory, atomicNumber, atomicMass);
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to create an Element.", e);
    }
  }

  /**
   * 
   */
  @Override
  public Element read(int id) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(id);
      return new Element(row.getID(), row.getName(), row.getInventory(), row.getAtomicNumber(), row.getAtomicMass());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to find an Element with ID '" + id + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public void update(Element element) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(element.getID());
      row.setName(element.getName());
      row.setInventory(element.getInventory());
      row.setAtomicNumber(element.getAtomicNumber());
      row.setAtomicMass(element.getAtomicMass());
      row.update();
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to update an Element with ID '" + element.getID() + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public void delete(Element element) throws DomainModelException {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(element.getID());
      row.delete();
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed to delete an Element with ID '" + element.getID() + "'.", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Element> getAll() throws DomainModelException {
    try {
      return convertToElement(chemicalTableDataGateway.getElements().executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Element> filterByWildCardName(String wildCard) throws DomainModelException {
    try {
      return convertToElement(chemicalTableDataGateway.getElements().filterByWildCardName(wildCard).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Element> filterByInventory(double inventory) throws DomainModelException {
    try {
      return convertToElement(chemicalTableDataGateway.getElements().filterByInventoryValue(inventory).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Element> filterByInventoryRange(double min, double max) throws DomainModelException {
    try {
      return convertToElement(chemicalTableDataGateway.getElements().filterByInventoryRange(min, max).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Element> filterByAtomicNumber(int atomicNumber) throws DomainModelException {
    try {
      return convertToElement(chemicalTableDataGateway.getElements().filterByAtomicNumberValue(atomicNumber).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Element> filterByAtomicMass(double atomicMass) throws DomainModelException {
    try {
      return convertToElement(chemicalTableDataGateway.getElements().filterByAtomicMassValue(atomicMass).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Element> filterByAtomicMassRange(double min, double max) throws DomainModelException {
    try {
      return convertToElement(chemicalTableDataGateway.getElements().filterByAtomicMassRange(min, max).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("", e);
    }
  }

  /**
   * 
   */
  @Override
  public List<Element> filterByPartOfCompound(int compoundID) throws DomainModelException {
    try {
      return convertToElement(ecTableDataGateway.findElementsByCompoundID(compoundID).getRelations());
    } catch (DatabaseException e) {
      throw new DomainModelException("", e);
    }
  }

  /**
   * 
   * @param chemicals
   * @return
   * @throws DomainModelException
   */
  private List<Element> convertToElement(List<ChemicalDTO> chemicals) throws DomainModelException {
    List<Element> elements = new ArrayList<>();
    for (ChemicalDTO dto : chemicals) {
      elements.add(new Element(dto.getID(), dto.getName(), dto.getInventory(), dto.getAtomicNumber(), dto.getAtomicMass()));
    }
    return elements;
  }

}
