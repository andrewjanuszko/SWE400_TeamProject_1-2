package datasource;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author kimberlyoneill
 *
 */
public interface CompoundsMadeOfTDG {

  void createTableCompoundMadeFrom();

  public void dropTableCompoundMadeFromElement();

  public void delete();

  ArrayList<CompoundDTO> findSetCompoundId(int elementId);

  ArrayList<CompoundDTO> findSetElementId(int compoundId);

  String getCompoundName();
  
  public double getInventory();
  
  public void setCompoundId(int compoundId);
  
  public void setMadeOf(List<Integer> madeOf);

  public void setName(String name);

  public void setInventory(double inventory);
}
