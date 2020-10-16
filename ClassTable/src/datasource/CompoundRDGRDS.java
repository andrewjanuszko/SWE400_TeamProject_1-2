package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class CompoundRDGRDS {
  List<CompoundDTO> compounds = new ArrayList<>();
  
  public List<CompoundDTO> create(int id, List<Integer> madeOf, String name, double inventory) throws Exception {
    try {
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
        
        compounds.add(new CompoundDTO(id, madeOf.get(i), name, inventory));
        
      }
      return compounds;

    } catch (SQLException | DatabaseException e) {
      throw new Exception();
    }
  }
  public CompoundDTO read(int id) {
    return null;
    
  }
  public CompoundDTO update(CompoundDTO compound) {
    // delete, re add 
    return compound;
    
  }
  public CompoundDTO delete(int id) {
    return null; 
  }
}
