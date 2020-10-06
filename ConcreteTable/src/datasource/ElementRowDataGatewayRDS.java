package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Row Data Gateway for Element.
 * @author 
 *
 */
public class ElementRowDataGatewayRDS implements ElementRowDataGateway{
	
  /**
   * Creates the table in the database. Drops the table if it already exists.
   * @throws DatabaseException
   */
	public static void createTable() throws DatabaseException{
		String drop = "DROP TABLE IF EXISTS Element";
		String create = "CREATE TABLE Element (" + 
				"elementID INT NOT NULL, " + 
				"name VARCHAR(30) NOT NULL, " +                      
				"inhabits VARCHAR(30), " +
				"atomicNumber INT NOT NULL, " +
				"atomicMass DOUBLE NOT NULL," + 
				"UNIQUE(name)," +
			  "PRIMARY KEY(elementID));";
		
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
			throw new DatabaseException("Unable to create Element table", e);
		}
		
	}

	private Connection conn;
	
	private int elementID;
	private String name;
	private String inhabits;
	private int atomicNumber;
	private double atomicMass;
	
	/**
   * Constructs Element Row Data Gateway based off of existing row by ID.
   * @param id
   * @throws DatabaseException
   */
	public ElementRowDataGatewayRDS(int id) throws DatabaseException {
		conn = DatabaseManager.getSingleton().getConnection();
		this.elementID = id;
		findByID(id);
	}
	
	/**
   * Finds existing row by ID.
   * @param id
   * @throws DatabaseException
   */
	private void findByID(int id) throws DatabaseException {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Element WHERE elementID = " + id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			System.out.println("testsu?");
			name = rs.getString("name");
			System.out.println("testsu");
			inhabits = rs.getString("inhabits");
			System.out.println("testsu1");
			atomicNumber = rs.getInt("atomicNumber");
			System.out.println("testsu2");
			atomicMass = rs.getDouble("atomicMass");
			System.out.println("testsu3");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find element with that ID", e);
		}
	}
	
	/**
   * Constructs Element Row Data Gateway based off of existing row by name.
   * @param name
   * @throws DatabaseException
   */
	public ElementRowDataGatewayRDS(String name) throws DatabaseException{
		conn = DatabaseManager.getSingleton().getConnection();
		this.name = name;
		findByName(name);
	}
	
	/**
   * Finds existing row by Name.
   * @param name
   * @throws DatabaseException
   */
	private void findByName(String name) throws DatabaseException{
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Element WHERE name = '" + name + "'");
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
	
	/**
	 * Constructs new Element Row Data Gateway from given parameters.
	 * @param id
	 * @param name
	 * @param inhabits
	 * @param atomicNumber
	 * @param atomicMass
	 * @throws DatabaseException
	 */
	public ElementRowDataGatewayRDS(int id, String name, String inhabits, int atomicNumber, double atomicMass) throws DatabaseException {
		elementID = id;
		this.name = name;
		this.inhabits = inhabits;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		conn = DatabaseManager.getSingleton().getConnection();
		insert();
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
  
  /**
   * Updates the information in the database to reflect changes made.
   * @return boolean
   */
  public boolean persist() {
	  try {
		PreparedStatement stmt = conn.prepareStatement("UPDATE Element SET"
				+ " name = '" + name
				+ "', inhabits = '" + inhabits
				+ "', atomicNumber = " + atomicNumber
				+ ", atomicMass = " + atomicMass
				+ " WHERE elementID = " + elementID);
		
		stmt.executeUpdate();
		return true;
	} catch (SQLException e) {
		new DatabaseException("could't update element table");
		return false;
	}
  }

  /**
   * Deletes row from database.
   * @return boolean
   */
  public boolean delete() {
	  try {
			PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM CompoundMadeOf WHERE elementID = " + elementID);
			stmt1.execute();
			PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Element WHERE elementID = " + elementID);
			stmt2.execute();
			return true;
		} catch (SQLException e) {
			new DatabaseException("could not delete Element");
			return false;
		}
  }
  
  /**
   * Inserts new row into database.
   */
  private void insert() {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Element(elementID, name, inhabits, atomicNumber, atomicMass) VALUES (" + elementID + ", '" + name + "', '" + inhabits + "', " + atomicNumber + ", " + atomicMass + ");");
			stmt.execute();
		} catch(SQLException e) {
			new DatabaseException("could not insert into Element table");
		}
	}
}
