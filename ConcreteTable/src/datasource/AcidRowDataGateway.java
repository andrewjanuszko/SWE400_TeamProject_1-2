package datasource;

public interface AcidRowDataGateway {
  
  public int getAcidID();
  
  public String getName();
  
  public double getInventory();
  
  public int getSolute();
  
  public void setName(String n);
  
  public void setInventory(double i);
  
  public void setSolute(int s);
  
  public boolean persist();
  
  public boolean delete();
}
