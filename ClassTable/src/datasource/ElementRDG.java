package datasource;

import java.util.List;

/**
 * 
 * @author kimberlyoneill
 *
 */
public interface ElementRDG {
  
  public void delete();

  public void update();
  
  public ElementDTO findByAtomicNumber(int atomicNum);
  
  public ElementDTO findByAtomicMass(double atomicMass);

  public List<ElementDTO> findSetAtomicMass(double lowerLimit, double upperLimit);

  public void setElementId(int elementId);

  public void setAtomicNumber(int atomicNumber);

  public void setAtomicMass(double atomicMass);
  
  public void setName(String name);
  
  public void setInventory(double inventory);
  
  ElementDTO getElement();

}
