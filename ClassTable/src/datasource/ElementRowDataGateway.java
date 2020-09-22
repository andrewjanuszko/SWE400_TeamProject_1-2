package datasource;

public interface ElementRowDataGateway {
  
  public void createTableElement();
  
  public int getAtomicNumber(int id);
  
  public int getAtomicMass(int id);
  
  public String getName(int id);
  
  public String getInhabits(int id);
  
  public void insert(int id, int atomicNum, int atomicMass, String name, String inhabits);
}
