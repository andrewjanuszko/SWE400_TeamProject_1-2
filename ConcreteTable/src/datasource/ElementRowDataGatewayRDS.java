package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementRowDataGatewayRDS implements ElementRowDataGateway{
	
	public static void createTable() throws DatabaseException{
		String drop = "DROP TABLE IF EXISTS Element";
		String create = "CREATE TABLE Element (" + 
				"elementID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + 
				"name VARCHAR(30) NOT NULL, " +                      
				"inhabits VARCHAR(30), " +
				"atomicNumer INT NOT NULL, " +
				"atomicMass DOUBLE NOT NULL," + 
				"UNIQUE(name);";
		
		Connection conn = DatabaseManager.getSingleton().getConnection();

		try
		{
			// drop table
			PreparedStatement stmt;
			stmt = DatabaseManager.getSingleton().getConnection().prepareStatement(drop);
			stmt.execute();
			stmt.close();

			// create table
			stmt = DatabaseManager.getSingleton().getConnection().prepareStatement(create);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to create InteractableItem table", e);
		}
		
	}

	private Connection conn;
	
	private int elementID;
	private String name;
	private String inhabits;
	private int atomicNumber;
	private double atomicMass;
	
	
	public ElementRowDataGatewayRDS(String name) throws DatabaseException{
		conn = DatabaseManager.getSingleton().getConnection();
		this.name = name;
		findByName(name);
	}
	
	private void findByName(String name) throws DatabaseException{
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Element WHERE name = " + name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			elementID = rs.getInt("elementID");
			inhabits = rs.getString("inhabits");
			atomicNumber = rs.getInt("atomicNumber");
			atomicMass = rs.getDouble("atomicMass");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find element with that name", e);
		}
	}

  @Override
  public int getElementID() {
    return this.elementID;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getInhabits() {
    return this.inhabits;
  }

  @Override
  public int getAtomicNumber() {
    return this.atomicNumber;
  }

  @Override
  public double getAtomicMass() {
    return this.atomicMass;
  }

  @Override
  public void setElementID(int id) {
    this.elementID = id;
    
  }

  @Override
  public void setName(String n) {
    this.name = n;
  }

  @Override
  public void setInhabits(String i) {
    this.inhabits = i;
  }

  @Override
  public void setAtomicNumber(int i) {
    this.atomicNumber = i;
    
  }

  @Override
  public void setAtomicMass(double d) {
    this.atomicMass = d;
    
  }
}
