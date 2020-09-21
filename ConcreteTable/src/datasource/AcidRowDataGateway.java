package datasource;

import java.util.List;

public interface AcidRowDataGateway {
	
	public int getAcidID();
	
	public String getName();
	
	public String getInhabits();
	
	public String getSolute();
	
	public List<Integer> getDissloves();
	
	public void setAcidID(int id);
	
	public void setName(String n);
	
	public void setInhabits(String i);
	
	public void setSolute(String s);
	
	/*
	 * Adds a chemical that an acid can dissolve.
	 */
	public boolean addDissolves(int id);
	
	/*
	 * deletes a chemical that an acid can dissolve.
	 */
	public boolean deleteDisolves(int id);
}
