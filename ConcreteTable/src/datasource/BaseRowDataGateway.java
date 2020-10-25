package datasource;

public interface BaseRowDataGateway {
	
	public int getBaseID();
	
	public String getName();
	
	public double getInventory();
	
	public int getSolute();
	
	public void setName(String name);
	
	public void setInventory(double inventory);
	
	public void setSolute(int solute);
	
	public boolean persist();
	
	public boolean delete();
}
