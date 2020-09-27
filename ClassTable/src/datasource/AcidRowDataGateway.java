package datasource;

import java.sql.SQLException;

public interface AcidRowDataGateway {

  public int getSolute();
  
  public void setSolute(int newSolute);
  
  public String getName();
  
  public void setName(String newName);
  
  public String getInhabits();
  
  void setInhabits(String newInhabits); 
  
  public void update(); 
  
  public void delete();
  
  public void dropTableAcid();
  
  public void dropTableChemical();
  
  public void dropAllTables();
  
  public void fetch(int newId) throws SQLException, DatabaseException;
  
}
