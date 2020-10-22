package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datadto.AcidDTO;
/**
 * Row Data Gateway for Acid
 * @author ChayZe
 *
 */
public class AcidRowDataGatewayRDS implements AcidRowDataGateway{
  //TODO find out how to assign id.
  /**
   * Creates table in database.
   * @throws DatabaseException
   */
	public static void createTable() throws DatabaseException{
		String drop = "DROP TABLE IF EXISTS Acid";
		String create = "CREATE TABLE Acid (" + 
				"acidID INT NOT NULL, " + 
				"name VARCHAR(30) NOT NULL, " +                      
				"inventory Double, " +
				"solute INT, " +
				"UNIQUE(name), "
				+ "PRIMARY KEY(acidID) );";
	
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
			throw new DatabaseException("Unable to create Acid table", e);
		}
	}
	
	/**
	 * Only drop the table.
	 * @throws DatabaseException
	 */
	public static void dropTable() throws DatabaseException{
	  String drop = "DROP TABLE IF EXISTS Acid";
	  try {
	    //drop table
	    PreparedStatement stmt;
	    stmt = DatabaseManager.getSingleton().getConnection().prepareStatement(drop);
	    stmt.execute();
	    stmt.close();
	  }catch (SQLException e) {
      throw new DatabaseException("Unable to drop Acid table", e);
    }
	  
    
	}

	private Connection conn;
	
	private int acidID;
	private String name;
	private double inventory;
	private int solute;
	
	/**
	 * Gateway Constructor. Finds existing Acid from given ID.
	 * @param id
	 * @throws DatabaseException
	 */
	public AcidRowDataGatewayRDS(int id) throws DatabaseException {
		conn = DatabaseManager.getSingleton().getConnection();
		this.acidID = id;
		findByID(id);
	}
	
	/**
	 * Finds Acid in database from ID.
	 * @param id
	 * @throws DatabaseException
	 */
	private void findByID(int id) throws DatabaseException {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Acid WHERE acidID = " + id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			name = rs.getString("name");
			inventory = rs.getDouble("inventory");
			solute = rs.getInt("solute");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find Acid with that name", e);
		}
	}
	
	/**
	 * Gateway Constructor. Finds existing Acid from given name.
	 * @param name
	 * @throws DatabaseException
	 */
	public AcidRowDataGatewayRDS(String name) throws DatabaseException{
		conn = DatabaseManager.getSingleton().getConnection();
		this.name = name;
		findByName(name);
	}
	
	/**
	 * Finds Acid in database from name.
	 * @param name
	 * @throws DatabaseException
	 */
	private void findByName(String name) throws DatabaseException{
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Acid WHERE name = '" + name + "'");
			ResultSet rs = stmt.executeQuery();
			rs.next();
			acidID = rs.getInt("acidID");
			inventory = rs.getDouble("inventory");
			solute = rs.getInt("solute");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find Acid with that name", e);
		}
	}
	
	/**
	 * Constructs Gateway and new Acid in the database.
	 * @param id
	 * @param name
	 * @param inventory
	 * @param solute
	 * @throws DatabaseException 
	 */
	public AcidRowDataGatewayRDS(int id, String name, Double inventory, int solute) throws DatabaseException {
	  conn = DatabaseManager.getSingleton().getConnection();
		acidID = id;
		this.name = name;
		this.inventory = inventory;
		this.solute = solute;
		insert();
	}
	
	@Override
	public int getAcidID() {
		return this.acidID;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getInventory() {
		return this.inventory;
	}

	@Override
	public int getSolute() {
		return this.solute;
	}

	@Override
	public void setName(String n) {
	  this.name = n;
	}

	@Override
	public void setInventory(double i) {
	  this.inventory = i;
	}

	@Override
	public void setSolute(int s) {
		this.solute = s;
	}
	
	/**
	 * Updates the row in the database.
	 */
	public boolean persist() {
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE Acid SET"
					+ " name = '" + name
					+ "', inventory = '" + inventory
					+ "', solute = " + solute 
					+ " WHERE acidID = " + acidID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			new DatabaseException("could not update acid table");
			return false;
		}
	}
	
	/**
	 * Deletes both gateway and database row.
	 */
	public boolean delete() {
		try {
			PreparedStatement stmt1 = conn.prepareStatement("UPDATE Metal SET dissolvedBy = NULL WHERE dissolvedBy = " + acidID + ";");
			stmt1.execute();
			PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Acid WHERE acidID = " + acidID + ";");
			stmt2.execute();
			return true;
		} catch (SQLException e) {
			new DatabaseException("could not delete acid");
			return false;
		}
		
	}
	
	/**
	 * Inserts new row.
	 */
	private void insert() {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Acid (acidID, name, inventory, solute) VALUES (" + acidID + ", '" + name + "', '" + inventory + "', " + solute + ");");
			stmt.execute();
		} catch(SQLException e) {
			new DatabaseException("could not insert into acid table");
		}
	}
	
}


