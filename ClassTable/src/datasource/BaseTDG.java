package datasource;

import java.util.List;

import database.DatabaseException;

public interface BaseTDG {

  public void getAllBases();
  
  public void filterByName(String name);
  
  public void filterByInventory(double inventory); 
  
  public void filterBySolute(int solute);
  
  public void filterByInventoryRange(double high, double low);
  
  public List<BaseDTO> executeQuery() throws DatabaseException;
}
