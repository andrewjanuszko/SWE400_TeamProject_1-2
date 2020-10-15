package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class ElementTDGRDS implements ElementTDG {
  String sql = "SELECT * FROM Element INNER JOIN Chemical ";
  private static ElementTDGRDS singleton;
  
  public ElementTDGRDS() {
    // TODO Auto-generated constructor stub
  }

  public static ElementTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new ElementTDGRDS();
    }
    return singleton;
  }
  
  @Override
  public List<ElementDTO> getAllElements() {
    String sql = "SELECT * FROM Element INNER JOIN Chemical WHERE Element.elementId = Chemical.chemicalId;";
    List<ElementDTO> elements = new ArrayList<ElementDTO>();
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        elements.add(new ElementDTO(rs.getInt("elementId"), rs.getInt("atomicNumber"), 
            rs.getDouble("atomicMass"), rs.getString("name"), rs.getDouble("inventory")));
      }
      return elements;
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
      return null;
    }  
  }
  
  public List<ElementDTO> findSetAtomicMass(double lowerLimit, double upperLimit) {
    List<ElementDTO> results = new ArrayList<>();
    try {
      String sql = "SELECT * FROM Metal WHERE atomicMass BETWEEN " + lowerLimit + " AND " + upperLimit + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        ElementDTO element = getDTO(rs.getInt("elementId"));
        results.add(element);
      }
    } catch (SQLException | DatabaseException e) {

    }
    return results;
  }
  
  public ElementDTO getDTO(int id) {
    String sql = "SELECT * FROM Element INNER JOIN Chemical ON Element.elementId = Chemical.chemicalId AND elementId = "
        + id + ";";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      rs.next();

      return new ElementDTO(id, rs.getInt("atomicNum"), rs.getDouble("atomicMass"), rs.getString("name"),
          rs.getDouble("inventory"));

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("No entry with id " + id);
    }
    return null;
  }
  
  public List<ElementDTO> executeQuery() throws DatabaseException {
    List<ElementDTO> listDTO = new ArrayList<>();
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(this.sql + ";");
      try {
        ResultSet results = statement.executeQuery();

        sql = "";
        while (results.next()) {
          int acidId = results.getInt("acidId");
          int atomicNum = results.getInt("atomicNumber");
          double atomicMass = results.getDouble("atomicMass");
          String name = results.getString("name");
          double inventory = results.getDouble("inventory");
          ElementDTO element = new ElementDTO(acidId, atomicNum, atomicMass, name, inventory);
          listDTO.add(element);
        }
      } catch (SQLException e) {
        throw new DatabaseException("Failed to convert query to DTO.", e);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
    return listDTO;
  }

  public void filterByName(String name) {
    sql += " AND (Chemical.name LIKE '" + name + "' ";
  }

  public void filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ")";
  }

  public void filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ")";
  }
  
  public void filterByAtomicMass(double atomicMass) {
    sql += " AND (Element.atomicMass = " + atomicMass + ")";
  }
  
  public void filterByAtomicMassRange(double high, double low) {
    sql += " AND (Element.atomicMass BETWEEN " + low + " AND " + high + ")";
  }
  
  public void filterByAtomicNumber(int atomicNumber) {
    sql += " AND (Element.atomicNumber = " + atomicNumber + ")";
  }
  
  public void filterByAtomicNumberRange(int high, int low) {
    sql += " AND (Element.atomicNumber BETWEEN " + low + " AND " + high + ")";
  }
  
}
