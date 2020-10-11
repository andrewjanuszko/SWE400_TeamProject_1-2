package datasource;

import dataDTO.ChemicalDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements a ChemicalTableDataGateway.
 * 
 * @author andrewjanuszko
 */
public class ChemicalTableDataGatewayRDS implements ChemicalTableDataGateway {

  private static ChemicalTableDataGateway singletonInstance;

  /**
   * Retrieves a Singleton instance of ChemicalTableDataGateway. Creates a new one
   * if it does not exist.
   * 
   * @return a Singleton instance of ChemicalTableDataGateway.
   */
  public static synchronized ChemicalTableDataGateway getSingletonInstance() {
    if (singletonInstance == null) {
      singletonInstance = new ChemicalTableDataGatewayRDS();
    }
    return singletonInstance;
  }

  /**
   * Converts a query to a list of ChemicalDTOs.
   * 
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
        double inventory = results.getDouble("inventory");
        int atomicNumber = results.getInt("atomicNumber");
        double atomicMass = results.getDouble("atomicMass");
        int dissolvedBy = results.getInt("dissolvedBy");
        double moles = results.getDouble("moles");
        int solute = results.getInt("solute");
        ChemicalDTO chemical = new ChemicalDTO(chemicalID, type, name, inventory, atomicNumber, atomicMass, dissolvedBy,
            moles, solute);
        listDTO.add(chemical);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to convert query to DTO.", e);
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
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> filterAllByName(String name) throws DatabaseException {
    try {
      String selectSQL = "SELECT * FROM Chemical WHERE Chemical.name = ?;";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
      statement.setString(1, name);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals with name '" + name + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> filterAllByPartialName(String partialName) throws DatabaseException {
    try {
      String selectSQL = "SELECT * FROM Chemical WHERE Chemical.name LIKE '%?%';";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
      statement.setString(1, partialName);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals with name like '" + partialName + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> filterAllByInventory(double inventory) throws DatabaseException {
    try {
      String selectSQL = "SELECT * FROM Chemical WHERE Chemical.inventory = ?;";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
      statement.setDouble(1, inventory);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals with inventory '" + inventory + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> filterAllByInventoryRange(double min, double max) throws DatabaseException {
    try {
      String selectSQL = "SELECT * FROM Chemical WHERE Chemical.inventory BETWEEN ? AND ?;";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
      statement.setDouble(1, min);
      statement.setDouble(2, max);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals with inventory in range.", e);
    }
  }

  /**
   * @see datasource.ChemicalTableDataGateway#filterAllByLowInventory().
   */
  @Override
  public ArrayList<ChemicalDTO> filterAllByLowInventory() throws DatabaseException {
    try {
      
      // This needs to call all other low inventory checks to work.
      
      @SuppressWarnings("unused")
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement("");
      
      
      
      return null;
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals with inventory in range.", e);
    }
  }
  
// Filters for elements start here  

  
/**
 * @see datasource.ChemicalTableDataGateway#filterElementByNameLike(String partialName)
 */
@Override
public ArrayList<ChemicalDTO> filterElementByNameLike(String partialName) throws DatabaseException {
	 try {
	      String selectSQL = "SELECT * FROM Chemical WHERE Chemical.name LIKE '%?%';";
	      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
	      statement.setString(1, partialName);
	      return convertToDTO(statement);
	    } catch (SQLException e) {
	      throw new DatabaseException("Failed to fetch all Element with name like '" + partialName + "'.", e);
	    }
}

/**
 * @see datasource.ChemicalTableDataGateway#filterElementByInventory(double inventory)
 */
@Override
public ArrayList<ChemicalDTO> filterElementByInventory(double inventory) throws DatabaseException {
	try {
		String selectSQL = "SELECT * FROM Chemical WHERE Chemical.inventory = ?;";
		PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
		statement.setDouble(1, inventory);
		return convertToDTO(statement);
	}catch (SQLException e) {
	      throw new DatabaseException("Failed to fetch all Element with inventory '" + inventory + "'.", e);
	}
}

/**
 * @see datasource.ChemicalTableDataGateway#filterElementByInvetoryRange(double min, double max)
 */
@Override
public ArrayList<ChemicalDTO> filterElementByInvetoryRange(double min, double max) throws DatabaseException {
	try {
		String selectSQL = "SELECT * FROM Chemical WHERE Chemical.inventory BETWEEN ? AND ?;";
		PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
		statement.setDouble(1, min);
		statement.setDouble(2, max);
		return convertToDTO(statement);
	}catch (SQLException e) {
	      throw new DatabaseException("Failed to fetch all Element with inventory between '" + max + " and "+ min + "'.", e);
	}
}

/**
 * @see datasource.ChemicalTableDataGateway#filterElementByAtomicMass(double atomicMass)
 */
@Override
public ArrayList<ChemicalDTO> filterElementByAtomicMass(double atomicMass) throws DatabaseException {
	try {
		String selectSQL = "SELECT * FROM Chemical WHERE Chemical.atomicMass = ?;";
		PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
		statement.setDouble(1, atomicMass);
		return convertToDTO(statement);
	}catch (SQLException e) {
	      throw new DatabaseException("Failed to fetch all Element with atomicMass '" + atomicMass + "'.", e);
	}
}

/*
 * @see datasource.ChemicalTableDataGateway#filterElementByAtomicMassRange(double min, double max)
 */
@Override
public ArrayList<ChemicalDTO> filterElementByAtomicMassRange(double min, double max) throws DatabaseException {
	try {
		String selectSQL = "SELECT * FROM Chemical WHERE Chemical.atomicMass BETWEEN ? AND ?;";
		PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
		statement.setDouble(1, min);
		statement.setDouble(2, max);
		return convertToDTO(statement);
	}catch (SQLException e) {
	      throw new DatabaseException("Failed to fetch all Element with atomicMass between '" + max + " and "+ min + "'.", e);
	}
}

/**
 * @see datasource.ChemicalTableDataGateway#filterElementByAtomicNumber(int atomicNumber)
 */
@Override
public ArrayList<ChemicalDTO> filterElementByAtomicNumber(int atomicNumber) throws DatabaseException {
	try {
		String selectSQL = "SELECT * FROM Chemical WHERE Chemical.atomicNumber = ?;";
		PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
		statement.setDouble(1, atomicNumber);
		return convertToDTO(statement);
	}catch (SQLException e) {
	      throw new DatabaseException("Failed to fetch all Element with atomicNumber '" + atomicNumber + "'.", e);
	}
}

/**
 * @see datasource.ChemicalTableDataGateway#filterElementByAtomicNumberRange(int min, int max)
 */
@Override
public ArrayList<ChemicalDTO> filterElementByAtomicNumberRange(int min, int max) throws DatabaseException {
	try {
		String selectSQL = "SELECT * FROM Chemical WHERE Chemical.atomicNumber BETWEEN ? AND ?;";
		PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(selectSQL);
		statement.setDouble(1, min);
		statement.setDouble(2, max);
		return convertToDTO(statement);
	}catch (SQLException e) {
	      throw new DatabaseException("Failed to fetch all Element with atomicNumber between '" + max + " and "+ min + "'.", e);
	}
}

}