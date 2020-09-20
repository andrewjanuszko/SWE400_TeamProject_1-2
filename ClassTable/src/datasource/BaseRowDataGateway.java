package datasource;

public interface BaseRowDataGateway {
  
  public void createTableBase();

  void insert(int baseId, int solute);
  
}
