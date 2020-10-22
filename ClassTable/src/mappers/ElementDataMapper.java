package mappers;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import datasource.ElementDTO;
import datasource.ElementRDG;
import datasource.ElementRDGRDS;
import datasource.ElementTDGRDS;
import model.DomainModelException;
import model.Element;
import model.ElementDataMapperInterface;

public class ElementDataMapper implements ElementDataMapperInterface {

  public ElementDataMapper() {
  }
  
  @Override
  public Element create(String name, double inventory, int atomicNumber, double atomicMass)
      throws DomainModelException {
    ElementRDG row = new ElementRDGRDS(atomicNumber, atomicMass, name, inventory);
    return convertFromDTO(row.getElement());
  }

  @Override
  public Element read(int id) {
    ElementRDG row = new ElementRDGRDS(id);
    ElementDTO dto = row.getElement();
    Element element = convertFromDTO(dto);
    return element;
  }

  @Override
  public void update(Element element) {
    ElementRDG row = new ElementRDGRDS(element.getID());
    row.setName(element.getName());
    row.setInventory(element.getInventory());
    row.setAtomicNumber(element.getAtomicNumber());
    row.setAtomicMass(element.getAtomicMass());
    row.update();
    
  }

  @Override
  public void delete(Element element) {
    ElementRDG row = new ElementRDGRDS(element.getID());
    row.delete();
    
  }

  @Override
  public List<Element> getAll() {
    List<ElementDTO> dtos;
    List<Element> element = new ArrayList<>();
    try {
      dtos = ElementTDGRDS.getSingleton().executeQuery();
      
      for(ElementDTO e : dtos) {
        element.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return element;
  }

  @Override
  public List<Element> filterByWildCardName(String wildCardName) {
    List<ElementDTO> dtos;
    ArrayList<Element> element = new ArrayList<>();
    try {
      dtos = ElementTDGRDS.getSingleton().filterByName(wildCardName).executeQuery();
      for(ElementDTO e : dtos) {
        element.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return element;
  }

  @Override
  public List<Element> filterByInventory(double inventory) {
    List<ElementDTO> dtos;
    ArrayList<Element> element = new ArrayList<>();
    try {
      dtos = ElementTDGRDS.getSingleton().filterByInventory(inventory).executeQuery();
      for(ElementDTO e : dtos) {
        element.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return element;
  }

  @Override
  public List<Element> filterByInventoryRange(double min, double max) {
    List<ElementDTO> dtos;
    ArrayList<Element> element = new ArrayList<>();
    try {
      dtos = ElementTDGRDS.getSingleton().filterByInventoryRange(max, min).executeQuery();
      for(ElementDTO e : dtos) {
        element.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return element;
  }

  @Override
  public List<Element> filterByAtomicNumber(int atomicNumber) {
    List<ElementDTO> dtos;
    ArrayList<Element> element = new ArrayList<>();
    try {
      dtos = ElementTDGRDS.getSingleton().filterByAtomicNumber(atomicNumber).executeQuery();
      for(ElementDTO e : dtos) {
        element.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return element;
  }

  @Override
  public List<Element> filterByAtomicMass(double atomicMass) {
    List<ElementDTO> dtos;
    ArrayList<Element> element = new ArrayList<>();
    try {
      dtos = ElementTDGRDS.getSingleton().filterByAtomicMass(atomicMass).executeQuery();
      for(ElementDTO e : dtos) {
        element.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return element;
  }

  @Override
  public List<Element> filterByAtomicMassRange(double min, double max) {
    List<ElementDTO> dtos;
    ArrayList<Element> element = new ArrayList<>();
    try {
      dtos = ElementTDGRDS.getSingleton().filterByAtomicMassRange(max, min).executeQuery();
      for(ElementDTO e : dtos) {
        element.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return element;
  }

  @Override
  public List<Element> filterByPartOfCompound(int compoundID) {
    List<ElementDTO> dtos;
    ArrayList<Element> element = new ArrayList<>();
    try {
      dtos = ElementTDGRDS.getSingleton().executeQuery(); //this isnt done but like isabella is slow
      for(ElementDTO e : dtos) {
        element.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return element;
  }

  public Element convertFromDTO(ElementDTO dto) {
    return new Element(dto.getElementId(), dto.getName(), dto.getInventory(), dto.getAtomicNumber(), dto.getAtomicMass());
  }

  public List<Element> filterByLowInventory(double filter) {
    List<ElementDTO> dtos;
    List<Element> elements = new ArrayList<>(); 
    
    try {
      dtos = ElementTDGRDS.getSingleton().filterByLowInventory(filter).executeQuery();
      
      for(ElementDTO e : dtos) {
        elements.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return elements;
  }
}
