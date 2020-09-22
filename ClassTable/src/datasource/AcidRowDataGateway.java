package datasource;

public interface AcidRowDataGateway {

  public void createTableAcid();

  public int getSoluteId(int id);
  
  public String getName(int id);
  
  public String getInhabits(int id);
  
  public void insert(int id, int soluteId);

}
