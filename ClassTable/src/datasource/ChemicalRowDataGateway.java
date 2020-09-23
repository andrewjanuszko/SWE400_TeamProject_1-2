package datasource;


public interface ChemicalRowDataGateway {

  public void createTableChemical();

  public String getName();
  
  public void setName(String newName);

  public String getInhabits();
  
  public void setInhabits(String newInhabits);
  
}
