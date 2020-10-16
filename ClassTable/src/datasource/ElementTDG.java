package datasource;

import java.util.List;

public interface ElementTDG {
  public ElementTDGRDS getAllElements();
  
  public ElementDTO getDTO(int id);

  public ElementTDGRDS filterByName(String name);

  public ElementTDGRDS filterByInventory(double inventory);

  public ElementTDGRDS filterByInventoryRange(double high, double low);
  
  public ElementTDGRDS filterByAtomicMass(double atomicMass);
  
  public ElementTDGRDS filterByAtomicMassRange(double high, double low);
  
  public ElementTDGRDS filterByAtomicNumber(int atomicNumber);
  
  public ElementTDGRDS filterByAtomicNumberRange(int high, int low);
  
}
