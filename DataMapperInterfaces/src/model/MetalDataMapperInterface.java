package model;

import java.util.ArrayList;

public interface MetalDataMapperInterface {
  
  public void create(Metal metal);
  
  public Metal read(int id);
  
  public void update(Metal metal);
  
  public void delete(Metal metal);
  
  public ArrayList<Metal> getAll();
  
  public ArrayList<Metal> filterByWildCardName(String wildCardName);
  
  public ArrayList<Metal> filterByInventory(double inventory);
  
  public ArrayList<Metal> filterByInventoryRange(double min, double max);
  
  public ArrayList<Metal> filterByAtomicNumber(int atomicNumber);
  
  public ArrayList<Metal> filterByAtomicMass(double atomicMass);
  
  public ArrayList<Metal> filterByAtomicMassRange(double min, double max);
  
  public ArrayList<Metal> filterByAcidRequired(double acidRequired);
  
  public ArrayList<Metal> filterByAcidRequiredRange(double min, double max);
  
  public ArrayList<Metal> filterByDissolvedBy(int acidID);
  

}
