package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * Row Data Gateway for Acid
 * @author ChayZe
 *
 */
public class AcidRowDataGatewayRDS implements AcidRowDataGateway{
  /**
   * Creates table in database.
   * @throws DatabaseException
   */
	public static void createTable() throws DatabaseException{
		String drop = "DROP TABLE IF EXISTS Acid";
		String create = "CREATE TABLE Acid (" + 
				"acidID INT NOT NULL, " + 
				"name VARCHAR(30) NOT NULL, " +                      
				"inhabits VARCHAR(30), " +
				"solute VARCHAR(30), " +
				"UNIQUE(name), "
				+ "PRIMARY KEY(acidID) );";
	
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
			throw new DatabaseException("Unable to create Acid table", e);
		}
	}
	

	private Connection conn;
	
	private int acidID;
	private String name;
	private String inhabits;
	private String solute;
	
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
			inhabits = rs.getString("inhabits");
			solute = rs.getString("solute");
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
			inhabits = rs.getString("inhabits");
			solute = rs.getString("solute");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find Acid with that name", e);
		}
		
		try {
			PreparedStatement stmt = conn.prepareStatement("");
		} catch(SQLException e) {
			
		}
	}
	
	/**
	 * Constructs Gateway and new Acid in the database.
	 * @param id
	 * @param name
	 * @param inhabits
	 * @param solute
	 * @throws DatabaseException 
	 */
	public AcidRowDataGatewayRDS(int id, String name, String inhabits, String solute) throws DatabaseException {
	  conn = DatabaseManager.getSingleton().getConnection();
		acidID = id;
		this.name = name;
		this.inhabits = inhabits;
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
	public String getInhabits() {
		return this.inhabits;
	}

	@Override
	public String getSolute() {
		return this.solute;
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
	public void setSolute(String s) {
		this.solute = s;
	}
	
	/**
	 * Updates the row in the database.
	 */
	public boolean persist() {
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE Acid SET"
					+ " name = '" + name
					+ "', inhabits = '" + inhabits
					+ "', solute = '" + solute 
					+ "' WHERE acidID = " + acidID);
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
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Acid (acidID, name, inhabits, solute) VALUES (" + acidID + ", '" + name + "', '" + inhabits + "', '" + solute + "');");
			stmt.execute();
		} catch(SQLException e) {
			new DatabaseException("could not insert into acid table");
		}
	}
}
