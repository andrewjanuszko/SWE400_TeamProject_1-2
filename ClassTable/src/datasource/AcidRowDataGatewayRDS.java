package datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AcidRowDataGatewayRDS implements AcidRowDataGateway {

  @Override
  public void createTableAcid() {
    String dropTable = "DROP TABLE IF EXISTS Acid;";
    String createTable = "CREATE TABLE Acid" + "(" + "acidId INT NOT NULL, " + "solute VARCHAR(20), "
        + "FOREIGN KEY(acidId) REFERENCES Chemical(chemicalId)" + ");";
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      // Drop the table if exists first
      statement.executeUpdate(dropTable);
      // Create new Monitorings Table
      statement.executeUpdate(createTable);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getSolute() {
    // TODO Auto-generated method stub
    return null;
  }
  
}
