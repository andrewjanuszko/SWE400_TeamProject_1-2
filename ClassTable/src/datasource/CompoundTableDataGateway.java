package datasource;

import java.util.List;

public interface CompoundTableDataGateway {


  public void createTableCompound();
  
  public void insert(int compoundId, String madeOf);
  
  public List<Integer> compoundsThatHaveElement(int id);
  
  public String getMadeOf(int id);
  
  public String getName(int id);
  
  public String getInhabits(int id);
}
