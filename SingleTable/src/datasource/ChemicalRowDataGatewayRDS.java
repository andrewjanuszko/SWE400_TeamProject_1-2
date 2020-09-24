package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public ChemicalRowDataGatewayRDS(int chemicalID, int type, String name, String inhabits) {
		if (type == ChemicalEnum.CHEMICAL.getChemicalType()) {
			//this(chemicalID, type, name, inhabits, -1, 0.0, -1, -1);
		} else {
			// throw new DatabaseException("Failed to insert due to invalid type. Looking for Chemical type (0) but recieved " + type + ".");
		}
	}
	
	public ChemicalRowDataGatewayRDS createElement(int chemicalID, int type, String name, String inhabits, int atomicNumber, double atomicMass) throws DatabaseException {
		if (type != ChemicalEnum.ELEMENT.getChemicalType()) {
			throw new DatabaseException("Failed to insert due to invalid type. Looking for Chemical type (1) but recieved " + type + ".");
		}
		ChemicalRowDataGatewayRDS element = new ChemicalRowDataGatewayRDS(chemicalID, type, name, inhabits, atomicNumber, atomicMass, -1, -1);
		return element;
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
	private ChemicalRowDataGatewayRDS(int chemicalID, int type, String name, String inhabits, int atomicNumber, double atomicMass,
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
	 * 
	 */
	@Override
	public void delete() throws DatabaseException {
		String deleteChemicalSQL = "DELETE FROM Chemical WHERE Chemical.chemicalID = ?;";
		try {
			if (this.type == ChemicalEnum.ACID.getChemicalType()) {
				ArrayList<Integer> results = findByDissolvedBy(chemicalID);
				if (!results.isEmpty()) {
					for (Integer metalID : results) {
						ChemicalRowDataGatewayRDS metal = new ChemicalRowDataGatewayRDS(metalID);
						metal.setDissolvedBy(0);
						metal.update();
					}
				}
			}
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(deleteChemicalSQL);
			statement.setInt(1, this.chemicalID);
			statement.execute();
		} catch(SQLException e) {
			throw new DatabaseException("Failed to delete Chemical with ID " + chemicalID + ".", e);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void update() throws DatabaseException {
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
	
	/**
	 * 
	 */
	@Override
	public ArrayList<Integer> findByType(int type) throws DatabaseException {
		ArrayList<Integer> resultSet = new ArrayList<Integer>();
		String selectSQL = "";
		if (type == ChemicalEnum.ELEMENT.getChemicalType()) {
			selectSQL = "SELECT chemicalID FROM Chemical WHERE Chemical.type = ? or Chemical.type = 2";
		}
		if (type == ChemicalEnum.CHEMICAL.getChemicalType()) {
			selectSQL = "SELECT chemicalID FROM Chemical";
		} else {
			selectSQL = "SELECT chemicalID FROM Chemical WHERE Chemical.type = ?";
		}
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, type);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				resultSet.add(results.getInt("chemicalID"));
			}
		} catch(SQLException e) {
			throw new DatabaseException("Chemicals of type " + type + " cannot be found.", e);
		}
		return resultSet;
	}
	
	/**
	 * 
	 */
	@Override
	public void findByName(String name) throws DatabaseException {
		String findSQL = "SELECT * FROM Chemical WHERE Chemical.name = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(findSQL);
			statement.setString(1, name);
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
			throw new DatabaseException("No chemical with name " + name + " could be found.", e);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public ArrayList<Integer> findByHabitat(String inhabits) throws DatabaseException {
		ArrayList<Integer> resultSet = new ArrayList<Integer>();
		String selectSQL = "SELECT chemicalID FROM Chemical WHERE Chemical.inhabits = ?";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setString(1, inhabits);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				resultSet.add(results.getInt("chemicalID"));
			}
		} catch(SQLException e) {
			throw new DatabaseException("Chemicals of habitat " + inhabits + " cannot be found.", e);
		}
		return resultSet;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getByAtomicNumber(int).
	 */
	@Override
	public void findByAtomicNumber(int atomicNumber) throws DatabaseException {
		String findSQL = "SELECT * FROM Chemical WHERE Chemical.atomicNumber = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(findSQL);
			statement.setInt(1, atomicNumber);
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
			throw new DatabaseException("No chemical with atomic number " + atomicNumber + " could be found.", e);
		}
	}

	/**
	 * @see datasource.ChemicalRowDataGateway#getByAtomicMass(double).
	 */
	@Override
	public void findByAtomicMass(double atomicMass) throws DatabaseException {
		String findSQL = "SELECT * FROM Chemical WHERE Chemical.atomicMass = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(findSQL);
			statement.setDouble(1, atomicMass);
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
			throw new DatabaseException("No chemical with atomic mass " + atomicMass + " could be found.", e);
		}
	}

	/**
	 * 
	 */
	@Override
	public ArrayList<Integer> findByDissolvedBy(int dissolvedBy) throws DatabaseException {
		ArrayList<Integer> resultSet = new ArrayList<Integer>();
		String selectSQL = "SELECT chemicalID FROM Chemical WHERE Chemical.dissolvedBy = ?";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, dissolvedBy);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				resultSet.add(results.getInt("chemicalID"));
			}
		} catch(SQLException e) {
			throw new DatabaseException("Chemicals of dissolvedBy " + dissolvedBy + " cannot be found.", e);
		}
		return resultSet;
	} 
	
	/**
	 * 
	 */
	@Override
	public ArrayList<Integer> findBySolute(int solute) throws DatabaseException {
		ArrayList<Integer> resultSet = new ArrayList<Integer>();
		String selectSQL = "SELECT chemicalID FROM Chemical WHERE Chemical.solute = ?";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, solute);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				resultSet.add(results.getInt("chemicalID"));
			}
		} catch(SQLException e) {
			throw new DatabaseException("Chemicals of solute " + solute + " cannot be found.", e);
		}
		return resultSet;
	}
}
