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
  
  public double getInventory();
  
  void setInventory(double inventory); 
  
  public void update(); 
  
  public void delete();
  
  public void dropTableAcid();
  
  public void dropTableChemical();
  
  public void dropAllTables();

  public List<AcidRDGRDS> findSet(int i);

  public void createTable();
  
}
