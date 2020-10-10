package datasource;

import java.sql.SQLException;
import java.util.List;

/**
 * AcidRowDataGateway
 * @author Isabella Boone, Kim O'Neill
 */
public interface AcidRDG {

  public int getSolute();
  
  public void setSolute(int newSolute);
  
  public String getName();
  
  public void setName(String newName);
  
  public String getInhabits();
  
  void setInhabits(String newInhabits); 
  
  public void update(); 
  
  public void delete();

  public List<AcidRDGRDS> findSet(int i);
  
}
