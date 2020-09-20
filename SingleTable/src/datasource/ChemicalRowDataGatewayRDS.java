package datasource;

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
								"chemicalID LONG NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
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
	
	private long ID;
	private int type;
	private String name;
	private String inhabits;
	private int atomicNumber;
	private double atomicMass;
	private long dissolvedBy;
	private long solute;
	
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
	public void setDissolvedBy(long acidID) {
		this.dissolvedBy = acidID;
	}

	/**
	 * @see datasource.ChemicalRowDataGateway#getSolute().
	 */
	@Override
	public void setSolute(long chemicalID) {
		this.solute = chemicalID;
	}
	
	/**
	 * Get the ID.
	 * @return the ID.
	 */
	@Override
	public long getID() {
		return this.ID;
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
	public long getDissolvedBy() {
		return this.dissolvedBy;
	}

	/**
	 * Get the solute ID.
	 * @return the solute ID.
	 */
	@Override
	public long getSolute() {
		return this.solute;
	}

}
