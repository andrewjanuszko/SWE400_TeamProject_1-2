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
	 * 
	 * @param chemicalID
	 * @throws DatabaseException
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
	
	@Override
	public void deleteChemical() throws DatabaseException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateChemical() throws DatabaseException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public int getType() {
		return this.type;
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
	public int getSolute() {
		return this.solute;
	}
	
	@Override
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
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
	public void setDissolvedBy(int dissolvedBy) throws DatabaseException {
		ChemicalRowDataGatewayRDS acid = new ChemicalRowDataGatewayRDS(dissolvedBy);
		if (acid.getType() == ChemicalEnum.ACID.getChemicalType()) {
			this.dissolvedBy = dissolvedBy;
		} else {
			throw new DatabaseException("Chemical with ID " + chemicalID + " is not an Acid.");
		}
	}
	
	@Override
	public void setSolute(int solute) {
		this.solute = solute;
	}
	
}
