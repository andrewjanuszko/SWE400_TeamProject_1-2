package datasource;

import java.util.List;

public interface CompoundRowDataGateway {
	
	public int getCompoundID();
	
	public String getName();
	
	public String getInhabits();
	
	public void setName(String n);
	
	public void setInhabits(String i);
	
	public boolean persist();
	
	public boolean delete();
}
