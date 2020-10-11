package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The RDS version of the gateway for Chemical.
 * 
 * @author andrewjanuszko
 */
public class ChemicalRowDataGatewayRDS implements ChemicalRowDataGateway {

  private int chemicalID;
  private int type;
  private String name;
  private double inventory;
  private int atomicNumber;
  private double atomicMass;
  private int dissolvedBy;
  private double moles;
  private int solute;

  /**
   * Constructor for finding Chemicals by ID.
   * 
   * @param chemicalID the ID of the Chemical.
   * @throws DatabaseException when things go wrong.
   */
  public ChemicalRowDataGatewayRDS(int chemicalID) throws DatabaseException {
    try {
      final String loadSQL = "SELECT * FROM Chemical WHERE Chemical.chemicalID = ?;";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(loadSQL);
      statement.setInt(1, chemicalID);
      ResultSet result = statement.executeQuery();
      result.next();
      loadInstanceVariables(chemicalID, result.getInt("type"), result.getString("name"), result.getDouble("inventory"),
          result.getInt("atomicNumber"), result.getDouble("atomicMass"), result.getInt("dissolvedBy"),
          result.getDouble("moles"), result.getInt("solute"));
    } catch (SQLException e) {
      throw new DatabaseException("Could not find Chemical with ID " + chemicalID + ".", e);
    }
  }

  /**
   * Insert a Chemical into the database then store it locally.
   * 
   * @param type         the type of the Chemical.
   * @param name         the name of the Chemical.
   * @param inventory    the amount of moles in storage.
   * @param atomicNumber the atomic number of the Element.
   * @param atomicMass   the atomic mass of the Element.
   * @param dissolvedBy  the acid that dissolves a Metal.
   * @param moles        the amount of acid needed to dissovle a Metal.
   * @param solute       the chemical solutes.
   * @throws DatabaseException when things go wrong.
   */
  public ChemicalRowDataGatewayRDS(int type, String name, double inventory, int atomicNumber, double atomicMass,
      int dissolvedBy, double moles, int solute) throws DatabaseException {
    try {
      final String createSQL = "INSERT INTO Chemical (type, name, inventory, atomicNumber, atomicMass, dissolvedBy, moles, solute)"
          + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(createSQL,
          PreparedStatement.RETURN_GENERATED_KEYS);
      loadPreparedStatement(statement, type, name, inventory, atomicNumber, atomicMass, dissolvedBy, moles, solute);
      statement.executeUpdate();
      ResultSet result = statement.getGeneratedKeys();
      result.next();
      loadInstanceVariables(result.getInt(1), type, name, inventory, atomicNumber, atomicMass, dissolvedBy, moles,
          solute);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to insert into 'Chemical' table.", e);
    }
  }

  /**
   * Creates a table in the database.
   * 
   * @throws DatabaseException when things go wrong.
   */
  public static void createTable() throws DatabaseException {
    try {
      final String createTableSQL = "CREATE TABLE IF NOT EXISTS Chemical("
          + "chemicalID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " + "type INTEGER NOT NULL, "
          + "name VARCHAR(20) NOT NULL UNIQUE, " + "inventory DOUBLE NOT NULL, " + "atomicNumber INTEGER, "
          + "atomicMass DOUBLE, " + "dissolvedBy INTEGER, " + "moles DOUBLE, " + "solute INTEGER);";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
      statement.executeUpdate(createTableSQL);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to create 'Chemical' table.", e);
    }
  }

  /**
   * Drops a table in the database.
   * 
   * @throws DatabaseException when things go wrong.
   */
  public static void dropTable() throws DatabaseException {
    try {
      final String dropTableSQL = "DROP TABLE IF EXISTS Chemical, CompoundMadeFromElement;";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
      statement.executeUpdate(dropTableSQL);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to drop 'Chemical', 'CompoundMadeFromElement' table.", e);
    }
  }

  /**
   * @see datasource.ChemicalRowDataGateway#delete(void).
   */
  @Override
  public void delete() throws DatabaseException {
    try {
      final String deleteSQL = "DELETE FROM Chemical WHERE Chemical.chemicalID = ?;";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(deleteSQL);
      statement.setInt(1, chemicalID);
      statement.execute();
    } catch (SQLException e) {
      throw new DatabaseException("Failed to delete Chemical with ID " + chemicalID + ".", e);
    }
  }

