package datasource;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * The RDS version of the gateway for CompoundMadeFromElement.
 * @author andrewjanuszko
 */
public class CompoundMadeFromElementTableDataGatewayRDS implements CompoundMadeFromElementTableDataGateway {
	
	private static CompoundMadeFromElementTableDataGateway singletonInstance;
	
	/**
	 * Get the singleton instance of the RDS gateway.
	 * @return the singleton instance.
	 */
	public static synchronized CompoundMadeFromElementTableDataGateway getSingleton() {
		if (singletonInstance.equals(null)) { //Possible error point (.equals vs ==)
			singletonInstance = new CompoundMadeFromElementTableDataGatewayRDS();
		}
		return singletonInstance;
	}

	/**
	 * Drop the CompoundMadeFromElement table if it already exists, then recreate it as an empty table.
	 * @throws DatabaseException when something goes really wrong.
	 */
	@Override
	public void createTable() throws DatabaseException {
		String dropTableSQL = "DROP TABLE IF EXISTS CompoundMadeFromElement";
		String createTableSQL = "CREATE TABLE CompoundMadeFromElement (" +
								"compoundID LONG NOT NULL, " +
								"elementID LONG NOT NULL)";
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			statement.executeUpdate(dropTableSQL);
			statement.executeUpdate(createTableSQL);
			statement.close();
		} catch (SQLException e) {
		    throw new DatabaseException("Failed to create CompoundMadeFromElement table.", e);
		}
		
	}

	@Override
	public void createRow(long compoundID, long elementID) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRelation(long compoundID, long elementID) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetData() {
		// TODO Auto-generated method stub
		
	}

}
