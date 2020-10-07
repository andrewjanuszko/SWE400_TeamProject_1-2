package datasource;

import java.util.List;
/**
 * 
 * @author kimberlyoneill
 *
 */
public interface CompoundsMadeOfTableDataGateway {

  void createTableCompoundMadeFrom();

  public void dropTableCompoundMadeFromElement();

  public void delete();

  List<Integer> findSetCompoundId(int elementId);

  List<Integer> findSetElementId(int compoundId);

  String getCompoundName();
  
  public String getInhabits();
  
  public void setCompoundId(int compoundId);
  
  public void setMadeOf(List<Integer> madeOf);

  public void setName(String name);

  public void setInhabits(String inhabits);
}
