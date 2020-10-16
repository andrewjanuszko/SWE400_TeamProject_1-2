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
  List<CompoundDTO> compounds = new ArrayList<>();

  public List<CompoundDTO> create(int id, List<Integer> madeOf, String name, double inventory) throws Exception {
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

      compounds.add(new CompoundDTO(id, elements, name, inventory));

      return compounds;

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
    compounds = new ArrayList<>();
    
    try {
      String sql = "SELECT * FROM Compound WHERE compoundId = " + id + ";";
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      
      // Get all elements connected to compound
      List<ElementDTO> elements = new ArrayList<>(); 
      while(rs.next()) {
        elements.add(elementIdToDTO(rs.getInt("elementId")));
      }
      
      compounds.add(new CompoundDTO(id, elements, rs.getString("name"), rs.getDouble("inventory")));
      
      return compounds;
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      throw new Exception("Failed to read" + id, e);
    } 
  }

  public CompoundDTO update(CompoundDTO compound) {
    // delete, re add
    return compound;

  }

  public CompoundDTO delete(int id) {
    return null;
  }

}
