package datasource;

import java.util.List;

public interface CompoundRowDataGateway {
	
	public int getCompoundID();
	
	public String getName();
	
	public String getInhabits();
	
	public List<Integer> getMadeOf();
	
	public void setCompoundID(int id);
	
	public void setName(String n);
	
	public void setInhabits(String i);
	
	/*
	 * adds an element to make up of the compound.
	 */
	public void addElementToCompound(int id);
	
	/*
	 * deletes and element from make up of compound.
	 */
	public boolean deleteElementFromCompound(int id);
}
