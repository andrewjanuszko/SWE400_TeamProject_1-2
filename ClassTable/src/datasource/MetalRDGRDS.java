package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

/**
 * 
 * @author kimberlyoneill
 *
 */
public class MetalRDGRDS implements MetalRDG {
  MetalDTO metal; 

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
    String sql = "SELECT * FROM Metal INNER JOIN Chemical WHERE chemicalId = Metal.metalId  INNER JOIN Element WHERE elementId = Metal.metalId AND metalId = " + id + ";";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next();
      
      metal = new MetalDTO(id, rs.getInt("dissolvedBy"), rs.getInt("atomicNumber"), rs.getDouble("atomicMass"), rs.getString("name"), rs.getDouble("inventory"));

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
   * @param inventory
   */
  public MetalRDGRDS(int id, int dissolvedById, int atomicNumber, double atomicMass, String name, double inventory) {
    
    try {
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inventory)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setDouble(3, inventory);
      
      PreparedStatement insertElement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Element (atomicNumber, atomicMass)" + "VALUES (?, ?);");
      insertElement.setInt(1, atomicNumber);
      insertElement.setDouble(2, atomicMass);
      
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Metal (metalId, dissolvedBy)" + "VALUES (?, ?);");

      insert.setInt(1, id);
      insert.setInt(2, dissolvedById);

      insertChemical.execute();
      insertElement.execute();
      insert.execute();
      
      metal = new MetalDTO(id, dissolvedById, atomicNumber, atomicMass, name, inventory);
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

    String sqlMetal = "DELETE FROM Metal WHERE metalId = " + metal.getMetalId() + ";";
    String sqlChem = "DELETE FROM Chemical WHERE chemicalId = " + metal.getMetalId() + ";";
    String sqlElement = "DELETE FROM Element WHERE elementId = " + metal.getMetalId() + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(sqlMetal);
      statement.executeUpdate(sqlChem);
      statement.executeUpdate(sqlElement);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Problem deleting Metal with id " + metal.getMetalId());
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
      updateMetal.setInt(1, metal.getDissolvedById());
      updateMetal.setInt(2, metal.getMetalId());

      PreparedStatement updateElement = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Element SET atomicNumber = ?, atomicMass = ? WHERE elementId = ?;");
      updateMetal.setInt(1, metal.getAtomicNumber());
      updateMetal.setDouble(2, metal.getAtomicMass());
      updateMetal.setInt(3, metal.getMetalId());
      
      PreparedStatement updateChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Chemical SET name = ?, inventory = ? WHERE chemicalId = ?;");
      updateChemical.setString(1, metal.getName());
      updateChemical.setDouble(2, metal.getInventory());
      updateChemical.setInt(3, metal.getMetalId());

      updateMetal.execute();
      updateElement.execute();
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

  @Override
  public void setDissolvedById(int dissolvedById) {
    metal.setDissolvedById(dissolvedById);;
  }

  @Override
  public void setName(String name) {
    metal.setName(name);
  }

  @Override
  public void setInventory(double inventory) {
    metal.setInventory(inventory);
  }

  public MetalDTO getMetal() {
    return metal;
  }

  
}
