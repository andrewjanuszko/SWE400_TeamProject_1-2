package datasource;


public interface ChemicalRowDataGateway {

  public void createTableChemcial();

  public String getName(int chemicalId);

  public String getInhabits(int chemicalId);

  public void insertChemical(int id, String name, String inhabits);
}
