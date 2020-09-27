package datasource;
/*
 * The Interface for MetalRowDataGateway
 */
public interface MetalRowDataGateway {

	public int getMetalID();
	
	public String getName();
	
	public String getInhabits();
	
	public int getAtomicNumber();
	
	public double getAtomicMass();
	
	public int getDissolvedBy();
	
	public void setName(String s);
	
	public void setInhabits(String s);
	
	public void setAtomicNumber(int i);
	
	public void setAtomicMass(double d);
	
	public void setDissolvedBy(int i);
	
	public void persist();
	
	public void delete();
}
