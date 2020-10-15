package model;

import java.util.ArrayList;

public interface BaseDataMapperInterface {
  
  public void create(Base base);
  
  public Base read(int id);
  
  public void update(Base base);
  
  public void delete(Base base);
  
  public ArrayList<Base> getAll();
  
  public ArrayList<Base> filterByWildCardName(String wildCardName);
  
  public ArrayList<Base> filterByInventory(double inventory);
  
  public ArrayList<Base> filterByInventoryRange(double min, double max);
  
  public ArrayList<Base> filterBySolute(int chemicalID);
}
