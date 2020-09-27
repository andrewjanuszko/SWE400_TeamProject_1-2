package datasource;

import java.util.List;

public interface CompoundRowDataGateway {
	
	public int getCompoundID();
	
	public String getName();
	
	public String getInhabits();
	
	public List<Integer> getMadeOf();
	
	public void setName(String n);
	
	public void setInhabits(String i);
	
	public void persist();
	
	public void delete();
}
