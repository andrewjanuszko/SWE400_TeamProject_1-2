package datasource;

import java.util.List;

/**
 * 
 * @author kimberlyoneill
 *
 */
public interface MetalRowDataGateway {

  public void createTableMetal();

  public void dropTableMetal();

  public void dropTableChemical();

  public void dropAllTables();
  
  public void delete();

  public void update(int id, int dissolvedById, String name, String inhabits);

   public List<MetalRowDataGatewayRDS> findSet(int dissolvedById);
   
   public String getName();

   public String getInhabits();

   public int getDissolvedBy();

}
