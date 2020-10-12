package datasource;

import java.sql.SQLException;
import java.util.List;

/**
 * BaseRowDataGateway
 * @author Isabella Boone, Kim O'Neill
 */
public interface BaseRowDataGateway {
  
  public int getSolute();
  
  public void setSolute(int newSolute);

  public String getName();
  
  public void setName(String newName);

  public double getInventory();
  
  public void setInventory(double inventory);

  public void update();

  public void delete();
  
  public void dropTableBase();
  
  public void dropTableChemical();
  
  public void dropAllTables(); 
  
  public void createTable();

  public List<BaseRowDataGatewayRDS> findSet(int solute);
}
