package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dataENUM.ChemicalEnum;


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
	
	/**
	 * Finder constructor for ChemicalRowDataGatewayRDS.
	 * @param chemicalID the ID of the chemical.
	 * @throws DatabaseException if chemical does not exist.
	 */
	public ChemicalRowDataGatewayRDS(int chemicalID) throws DatabaseException {
		String findChemicalSQL = "SELECT * FROM Chemical WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(findChemicalSQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery();
			result.next();
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
	 * Plain constructor for ChemicalRowDataGatewayRDS
	 * @param chemicalID the ID of the chemical.
	 * @param type the type of the chemical.
	 * @param name the name of the chemical.
	 * @param inhabits the habitat of the chemical.
	 * @param atomicNumber the atomic number of the chemical.
	 * @param atomicMass the atomic mass of the chemical.
	 * @param dissolvedBy the acid ID that dissolves a metal.
	 * @param solute the chemical ID for a base/solute.
	 * @throws DatabaseException when it fails to insert a new chemical.
	 */
	public ChemicalRowDataGatewayRDS(int chemicalID, int type, String name, String inhabits, int atomicNumber, double atomicMass,
			int dissolvedBy, int solute) throws DatabaseException {
		String insertChemicalSQL = "INSERT INTO Chemical (chemicalID, type, name, inhabits, atomicNumber, atomicMass, dissolvedBy, solute) " +
								   "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(insertChemicalSQL);
			statement.setInt(1, chemicalID);
			statement.setInt(2, type);
			statement.setString(3, name);
			statement.setString(4, inhabits);
			statement.setInt(5, atomicNumber);
			statement.setDouble(6, atomicMass);
			statement.setInt(7, dissolvedBy);
			statement.setInt(8, solute);
			statement.execute();
		} catch(SQLException e) {
			throw new DatabaseException("Failed to insert Chemical into database.", e);
		}
	}
	
	/**
	 * Create a table in the database.
	 * @throws DatabaseException if the connection to the database is lost.
	 */
	public void createTable() throws DatabaseException {
		String dropTableSQL = "DROP TABLE IF EXISTS Chemical, CompoundMadeFromElement;";
		String createTableSQL = "CREATE TABLE Chemical(" +
						   "chemicalID INT NOT NULL," +
						   "type INT NOT NULL, " +
						   "name VARCHAR(20)," +
						   "inhabits VARCHAR(20), " +
						   "atomicNumber INT, " +
						   "atomicMass DOUBLE, " +
						   "dissolvedBy INT, " +
						   "solute INT, " +
						   "PRIMARY KEY (chemicalID));";
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
			statement.executeUpdate(dropTableSQL);
			
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
			statement.executeUpdate(createTableSQL);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to drop/create table.", e);
		}
	}
	
	/**
	 * Delete this instance of a chemical from the database.
	 * @throws DatabaseException if the chemical fails to be deleted.
	 */
	@Override
	public void deleteChemical() throws DatabaseException {
		String deleteChemicalSQL = "DELETE FROM Chemical WHERE Chemical.chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(deleteChemicalSQL);
			statement.setInt(1, this.chemicalID);
			statement.execute();
		} catch(SQLException e) {
			throw new DatabaseException("Failed to delete Chemical with ID " + chemicalID + ".", e);
		}
	}
	
	/**
	 * Update this instance of a chemical from the database.
	 * @throws DatabaseException if the chemical fails to be updated.
	 */
	@Override
	public void updateChemical() throws DatabaseException {
		String updateChemicalSQL = "UPDATE Chemical SET type = ?, name = ?, inhabits = ?, atomicNumber = ?, atomicMass = ?, dissolvedBy = ?, solute = ? WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(updateChemicalSQL);
			statement.setInt(1, type);
			statement.setString(2, name);
			statement.setString(3, inhabits);
			statement.setInt(4, atomicNumber);
			statement.setDouble(5, atomicMass);
			statement.setInt(6, dissolvedBy);
			statement.setInt(7, solute);
			statement.setInt(8, chemicalID);
			statement.execute();
		} catch(SQLException e) {
			throw new DatabaseException("Failed to update Chemical with ID " + chemicalID + ".", e);
		}
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
	public void setDissolvedBy(int dissolvedBy) throws DatabaseException {
		ChemicalRowDataGatewayRDS possibleAcid = new ChemicalRowDataGatewayRDS(dissolvedBy);
		if (possibleAcid.getType() == ChemicalEnum.ACID.getChemicalType()) {
			this.dissolvedBy = dissolvedBy;
		} else {
			throw new DatabaseException("Chemical with ID " + chemicalID + " is not an Acid.");
		}
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#setSolute(int).
	 */
	@Override
	public void setSolute(int solute) {
		this.solute = solute;
	}
	
}
