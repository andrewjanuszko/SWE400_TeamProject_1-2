package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class ChemicalTDGRDS implements ChemicalTDG {
  String sql = "SELECT * FROM Chemical";
  private static ChemicalTDGRDS singleton;

  public ChemicalTDGRDS() {
    sql = "SELECT * FROM Chemical";
  }

  public static ChemicalTDGRDS getSingleton() {
    if (singleton == null) {
      singleton = new ChemicalTDGRDS();
    }
    return singleton;
  }

  public ChemicalTDGRDS filterByName(String name) {
    if(!(sql.contains("WHERE"))) {
      sql += " WHERE (name LIKE '%" + name + "%')";
    } else {
      sql += " AND (name LIKE '%" + name + "%')";
    }
    
    System.out.println(sql);
    
    return getSingleton();
  }

  @Override
  public ChemicalTDGRDS filterByInventory(double inventory) {
    if(!(sql.contains("WHERE"))) {
      sql += " WHERE (inventory = '" + inventory + "')";
    } else {
      sql += " AND (inventory = '" + inventory + "')";
    }
    System.out.println(sql);
    return getSingleton();
  }

  @Override
  public ChemicalTDGRDS filterByInventoryRange(double high, double low) {
    if(!(sql.contains("WHERE"))) {
      sql += " WHERE (inventory BETWEEN " + low + " AND " + high + ")";
    } else {
      sql += " AND (inventory BETWEEN " + low + " AND " + high + ")";
    }
    System.out.println(sql);
    return getSingleton();
  }

  @Override
  public List<ChemicalDTO> executeQuery() throws DatabaseException {
    List<ChemicalDTO> listDTO = new ArrayList<>();
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(this.sql + ";");
      try {
        ResultSet results = statement.executeQuery();

        while (results.next()) {
          
          int baseId = results.getInt("chemicalId");
          String name = results.getString("name");
          double inventory = results.getDouble("inventory");
          ChemicalDTO base = new ChemicalDTO(baseId, name, inventory);
          listDTO.add(base);
        }
        
        // Reset SQL string
        sql = "SELECT * FROM Chemical";
      } catch (SQLException e) {
        throw new DatabaseException("Failed to convert query to DTO.", e);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
    return listDTO;
  }

  @Override
  public ChemicalTDGRDS getAllChemicals() {
    sql = "SELECT * FROM Chemical";
    return getSingleton();
  }

}
