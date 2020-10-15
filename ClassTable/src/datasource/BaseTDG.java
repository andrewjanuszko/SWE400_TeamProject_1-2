package datasource;

import java.util.List;

import database.DatabaseException;

public interface BaseTDG {

  public BaseTDGRDS getAllBases();
  
  public BaseTDGRDS filterByName(String name);
  
  public BaseTDGRDS filterByInventory(double inventory); 
  
  public BaseTDGRDS filterBySolute(int solute);
  
  public BaseTDGRDS filterByInventoryRange(double high, double low);
  
  public List<BaseDTO> executeQuery() throws DatabaseException;
}
