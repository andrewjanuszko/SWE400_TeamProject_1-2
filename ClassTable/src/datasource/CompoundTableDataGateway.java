package datasource;


public abstract class CompoundTableDataGateway {


  public abstract void createTableCompound();
  
  public abstract void insert(int compoundId, String madeOf);
  
  public abstract String getMadeOf(int id);
  
  public abstract String getName(int id);
  
  public abstract String getInhabits(int id);
}
