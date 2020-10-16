package model;

import java.util.List;

public interface BaseDataMapperInterface {
  
  public void create(Base base);
  
  public Base read(int id);
  
  public void update(Base base);
  
  public void delete(Base base);
  
  public List<Base> getAll();
  
  public List<Base> filterByWildCardName(String wildCardName);
  
  public List<Base> filterByInventory(double inventory);
  
  public List<Base> filterByInventoryRange(double min, double max);
  
  public List<Base> filterBySolute(int chemicalID);
}
