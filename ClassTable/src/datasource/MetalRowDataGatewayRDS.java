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
public class MetalRowDataGatewayRDS implements MetalRowDataGateway {

  private int metalId;
  private int dissolvedById;
  private String name;
  private String inhabits;

  /**
   * empty constructor drops the table and recreates it
   */
  public MetalRowDataGatewayRDS() {
  }

  /**
   * finds a metal
   * 
   * @param id
   */
  public MetalRowDataGatewayRDS(int id) {
    this.metalId = id;

    String sqlChem = "SELECT * FROM Chemical WHERE chemicalId = " + id + ";";
    String sqlElement = "SELECT * FROM Metal WHERE metalId = " + id + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlElement);
      rs.next();
      this.dissolvedById = rs.getInt("dissolvedBy");

      rs = statement.executeQuery(sqlChem);
      rs.next();
      this.name = rs.getString("name");
      this.inhabits = rs.getString("inhabits");

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
  public MetalRowDataGatewayRDS(int id, int dissolvedById, String name, String inhabits) {
    try {
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Metal (metalId, dissolvedBy)" + "VALUES (?, ?);");

      insert.setInt(1, id);
      insert.setInt(2, dissolvedById);

      insertChemical.execute();
      insert.execute();
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
          .prepareStatement("UPDATE Metal SET dissolvedById = ? WHERE elementId = ?;");
      updateMetal.setInt(1, this.dissolvedById);
      updateMetal.setInt(2, this.metalId);

      PreparedStatement updateChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Chemical SET name = ?, inhabits = ? WHERE chemicalId = ?;");
      updateChemical.setString(1, this.name);
      updateChemical.setString(2, this.inhabits);
      updateChemical.setInt(3, this.metalId);

      updateMetal.execute();
      updateChemical.execute();
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
  public List<MetalRowDataGatewayRDS> findSet(int dissolvedById) {
    List<MetalRowDataGatewayRDS> results = new ArrayList<>();
    try {
      String sql = "SELECT * FROM Metal WHERE dissolvedBy = " + dissolvedById + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        MetalRowDataGatewayRDS metalRDS = new MetalRowDataGatewayRDS(rs.getInt("metalId"));
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


  
}
