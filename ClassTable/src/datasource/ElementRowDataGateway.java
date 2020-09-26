package datasource;

public interface ElementRowDataGateway {
  
  public void createTableElement();
  
  public int getAtomicNumber();
  
  public double getAtomicMass();
  
  public String getName();
  
  public String getInhabits();
  
  public void delete(int id);
  
  public void update(int id, int atomicNum, int atomicMass, String name, String inhabits);
}
