package model;

import java.util.List;

public interface MetalDataMapperInterface {
  
  public void create(Metal metal);
  
  public Metal read(int id);
  
  public void update(Metal metal);
  
  public void delete(Metal metal);
  
  public List<Metal> getAll();
  
  public List<Metal> filterByWildCardName(String wildCardName);
  
  public List<Metal> filterByInventory(double inventory);
  
  public List<Metal> filterByInventoryRange(double min, double max);
  
  public List<Metal> filterByAtomicNumber(int atomicNumber);
  
  public List<Metal> filterByAtomicMass(double atomicMass);
  
  public List<Metal> filterByAtomicMassRange(double min, double max);
  
  public List<Metal> filterByAcidRequired(double acidRequired);
  
  public List<Metal> filterByAcidRequiredRange(double min, double max);
  
  public List<Metal> filterByDissolvedBy(int acidID);
  

}
