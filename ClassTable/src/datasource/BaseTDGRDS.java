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
  
  String sql = "SELECT * FROM Base INNER JOIN Chemical WHERE (Base.BaseId = Chemical.chemicalId)";
  private static BaseTDGRDS singleton;
  
  public BaseTDGRDS() {
    sql = "(SELECT * FROM Base INNER JOIN Chemical WHERE Base.BaseId = Chemical.chemicalId)";
  }
  
  public static BaseTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new BaseTDGRDS();
    }
    return singleton;
  }
  
  public static void delete(int baseId) {
    String deleteChemical = "DELETE FROM Chemical WHERE ChemicalId = " + baseId + ";",
        deleteBase = "DELETE FROM Base WHERE BaseId = " + baseId + ";";
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
      statement.executeUpdate(deleteBase);
      statement.executeUpdate(deleteChemical);
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
      
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Error deleting base " + baseId);
    }
  }
  
  public BaseTDGRDS filterByName(String name) {
    sql +=  " AND (Chemical.name LIKE '%" + name + "%') ";
    System.out.println(sql);
    return getSingleton();
  }

  @Override
  public BaseTDGRDS filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ") ";
    System.out.println(sql);
    return getSingleton();
  }

  @Override
  public BaseTDGRDS filterBySolute(int solute) {
    sql += " AND (Base.solute = " + solute + ") ";
    System.out.println(sql);
    return getSingleton();
  }

  @Override
  public BaseTDGRDS filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ") ";
    System.out.println(sql);
    return getSingleton();
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
        sql = "SELECT * FROM Base INNER JOIN Chemical WHERE (Base.baseId = Chemical.chemicalId)";
      } catch (SQLException e) {
        throw new DatabaseException("Failed to convert query to DTO.", e);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
    return listDTO;
  }

  @Override
  public BaseTDGRDS getAllBases() {
    sql = "SELECT * FROM Base INNER JOIN Chemical WHERE (Base.baseId = Chemical.chemicalId)";
    return getSingleton();
  }
}
