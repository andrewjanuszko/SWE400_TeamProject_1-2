package datasource;

import java.util.List;

public interface CompoundsMadeOfTableDataGateway {

  List<Integer> getCompoundId(int elementId);
  
  List<Integer> getElementId(int compoundId);
  
  void insert(int compoundId, int elementId);

  void createTableDataMadeOf(); 
  
  String getCompoundName(int compoundId);
  
  public String getInhabits(int compoundId);
}
