package datasource;

import dataDTO.ChemicalDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements a ChemicalTableDataGateway.
 * @author andrewjanuszko
 */
public class ChemicalTableDataGatewayRDS implements ChemicalTableDataGateway {
	
	private static ChemicalTableDataGateway singletonInstance;
	
	/**
	 * Retrieves a Singleton instance of ChemicalTableDataGateway.
	 * Creates a new one if it does not exist.
	 * @return a Singleton instance of ChemicalTableDataGateway.
	 */
	public static synchronized ChemicalTableDataGateway getSingletonInstance() {
		if(singletonInstance == null) {
			singletonInstance = new ChemicalTableDataGatewayRDS();
		}
		return singletonInstance;
	}
	
	/**
	 * Converts a query to a list of ChemicalDTOs.
	 * @param statement the query on the database.
	 * @return a list of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	private ArrayList<ChemicalDTO> convertToDTO(PreparedStatement statement) throws DatabaseException {
		ArrayList<ChemicalDTO> listDTO = new ArrayList<ChemicalDTO>();
		try {
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				int chemicalID = results.getInt("chemicalID");
				int type = results.getInt("type");
				String name = results.getString("name");
				String inhabits = results.getString("inhabits");
				int atomicNumber = results.getInt("atomicNumber");
				double atomicMass = results.getDouble("atomicMass");
				int dissolvedBy = results.getInt("dissolvedBy");
				int solute = results.getInt("solute");
				
				ChemicalDTO chemical = new ChemicalDTO(chemicalID, type, name, inhabits, atomicNumber, atomicMass, dissolvedBy, solute);
				listDTO.add(chemical);
			}
		} catch(SQLException e) {
			throw new DatabaseException("Failed to load Chemicals.", e);
		}
		return listDTO;
	}
	
	/**
	 * @see datasource.ChemicalTableDataGateway#fetchAll();
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchAll() throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Chemicals.", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchELements();
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchElements() throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.type = 1 OR Chemical.type = 2;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Elements.", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchMetals();
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchMetals() throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.type = 2;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Metals.", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchCompounds();
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchCompounds() throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.type = 3;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Compounds.", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchBases();
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchBases() throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.type = 4;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Bases.", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchAcids();
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchAcids() throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.type = 5;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Acids.", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchByName(String);
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchByName(String name) throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.name = ?;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setString(1, name);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Chemicals named '" + name + "'. ", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchByHabitat(String);
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchByHabitat(String habitat) throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.inhabits = ?;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setString(1, habitat);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Chemicals in '" + habitat + "'. ", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchByAtomicNumber(int);
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchByAtomicNumber(int atomicNumber) throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.atomicNumber = ?;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, atomicNumber);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Elements with atomic number '" + atomicNumber + "'.", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchByAtomicMassRange(double, double);
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchByAtomicMassRange(double min, double max) throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.atomicMass BETWEEN ? AND ?;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setDouble(1, min);
			statement.setDouble(2, max);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Elements with atomic mass between '" + min + "' and '" + max + "'.", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchByDissolvedBy(int);
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchByDissolvedBy(int acidID) throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.dissolvedBy = ?;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, acidID);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Metals dissolved by '" + acidID + "'.", e);
		}
	}

	/**
	 * @see datasource.ChemicalTableDataGateway#fetchBySolute(int);
	 */
	@Override
	public ArrayList<ChemicalDTO> fetchBySolute(int chemicalID) throws DatabaseException {
		try {
			String selectSQL = "SELECT * FROM Chemical WHERE Chemical.solute = ?;";
			PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
			statement.setInt(1, chemicalID);
			return convertToDTO(statement);
		} catch(SQLException e) {
			throw new DatabaseException("Failed to fetch all Bases/Acids with solute '" + chemicalID + "'.", e);
		}
	}
	
}