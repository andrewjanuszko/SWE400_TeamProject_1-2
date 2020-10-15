package model;

import java.util.ArrayList;

public interface ElementDataMapperInterface {
  
  public void create(Element element);
  
  public Element read(int id);
  
  public void update(Element element);
  
  public void delete(Element element);
  
  public ArrayList<Element> getAll();
  
  public ArrayList<Element> filterByWildCardName(String wildCardName);
  
  public ArrayList<Element> filterByInventory(double inventory);
  
  public ArrayList<Element> filterByInventoryRange(double min, double max);
  
  public ArrayList<Element> filterByAtomicNumber(int atomicNumber);
  
  public ArrayList<Element> filterByAtomicMass(double atomicMass);
  
  public ArrayList<Element> filterByAtomicMassRange(double min, double max);
  
  public ArrayList<Element> filterByPartOfCompound(int compoundID);
  
}
