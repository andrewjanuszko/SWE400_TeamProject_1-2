package datasource;


public interface ChemicalRowDataGateway {

  public void createTableChemcial();

  public String getName(int chemicalId);

  public String getInhabits(int chemicalId);
  
  void insert(int chemicalId, String name, String inhabits);
}
