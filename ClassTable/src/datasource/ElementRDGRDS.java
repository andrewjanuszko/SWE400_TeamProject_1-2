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
 * Element RDS
 * Row data Gateway
 * @author kimberlyoneill
 *
 */
public class ElementRDGRDS implements ElementRDG {
  ElementDTO element;

  /**
   * Empty constructor 
   */
  public ElementRDGRDS() {
  }

  /**
   * constructor to search for an element
   * @param id
   */
  public ElementRDGRDS(int id) {
    String sqlChem = "SELECT * FROM Chemical INNER JOIN Element ON Chemical.chemicalId = " + id + ";";
    String sqlElement = "SELECT * FROM Element WHERE elementId = " + id + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlElement);
      rs.next();
      int atomicNumber = rs.getInt("atomicNumber");
      double atomicMass = rs.getDouble("atomicMass");

      rs = statement.executeQuery(sqlChem);
      rs.next();
      element = new ElementDTO(id, atomicNumber, atomicMass, rs.getString("name"), rs.getDouble("inventory"));

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
      
      element = new ElementDTO(id, atomicNum, atomicMass, name, inventory);
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert");
    }
  }

  /**
   * deletes Element already held by the RDS
   */
  @Override
  public void delete() {

    String sqlElement = "DELETE FROM Element WHERE elementId = " + element.getElementId() + ";";
    String sqlChem = "DELETE FROM Chemical WHERE chemicalId = " + element.getElementId() + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(sqlElement);
      statement.executeUpdate(sqlChem);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Problem deleting Element with id " + element.getElementId());
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
      updateElement.setInt(1, element.getAtomicNumber());
      updateElement.setDouble(2, element.getAtomicMass());
      updateElement.setInt(3, element.getElementId());

      PreparedStatement updateChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Chemical SET name = ?, inventory = ? WHERE chemicalId = ?;");
      updateChemical.setString(1, element.getName());
      updateChemical.setDouble(2, element.getInventory());
      updateChemical.setInt(3, element.getElementId());

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
  public ElementDTO findByAtomicNumber(int atomicNum) {
    int id;
    double atomicMass, inventory;
    String name;
    String sqlElement = "SELECT * FROM Element WHERE atomicNumber = " + atomicNum + ";";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlElement);
      rs.next();
      id = rs.getInt("elementId");
      atomicMass = rs.getDouble("atomicMass");

      String sqlChem = "SELECT * FROM Chemical INNER JOIN Element ON Chemical.chemicalId = " + id + ";";
      rs = statement.executeQuery(sqlChem);
      rs.next();
      
      name = rs.getString("name");
      inventory = rs.getDouble("inventory");
      
      return new ElementDTO(id, atomicNum, atomicMass, name, inventory);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      return null; 
    }
  }
  
  /**
   * finds entry by atomic Mass
   */
  @Override
  public ElementDTO findByAtomicMass(double atomicMass) {
    int id, atomicNum;
    double inventory;
    String name;
    
    String sqlElement = "SELECT * FROM Element WHERE atomicMass = " + atomicMass + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlElement);
      rs.next();
      id = rs.getInt("elementId");
      atomicNum = rs.getInt("atomicNumber");

      String sqlChem = "SELECT * FROM Chemical INNER JOIN Element ON Chemical.chemicalId = " + id + ";";
      rs = statement.executeQuery(sqlChem);
      rs.next();
      name = rs.getString("name");
      inventory = rs.getDouble("inventory");
      
      return new ElementDTO(id, atomicNum, atomicMass, name, inventory);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
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

  @Override
  public void setElementId(int elementId) {
    element.setElementId(elementId);;
  }

  @Override
  public void setAtomicNumber(int atomicNumber) {
    element.setAtomicNumber(atomicNumber);
  }

  @Override
  public void setAtomicMass(double atomicMass) {
    element.setAtomicMass(atomicMass);
  }

  @Override
  public void setName(String name) {
    element.setName(name);
  }

  @Override
  public void setInventory(double inventory) {
    element.setInventory(inventory);
  }
  
  public ElementDTO getElement() {
    return element;
  }
  
}
