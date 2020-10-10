package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Row Data Gateway for Compound.
 * @author Chase and Joel.
 *
 */
public class CompoundRowDataGatewayRDS implements CompoundRowDataGateway{
  
  /**
   * Creates the table in the database. Drops the table if it already exists.
   * @throws DatabaseException
   */
	public static void createTable() throws DatabaseException{
		
		String drop = "DROP TABLE IF EXISTS Compound";
		String create = "CREATE TABLE Compound (" + 
				"compoundID INT NOT NULL, " + 
				"name VARCHAR(30) NOT NULL, " +                      
				"inhabits VARCHAR(30), " + 
				"UNIQUE(name), " +
				"PRIMARY KEY(compoundID)) ;";
		
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
			throw new DatabaseException("Unable to create compound table", e);
		}
	}
	

	private Connection conn;
	
	private int compoundID;
	private String name;
	private String inhabits;
	
	/**
   * Constructs Compound Row Data Gateway based off of existing row by ID.
   * @param id
   * @throws DatabaseException
   */
	public CompoundRowDataGatewayRDS(int id) throws DatabaseException {
		conn = DatabaseManager.getSingleton().getConnection();
		this.compoundID = id;
		findByID(id);
	}
	
	/**
   * Finds existing row by ID.
   * @param id
   * @throws DatabaseException
   */
	private void findByID(int id) throws DatabaseException {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Compound WHERE compoundID = " + id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			name = rs.getString("name");
			inhabits = rs.getString("inhabits");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find Compound with that Id", e);
		}
		
	}
	
	/**
   * Constructs Compound Row Data Gateway based off of existing row by name.
   * @param name
   * @throws DatabaseException
   */
	public CompoundRowDataGatewayRDS(String name) throws DatabaseException{
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
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Compound WHERE name = '" + name + "'");
			ResultSet rs = stmt.executeQuery();
			rs.next();
			compoundID = rs.getInt("compoundID");
			inhabits = rs.getString("inhabits");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find Compound with that name", e);
		}
	}
	
	/**
	 * Constructs new Base Row Data Gateway from given parameters.
	 * @param id
	 * @param name
	 * @param inhabits
	 * @throws DatabaseException
	 */
	public CompoundRowDataGatewayRDS(int id, String name, String inhabits) throws DatabaseException {
		compoundID = id;
		this.name = name;
		this.inhabits = inhabits;
		conn = DatabaseManager.getSingleton().getConnection();
		insert();
	}

  @Override
  public int getCompoundID() {
    return this.compoundID;
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
  public void setName(String n) {
   this.name = n;
  }

  @Override
  public void setInhabits(String i) {
    this.inhabits = i;
  }
  
  /**
   * Updates the information in the database to reflect changes made.
   * @return boolean
   */
  public boolean persist() {
    try {
      PreparedStatement stmt = conn.prepareStatement("UPDATE Compound SET"
          + " name = '" + name
          + "', inhabits = '" + inhabits
          + "' WHERE compoundID = " + compoundID);
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      new DatabaseException("Couldn't update Compound table");
      return false;
    }
  }
  
  /**
   * Deletes row from database.
   * @return boolean
   */
  public boolean delete() {
  	try {
  		PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM CompoundMadeOf WHERE compoundID = " + compoundID);
  		stmt1.execute();
  		PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Compound WHERE compoundID = " + compoundID);
  		stmt2.execute();
  		return true;
  	} catch (SQLException e) {
  		new DatabaseException("could not delete Compound");
  		return false;
  	}
  }
  
  /**
   * Inserts new row into database.
   */
  private void insert() {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Compound(compoundID, name, inhabits) VALUES (" + compoundID + ", '" + name + "', '" + inhabits + "');");
			stmt.execute();
		} catch(SQLException e) {
			new DatabaseException("could not insert into compound table");
		}
	}
}
