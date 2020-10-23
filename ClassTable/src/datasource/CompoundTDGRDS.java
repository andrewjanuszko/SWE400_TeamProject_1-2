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
   * 
   * @return
   */
  public static CompoundTDGRDS getSingleton() {
    if (singleton == null) {
      singleton = new CompoundTDGRDS();
    }
    return singleton;
  }

  private ElementDTO elementIdToDTO(int id) {
    ElementRDG element = new ElementRDGRDS(id);
    return element.getElement();
  }

  private CompoundDTO getDTO(int id) throws Exception {
    try {
      String sql = "SELECT * FROM Compound WHERE compoundId = " + id + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);

      // Get all elements connected to compound
      List<ElementDTO> elements = new ArrayList<>();
      while (rs.next()) {
        elements.add(elementIdToDTO(rs.getInt("elementId")));
      }

      return (new CompoundDTO(id, elements, rs.getString("name"), rs.getDouble("inventory")));

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      throw new Exception("Failed to read" + id, e);
    }
  }

  @Override
  public List<CompoundDTO> executeQuery() throws DatabaseException {
    List<CompoundDTO> listDTO = new ArrayList<>();

    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(this.sql + ";");

      ResultSet results = statement.executeQuery();

      sql = "";
      while (results.next()) {
        listDTO.add(getDTO(results.getInt("compoundId")));
      }

    } catch (Exception e) {
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
    sql += " AND Compound.elementId = " + elementId + ")";
    return getSingleton();
  }

  public CompoundTDGRDS filterByCompoundId(int compoundId) {
    sql += "AND Compound.compoundId = " + compoundId + ")";
    return getSingleton();

  }

}
