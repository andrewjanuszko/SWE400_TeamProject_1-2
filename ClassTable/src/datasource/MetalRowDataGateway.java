package datasource;


public interface MetalRowDataGateway {
  
  public void createTableMetal();
  
  public String getName();
  
  public String getInhabits();
  
  public int getDissolvedBy();
  
  public void delete(int id);
  
  public void update(int id, int dissolvedById, String name, String inhabits);
  
  public void dropTableMetal();
  
  public void dropTableChemical();
  
  public void dropAllTables();
}
