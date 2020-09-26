package datasource;

public interface BaseRowDataGateway {
  
  public int getSolute();
  
  public void setSolute(int newSolute);

  public String getName();
  
  public void setName(String newName);

  public String getInhabits();
  
  public void setInhabits(String newInhabits);

  public void update();

  public void delete();
  
  public void dropTableBase();
  
  public void dropTableChemical();
  
  public void dropAllTables(); 
  
  public void fetch(int id);
  
}
