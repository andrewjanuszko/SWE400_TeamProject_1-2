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
}
