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

  private final String chemicalSQL = "SELECT * FROM Chemical";
  private final String elementSQL = "SELECT * FROM Chemical WHERE (Chemical.type = 1 OR Chemical.type = 2)";
  private final String metalSQL = "SELECT * FROM Chemical WHERE (Chemical.type = 2)";
  private final String compoundSQL = "SELECT * FROM Chemical WHERE (Chemical.type = 3)";
  private final String baseSQL = "SELECT * FROM Chemical WHERE (Chemical.type = 4)";
  private final String acidSQL = "SELECT * FROM Chemical WHERE (Chemical.type = 5)";

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
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(chemicalSQL + ";");
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> filterAllByName(String name) throws DatabaseException {
    try {
      String whereSQL = " WHERE (Chemical.name = ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(chemicalSQL + whereSQL);
      statement.setString(1, name);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals with name '" + name + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> filterAllByPartialName(String partialName) throws DatabaseException {
    try {
      String whereSQL = " WHERE (Chemical.name LIKE '%?%');";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(chemicalSQL + whereSQL);
      statement.setString(1, partialName);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals with name like '" + partialName + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> filterAllByInventory(double inventory) throws DatabaseException {
    try {
      String whereSQL = " WHERE (Chemical.inventory = ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(chemicalSQL + whereSQL);
      statement.setDouble(1, inventory);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Chemicals with inventory '" + inventory + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> filterAllByInventoryRange(double min, double max) throws DatabaseException {
    try {
      String whereSQL = " WHERE (Chemical.inventory BETWEEN ? AND ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(chemicalSQL + whereSQL);
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
  public ArrayList<ChemicalDTO> filterAllWithLowInventory() throws DatabaseException {
    // TODO Auto-generated method stub
    return null;
  }

// Filters for elements start here  

  @Override
  public ArrayList<ChemicalDTO> fetchElements() throws DatabaseException {
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(elementSQL + ";");
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to get all Elements.", e);
    }
  }

  /**
   * @see datasource.ChemicalTableDataGateway#filterElementByNameLike(String
   *      partialName)
   */
  @Override
  public ArrayList<ChemicalDTO> filterElementByNameLike(String partialName) throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.name LIKE '%?%');";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(elementSQL + andSQL);
      statement.setString(1, partialName);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Elements with name like '" + partialName + "'.", e);
    }
  }

  /**
   * @see datasource.ChemicalTableDataGateway#filterElementByInventory(double
   *      inventory)
   */
  @Override
  public ArrayList<ChemicalDTO> filterElementByInventory(double inventory) throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.inventory = ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(elementSQL + andSQL);
      statement.setDouble(1, inventory);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with inventory '" + inventory + "'.", e);
    }
  }

  /**
   * @see datasource.ChemicalTableDataGateway#filterElementByInvetoryRange(double
   *      min, double max)
   */
  @Override
  public ArrayList<ChemicalDTO> filterElementByInvetoryRange(double min, double max) throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.inventory BETWEEN ? AND ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(elementSQL + andSQL);
      statement.setDouble(1, min);
      statement.setDouble(2, max);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with inventory between '" + max + " and " + min + "'.",
          e);
    }
  }

  /**
   * @see datasource.ChemicalTableDataGateway#filterElementByAtomicMass(double
   *      atomicMass)
   */
  @Override
  public ArrayList<ChemicalDTO> filterElementByAtomicMass(double atomicMass) throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.atomicMass = ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(elementSQL + andSQL);
      statement.setDouble(1, atomicMass);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with atomicMass '" + atomicMass + "'.", e);
    }
  }

  /*
   * @see
   * datasource.ChemicalTableDataGateway#filterElementByAtomicMassRange(double
   * min, double max)
   */
  @Override
  public ArrayList<ChemicalDTO> filterElementByAtomicMassRange(double min, double max) throws DatabaseException {
    try {
      String andSQL = " AND Chemical.atomicMass BETWEEN ? AND ?;";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(elementSQL + andSQL);
      statement.setDouble(1, min);
      statement.setDouble(2, max);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with atomicMass between '" + max + " and " + min + "'.",
          e);
    }
  }

  /**
   * @see datasource.ChemicalTableDataGateway#filterElementByAtomicNumber(int
   *      atomicNumber)
   */
  @Override
  public ArrayList<ChemicalDTO> filterElementByAtomicNumber(int atomicNumber) throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.atomicNumber = ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(elementSQL + andSQL);
      statement.setDouble(1, atomicNumber);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with atomicNumber '" + atomicNumber + "'.", e);
    }
  }

  /**
   * @see datasource.ChemicalTableDataGateway#filterElementByAtomicNumberRange(int
   *      min, int max)
   */
  @Override
  public ArrayList<ChemicalDTO> filterElementByAtomicNumberRange(int min, int max) throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.atomicNumber BETWEEN ? AND ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(elementSQL + andSQL);
      statement.setDouble(1, min);
      statement.setDouble(2, max);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException(
          "Failed to fetch all Element with atomicNumber between '" + max + " and " + min + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findElementsWithLowInventory() throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.inventory < 20.0);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(elementSQL + andSQL);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with low inventory.", e);
    }
  }

// Metal Queries.

  @Override
  public ArrayList<ChemicalDTO> fetchMetals() throws DatabaseException {
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(metalSQL);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Metals from 'Chemical'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByPartialName(String partialName) throws DatabaseException {
    try {
      final String andSQL = " AND (Chemical.name LIKE '%?%');";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(metalSQL + andSQL);
      statement.setString(1, partialName);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to find Metals with name like '" + partialName + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByInventory(double inventory) throws DatabaseException {
    try {
      final String andSQL = " AND Chemical.inventory = ?;";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(metalSQL + andSQL);
      statement.setDouble(1, inventory);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Metals with inventory of '" + inventory + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByInventoryRange(double min, double max) throws DatabaseException {
    try {
      String whereSQL = " AND (Chemical.inventory BETWEEN ? AND ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(metalSQL + whereSQL);
      statement.setDouble(1, min);
      statement.setDouble(2, max);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Metals with inventory in range.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsWithLowInventory() throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.inventory < 40.0);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(metalSQL + andSQL);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with low inventory.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByAtomicNumber(int atomicNumber) throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.atomicNumber = ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(metalSQL + andSQL);
      statement.setDouble(1, atomicNumber);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with atomicNumber '" + atomicNumber + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByAtomicNumberRange(int min, int max) throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.atomicNumber BETWEEN ? AND ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(metalSQL + andSQL);
      statement.setDouble(1, min);
      statement.setDouble(2, max);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException(
          "Failed to fetch all metals with atomicNumber between '" + max + " and " + min + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByAtomicMass(double atomicMass) throws DatabaseException {
    try {
      String andSQL = " AND (Chemical.atomicMass = ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(metalSQL + andSQL);
      statement.setDouble(1, atomicMass);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with atomicMass '" + atomicMass + "'.", e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByAtomicMassRange(double min, double max) throws DatabaseException {
    try {
      String andSQL = " AND Chemical.atomicMass BETWEEN ? AND ?;";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement(metalSQL + andSQL);
      statement.setDouble(1, min);
      statement.setDouble(2, max);
      return convertToDTO(statement);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to fetch all Element with atomicMass between '" + max + " and " + min + "'.",
          e);
    }
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByDissolvedBy(int acidID) throws DatabaseException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByMoles(double moles) throws DatabaseException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<ChemicalDTO> findMetalsByMolesRange(double min, double max) throws DatabaseException {
    // TODO Auto-generated method stub
    return null;
  }

}