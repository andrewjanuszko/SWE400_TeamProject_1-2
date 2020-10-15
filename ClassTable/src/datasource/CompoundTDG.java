package datasource;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author kimberlyoneill
 *
 */
public interface CompoundTDG {

  public void delete();

  ArrayList<CompoundDTO> findMakes(int elementId);

  ArrayList<CompoundDTO> findMadeOf(int compoundId);

  String getCompoundName();
  
  public double getInventory();
  
  public void setCompoundId(int compoundId);
  
  public void setMadeOf(List<Integer> madeOf);

  public void setName(String name);

  public void setInventory(double inventory);
  
  public void addCompound(int compoundId, List<Integer> madeOf, String name, double inventory);

  void delete(int compoundId);
}
