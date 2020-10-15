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
  String sql = "SELECT * FROM Chemical ";
  private static ChemicalTDGRDS singleton;

  public ChemicalTDGRDS() {
    // TODO Auto-generated constructor stub
  }

  public static ChemicalTDGRDS getSingleton() {
    if (singleton == null) {
      singleton = new ChemicalTDGRDS();
    }
    return singleton;
  }

  public void filterByName(String name) {
    sql += " AND (Chemical.name LIKE '" + name + "' ";
  }

  @Override
  public void filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ")";
  }

  @Override
  public void filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ")";
  }

  @Override
  public List<ChemicalDTO> executeQuery() throws DatabaseException {
    List<ChemicalDTO> listDTO = new ArrayList<>();
    try {
      PreparedStatement statement = DatabaseManager.getSingleton().getConnection().prepareStatement(this.sql + ";");
      try {
        ResultSet results = statement.executeQuery();

        sql = "";
        while (results.next()) {
          int baseId = results.getInt("baseId");
          String name = results.getString("name");
          double inventory = results.getDouble("inventory");
          ChemicalDTO base = new ChemicalDTO(baseId, name, inventory);
          listDTO.add(base);
        }
      } catch (SQLException e) {
        throw new DatabaseException("Failed to convert query to DTO.", e);
      }
    } catch (SQLException e) {
      throw new DatabaseException("Failed to execute query.", e);
    }
    return listDTO;
  }

  @Override
  public void getAllChemicals() {
//
  }

}
