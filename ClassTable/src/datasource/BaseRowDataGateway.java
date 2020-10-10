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

  public String getInhabits();
  
  public void setInhabits(String newInhabits);

  public void update();

  public void delete();

  public List<BaseRowDataGatewayRDS> findSet(int solute);
}
