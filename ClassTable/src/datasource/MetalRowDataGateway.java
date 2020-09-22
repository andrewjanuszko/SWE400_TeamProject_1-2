package datasource;


public interface MetalRowDataGateway {
  
  public void createTableMetal();
  
  public String getName(int id);
  
  public String getInhabits(int id);
  
  public int getDissolvedBy(int id);
  
  public void insert(int id, int dissolvedBy);
}
