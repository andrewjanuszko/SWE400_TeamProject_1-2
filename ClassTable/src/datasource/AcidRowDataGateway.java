package datasource;

public interface AcidRowDataGateway {

  public int getSolute();
  
  public void setSolute(int newSolute);
  
  public String getName();
  
  public void setName(String newName);
  
  public String getInhabits();
  
  void setInhabits(String newInhabits); 
  
  public void update() throws DatabaseException; 
  
  public void delete(int id);
  
  public void dropTableAcid();
  
  public void dropTableChemical();
  
  public void dropAllTables();
  
  public void fetch(int newId);
}
