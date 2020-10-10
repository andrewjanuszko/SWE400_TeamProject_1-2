package datasource;

import java.util.List;

/**
 * 
 * @author kimberlyoneill
 *
 */
public interface MetalRowDataGateway {
  
  public void delete();

  public void update();

   public List<MetalRowDataGatewayRDS> findSet(int dissolvedById);
   
   public String getName();
   
   public String getInhabits();
   
   public int getMetalId();
   
   public int getDissolvedBy();

   public void setMetalId(int metalId);

   public void setDissolvedById(int dissolvedById);

   public void setName(String name);

   public void setInhabits(String inhabits);



}
