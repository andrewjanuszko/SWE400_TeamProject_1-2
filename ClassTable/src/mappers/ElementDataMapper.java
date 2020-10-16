package mappers;

import java.util.List;

import datasource.ElementRDG;
import datasource.ElementRDGRDS;
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(Element element) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(Element element) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Element> getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Element> filterByWildCardName(String wildCardName) {
    // TODO Auto-generated method stub
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

}
