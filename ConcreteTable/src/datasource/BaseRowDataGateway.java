package datasource;

public interface BaseRowDataGateway {
	
	public int getBaseID();
	
	public String getName();
	
	public double getInventory();
	
	public String getSolute();
	
	public void setName(String name);
	
	public void setInventory(double inventory);
	
	public void setSolute(String solute);
	
	public boolean persist();
	
	public boolean delete();
}
