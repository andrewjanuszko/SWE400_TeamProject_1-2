package database;

import java.sql.SQLException;
import java.sql.Statement;

public class ClassTableInitializer {
  
  public static void createTables() {
    try {
      Statement stmt = DatabaseManager.getSingleton().getConnection().createStatement();
      
      String[] create = { 
          
          "CREATE TABLE IF NOT EXISTS Chemical(chemicalId INT NOT NULL AUTO_INCREMENT, name VARCHAR(20), "
              + "inventory DOUBLE, PRIMARY KEY (chemicalId));",
              
          "CREATE TABLE IF NOT EXISTS Acid" + "(acidId INT NOT NULL, solute INT, " 
              + "FOREIGN KEY(acidId) REFERENCES Chemical(chemicalId));",
              
          "CREATE TABLE IF NOT EXISTS Base(baseId INT NOT NULL, solute INT, "
              + "FOREIGN KEY(baseId) REFERENCES Chemical(chemicalId));",
              
          "CREATE TABLE IF NOT EXISTS Element(elementId INT NOT NULL, atomicNumber INT, "
              + "atomicMass DOUBLE, FOREIGN KEY(elementId) REFERENCES Chemical(chemicalId));",
              
          "CREATE TABLE IF NOT EXISTS Metal(metalId INT NOT NULL, dissolvedBy INT, moles DOUBLE, "
              + "FOREIGN KEY(dissolvedBy) REFERENCES Acid(acidId), "
              + "FOREIGN KEY(metalId) REFERENCES Element(elementId)); ",
              
          "CREATE TABLE IF NOT EXISTS Compound(compoundId INT NOT NULL, "
              + "elementId INT NOT NULL, FOREIGN KEY (compoundId) REFERENCES Chemical(chemicalId), "
              + "FOREIGN KEY (elementId) REFERENCES Element(elementId));"    
      };
      
      for (int i = 0; i < create.length; i++) {
        stmt.execute(create[i]);
      }
      
    } catch(DatabaseException | SQLException e) {
      e.printStackTrace();
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
  
}
