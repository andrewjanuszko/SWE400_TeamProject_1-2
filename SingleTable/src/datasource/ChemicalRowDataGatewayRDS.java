package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


/**
 * The RDS version of the gateway for Chemical.
 * @author andrewjanuszko
 */
public class ChemicalRowDataGatewayRDS implements ChemicalRowDataGateway {
	
	private int chemicalID;
	private int type;
	private String name;
	private String inhabits;
	private int atomicNumber;
	private double atomicMass;
	private int dissolvedBy;
	private int solute;

	private static int key = 1;
	
	/**
	 * Constructor for creating the table in the database.
	 * @throws DatabaseException when things go wrong.
	 */
	public ChemicalRowDataGatewayRDS() throws DatabaseException{
		createTable();
	}
	
	/**
	 * Constructor for finding Chemicals by ID.
	 * @param chemicalID the ID of the Chemical.
	 * @throws DatabaseException when things go wrong.
	 */
	public ChemicalRowDataGatewayRDS(int chemicalID) throws DatabaseException {
		String findChemicalSQL = "SELECT * FROM Chemical WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(findChemicalSQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery();
			result.next();
			this.chemicalID = result.getInt("chemicalID");
			this.type = result.getInt("type");
			this.name = result.getString("name");
			this.inhabits = result.getString("inhabits");
			this.atomicNumber = result.getInt("atomicNumber");
			this.atomicMass = result.getDouble("atomicMass");
			this.dissolvedBy = result.getInt("dissolvedBy");
			this.solute = result.getInt("solute");
		} catch(SQLException e) {
			throw new DatabaseException("Could not find Chemical with ID " + chemicalID + ".", e);
		}
	}
	
	/**
	 * Insert new chemical entry into the database.
	 * @param type the type of the Chemical
	 * @param name the name of the Chemical
	 * @param inhabits the habitat of the Chemical
	 * @param atomicNumber the atomic number of the Chemical
	 * @param atomicMass the atomic mass of the Chemical
	 * @param dissolvedBy the Acid that dissolves the Chemical
	 * @param solute the Acid or Base that the Chemical dissolves in.
	 * @throws DatabaseException when things go wrong.
	 */
	public ChemicalRowDataGatewayRDS(int type, String name, String inhabits, int atomicNumber, double atomicMass, int dissolvedBy, int solute) throws DatabaseException {
		String insertChemicalSQL = "INSERT INTO Chemical SET chemicalID = ?, type = ?, name = ?, inhabits = ?, atomicNumber = ?, atomicMass = ?, dissolvedBy = ?, solute = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(insertChemicalSQL);
			statement.setInt(1, key);
			statement.setInt(2, type);
			statement.setString(3, name);
			
			if (inhabits.isEmpty()) {
				statement.setNull(5, Types.VARCHAR);
			} else {
				statement.setString(4, inhabits);
			}
			
			if (atomicNumber == -1) {  
				statement.setNull(5, Types.INTEGER);
			} else {
				statement.setInt(5, atomicNumber);
			}
			
			if (atomicMass == -1.0) {  
				statement.setNull(6, Types.DOUBLE);
			} else {
				statement.setDouble(6, atomicMass);
			}
			
			if (dissolvedBy == -1) {  
				statement.setNull(7, Types.INTEGER);
			} else {
				statement.setInt(7, dissolvedBy);
			}
			
			if (solute == -1) {  
				statement.setNull(8, Types.INTEGER);
			} else {
				statement.setInt(8, solute);
			}
			
			statement.execute();
			key = key + 1;
		} catch(SQLException e) {
			throw new DatabaseException("Failed to insert Chemical into database.", e);
		}
	}
	
	/**
	 * Creates a table in the database.
	 * Only called by ChemicalRowDataGatewayRDS(int).
	 * @throws DatabaseException when things go wrong.
	 */
	private void createTable() throws DatabaseException {
		String dropTableSQL = "DROP TABLE IF EXISTS Chemical, CompoundMadeFromElement;";
		String createTableSQL = "CREATE TABLE Chemical(" +
						   "chemicalID INTEGER NOT NULL," +
						   "type INTEGER NOT NULL, " +
						   "name VARCHAR(20) NOT NULL UNIQUE," +
						   "inhabits VARCHAR(20), " +
						   "atomicNumber INTEGER, " +
						   "atomicMass DOUBLE, " +
						   "dissolvedBy INTEGER, " +
						   "solute INTEGER, " +
						   "PRIMARY KEY (chemicalID));";
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
			statement.executeUpdate(dropTableSQL);
			
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
			statement.executeUpdate(createTableSQL);
			key = 1;
		} catch(SQLException e) {
			throw new DatabaseException("Failed to drop/create table. ", e);
		}
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#delete(void).
	 */
	@Override
	public void delete() throws DatabaseException {
		try {
			String deleteChemicalSQL = "DELETE FROM Chemical WHERE Chemical.chemicalID = ?;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(deleteChemicalSQL);
			statement.setInt(1, this.chemicalID);
			statement.execute();
		} catch(SQLException e) {
			throw new DatabaseException("Failed to delete Chemical with ID " + chemicalID + ".", e);
		}
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#update(void).
	 */
	@Override
	public void update() throws DatabaseException {
		String updateChemicalSQL = "UPDATE Chemical SET type = ?, name = ?, inhabits = ?, atomicNumber = ?, atomicMass = ?, dissolvedBy = ?, solute = ? WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(updateChemicalSQL);
			statement.setInt(1, this.type);
			statement.setString(2, this.name);
			statement.setString(3, this.inhabits);
			statement.setInt(4, this.atomicNumber);
			statement.setDouble(5, this.atomicMass);
			statement.setInt(6, this.dissolvedBy);
			statement.setInt(7, this.solute);
			statement.setInt(8, this.chemicalID);
			statement.execute();
		} catch(SQLException e) {
			throw new DatabaseException("Failed to update Chemical with ID " + chemicalID + ".", e);
		}
	}
	
	/**
	 * Returns the ID of a chemical.
	 * @return the ID.
	 */
	@Override
	public int getChemicalID() {
		return this.chemicalID;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getType().
	 */
	@Override
	public int getType() {
		return this.type;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getName().
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getInhbaits().
	 */
	@Override
	public String getInhabits() {
		return this.inhabits;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getAtomicNumber().
	 */
	@Override
	public int getAtomicNumber() {
		return this.atomicNumber;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getAtomicMass().
	 */
	@Override
	public double getAtomicMass() {
		return this.atomicMass;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getDissolvedBy().
	 */
	@Override
	public int getDissolvedBy() {
		return this.dissolvedBy;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getSolute().
	 */
	@Override
	public int getSolute() {
		return this.solute;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#setType(int).
	 */
	@Override
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#setName(String).
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#setInhabits(String).
	 */
	@Override
	public void setInhabits(String inhabits) {
		this.inhabits = inhabits;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#setAtomicNumber(int).
	 */
	@Override
	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#setAtomicMass(double).
	 */
	@Override
	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#setDissolvedBy(int).
	 */
	@Override
	public void setDissolvedBy(int dissolvedBy) {
		this.dissolvedBy = dissolvedBy;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#setSolute(int).
	 */
	@Override
	public void setSolute(int solute) {
		this.solute = solute;
	}
	
}