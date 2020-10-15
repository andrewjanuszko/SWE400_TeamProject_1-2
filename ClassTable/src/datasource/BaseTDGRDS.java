package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class BaseTDGRDS implements BaseTDG {
  
  String sql = "SELECT * FROM Acid INNER JOIN Chemical WHERE Acid.acidId = Chemical.chemicalId ";
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
  
  public void filterByName(String name) {
    sql +=  " AND (Chemical.name LIKE '" + name + "') ";
    System.out.println(sql);
  }

  @Override
  public void filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ") ";
    System.out.println(sql);
  }

  @Override
  public void filterBySolute(int solute) {
    sql += " AND (Acid.solute = " + solute + ") ";
    System.out.println(sql);
  }

  @Override
  public void filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ") ";
    System.out.println(sql);
  }

  @Override
  public List<BaseDTO> executeQuery() throws DatabaseException {
    List<BaseDTO> listDTO = new ArrayList<>();
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(this.sql + ";");
      try {
        ResultSet results = statement.executeQuery();

        while (results.next()) {
          int baseId = results.getInt("baseId");
          int soluteId = results.getInt("solute");
          String name = results.getString("name");
          double inventory = results.getDouble("inventory");
          BaseDTO base = new BaseDTO(baseId, soluteId, name, inventory);
          listDTO.add(base);
        }
        
        // Reset sql string
        sql = "SELECT * FROM Acid INNER JOIN Chemical WHERE Acid.acidId = Chemical.chemicalId ";
      } catch (SQLException e) {
        throw new DatabaseException("Failed to convert query to DTO.", e);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
    return listDTO;
  }

  @Override
  public void getAllBases() {
    
  }
}
