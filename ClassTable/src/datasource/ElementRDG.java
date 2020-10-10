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
  
  public void findByAtomicNumber(int atomicNum);
  
  public void findByAtomicMass(double atomicMass);

  public List<ElementRDGRDS> findSetAtomicMass(double lowerLimit, double upperLimit);
  
  public int getAtomicNumber();

  public double getAtomicMass();

  public String getName();

  public String getInhabits();

  public void setElementId(int elementId);

  public void setAtomicNumber(int atomicNumber);

  public void setAtomicMass(double atomicMass);
  
  public void setName(String name);
  
  public void setInhabits(String inhabits);

}
