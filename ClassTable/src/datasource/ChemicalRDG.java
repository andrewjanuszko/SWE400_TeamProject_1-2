package datasource;

import java.sql.SQLException;
import java.util.List;

/**
 * ChemicalRowDataGateway
 * @author Isabella Boone, Kim O'Neill
 */
public interface ChemicalRDG {
  
  public void delete();
  
  public void update(); 
  
  public String getName();
  
  public void setName(String newName);

  public double getInventory();
  
  public void setInventory(double inventory);
  
  public List<ChemicalDTO> getAll();
  
}
