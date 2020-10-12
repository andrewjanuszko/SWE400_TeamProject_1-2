package datasource;

import java.util.List;

public interface AcidRowDataGateway {
	
	public int getAcidID();
	
	public String getName();
	
	public double getInventory();
	
	public String getSolute();
	
	public void setName(String n);
	
	public void setInventory(double i);
	
	public void setSolute(String s);
	
	public boolean persist();
	
	public boolean delete();
}
