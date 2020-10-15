package model;

import java.util.ArrayList;

import dataDTO.ChemicalDTO;
import dataDTO.CompoundDTO;
import datasource.ChemicalRowDataGatewayRDS;
import datasource.ChemicalTableDataGatewayRDS;
import datasource.DatabaseException;
import datasource.ElementCompoundTableDataGatewayRDS;

public class ElementMapper implements ElementDataMapperInterface {

  private ChemicalTableDataGatewayRDS chemicalTDG;
  private ElementCompoundTableDataGatewayRDS ecTDG;
  private ArrayList<ChemicalDTO> chemicalDTOs = new ArrayList<ChemicalDTO>();
  
  @Override
  public void create(Element element) {
    
  }

  @Override
  public Element read(int id) {
   Element element = null;
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(id);
      element = new Element(row.getChemicalID(), row.getName(), row.getInventory(), row.getAtomicNumber(), row.getAtomicMass());
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return element;
  }

  @Override
  public void update(Element element) {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(element.getID());
      row.setName(element.getName());
      row.setInventory(element.getInventory());
      row.setAtomicNumber(element.getAtomicNumber());
      row.setAtomicMass(element.getAtomicMass());
      row.update();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public void delete(Element element) {
    try {
      ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(element.getID());
      row.delete();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public ArrayList<Element> getAll() {
    try {
      chemicalDTOs = chemicalTDG.getElements().executeQuery();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return convertFromChemicalDTO(chemicalDTOs);
  }

  @Override
  public ArrayList<Element> filterByWildCardName(String wildCardName) {
    try {
      chemicalDTOs = chemicalTDG.getElements().filterByWildCardName(wildCardName).executeQuery();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return convertFromChemicalDTO(chemicalDTOs);
  }

  @Override
  public ArrayList<Element> filterByInventory(double inventory) {
    try {
      chemicalDTOs = chemicalTDG.getElements().filterByInventoryValue(inventory).executeQuery();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return convertFromChemicalDTO(chemicalDTOs);
  }

  @Override
  public ArrayList<Element> filterByInventoryRange(double min, double max) {
    try {
      chemicalDTOs = chemicalTDG.getElements().filterByInventoryRange(min, max).executeQuery();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return convertFromChemicalDTO(chemicalDTOs);
  }

  @Override
  public ArrayList<Element> filterByAtomicNumber(int atomicNumber) {
    try {
      chemicalDTOs = chemicalTDG.getElements().filterByAtomicNumberValue(atomicNumber).executeQuery();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return convertFromChemicalDTO(chemicalDTOs);
  }

  @Override
  public ArrayList<Element> filterByAtomicMass(double atomicMass) {
    try {
      chemicalDTOs = chemicalTDG.getElements().filterByAtomicMassValue(atomicMass).executeQuery();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return convertFromChemicalDTO(chemicalDTOs);
  }

  @Override
  public ArrayList<Element> filterByAtomicMassRange(double min, double max) {
    try {
      chemicalDTOs = chemicalTDG.getElements().filterByAtomicMassRange(min, max).executeQuery();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return convertFromChemicalDTO(chemicalDTOs);
  }

  @Override
  public ArrayList<Element> filterByPartOfCompound(int compoundID) {
    ArrayList<Element> elements = new ArrayList<Element>();
    try {
      CompoundDTO compound = ecTDG.findElementsByCompoundID(compoundID);
      for(Integer elementID : compound.madeOf()) {
        elements.add(read(elementID.intValue()));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return elements;
  }

  private ArrayList<Element> convertFromChemicalDTO(ArrayList<ChemicalDTO> chemicalDTOs) {
    ArrayList<Element> elements = new ArrayList<Element>();
    for (ChemicalDTO chemicalDTO : chemicalDTOs) {
      elements.add(new Element(chemicalDTO.getChemicalID(), chemicalDTO.getName(), chemicalDTO.getInventory(),
          chemicalDTO.getAtomicNumber(), chemicalDTO.getAtomicMass()));
    }
    chemicalDTOs = null;
    return elements;
  }

}
