package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * The RDS version of the gateway for Chemical.
 * @author andrewjanuszko
 */
public class ChemicalRowDataGatewayRDS implements ChemicalRowDataGateway {

	/**
	 * Drop the Chemical table if it already exists, then recreate it as an empty table.
	 * @throws DatabaseException when something goes really wrong.
	 */
	public static void createTable() throws DatabaseException {
		String dropTableSQL = "DROP TABLE IF EXISTS Chemical,CompoundMadeOfElement";
		String createTableSQL = "CREATE TABLE Chemical (" +
								"chemicalID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
								"chemicalType INT NOT NULL, " +
								"chemicalName VARCHAR(64) NOT NULL," +
								"inhabits VARCHAR(64) NOT NULL, " +
								"atomicNumber INT, " +
								"atomicMass DOUBLE, " +
								"dissolvedBy INT, " +
								"solute INT)";
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			statement.executeUpdate(dropTableSQL);
			statement.executeUpdate(createTableSQL);
			statement.close();
		} catch (SQLException e) {
		    throw new DatabaseException("Failed to create Chemical table.", e);
		}
	}
	
	private int chemicalID;
	private int type;
	private String name;
	private String inhabits;
	private int atomicNumber;
	private double atomicMass;
	private int dissolvedBy;
	private int solute;
	
	/**
	 * Constructor that finds chemicals by ID.
	 * @param chemicalID the ID of the chemical we are working with.
	 * @throws DatabaseException if the chemical is not in the database.
	 */
	public ChemicalRowDataGatewayRDS(int chemicalID) throws DatabaseException {
		this.chemicalID = chemicalID;
		String selectSQL = "SELECT * FROM Chemical WHERE chemicalID = ?";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery();
			this.type = result.getInt("type");
			this.name = result.getString("name");
			this.inhabits = result.getString("inhabits");
			this.atomicNumber = result.getInt("atomicNumber");
			this.atomicMass = result.getDouble("atomicMass");
			this.dissolvedBy = result.getInt("dissolvedBy");
			this.solute = result.getInt("solute");
		} catch(SQLException e) {	
			throw new DatabaseException("Could not find chemical with ID " + chemicalID, e);
		}
	}
	
	/**
	 * 
	 * @param type
	 * @param name
	 * @param inhabits
	 * @param atomicNumber
	 * @param atomicMass
	 * @param dissolvedBy
	 * @param solute
	 * @throws DatabaseException
	 */
	public ChemicalRowDataGatewayRDS(int type, String name, String inhabits, int atomicNumber, double atomicMass, int dissolvedBy, int solute) throws DatabaseException {
		
		String insertSQL = "INSERT INTO CHEMICAL SET type = ?, name = ?, inhabits = ?, atomicNumber = ?, atomicMass = ?, dissolvedBy = ?, solute = ?";
		
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, type);
			statement.setString(2, name);
			statement.setString(3, inhabits);
			statement.setInt(4, atomicNumber);
			statement.setDouble(5, atomicMass);
			statement.setInt(6, dissolvedBy);
			statement.setInt(7, solute);
			statement.executeUpdate();
			
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				chemicalID = result.getInt(1);
				this.type = type;
				this.name = name;
				this.inhabits = inhabits;
				this.atomicNumber = atomicNumber;
				this.atomicMass = atomicMass;
				this.dissolvedBy = dissolvedBy;
				this.solute = solute;
			} else {
				throw new DatabaseException("Generated key for Chemical not found.");
			}
		} catch(SQLException e) {
			throw new DatabaseException("Failed to create record for Chemical with ID " + chemicalID, e);
		}
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getType().
	 */
	@Override
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @see datasource.ChemicalRowDataGateway#getName().
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getHabitat().
	 */
	@Override
	public void setHabitat(String inhabits) {
		this.inhabits = inhabits;
	}

	/**
	 * @see datasource.ChemicalRowDataGateway#getAtomicNumber().
	 */
	@Override
	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	/**
	 * @see datasource.ChemicalRowDataGateway#getAtomicMass().
	 */
	@Override
	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	/**
	 * @see datasource.ChemicalRowDataGateway#getDissolvedBy().
	 */
	@Override
	public void setDissolvedBy(int acidID) {
		this.dissolvedBy = acidID;
	}

	/**
	 * @see datasource.ChemicalRowDataGateway#getSolute().
	 */
	@Override
	public void setSolute(int chemicalID) {
		this.solute = chemicalID;
	}
	
	/**
	 * Get the ID.
	 * @return the ID.
	 */
	@Override
	public int getChemicalID() {
		return this.chemicalID;
	}

	/**
	 * Get the type.
	 * @return the type.
	 */
	@Override
	public int getType() {
		return this.type;
	}

	/**
	 * Get the name.
	 * @return the name.
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Get the habitat.
	 * @return the habitat.
	 */
	@Override
	public String getHabitat() {
		return this.inhabits;
	}

	/**
	 * Get the atomic number.
	 * @return the atomic number.
	 */
	@Override
	public int getAtomicNumber() {
		return this.atomicNumber;
	}
	
	/**
	 * Get the atomic mass.
	 * @return the atomic mass.
	 */
	@Override
	public double getAtomicMass() {
		return this.atomicMass;
	}

	/**
	 * Get the acid ID.
	 * @return the acid ID.
	 */
	@Override
	public int getDissolvedBy() {
		return this.dissolvedBy;
	}

	/**
	 * Get the solute ID.
	 * @return the solute ID.
	 */
	@Override
	public int getSolute() {
		return this.solute;
	}

	/**
	 * 
	 * @throws DatabaseException
	 */
	@Override
	public void persistData() throws DatabaseException {
		String updateSQL = "UPDATE CHEMICAL SET type = ?, name = ?, inhabits = ?, atomicNumber = ?, atomicMass = ?, dissolvedBy = ?, solute = ? WHERE chemicaID = ?";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(updateSQL);
			statement.setInt(1, type);
			statement.setString(2, name);
			statement.setString(3, inhabits);
			statement.setInt(4, atomicNumber);
			statement.setDouble(5, atomicMass);
			statement.setInt(6, dissolvedBy);
			statement.setInt(7, solute);
			statement.setInt(8, chemicalID);
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DatabaseException("Could not persist data for Chemical with ID " + chemicalID, e);
		}
	}

	@Override
	public void resetData() {
		// EMPTY
	}

	/**
	 * 
	 * @throws DatabaseException
	 */
	@Override
	public void deleteInstance() throws DatabaseException {
		String deleteSQL = "DELETE FROM Chemical WHERE Chemical.chemicalID = ?";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(deleteSQL);
			statement.setInt(1, chemicalID);
			statement.execute();
		} catch(SQLException e) {
			throw new DatabaseException("Could not delete Chemical with ID " + chemicalID, e);
		}
	}

}
