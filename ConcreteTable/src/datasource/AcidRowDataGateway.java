package datasource;

import java.util.List;

public interface AcidRowDataGateway {
	
	public int getAcidID();
	
	public String getName();
	
	public String getInhabits();
	
	public String getSolute();
	
	public void setName(String n);
	
	public void setInhabits(String i);
	
	public void setSolute(String s);
}
