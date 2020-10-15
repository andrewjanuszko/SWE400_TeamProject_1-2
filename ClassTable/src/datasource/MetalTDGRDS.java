package datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import database.DatabaseManager;

public class MetalTDGRDS implements MetalTDG {
  String sql = "SELECT * FROM Metal INNER JOIN Chemical WHERE Metal.metalId = Chemical.chemicalId "
      + "INNER JOIN Element WHERE Element.elementId = Metal.metalId ";
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
  public void getAllMetals() {
    
  }

  public void filterByName(String name) {
    sql += " AND (Chemical.name LIKE '" + name + "' ";
  }

  public void filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ")";
  }

  public void filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ")";
  }
  
  public void filterByAtomicMass(double atomicMass) {
    sql += " AND (Element.atomicMass = " + atomicMass + ")";
  }
  
  public void filterByAtomicMassRange(double high, double low) {
    sql += " AND (Element.atomicMass BETWEEN " + low + " AND " + high + ")";
  }
  
  public void filterByAtomicNumber(int atomicNumber) {
    sql += " AND (Element.atomicNumber = " + atomicNumber + ")";
  }
  
  public void filterByAtomicNumberRange(int high, int low) {
    sql += " AND (Element.atomicNumber BETWEEN " + low + " AND " + high + ")";
  }
  
}
