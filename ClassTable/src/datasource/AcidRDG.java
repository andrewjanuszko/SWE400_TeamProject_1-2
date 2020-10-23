package datasource;

import java.util.List;

/**
 * AcidRDG interface
 * 
 * @author Isabella Boone, Kim O'Neill
 */
public interface AcidRDG {

  public void setSolute(int newSolute);

  public void setName(String newName);

  void setInventory(double inventory);

  AcidDTO getAcid();

  public void update();

  public void delete();

  public List<AcidRDGRDS> findSet(int i);

}
