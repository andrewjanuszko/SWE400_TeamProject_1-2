package datasource;

import java.util.List;

/**
 * 
 * @author kimberlyoneill
 *
 */
public interface MetalRDG {
  
  public void delete();

  public void update();

   public List<MetalRDGRDS> findSet(int dissolvedById);

   public void setDissolvedById(int dissolvedById);

   public void setName(String name);

   public void setInventory(double inventory);

   MetalDTO getMetal();


}
