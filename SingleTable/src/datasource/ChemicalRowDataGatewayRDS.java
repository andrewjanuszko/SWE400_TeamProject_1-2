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
	 * 
	 */
	@Override
	public void createTableChemical() throws DatabaseException {
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
		} catch(SQLException sqle) {
			throw new DatabaseException("Something went really wrong.", sqle);
		}
	}
	
	@Override
	public int getType(int chemicalID) throws DatabaseException {
		int type = 0;
		String getTypeSQL = "SELECT type FROM Chemical WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(getTypeSQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery(getTypeSQL);
			result.next();
			type = result.getInt("type");
		} catch(SQLException sqle) {
			throw new DatabaseException("Could not find chemical with ID: " + chemicalID, sqle);
		}
		return type;
	}

	@Override
	public String getName(int chemicalID) throws DatabaseException {
		String chemicalName = "";
		String getNameSQL = "SELECT name FROM Chemical WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(getNameSQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery(getNameSQL);
			result.next();
			chemicalName = result.getString("name");
		} catch(SQLException sqle) {
			throw new DatabaseException("Could not find chemical with ID: " + chemicalID, sqle);
		}
		return chemicalName;
	}

	@Override
	public String getInhabits(int chemicalID) throws DatabaseException {
		String chemicalInhabits = "";
		String getInhabitsSQL = "SELECT inhabits FROM Chemical WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(getInhabitsSQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery(getInhabitsSQL);
			result.next();
			chemicalInhabits = result.getString("inhabits");
		} catch(SQLException sqle) {
			throw new DatabaseException("Could not find chemical with ID: " + chemicalID, sqle);
		}
		return chemicalInhabits;
	}

	@Override
	public int getAtomicNumber(int chemicalID) throws DatabaseException {
		int atomicNumber = 0;
		String getAtomicNumberSQL = "SELECT atomicNumber FROM Chemical WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(getAtomicNumberSQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery(getAtomicNumberSQL);
			result.next();
			atomicNumber = result.getInt("atomicNumber");
		} catch(SQLException sqle) {
			throw new DatabaseException("Could not find chemical with ID: " + chemicalID, sqle);
		}
		return atomicNumber;
	}

	@Override
	public double getAtomicMass(int chemicalID) throws DatabaseException {
		double atomicMass = 0.0;
		String getAtomicMassSQL = "SELECT atomicMass FROM Chemical WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(getAtomicMassSQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery(getAtomicMassSQL);
			result.next();
			atomicMass = result.getDouble("atomicMass");
		} catch(SQLException sqle) {
			throw new DatabaseException("Could not find chemical with ID: " + chemicalID, sqle);
		}
		return atomicMass;
	}

	@Override
	public int getDissolvedBy(int chemicalID) throws DatabaseException {
		int dissolvedBy = 0;
		String getDissolvedBySQL = "SELECT dissolvedBy FROM Chemical WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(getDissolvedBySQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery(getDissolvedBySQL);
			result.next();
			dissolvedBy = result.getInt("dissolvedBy");
		} catch(SQLException sqle) {
			throw new DatabaseException("Could not find chemical with ID: " + chemicalID, sqle);
		}
		return dissolvedBy;
	}

	@Override
	public int getSolute(int chemicalID) throws DatabaseException {
		int solute = 0;
		String getSoluteSQL = "SELECT solute FROM Chemical WHERE chemicalID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(getSoluteSQL);
			statement.setInt(1, chemicalID);
			ResultSet result = statement.executeQuery(getSoluteSQL);
			result.next();
			solute = result.getInt("solute");
		} catch(SQLException sqle) {
			throw new DatabaseException("Could not find chemical with ID: " + chemicalID, sqle);
		}
		return solute;
	}

	@Override
	public void insert(int chemicalID, String name, String inhabits, int atomicNumber, double atomicMass,
			int dissolvedBy, int solute) throws DatabaseException {
		String insertSQL = "INSERT INTO Chemical (chemicaID, name, inhabits, atomicNumber, atomicMass, dissolvedBy, solute)" +
						   "VALUES (?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(insertSQL);
			statement.setInt(1, chemicalID);
			statement.setString(2, name);
			statement.setString(3, inhabits);
			statement.setInt(4, atomicNumber);
			statement.setDouble(5, atomicMass);
			statement.setInt(6, dissolvedBy);
			statement.setInt(7, solute);
			statement.execute();
		} catch(SQLException sqle) {
			throw new DatabaseException("Could not find chemical with ID: " + chemicalID, sqle);
		}
	}
}
