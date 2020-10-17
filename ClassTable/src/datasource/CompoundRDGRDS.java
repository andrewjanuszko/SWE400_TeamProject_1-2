package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class CompoundRDGRDS {
  CompoundDTO compound;

  public CompoundDTO create(int id, List<Integer> madeOf, String name, double inventory) throws Exception {
    try {
      List<ElementDTO> elements = new ArrayList<>();
      // Insert compound
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inventory) VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setDouble(3, inventory);

      insertChemical.execute();

      // Insert all compounds
      for (int i = 0; i < madeOf.size(); i++) {
        PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
            .prepareStatement("INSERT INTO Compound(compoundId, elementId) VALUES (?, ?);");

        insert.setInt(1, id);
        insert.setInt(2, madeOf.get(i));
        insert.execute();

        // List of elements
        elements.add(elementIdToDTO(madeOf.get(i)));
      }

      compound = (new CompoundDTO(id, elements, name, inventory));

      return compound;

    } catch (SQLException | DatabaseException e) {
      throw new Exception("CompoundRDGRDS", e);
    }
  }
  
  public void create(CompoundDTO compound) throws Exception {
    try {
      List<ElementDTO> elements = new ArrayList<>();
      // Insert compound
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inventory) VALUES (?, ?, ?);");
      insertChemical.setInt(1, compound.getCompoundId());
      insertChemical.setString(2, compound.getName());
      insertChemical.setDouble(3, compound.getInventory());

      insertChemical.execute();

      // Insert all compounds
      for (int i = 0; i < compound.getElements().size(); i++) {
        PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
            .prepareStatement("INSERT INTO Compound(compoundId, elementId) VALUES (?, ?);");

        insert.setInt(1, compound.getCompoundId());
        insert.setInt(2, compound.getElements().get(i).getElementId()); 
        insert.execute();
      }
    } catch (SQLException | DatabaseException e) {
      throw new Exception("CompoundRDGRDS", e);
    }
  }

  /**
   * 
   * @param id
   * @return
   */
  private ElementDTO elementIdToDTO(int id) {
    ElementRDG element = new ElementRDGRDS(id);
    return element.getElement();
  }

  public CompoundDTO read(int id) throws Exception {
    try {
      String sql = "SELECT * FROM Compound WHERE compoundId = " + id + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      
      // Get all elements connected to compound
      List<ElementDTO> elements = new ArrayList<>(); 
      while(rs.next()) {
        elements.add(elementIdToDTO(rs.getInt("elementId")));
      }
      
      compound = (new CompoundDTO(id, elements, rs.getString("name"), rs.getDouble("inventory")));
      
      return compound;
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      throw new Exception("Failed to read" + id, e);
    } 
  }

  public CompoundDTO update(CompoundDTO compound) {
    // Note: This might get slow when we get a compound with a LOT of elements
    try {
      delete(compound.getCompoundId());
      create(compound);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this.compound;
  }

  public void delete(int id) {
    try {
      PreparedStatement sql = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("DELETE FROM Compound WHERE compoundId = " + id + ";");
      sql.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

}
