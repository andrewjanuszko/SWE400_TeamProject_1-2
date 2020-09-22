package datasource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Implementation of ChemicalRowDataGateway.
 * @author andrewjanuszko
 *
 */
public class ChemicalRowDataGatewayMock implements ChemicalRowDataGateway {

	/**
	 * 
	 */
	@Override
	public void createTableChemical() throws DatabaseException {
		String dropTableSQL = "DROP TABLE IF EXISTS Chemical, CompoundMadeFromElement;";
		String createTableSQL = "CREATE TABLE Chemical(" +
						   "chemicalID INT NOT NULL," +
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
		} catch(SQLException dbe) {
			throw new DatabaseException("Something went really wrong.", dbe);
		}
	}

	@Override
	public String getName(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInhabits(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAtomicNumber(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAtomicMass(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDissolvedBy(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSolute(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(int chemicalID, String name, String inhabits, int atomicNumber, double atomicMass,
			int dissolvedBy, int solute) {
		// TODO Auto-generated method stub
		
	}

}
