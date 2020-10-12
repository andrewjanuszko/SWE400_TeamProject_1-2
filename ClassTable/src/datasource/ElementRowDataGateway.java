package datasource;

import java.util.List;

/**
 * 
 * @author kimberlyoneill
 *
 */
public interface ElementRowDataGateway {

  public void createTableElement();

  public void dropTableElement();

  public void dropTableChemical();

  public void dropAllTables();
  
  public void delete();

  public void update();
  
  public void findByAtomicNumber(int atomicNum);
  
  public void findByAtomicMass(double atomicMass);

  public List<ElementRowDataGatewayRDS> findSetAtomicMass(double lowerLimit, double upperLimit);
  
  public int getAtomicNumber();

  public double getAtomicMass();

  public String getName();

  public double getInventory();

  public void setElementId(int elementId);

  public void setAtomicNumber(int atomicNumber);

  public void setAtomicMass(double atomicMass);
  
  public void setName(String name);
  
  public void setInventory(double inventory);

}
