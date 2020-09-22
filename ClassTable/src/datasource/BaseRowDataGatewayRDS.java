package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseRowDataGatewayRDS implements BaseRowDataGateway {

  public void createTableBase() {
    String dropTable = "DROP TABLE IF EXISTS Base;";
    String createTable = "CREATE TABLE Base" + "(" 
        + "baseId INT NOT NULL, "
        + "solute VARCHAR(20), " 
        + "FOREIGN KEY(baseId) REFERENCES Chemical(chemicalId)"
        + ");"; // Not sure how to store solute, is a chemical
    
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      // Drop the table if exists first
      statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;"); 
      statement.executeUpdate(dropTable);
      // Create new Monitorings Table
      statement.executeUpdate(createTable);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void insert(int baseId, int solute) {
    try {
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Base (baseId, solute)" + "VALUES (?, ?);");
      insert.setInt(1, baseId);
      insert.setInt(2, solute);

      insert.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }
}
