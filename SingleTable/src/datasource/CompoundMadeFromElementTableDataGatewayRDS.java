package datasource;

import java.sql.PreparedStatement;
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

	/**
	 * Creates a new row in the CompoundMadeFromElement table.
	 * @param compoundID the id of the compound.
	 * @param elementID the id of the element.
	 * 
	 * @throws DatabaseException when insertion fails.
	 */
	@Override
	public void createRow(long compoundID, long elementID) throws DatabaseException {
		
		String insertSQL = "INSERT INTO CompoundMadeFromElement SET compoundID = ?, elementID = ?";
		
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(insertSQL);
			statement.setLong(1, compoundID);
			statement.setLong(2, elementID);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseException("Failed to create row in CompoundMadeFromElement table.", e);
		}
	}
	
	/**
	 * @see datasource.CompoundMadeFromElementTableDataGateway#updateRow(long, long).
	 */
	@Override
	public void updateRow(long compoundID, long elementID) throws DatabaseException {
		
		String updateSQL = "UPDATE CompoundMadeFromElement SET compoundID = ?, elementID = ?";		
		
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(updateSQL);
			statement.setLong(1, compoundID);
			statement.setLong(2, elementID);
			int count = statement.executeUpdate();
			
			if (count == 0) {
				this.createRow(compoundID, elementID);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Failed to update row in CompoundMadeFromElement table.", e);
		}
	}

	@Override
	public void resetData() {
		// I want to go to bed.
		
	}

}
