package datasource;

public interface AcidRowDataGateway {

  public void createTableAcid();

  public int getSolute();
  
  public void setSolute(int newSolute);
  
  public String getName();
  
  public void setName(String newName);
  
  public String getInhabits();
  
  void setInhabits(String newInhabits); 
  
  public void update() throws DatabaseException; 
  
  public void delete(int id);
  
}
