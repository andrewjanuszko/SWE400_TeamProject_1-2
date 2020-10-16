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
  public MetalTDGRDS getAllMetals() {
    sql = "SELECT * FROM Metal INNER JOIN Chemical WHERE Metal.metalId = Chemical.chemicalId "
        + "INNER JOIN Element WHERE Element.elementId = Metal.metalId ";
    return getSingleton();
  }

  public MetalTDGRDS filterByName(String name) {
    sql += " AND (Chemical.name LIKE '%" + name + "%')";
    return getSingleton();
  }

  public MetalTDGRDS filterByInventory(double inventory) {
    sql += " AND (Chemical.inventory = " + inventory + ")";
    return getSingleton();
  }

  public MetalTDGRDS filterByInventoryRange(double high, double low) {
    sql += " AND (Chemical.inventory BETWEEN " + low + " AND " + high + ")";
    return getSingleton();
  }
  
  public MetalTDGRDS filterByAtomicMass(double atomicMass) {
    sql += " AND (Element.atomicMass = " + atomicMass + ")";
    return getSingleton();
  }
  
  public MetalTDGRDS filterByAtomicMassRange(double high, double low) {
    sql += " AND (Element.atomicMass BETWEEN " + low + " AND " + high + ")";
    return getSingleton();
  }
  
  public MetalTDGRDS filterByAtomicNumber(int atomicNumber) {
    sql += " AND (Element.atomicNumber = " + atomicNumber + ")";
    return getSingleton();
  }
  
  public MetalTDGRDS filterByAtomicNumberRange(int high, int low) {
    sql += " AND (Element.atomicNumber BETWEEN " + low + " AND " + high + ")";
    return getSingleton();
  }
  
  public MetalTDGRDS filterByDissolvedBy() {
    
    return getSingleton();
  }
  
  public MetalTDGRDS filterByMoles() {
    
    return getSingleton();
  }
  
}
