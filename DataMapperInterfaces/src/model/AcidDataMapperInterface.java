package model;

import java.util.ArrayList;

public interface AcidDataMapperInterface {
  
  public void create(Acid acid);
  
  public Acid read(int id);
  
  public void update(Acid acid);
  
  public void delete(Acid acid);
  
  public ArrayList<Acid> getAll();
  
  public ArrayList<Acid> filterByWildCardName(String wildCardName);
  
  public ArrayList<Acid> filterByInventory(double inventory);
  
  public ArrayList<Acid> filterByInventoryRange(double min, double max);
  
  public ArrayList<Acid> filterBySolute(int chemicalID);
  
}
