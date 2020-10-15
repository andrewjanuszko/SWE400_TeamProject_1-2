package datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class ElementTDGRDS implements ElementTDG {

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

}
