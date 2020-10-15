package datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class ChemicalTDGRDS implements ChemicalTDG {

  private static ChemicalTDGRDS singleton;
  public ChemicalTDGRDS() {
    // TODO Auto-generated constructor stub
  }

  public static ChemicalTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new ChemicalTDGRDS();
    }
    return singleton;
  }
  

  public List<ChemicalDTO> getAllChemicals() {
    String sql = "SELECT * FROM Chemical;";
    ArrayList<ChemicalDTO> chemicals = new ArrayList<ChemicalDTO>();
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);

      while (rs.next()) {
        chemicals.add(new ChemicalDTO(rs.getInt("chemicalId"), rs.getString("name"), rs.getDouble("inventory")));
      }
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    return chemicals;
  }
  
}
