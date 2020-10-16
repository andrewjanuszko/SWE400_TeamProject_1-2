package mappers;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import datasource.ElementDTO;
import datasource.ElementRDG;
import datasource.ElementRDGRDS;
import datasource.ElementTDGRDS;
import model.Element;
import model.ElementDataMapperInterface;

public class ElementDataMapper implements ElementDataMapperInterface {

  public ElementDataMapper() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void create(Element element) {
    ElementRDG rowGateway = new ElementRDGRDS(element.getID(), element.getAtomicNumber(), element.getAtomicMass(), element.getName(), element.getInventory());
    
  }

  @Override
  public Element read(int id) {
    Element element = null;
    ElementRDG row = new ElementRDGRDS(id);
    ElementDTO dto = row.getElement();

    element = convertFromDTO(dto);
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
    return null;
  }

  @Override
  public List<Element> filterByWildCardName(String wildCardName) {
    List<ElementDTO> dtos;
    ArrayList<Element> element = new ArrayList<>();
    try {
      dtos = ElementTDGRDS.getSingleton().filterByName(wildCardName).executeQuery();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Element> filterByInventory(double inventory) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Element> filterByInventoryRange(double min, double max) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Element> filterByAtomicNumber(int atomicNumber) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Element> filterByAtomicMass(double atomicMass) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Element> filterByAtomicMassRange(double min, double max) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Element> filterByPartOfCompound(int compoundID) {
    // TODO Auto-generated method stub
    return null;
  }

  public Element convertFromDTO(ElementDTO dto) {
    return new Element(dto.getElementId(), dto.getName(), dto.getInventory(), dto.getAtomicNumber(), dto.getAtomicMass());
  }
}
