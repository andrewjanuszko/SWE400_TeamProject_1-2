package datasource;

public interface CompoundMadeOfTableDataGateway {
	public void createTable();
	
	public void getCompoundMadeOf(int compoundID);
	
	public void getCompoundMadeOf(String name);
}
