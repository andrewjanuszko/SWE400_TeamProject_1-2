package datasource;

import java.sql.SQLException;

public interface ChemicalRowDataGateway {

  public void createTable(); 
  
  public void dropTable();
  
  public void delete();
  
  public void fetch(int newId) throws SQLException, DatabaseException;
  
  public void update(); 
  
  public String getName();
  
  public void setName(String newName);

  public String getInhabits();
  
  public void setInhabits(String newInhabits);
  
  public void insert(int id, String name, String inhabits);
  
}
