package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ElementRowDataGatewayRDS implements ElementRowDataGateway {
  /**
   * Create element table
   */
  int elementId;
  int atomicNumber;
  double atomicMass;
  String name;
  String inhabits;

  public ElementRowDataGatewayRDS() {
    dropAllTables();
    createTableElement();
  }
  public ElementRowDataGatewayRDS(int id) {
    this.createTableElement();
    this.elementId = id;

    String sqlChem = "SELECT * FROM Chemical INNER JOIN Element ON Chemical.chemicalId = " + id + ";";
    String sqlElement = "SELECT * FROM Element where elementId = " + id + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlElement);
      rs.next();
      this.atomicNumber = rs.getInt("atomicNumber");
      this.atomicMass = rs.getDouble("atomicMass");

      rs = statement.executeQuery(sqlChem);
      rs.next();
      this.name = rs.getString("name");
      this.inhabits = rs.getString("inhabits");

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("No entry with id " + id);
    }

  }

  public ElementRowDataGatewayRDS(int id, int atomicNum, int atomicMass, String name, String inhabits) {
    this.createTableElement();
    try {
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
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
  public String getInhabits() {
    return this.inhabits;
  }
  
  public void dropTableElement() {
    String dropTable = "DROP TABLE IF EXISTS Element";
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
  public void dropTableChemical() {
    String dropTable = "DROP TABLE IF EXISTS Chemical";
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
   * Drop acid and all tables connected (acid & chemical)
   */
  public void dropAllTables() {
    dropTableElement();
    dropTableChemical();
  }

  @Override
  public void delete(int id) {

    String sqlElement = "DELETE FROM Element WHERE elementId = " + id + ";";
    String sqlChem = "DELETE FROM Chemical WHERE chemicalId = " + id + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(sqlElement);
      statement.executeUpdate(sqlChem);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("No entry with id " + id);
    }
  }

  @Override
  public void update(int id, int atomicNum, int atomicMass, String name, String inhabits) {
    try {
      PreparedStatement updateElement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Element SET atomicNumber = ?, atomicMass = ? WHERE elementId = ?;");
      updateElement.setInt(1, atomicNum);
      updateElement.setDouble(2, atomicMass);
      updateElement.setInt(3, id);
      
      PreparedStatement updateChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Chemical SET name = ?, inhabits = ? WHERE chemicalId = ?;");
      updateChemical.setString(1, name);
      updateChemical.setString(2, inhabits);
      updateChemical.setInt(3, id);
      
      updateElement.execute();
      updateChemical.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to update");
    }

  }

}
