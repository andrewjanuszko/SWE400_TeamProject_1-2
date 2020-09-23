package datasource;

public interface BaseRowDataGateway {

  public void createTableBase();
  
  public int getSolute();
  
  public void setSolute(int newSolute);

  public String getName();
  
  public void setName(String newName);

  public String getInhabits();
  
  public void setInhabits(String newInhabits);

}
