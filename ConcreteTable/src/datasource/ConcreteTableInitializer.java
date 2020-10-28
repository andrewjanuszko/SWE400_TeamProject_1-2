package datasource;

import java.sql.SQLException;
import java.sql.Statement;

import model.AcidDataMapper;
import model.BaseDataMapper;
import model.CompoundDataMapper;
import model.ElementDataMapper;
import model.MetalDataMapper;

public class ConcreteTableInitializer {
  public static void createTables() {
    try {
      Statement stmt = DatabaseManager.getSingleton().getConnection().createStatement();

      String[] create = {
          "CREATE TABLE Acid (" + "acidID INT NOT NULL AUTO_INCREMENT, " + "name VARCHAR(30) NOT NULL, "
              + "inventory Double, " + "solute INT, " + "UNIQUE(name), " + "PRIMARY KEY(acidID) );",
          "CREATE TABLE Base (" + "baseID INT NOT NULL AUTO_INCREMENT, " + "name VARCHAR(30) NOT NULL, "
              + "inventory DOUBLE, " + "solute INT, " + "UNIQUE(name), " + "PRIMARY KEY(baseID)) ;",
          "CREATE TABLE Metal (" + "metalID INT NOT NULL AUTO_INCREMENT, " + "name VARCHAR(30) NOT NULL, "
              + "inventory Double, " + "atomicNumber INT NOT NULL, " + "atomicMass DOUBLE NOT NULL, "
              + "acidAmount DOUBLE NOT NULL, " + "dissolvedBy INT, " + "UNIQUE(name), " + "PRIMARY KEY(metalID), "
              + "FOREIGN KEY(dissolvedBy) REFERENCES Acid(acidID)); ",
          "CREATE TABLE Element (" + "elementID INT NOT NULL AUTO_INCREMENT, " + "name VARCHAR(30) NOT NULL, "
              + "inventory Double, " + "atomicNumber INT NOT NULL, " + "atomicMass DOUBLE NOT NULL," + "UNIQUE(name),"
              + "PRIMARY KEY(elementID));",
          "CREATE TABLE Compound (" + "compoundID INT NOT NULL AUTO_INCREMENT, " + "name VARCHAR(30) NOT NULL, "
              + "inventory Double, " + "UNIQUE(name), " + "PRIMARY KEY(compoundID)) ;",
          "CREATE TABLE CompoundMadeOf (" + "compoundID INT NOT NULL," + "elementID INT," + "metalID INT,"
              + "FOREIGN KEY(compoundID) REFERENCES Compound(compoundID), "
              + "FOREIGN KEY(elementID) REFERENCES Element(elementID), "
              + "FOREIGN KEY(metalID) REFERENCES Metal(metalID));" };
      
      for (int i = 0; i < create.length; i++) {
        stmt.execute(create[i]);
      }
    } catch (DatabaseException | SQLException e) {
      //e.printStackTrace();
    }
  }
  
  public static void dropTables() {
    try {
      Statement stmt = DatabaseManager.getSingleton().getConnection().createStatement();
      
      String[] drop = {    
          "SET FOREIGN_KEY_CHECKS = 0;",
          "DROP TABLE IF EXISTS Chemical;",
          "DROP TABLE IF EXISTS Acid;",
          "DROP TABLE IF EXISTS Base;",
          "DROP TABLE IF EXISTS Compound;",
          "DROP TABLE IF EXISTS Element;",
          "DROP TABLE IF EXISTS Metal;",
          "SET FOREIGN_KEY_CHECKS = 0;"
      };
      
      for (int i = 0; i < drop.length; i++ ) {
        stmt.execute(drop[i]);
      }
    } catch(SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }
  public static void clearMaps() {
    AcidDataMapper.acidMap.clear();
    BaseDataMapper.baseMap.clear();
    CompoundDataMapper.compoundMap.clear();
    ElementDataMapper.elementMap.clear();
    MetalDataMapper.metalMap.clear();
    
  }
}
