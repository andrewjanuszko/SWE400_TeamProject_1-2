package model;

import java.util.List;

public interface CompoundDataMapperInterface {
  
  public void create(Compound compound);
  
  public Compound read(int id);
  
  public void update(Compound compound);
  
  public void delete(Compound compound);
  
  public List<Compound> getAll();
  
  public List<Compound> filterByWildCardName(String wildCardName);
  
  public List<Compound> filterByInventory(double inventory);
  
  public List<Compound> filterByInventoryRange(double min, double max);
  
  public List<Compound> filterByMadeOf(int elementID);
  
}
