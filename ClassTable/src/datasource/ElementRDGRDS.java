package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Element RDS
 * Row data Gateway
 * @author kimberlyoneill
 *
 */
public class ElementRDGRDS implements ElementRDG {
  /**
   * Create element table
   */
  private int elementId;
  private int atomicNumber;
  private double atomicMass;
  private String name;
  private double inventory;

  /**
   * Empty constructor drops and recreates table
   */
  public ElementRDGRDS() {
    createTableElement();
  }

  /**
   * constructor to search for an element
   * @param id
   */
  public ElementRDGRDS(int id) {
    this.createTableElement();
    this.elementId = id;

    String sqlChem = "SELECT * FROM Chemical INNER JOIN Element ON Chemical.chemicalId = " + id + ";";
    String sqlElement = "SELECT * FROM Element WHERE elementId = " + id + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlElement);
      rs.next();
      this.atomicNumber = rs.getInt("atomicNumber");
      this.atomicMass = rs.getDouble("atomicMass");

      rs = statement.executeQuery(sqlChem);
      rs.next();
      this.name = rs.getString("name");
      this.inventory = rs.getDouble("inventory");

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("No entry with id " + id);
    }

  }
  
  /**
   * Constructor to create an element
   * @param id
   * @param atomicNum
   * @param atomicMass
   * @param name
   * @param inhabits
   */
  public ElementRDGRDS(int id, int atomicNum, int atomicMass, String name, double inventory) {
    this.createTableElement();
    try {
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inventory)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setDouble(3, inventory);
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Element (elementId, atomicNumber, atomicMass)" + "VALUES (?, ?, ?);");

      insert.setInt(1, id);
      insert.setInt(2, atomicNum);
      insert.setInt(3, atomicMass);

      insertChemical.execute();
      insert.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert");
    }
  }

  /**
   * Create table
   * Checking if the table exists already is included
   */
  @Override
  public void createTableElement() {
    String createTable = "CREATE TABLE IF NOT EXISTS Element" + "(" + "elementId INT NOT NULL, " + "atomicNumber INT, "
        + "atomicMass DOUBLE, " + "FOREIGN KEY(elementId) REFERENCES Chemical(chemicalId)" + ");";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();

      // Drop the table if exists first
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");

      // Create tables
      statement.executeUpdate(createTable);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to create/drop element table");
    }
  }


  @Override
  public void dropTableElement() {
    String dropTable = "DROP TABLE IF EXISTS Element;";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
      statement.executeUpdate(dropTable);
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error dropping element table");
    }
  }

  /**
   * Drop the chemical table if it exists.
   */

  @Override
  public void dropTableChemical() {
    String dropTable = "DROP TABLE IF EXISTS Chemical;";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
      statement.executeUpdate(dropTable);
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error dropping chemical table");
    }
  }

  /**
   * Drop Element and all tables connected (element & chemical)
   */

  @Override
  public void dropAllTables() {
    dropTableElement();
    dropTableChemical();
  }

  /**
   * deletes Element already held by the RDS
   */
  @Override
  public void delete() {

    String sqlElement = "DELETE FROM Element WHERE elementId = " + this.elementId + ";";
    String sqlChem = "DELETE FROM Chemical WHERE chemicalId = " + this.elementId + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(sqlElement);
      statement.executeUpdate(sqlChem);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Problem deleting Element with id " + this.elementId);
    }
  }

  /**
   * updates an element with the new values given
   */
  @Override
  public void update() {
    try {
      PreparedStatement updateElement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Element SET atomicNumber = ?, atomicMass = ? WHERE elementId = ?;");
      updateElement.setInt(1, this.atomicNumber);
      updateElement.setDouble(2, this.atomicMass);
      updateElement.setInt(3, this.elementId);

      PreparedStatement updateChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Chemical SET name = ?, inventory = ? WHERE chemicalId = ?;");
      updateChemical.setString(1, this.name);
      updateChemical.setDouble(2, this.inventory);
      updateChemical.setInt(3, this.elementId);

      updateElement.execute();
      updateChemical.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to update");
    }

  }
  /**
   * finds entry by atomic number
   */
  @Override
  public void findByAtomicNumber(int atomicNum) {
    this.atomicNumber = atomicNum;
    String sqlElement = "SELECT * FROM Element WHERE atomicNumber = " + atomicNum + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlElement);
      rs.next();
      this.elementId = rs.getInt("elementId");
      this.atomicMass = rs.getDouble("atomicMass");

      String sqlChem = "SELECT * FROM Chemical INNER JOIN Element ON Chemical.chemicalId = " + this.elementId + ";";
      rs = statement.executeQuery(sqlChem);
      rs.next();
      this.name = rs.getString("name");
      this.inventory = rs.getDouble("inventory");

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * finds entry by atomic Mass
   */
  @Override
  public void findByAtomicMass(double atomicMass) {
    this.atomicMass = atomicMass;
    String sqlElement = "SELECT * FROM Element WHERE atomicMass = " + atomicMass + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlElement);
      rs.next();
      this.elementId = rs.getInt("elementId");
      this.atomicNumber = rs.getInt("atomicNumber");

      String sqlChem = "SELECT * FROM Chemical INNER JOIN Element ON Chemical.chemicalId = " + this.elementId + ";";
      rs = statement.executeQuery(sqlChem);
      rs.next();
      this.name = rs.getString("name");
      this.inventory = rs.getDouble("inventory");

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<ElementRDGRDS> findSetAtomicMass(double lowerLimit, double upperLimit) {
    List<ElementRDGRDS> results = new ArrayList<>();
    try {
      String sql = "SELECT * FROM Metal WHERE atomicMass BETWEEN " + lowerLimit + " AND " + upperLimit + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        ElementRDGRDS elementRDS = new ElementRDGRDS(rs.getInt("elementId"));
        results.add(elementRDS);
      }
    } catch (SQLException | DatabaseException e) {

    }
    return results;
  }
  
  /**
   * Get atomic number of element from a given id
   * 
   * @param id
   *          to search for atomic number of
   */
  @Override
  public int getAtomicNumber() {
    return this.atomicNumber;
  }

  /**
   * Get atomic mass of element from a given id
   * 
   * @param to
   *          search for atomic mass of
   */
  @Override
  public double getAtomicMass() {
    return this.atomicMass;
  }

  /**
   * Get name of element
   * 
   * @param id
   *          to search for name of
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get inhabits of a element
   * 
   * @param id
   *          to search for inhabits of
   */
  @Override
  public double getInventory() {
    return this.inventory;
  }

  @Override
  public void setElementId(int elementId) {
    this.elementId = elementId;
  }

  @Override
  public void setAtomicNumber(int atomicNumber) {
    this.atomicNumber = atomicNumber;
  }

  @Override
  public void setAtomicMass(double atomicMass) {
    this.atomicMass = atomicMass;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setInventory(double inventory) {
    this.inventory = inventory;
  }
  
  
}
