package datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class MetalTDGRDS implements MetalTDG {

  private static MetalTDGRDS singleton;
  
  public MetalTDGRDS() {
    // TODO Auto-generated constructor stub
  }
  
  public static MetalTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new MetalTDGRDS();
    }
    return singleton;
  }

  @Override
  public List<MetalDTO> getAllMetals() {
    String sql = "SELECT * FROM Metal INNER JOIN Chemical WHERE Metal.metalId = Chemical.chemicalId;";
    List<MetalDTO> metals = new ArrayList<MetalDTO>();
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        metals.add(new MetalDTO(rs.getInt("metalId"), rs.getInt("dissolvedBy"), 
            rs.getString("name"), rs.getDouble("inventory")));
      }
      return metals;
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
      return null;
    }
  }

}
