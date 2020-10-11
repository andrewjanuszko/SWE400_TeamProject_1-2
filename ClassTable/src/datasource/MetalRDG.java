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
   
   public String getName();
   
   public String getInhabits();
   
   public int getMetalId();
   
   public int getDissolvedBy();
   
   public int getAtomicNumber();
   
   public double getAtomicMass();

   public void setMetalId(int metalId);

   public void setDissolvedById(int dissolvedById);

   public void setName(String name);

   public void setInhabits(String inhabits);
   
   public void setAtomicNumber(int atomicNumber);
   
   public void setAtomicMass(int atomicMass);



}
