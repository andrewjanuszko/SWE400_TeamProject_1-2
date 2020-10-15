package datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class BaseTDGRDS implements BaseTDG {

  private static BaseTDGRDS singleton;
  
  public BaseTDGRDS() {
    // TODO Auto-generated constructor stub
  }
  
  public static BaseTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new BaseTDGRDS();
    }
    return singleton;
  }

  @Override
  public List<BaseDTO> getAllBases() {
    String sql = "SELECT * FROM Base INNER JOIN Chemical WHERE Base.baseId = Chemical.chemicalId;";
    List<BaseDTO> bases = new ArrayList<BaseDTO>();
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        bases.add(new BaseDTO(rs.getInt("baseId"), rs.getInt("solute"), 
            rs.getString("name"), rs.getDouble("inventory")));
      }
      return bases;
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
      return null;
    }
  }

}
