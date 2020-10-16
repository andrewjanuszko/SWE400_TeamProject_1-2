package model;

import java.util.List;

public interface AcidDataMapperInterface {
  
  public void create(Acid acid);
  
  public Acid read(int id);
  
  public void update(Acid acid);
  
  public void delete(Acid acid);
  
  public List<Acid> getAll();
  
  public List<Acid> filterByWildCardName(String wildCardName);
  
  public List<Acid> filterByInventory(double inventory);
  
  public List<Acid> filterByInventoryRange(double min, double max);
  
  public List<Acid> filterBySolute(int chemicalID);
  
}
