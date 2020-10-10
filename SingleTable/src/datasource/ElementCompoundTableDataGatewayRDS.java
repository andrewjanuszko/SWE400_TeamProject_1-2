package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dataDTO.CompoundDTO;
import dataDTO.ElementDTO;

/**
 * The RDS version of the gateway for CompoundMadeFromElement.
 * @author andrewjanuszko
 */
public class ElementCompoundTableDataGatewayRDS implements ElementCompoundTableDataGateway {
	
	private static ElementCompoundTableDataGateway singletonInstance;
	
	/**
	 * Get the singleton instance of the RDS gateway.
	 * @return the singleton instance.
	 */
	public static synchronized ElementCompoundTableDataGateway getSingletonInstance() {
		if (singletonInstance == null) {
			singletonInstance = new ElementCompoundTableDataGatewayRDS();
		}
		return singletonInstance;
	}
	
	private ElementCompoundTableDataGatewayRDS() {
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
	public void updateRow(int oldCompoundID, int oldElementID, int newCompoundID, int newElementID) throws DatabaseException {
		String updateSQL = "UPDATE CompoundMadeFromElement SET compoundID = ?, elementID = ? WHERE compoundID = ? and elementID = ?;";		
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(updateSQL);
			statement.setInt(1, newCompoundID);
			statement.setInt(2, newElementID);
			statement.setInt(3, oldCompoundID);
			statement.setInt(4, oldElementID);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseException("Failed to update row in CompoundMadeFromElement table.", e);
		}
	}
	
	/**
	 * converts a query to a compoundDTO.
	 * @param statement
	 * @return
	 * @throws DatabaseException
	 */
	private CompoundDTO convertToCDTO(PreparedStatement statement) throws DatabaseException {
		ArrayList<Integer> elementIDs = new ArrayList<Integer>();
		int compoundID = 0;
		try {
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				compoundID = results.getInt("compoundID");
				int elementID = results.getInt("elementID");
				elementIDs.add(elementID);
			}
		} catch(SQLException e) {
			throw new DatabaseException("Failed to convert query to DTO.", e);
		}
		return new CompoundDTO(compoundID, elementIDs);
	}
	
	/**
	 * @see datasource.findElementsByCompoundID(int compoundID).
	 */
	@Override
	public CompoundDTO findElementsByCompoundID(int compoundID) throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM CompoundMadeFromElement WHERE compoundID = ? ORDER BY elementID ASC;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, compoundID);
			return convertToCDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to convert query to DTO.", e);
		}
	}

	/**
	 *  converts a query to a elementInCompoundDTO
	 * @param statement
	 * @return
	 * @throws DatabaseException
	 */
	private ElementDTO convertToEICDTO(PreparedStatement statement) throws DatabaseException {
		ArrayList<Integer> compoundIDs = new ArrayList<Integer>();
		int elementID = 0;
		try {
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				elementID = results.getInt("elementID");
				int compoundID = results.getInt("compoundID");
				compoundIDs.add(compoundID);
			}
		} catch(SQLException e) {
			throw new DatabaseException("Failed to convert query to DTO.", e);
		}
		return new ElementDTO(elementID, compoundIDs);
	}
	
	/**
	* @see datasource.findCompoundsByElementID(int elementID).
	*/
	@Override
	public ElementDTO findCompoundsByElementID(int elementID) throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM CompoundMadeFromElement WHERE elementID = ? ORDER BY compoundID ASC;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, elementID);
			return convertToEICDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to convert query to DTO.", e);
		}
	}
	
	/**
	 * Delete a row from the table.
	 */
	@Override
	public void delete(int compoundID, int elementID) throws DatabaseException {
		String deleteSQL = "DELETE FROM CompoundMadeFromElement WHERE compoundID = ? and elementID = ?;";	
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(deleteSQL);
			statement.setInt(1, compoundID);
			statement.setInt(2, elementID);
			statement.execute();	
		} catch (SQLException e) {
			throw new DatabaseException("Could not delete compound "+compoundID+" with element "+elementID+".", e);
		}
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

