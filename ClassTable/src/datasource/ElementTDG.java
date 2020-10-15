package datasource;

import java.util.List;

public interface ElementTDG {
  public List<ElementDTO> getAllElements();
  
  public List<ElementDTO> findSetAtomicMass(double lowerLimit, double upperLimit);
  
  public ElementDTO getDTO(int id);

  public void filterByName(String name);

  public void filterByInventory(double inventory);

  public void filterByInventoryRange(double high, double low);
  
  public void filterByAtomicMass(double atomicMass);
  
  public void filterByAtomicMassRange(double high, double low);
  
  public void filterByAtomicNumber(int atomicNumber);
  
  public void filterByAtomicNumberRange(int high, int low);
}
