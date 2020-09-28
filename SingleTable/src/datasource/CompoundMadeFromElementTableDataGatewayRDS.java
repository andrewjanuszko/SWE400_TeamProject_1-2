package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dataDTO.CompoundMadeFromElementDTO;

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
	public static synchronized CompoundMadeFromElementTableDataGateway getSingletonInstance() {
		if (singletonInstance == null) {
			singletonInstance = new CompoundMadeFromElementTableDataGatewayRDS();
		}
		return singletonInstance;
	}
	
	private CompoundMadeFromElementTableDataGatewayRDS() {
		try {
			createTable();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Drop the CompoundMadeFromElement table if it already exists, then recreate it as an empty table.
	 * @throws DatabaseException when something goes really wrong.
	 */
	private void createTable() throws DatabaseException {
		String dropTableSQL = "DROP TABLE IF EXISTS CompoundMadeFromElement";
		String createTableSQL = "CREATE TABLE CompoundMadeFromElement (" +
								"compoundID INTEGER NOT NULL, " +
								"elementID INTEGER NOT NULL, " +
								"FOREIGN KEY (compoundID) REFERENCES Chemical(chemicalID), " +
								"FOREIGN KEY (elementID) REFERENCES Chemical(chemicalID));";
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
	 * @throws DatabaseException when insertion fails.
	 */
	public void createRow(int compoundID, int elementID) throws DatabaseException {
		String insertSQL = "INSERT INTO CompoundMadeFromElement SET compoundID = ?, elementID = ?;";
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(insertSQL);
			statement.setInt(1, compoundID);
			statement.setInt(2, elementID);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseException("Failed to create row in CompoundMadeFromElement table.", e);
		}
	}
	
	/**
	 * @see datasource.CompoundMadeFromElementTableDataGateway#updateRow(long, long).
	 */
	@Override
	public void updateRow(int compoundID, int elementID) throws DatabaseException {
		String updateSQL = "UPDATE CompoundMadeFromElement SET compoundID = ?, elementID = ?;";		
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(updateSQL);
			statement.setInt(1, compoundID);
			statement.setInt(2, elementID);
			int count = statement.executeUpdate();
			
			if (count == 0) {
				this.createRow(compoundID, elementID);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Failed to update row in CompoundMadeFromElement table.", e);
		}
	}
	
	/**
	 * @see datasource.CompoundMadeFromElementTableDataGateway#findElementsByCompoundID(int).
	 */
	@Override
	public ArrayList<CompoundMadeFromElementDTO> findElementsByCompoundID(int compoundID) throws DatabaseException {
		ArrayList<CompoundMadeFromElementDTO> resultSet = new ArrayList<CompoundMadeFromElementDTO>();
		String selectSQL = "SELECT * FROM CompoundMadeFromElement WHERE compoundID = ?;";	
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, compoundID);
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				resultSet.add(new CompoundMadeFromElementDTO(r.getInt("compoundID"), r.getInt("elementID")));
			}
			
		} catch (SQLException e) {
			throw new DatabaseException("Compound with compoundID = " + compoundID + " cannot be found.", e);
		}
		return resultSet;
	}

	/**
	 * @see datasource.CompoundMadeFromElementTableDataGateway#findCompoundsByElementID(int).
	 */
	@Override
	public ArrayList<CompoundMadeFromElementDTO> findCompoundsByElementID(int elementID) throws DatabaseException {
		ArrayList<CompoundMadeFromElementDTO> resultSet = new ArrayList<CompoundMadeFromElementDTO>();
		String selectSQL = "SELECT * FROM CompoundMadeFromElement WHERE elementID = ?;";	
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, elementID);
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				resultSet.add(new CompoundMadeFromElementDTO(r.getInt("compoundID"), r.getInt("elementID")));
			}
			
		} catch (SQLException e) {
			throw new DatabaseException("Compound made with elementID = " + elementID + " cannot be found.", e);
		}
		return resultSet;
	}
	
	/**
	 * this is for testing only.
	 * @throws DatabaseException
	 */
	@Override
	public void resetData() throws DatabaseException {
		singletonInstance = null;
	}
		
}

