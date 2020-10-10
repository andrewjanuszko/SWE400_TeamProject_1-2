package datasource;

public interface BaseRowDataGateway {
	
	public int getBaseID();
	
	public String getName();
	
	public String getInhabits();
	
	public String getSolute();
	
	public void setName(String name);
	
	public void setInhabits(String inhabits);
	
	public void setSolute(String solute);
	
	public boolean persist();
	
	public boolean delete();
}
