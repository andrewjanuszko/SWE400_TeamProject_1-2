package model;

import java.util.List;

public interface ElementDataMapperInterface {
  
  public void create(Element element);
  
  public Element read(int id);
  
  public void update(Element element);
  
  public void delete(Element element);
  
  public List<Element> getAll();
  
  public List<Element> filterByWildCardName(String wildCardName);
  
  public List<Element> filterByInventory(double inventory);
  
  public List<Element> filterByInventoryRange(double min, double max);
  
  public List<Element> filterByAtomicNumber(int atomicNumber);
  
  public List<Element> filterByAtomicMass(double atomicMass);
  
  public List<Element> filterByAtomicMassRange(double min, double max);
  
  public List<Element> filterByPartOfCompound(int compoundID);
  
}
