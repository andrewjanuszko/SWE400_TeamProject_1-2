package datasource;

public interface BaseRowDataGateway {

  public void createTableBase();
  
  public int getSoluteId(int id);

  public String getName(int id);

  public String getInhabits(int id);

  void insert(int baseId, int solute, String name, String inhabits);

}
