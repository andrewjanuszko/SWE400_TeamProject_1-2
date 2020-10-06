package datasource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MetalRowDataGatewayRDS implements MetalRowDataGateway{
	/*
	 * Creates Table
	 * 
	 * @throws DatabaseException if it can't create or drop table.
	 */
	public static void createTable() throws DatabaseException
	{
		String drop = "DROP TABLE IF EXISTS Metal";
		String create = "CREATE TABLE Metal (" + 
				"metalID INT NOT NULL, " + 
				"name VARCHAR(30) NOT NULL, " +                      //maybe Unique
				"inhabits VARCHAR(30), " +
				"atomicNumer INT NOT NULL, " +
				"atomicMass DOUBLE NOT NULL, " +
				"dissolvedBy INT, " + 
				"UNIQUE(name), " +
				"PRIMARY KEY(metalID), " +
				"FOREIGN KEY(metalID) REFERENCES Acid(acidID)); ";
		
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
		} catch (SQLException e)
		{
			throw new DatabaseException("Unable to create Metal table", e);
		}
	}
	 public static void dropTable() throws DatabaseException {
	   String drop = "DROP TABLE IF EXISTS Metal";
	   try {
	      // drop table
	      PreparedStatement stmt;
	      stmt = DatabaseManager.getSingleton().getConnection().prepareStatement(drop);
	      stmt.execute();
	      stmt.close();
	    } catch (SQLException e)
      {
        throw new DatabaseException("Unable to drop Metal table", e);
      }
	 }

	private Connection conn;
	
	private int metalID;
	private String name;
	private String inhabits;
	private int atomicNumber;
	private double atomicMass;
	private int dissolvedBy;
	
	public MetalRowDataGatewayRDS(int id) throws DatabaseException {
		conn = DatabaseManager.getSingleton().getConnection();
		this.metalID = id;
		findByID(id);
	}
	
	private void findByID(int id) throws DatabaseException{
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Metal WHERE metalID = " + id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			name = rs.getString("name");
			inhabits = rs.getString("inhabits");
			atomicNumber = rs.getInt("atomicNumber");
			atomicMass = rs.getDouble("atomicMass");
			dissolvedBy = rs.getInt("dissolvedBy");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find metal with that name", e);
		}
	}
	
	public MetalRowDataGatewayRDS(String name) throws DatabaseException{
		conn = DatabaseManager.getSingleton().getConnection();
		this.name = name;
		findByName(name);
	}
	
	private void findByName(String name) throws DatabaseException{
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Metal WHERE name = " + name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			metalID = rs.getInt("metalID");
			inhabits = rs.getString("inhabits");
			atomicNumber = rs.getInt("atomicNumber");
			atomicMass = rs.getDouble("atomicMass");
			dissolvedBy = rs.getInt("dissolvedBy");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find metal with that name", e);
		}
	}
	
	public MetalRowDataGatewayRDS(int id, String name, String inhabits, int atomicNumber, double atomicMass, int dissolvedBy) {
		metalID = id;
		this.name = name;
		this.inhabits = inhabits;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.dissolvedBy = dissolvedBy;
		insert();
	}

  @Override
  public int getMetalID() {
    return this.getMetalID();
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
  public int getDissolvedBy() {
    return this.dissolvedBy;
  }

  @Override
  public void setName(String s) {
    this.name = s;
  }

  @Override
  public void setInhabits(String s) {
   this.inhabits = s;
  }

  @Override
  public void setAtomicNumber(int i) {
    this.atomicNumber = i;
  }

  @Override
  public void setAtomicMass(double d) {
    this.atomicMass = d;
  }

  @Override
  public void setDissolvedBy(int i) {
    this.dissolvedBy = i;
  }
  

  public boolean persist() {
	  try {
		PreparedStatement stmt = conn.prepareStatement("UPDATE Metal SET"
				+ " name = " + name
				+ ", inhabits = " + inhabits
				+ ", atomicNumber = " + atomicNumber
				+ ", atomicMass = " + atomicMass
				+ ", dissolvedBy = " + dissolvedBy
				+ " WHERE metalID = " + metalID);
		
		stmt.executeUpdate();
		return true;
	} catch (SQLException e) {
		new DatabaseException("could't update metal table");
		return false;
	}
  }

  @Override
  public boolean delete() {
	  try {
		  PreparedStatement stmt = conn.prepareStatement("DELETE FROM Metal WHERE metalID = " + metalID);  
		  stmt.execute();
		  return true;
	  } catch (SQLException e) {
			new DatabaseException("couldn't delete metal");
			return false;
	  }
  }
	
  private void insert() {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Metal(metalID, name, inhabits, atomicNumber, atomicMass, dissolvedBy) VALUES (" + metalID + ", " + name + ", " + inhabits + ", " + atomicNumber + ", " + atomicMass + ", " + dissolvedBy +");");
			stmt.execute();
		} catch(SQLException e) {
			new DatabaseException("could not insert into Metal table");
		}
	}
}