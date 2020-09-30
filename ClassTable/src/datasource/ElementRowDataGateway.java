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

  public void update(int id, int atomicNum, int atomicMass, String name, String inhabits);

  public List<ElementRowDataGatewayRDS> findSetAtomicMass(double lowerLimit, double upperLimit);
  
  public int getAtomicNumber();

  public double getAtomicMass();

  public String getName();

  public String getInhabits();

}
