package datasource;

import java.util.List;

public interface CompoundsMadeOfTableDataGateway {
  
  void insert(int compoundId, int elementId);
  
  void createTableDataMadeOf(); 
  
  String getCompoundName(int compoundId);
  
  public String getInhabits(int compoundId);

  List<Integer> findSetElementId(int compoundId);

  List<Integer> findSetCompoundId(int elementId);
  
  public void delete(int id);
  
  public void update();
  
}
