package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataDTO.ChemicalDTO;
import dataDTO.CompoundMadeFromElementRecordDTO;

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
		if (singletonInstance == null) { //Possible error point (.equals vs ==)
			singletonInstance = new CompoundMadeFromElementTableDataGatewayRDS();
		}
		return singletonInstance;
	}

	/**
	 * Drop the CompoundMadeFromElement table if it already exists, then recreate it as an empty table.
	 * @throws DatabaseException when something goes really wrong.
	 */
	public void createTable() throws DatabaseException {
		String dropTableSQL = "DROP TABLE IF EXISTS CompoundMadeFromElement";
		String createTableSQL = "CREATE TABLE CompoundMadeFromElement (" +
								"compoundID LONG NOT NULL, " +
								"elementID LONG NOT NULL, " +
								"FOREIGN KEY (compondID) REFERENCES Chemical(chemicalID), " +
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
	 * 
	 * @throws DatabaseException when insertion fails.
	 */
	public void createRow(long compoundID, long elementID) throws DatabaseException {
		
		String insertSQL = "INSERT INTO CompoundMadeFromElement SET compoundID = ?, elementID = ?;";
		
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
		
		String updateSQL = "UPDATE CompoundMadeFromElement SET compoundID = ?, elementID = ?;";		
		
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
	public ArrayList<CompoundMadeFromElementRecordDTO> findElementsByCompoundID(int compoundID) throws DatabaseException {
		ArrayList<CompoundMadeFromElementRecordDTO> resultSet = new ArrayList<CompoundMadeFromElementRecordDTO>();
		String selectSQL = "SELECT * FROM CompoundMadeFromElement WHERE compoundID = ?;";	
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setLong(1, compoundID);
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				resultSet.add(new CompoundMadeFromElementRecordDTO(r.getInt("compoundID"), r.getInt("elementID")));
			}
			
		} catch (SQLException e) {
			throw new DatabaseException("Compound with compoundID = " + compoundID + " cannot be found.", e);
		}
		return resultSet;
	}

	@Override
	public ArrayList<CompoundMadeFromElementRecordDTO> findCompoundsByElementID(int elementID) throws DatabaseException {
		ArrayList<CompoundMadeFromElementRecordDTO> resultSet = new ArrayList<CompoundMadeFromElementRecordDTO>();
		String selectSQL = "SELECT * FROM CompoundMadeFromElement WHERE elementID = ?;";	
		try {
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setLong(1, elementID);
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				resultSet.add(new CompoundMadeFromElementRecordDTO(r.getInt("compoundID"), r.getInt("elementID")));
			}
			
		} catch (SQLException e) {
			throw new DatabaseException("Compound made with elementID = " + elementID + " cannot be found.", e);
		}
		return resultSet;
	}

}
