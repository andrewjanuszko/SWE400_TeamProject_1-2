package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MetalRowDataGatewayRDS implements MetalRowDataGateway {

  int metalId;
  int dissolvedById;
  String name;
  String inhabits;
  
  public MetalRowDataGatewayRDS(int id) {
    this.createTableMetal();
    this.metalId = id;

    String sqlChem = "SELECT * FROM Chemical INNER JOIN Metal ON Chemical.chemicalId = " + id + ";";
    String sqlElement = "SELECT * FROM Element where elementId = " + id + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sqlElement);
      rs.next();
      this.dissolvedById = rs.getInt("dissolvedBy");

      rs = statement.executeQuery(sqlChem);
      rs.next();
      this.name = rs.getString("name");
      this.inhabits = rs.getString("inhabits");

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("No entry with id " + id);
    }

  }

  public MetalRowDataGatewayRDS(int id, int dissolvedById, String name, String inhabits) {
    this.createTableMetal();
    try {
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, id);
      insertChemical.setString(2, name);
      insertChemical.setString(3, inhabits);
      PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Metal (metalId, dissolvedBy)" + "VALUES (?, ?);");

      insert.setInt(1, id);
      insert.setInt(2, dissolvedById);

      insertChemical.execute();
      insert.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to insert");
    }
  }
  public void createTableMetal() {
    String dropTable = "DROP TABLE IF EXISTS Metal;";
    String createTable = "CREATE TABLE Metal" + "(" 
        + "metalId INT NOT NULL, "
        + "dissolvedBy INT," 
        + "FOREIGN KEY(dissolvedBy) REFERENCES Acid(acidId),"
        + "FOREIGN KEY(metalId) REFERENCES Chemical(chemicalId)"
        + ");"; 
    
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
  public String getName() {
    return this.name;
  }

  @Override
  public String getInhabits() {
    return this.inhabits;
  }

  @Override
  public int getDissolvedBy() {
    return this.dissolvedById;
  }
  @Override
  public void delete(int id) {

    String sqlMetal = "DELETE FROM Metal WHERE metalId = " + id + ";";
    String sqlChem = "DELETE FROM Chemical WHERE chemicalId = " + id + ";";
    try {

      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      statement.executeUpdate(sqlMetal);
      statement.executeUpdate(sqlChem);

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("No entry with id " + id);
    }
  }

  @Override
  public void update(int id, int dissolvedById, String name, String inhabits) {
    try {
      PreparedStatement updateMetal = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Element SET atomicNumber = ?, atomicMass = ? WHERE elementId = ?;");
      updateMetal.setInt(1, dissolvedById);
      updateMetal.setInt(2, id);
      
      PreparedStatement updateChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("UPDATE Chemical SET name = ?, inhabits = ? WHERE chemicalId = ?;");
      updateChemical.setString(1, name);
      updateChemical.setString(2, inhabits);
      updateChemical.setInt(3, id);
      
      updateMetal.execute();
      updateChemical.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Failed to update");
    }

  }
}