  /**
   * @see datasource.ChemicalRowDataGateway#update(void).
   */
  @Override
  public void update() throws DatabaseException {
    try {
      final String updateSQL = "UPDATE Chemical " + "SET Chemical.type = ?, " + "Chemical.name = ?, "
          + "Chemical.inventory = ?, " + "Chemical.atomicNumber = ?, " + "Chemical.atomicMass = ?, "
          + "Chemical.dissolvedBy = ?, " + "Chemical.moles = ?, " + "Chemical.solute = ?;";
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(updateSQL);
      loadPreparedStatement(statement, this.type, this.name, this.inventory, this.atomicNumber, this.atomicMass,
          this.dissolvedBy, this.moles, this.solute);
      statement.execute();
    } catch (SQLException e) {
      throw new DatabaseException("Failed to update Chemical with ID " + chemicalID + ".", e);
    }
  }

  /**
   * Loads values into a PreparedStatement.
   * 
   * @param type         the type of the Chemical.
   * @param name         the name of the Chemical.
   * @param inventory    the amount of moles in storage.
   * @param atomicNumber the atomic number of the Element.
   * @param atomicMass   the atomic mass of the Element.
   * @param dissolvedBy  the acid that dissolves a Metal.
   * @param moles        the amount of acid needed to dissovle a Metal.
   * @param solute       the chemical solutes.
   * @throws DatabaseException when things go wrong.
   */
  private void loadPreparedStatement(PreparedStatement statement, int type, String name, double inventory,
      int atomicNumber, double atomicMass, int dissolvedBy, double moles, int solute) throws DatabaseException {
    try {
      statement.setInt(1, type);
      statement.setString(2, name);
      statement.setDouble(3, inventory);
      statement.setInt(4, atomicNumber);
      statement.setDouble(5, atomicMass);
      statement.setInt(6, dissolvedBy);
      statement.setDouble(7, moles);
      statement.setInt(8, solute);
    } catch (SQLException e) {
      throw new DatabaseException("Failed to load prepared statment.", e);
    }
  }

  /**
   * Loads values into local instance variables.
   * 
   * @param chemicalID   the ID of the Chemical.
   * @param type         the type of the Chemical.
   * @param name         the name of the Chemical.
   * @param inventory    the amount of moles in storage.
   * @param atomicNumber the atomic number of the Element.
   * @param atomicMass   the atomic mass of the Element.
   * @param dissolvedBy  the acid that dissolves a Metal.
   * @param moles        the amount of acid needed to dissovle a Metal.
   * @param solute       the chemical solutes.
   * @throws DatabaseException when things go wrong.
   */
  private void loadInstanceVariables(int chemicalID, int type, String name, double inventory, int atomicNumber,
      double atomicMass, int dissolvedBy, double moles, int solute) {
    setChemicalID(chemicalID);
    setType(type);
    setName(name);
    setInventory(inventory);
    setAtomicNumber(atomicNumber);
    setAtomicMass(atomicMass);
    setDissolvedBy(dissolvedBy);
    setMoles(moles);
    setSolute(solute);
  }

  /**
   * Returns the ID of a chemical.
   * 
   * @return the ID.
   */
  @Override
  public int getChemicalID() {
    return this.chemicalID;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#getType().
   */
  @Override
  public int getType() {
    return this.type;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#getName().
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#getInventory().
   */
  @Override
  public double getInventory() {
    return this.inventory;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#getAtomicNumber().
   */
  @Override
  public int getAtomicNumber() {
    return this.atomicNumber;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#getAtomicMass().
   */
  @Override
  public double getAtomicMass() {
    return this.atomicMass;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#getDissolvedBy().
   */
  @Override
  public int getDissolvedBy() {
    return this.dissolvedBy;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#getMoles().
   */
  @Override
  public double getMoles() {
    return this.moles;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#getSolute().
   */
  @Override
  public int getSolute() {
    return this.solute;
  }

  /**
   * Sets the ID of the chemical.
   * 
   * @param chemicalID the ID of the Chemical.
   */
  private void setChemicalID(int chemicalID) {
    this.chemicalID = chemicalID;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#setType(int).
   */
  @Override
  public void setType(int type) {
    this.type = type;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#setName(String).
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#setInventory(String).
   */
  @Override
  public void setInventory(double inventory) {
    this.inventory = inventory;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#setAtomicNumber(int).
   */
  @Override
  public void setAtomicNumber(int atomicNumber) {
    this.atomicNumber = atomicNumber;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#setAtomicMass(double).
   */
  @Override
  public void setAtomicMass(double atomicMass) {
    this.atomicMass = atomicMass;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#setDissolvedBy(int).
   */
  @Override
  public void setDissolvedBy(int dissolvedBy) {
    this.dissolvedBy = dissolvedBy;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#setMoles(double);
   */
  @Override
  public void setMoles(double moles) {
    this.moles = moles;
  }

  /**
   * @see datasource.ChemicalRowDataGateway#setSolute(int).
   */
  @Override
  public void setSolute(int solute) {
    this.solute = solute;
  }

}