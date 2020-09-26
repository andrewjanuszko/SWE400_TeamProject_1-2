package datasource;


public interface ChemicalRowDataGateway {

  public void createTable(); 
  
  public void dropTable();
  
  public void delete();
  
  public void fetch(int newId);
  
  public void update(); 
  
  public String getName();
  
  public void setName(String newName);

  public String getInhabits();
  
  public void setInhabits(String newInhabits);
  
}
