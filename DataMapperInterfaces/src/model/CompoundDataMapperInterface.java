package model;

import java.util.ArrayList;

public interface CompoundDataMapperInterface {
  
  public void create(Compound compound);
  
  public Compound read(int id);
  
  public void update(Compound compound);
  
  public void delete(Compound compound);
  
  public ArrayList<Compound> getAll();
  
  public ArrayList<Compound> filterByWildCardName(String wildCardName);
  
  public ArrayList<Compound> filterByInventory(double inventory);
  
  public ArrayList<Compound> filterByInventoryRange(double min, double max);
  
  public ArrayList<Compound> filterByMadeOf(int elementID);
  
}
