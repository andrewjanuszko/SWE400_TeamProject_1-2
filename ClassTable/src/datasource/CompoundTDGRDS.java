package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import database.DatabaseException;
import database.DatabaseManager;

/**
 * RDS For Compounds table many-to-many relationship
 * 
 * @author kimberlyoneill
 *
 */
public class CompoundTDGRDS implements CompoundTDG {
  String sql = "SELECT * FROM Compound INNER JOIN Chemical ";
  
  private static CompoundTDGRDS singleton;

  /**
   * Singleton
   * @return
   */
  public static CompoundTDGRDS getSingleton() {
    if (singleton == null) {
      singleton = new CompoundTDGRDS();
    }
    return singleton;
  }

  /**
   * Add method
   * 
   * @param compoundId
   * @param madeOf
   * @param name
   * @param inhabits
   */
  public void addCompound(int compoundId, List<Integer> madeOf, String name, double inventory) {
    try {
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inventory)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, compoundId);
      insertChemical.setString(2, name);
      insertChemical.setDouble(3, inventory);

      insertChemical.execute();

      for (int i = 0; i < madeOf.size(); i++) {
        PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
            .prepareStatement("INSERT INTO Compound(compoundId, elementId)" + "VALUES (?, ?);");

        insert.setInt(1, compoundId);
        insert.setInt(2, madeOf.get(i));
        insert.execute();
      }

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Problem inserting CompoundsMadeOfTableDataGatewayRDS");
    }
  }
  
  /**
   * Delete a compound from the database. 
   */
  @Override
  public void delete(int compoundId) {
    try {
      PreparedStatement sql = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("DELETE FROM Compound WHERE compoundId = " + compoundId + ";");
      sql.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Get all elementId's of of compoundId
   * 
   * @param compoundId
   *          to search for
   */
  @Override
  public ArrayList<CompoundDTO> findMadeOf(int compoundId) {
    String sql = "SELECT * FROM Compound INNER JOIN Chemical WHERE compoundId = " + compoundId + ";";
    ArrayList<CompoundDTO> compounds = new ArrayList<CompoundDTO>();

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      // While there are still results to search through
      while (rs.next()) {
        // Add each result to compound list
        compounds.add(new CompoundDTO(compoundId, rs.getInt("elementId"), 
            rs.getString("name"), rs.getDouble("inventory")));
      }
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    
    return compounds;
  }
  
  /**
   * Get all compoundId's of elementId
   * 
   * @param elementId
   *          to search for
   */
  @Override
  public ArrayList<CompoundDTO> findMakes(int elementId) { 
    String sql = "SELECT * FROM Compound WHERE elementId = " + elementId + ";"; 
    ArrayList<CompoundDTO> compounds = new ArrayList<CompoundDTO>(); 
    try { 
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement(); 
      ResultSet thatboy = statement.executeQuery(sql); 
      // While there are still results to search through 
      while (thatboy.next()) { 
        // Add each result to compound list 
        compounds.add(this.getDTO(thatboy.getInt("compoundId"))); 
      } 
    } catch (SQLException | DatabaseException e) { 
      e.printStackTrace(); 
    } 
    return compounds; 
  } 

  /**
   * Create a dto from a given compoundId. 
   * @param id int compoundid
   */
  public CompoundDTO getDTO(int id) {
    String sql = "SELECT * FROM Element INNER JOIN Chemical ON Element.elementId = Chemical.chemicalId AND elementId = "
        + id + ";";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next();

      return new CompoundDTO(rs.getInt("compoundId"), rs.getInt("elementId"), rs.getString("name"),
          rs.getDouble("inventory"));

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("No entry with id " + id);
    }
    return null;
  }
  
  @Override
  public List<CompoundDTO> executeQuery() throws DatabaseException {
    List<CompoundDTO> listDTO = new ArrayList<>();
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(this.sql + ";");
      try {
        ResultSet results = statement.executeQuery();

        sql = "";
        while (results.next()) {
          int compoundId = results.getInt("compoundId");
          int elementId = results.getInt("elementId");
          String name = results.getString("name");
          double inventory = results.getDouble("inventory");
          
          CompoundDTO base = new CompoundDTO(compoundId, elementId, name, inventory);
          listDTO.add(base);
        }
      } catch (SQLException e) {
        throw new DatabaseException("Failed to convert query to DTO.", e);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
    return listDTO;
  }

  public CompoundTDGRDS getAllCompounds() {
    sql = "SELECT * FROM Compound INNER JOIN Chemical ";
    return getSingleton();
  }

  public CompoundTDGRDS filterByName(String name) {
    sql += " AND (Chemical.name LIKE '%" + name + "%') ";
    return getSingleton();
  }

  @Override
  public CompoundTDGRDS filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ") ";
    return getSingleton();
  }

  @Override
  public CompoundTDGRDS filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ") ";
    return getSingleton();
  }
  
  @Override
  public CompoundTDGRDS filterByElements(int elementId) {
    sql += " AND Compound.elementId = " + elementId;
    return getSingleton();
  }
}
