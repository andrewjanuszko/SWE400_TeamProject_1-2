package datasource;

import java.sql.Connection;
import java.sql.SQLException;

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
		// Connection connection = DatabaseManager.getSingleton().getConnection();
		
		try {
			
		} catch (SQLException e) {
			// throw new DatabaseException("Failed to create Chemical table.", e);
		}
	}
	
	private long chemicalID;
	private int chemicalType;
	private String chemicalName;
	private String inhabits;
	private int atomicNumber;
	private double atomicMass;
	private long dissolvedBy;
	private long solute;
	
	/**
	 * @see datasource.ChemicalRowDataGateway#getType()
	 */
	@Override
	public void setType(int type) {
		this.chemicalType = type;
	}

	@Override
	public void setName(String name) {
		this.chemicalName = name;
	}

	@Override
	public void setInhabits(String inhabits) {
		this.inhabits = inhabits;
	}

	@Override
	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	@Override
	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	@Override
	public void setDissolvedBy(long acidID) {
		this.dissolvedBy = acidID;
	}

	@Override
	public void setSolute(long chemicalID) {
		this.solute = chemicalID;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHabitat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAtomicNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAtomicMass() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getDissolvedBy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSolute() {
		// TODO Auto-generated method stub
		return 0;
	}

}
