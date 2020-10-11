
package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author kimberlyoneill
 *
 */
public class MetalRDGRDS implements MetalRDG {

  private int metalId;
  private int dissolvedById;
  private String name;
  private String inhabits;
  private int atomicNumber;
  private double atomicMass;

  /**
   * empty constructor drops the table and recreates it
   */
  public MetalRDGRDS() {
  }

  /**
   * finds a metal
   * 
   * @param id
   */
  public MetalRDGRDS(int id) {
    this.metalId = id;

    String sqlChem = "SELECT * FROM Chemical WHERE chemicalId = " + id + ";";
    String sqlMetal = "SELECT * FROM Metal WHERE metalId = " + id + ";";
    String sqlElement = "SELECT * FROM Element WHERE elementId = " + id + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlMetal);
      rs.next();
      this.dissolvedById = rs.getInt("dissolvedBy");

      rs = statement.executeQuery(sqlChem);
      rs.next();
      this.name = rs.getString("name");
      this.inhabits = rs.getString("inhabits");

      rs = statement.executeQuery(sqlElement);
      rs.next();
      this.atomicNumber = rs.getInt("atomicNumber");
      this.atomicMass = rs.getDouble("atomicMass");

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("No entry with id " + id);
    }

  }

  /**
   * constructor to create a metal
   * 
   * @param id
   * @param dissolvedById
   * @param name
   * @param inhabits
   */
  public MetalRDGRDS(int id, int dissolvedById, String name, String inhabits) {
    try {
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);

      PreparedStatement insertMetal = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Metal (metalId, dissolvedBy)" + "VALUES (?, ?);");
      insertMetal.setInt(1, id);
      insertMetal.setInt(2, dissolvedById);

      PreparedStatement insertElement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Element (atomicNumber, atomicMass)" + "VALUES (?, ?);");
      insertElement.setInt(1, this.atomicNumber);
      insertElement.setDouble(2, this.atomicMass);

      insertChemical.execute();
      insertMetal.execute();
      insertElement.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert");
    }
  }

  /**
   * Deletes the metal
   */
  @Override
  public void delete() {

    String sqlMetal = "DELETE FROM Metal WHERE metalId = " + this.metalId + ";";
    String sqlChem = "DELETE FROM Chemical WHERE chemicalId = " + this.metalId + ";";
    String sqlElement = "DELETE FROM Element WHERE elementId = " + this.metalId + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(sqlMetal);
      statement.executeUpdate(sqlChem);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Problem deleting Metal with id " + this.metalId);
    }
  }

  /**
   * updates metal with given values
   */
  @Override
  public void update() {
    try {
      PreparedStatement updateMetal = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Metal SET dissolvedById = ? WHERE metalId = ?;");
      updateMetal.setInt(1, this.dissolvedById);
      updateMetal.setInt(2, this.metalId);

      PreparedStatement updateChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Chemical SET name = ?, inhabits = ? WHERE chemicalId = ?;");
      updateChemical.setString(1, this.name);
      updateChemical.setString(2, this.inhabits);
      updateChemical.setInt(3, this.metalId);

      PreparedStatement updateElement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Element SET atomicNumber = ?, atomicMass = ? WHERE elementId = ?;");
      updateMetal.setInt(1, this.atomicNumber);
      updateMetal.setDouble(2, this.atomicMass);

      updateMetal.execute();
      updateChemical.execute();
      updateElement.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to update");
    }

  }

  /**
   * finds all the metals dissolved by an Acid
   * 
   * @param dissolvedById
   *          an Acid
   * @return list of MetalRowDataGatewayRDS that contain the metals dissolved by
   *         the given acid
   */
  public List<MetalRDGRDS> findSet(int dissolvedById) {
    List<MetalRDGRDS> results = new ArrayList<>();
    try {
      String sql = "SELECT * FROM Metal WHERE dissolvedBy = " + dissolvedById + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        MetalRDGRDS metalRDS = new MetalRDGRDS(rs.getInt("metalId"));
        results.add(metalRDS);
      }
    } catch (SQLException | DatabaseException e) {

    }
    return results;
  }

  /**
   * getter for name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * getter for inhabits
   */
  @Override
  public String getInhabits() {
    return this.inhabits;
  }

  @Override
  public int getMetalId() {
    return metalId;
  }

  /**
   * getter for name
   */
  @Override
  public int getDissolvedBy() {
    return this.dissolvedById;
  }

  @Override
  public void setMetalId(int metalId) {
    this.metalId = metalId;
  }

  @Override
  public void setDissolvedById(int dissolvedById) {
    this.dissolvedById = dissolvedById;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setInhabits(String inhabits) {
    this.inhabits = inhabits;
  }

  @Override
  public int getAtomicNumber() {
    return this.atomicNumber;
  }

  @Override
  public double getAtomicMass() {
    return this.atomicMass;
  }

  @Override
  public void setAtomicNumber(int atomicNumber) {
    this.atomicNumber = atomicNumber;

  }

  @Override
  public void setAtomicMass(int atomicMass) {
    this.atomicMass = atomicMass;

  }

}
