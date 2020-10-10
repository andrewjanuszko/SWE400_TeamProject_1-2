package datasource;

import java.util.List;
/**
 * 
 * @author kimberlyoneill
 *
 */
public interface CompoundsMadeOfTableDataGateway {

  public void delete();

  List<Integer> findMakes(int elementId);

  List<Integer> findMadeOf(int compoundId);

  String getCompoundName();
  
  public String getInhabits();
  
  public void setCompoundId(int compoundId);
  
  public void setMadeOf(List<Integer> madeOf);

  public void setName(String name);

  public void setInhabits(String inhabits);
}
