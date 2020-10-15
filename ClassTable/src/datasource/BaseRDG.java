package datasource;

import java.sql.SQLException;
import java.util.List;

/**
 * BaseRowDataGateway
 * @author Isabella Boone, Kim O'Neill
 */
public interface BaseRDG {
  
  public void setSolute(int newSolute);
  
  public void setName(String newName);
  
  public void setInventory(double inventory);
  
  public BaseDTO getBase();

  public void update();

  public void delete();

  public List<BaseRDGRDS> findSet(int solute);
  
}
