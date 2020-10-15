package datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class AcidTDGRDS implements AcidTDG{

  private static AcidTDGRDS singleton;
  
  public AcidTDGRDS() {
 
  }
  
  public static AcidTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new AcidTDGRDS();
    }
    return singleton;
  }

  /**
   * Get all acids in the database. 
   */
  public List<AcidDTO> getAllAcids() {
    String sql = "SELECT * FROM Acid INNER JOIN Chemical WHERE Acid.acidId = Chemical.chemicalId;";
    ArrayList<AcidDTO> acids = new ArrayList<AcidDTO>();
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);

      while (rs.next()) {
        acids.add(
            new AcidDTO(rs.getInt("acidId"), rs.getInt("solute"), rs.getString("name"), rs.getDouble("inventory")));
      }
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    return acids;
  }

}
