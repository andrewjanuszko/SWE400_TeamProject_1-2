package interfaces;

import java.util.ArrayList;
import model.Chemical;

public interface ChemicalDataMapperInterface {

  public void create(Chemical chemical);
  
  public Chemical read(int id);
  
  public void update(Chemical chemical);
  
  public void delete(Chemical chemical);
  
  public ArrayList<Chemical> getAll();
  
  public ArrayList<Chemical> filterByWildCardName(String wildCardName);
  
  public ArrayList<Chemical> filterByInventory(double inventory);
  
  public ArrayList<Chemical> filterByInventoryRange(double min, double max);
  
}
