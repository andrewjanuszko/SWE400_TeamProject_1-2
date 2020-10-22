package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datadto.AcidDTO;
/**
 * Table Data Gateway for the Acid table
 * @author jeol
 *
 */
public abstract class AcidTableDataGatewayRDS {
  //TODO maybe make singleton.
  /**
   * Creates table in database.
   * @throws DatabaseException
   */
  public static void createTable() throws DatabaseException{
    String drop = "DROP TABLE IF EXISTS Acid";
    String create = "CREATE TABLE Acid (" + 
        "acidID INT NOT NULL, " + 
        "name VARCHAR(30) NOT NULL, " +                      
        "inventory Double, " +
        "solute INT, " +
        "UNIQUE(name), "
        + "PRIMARY KEY(acidID) );";
  
    try
    {
      // drop table
      PreparedStatement stmt;
      stmt = DatabaseManager.getSingleton().getConnection().prepareStatement(drop);
      stmt.execute();
      stmt.close();

      // create table
      stmt = DatabaseManager.getSingleton().getConnection().prepareStatement(create);
      stmt.execute();
      stmt.close();
    } catch (SQLException e) {
      throw new DatabaseException("Unable to create Acid table", e);
    }
  }
  
  /**
   * Only drop the table.
   * @throws DatabaseException
   */
  public static void dropTable() throws DatabaseException{
    String drop = "DROP TABLE IF EXISTS Acid";
    try {
      //drop table
      PreparedStatement stmt;
      stmt = DatabaseManager.getSingleton().getConnection().prepareStatement(drop);
      stmt.execute();
      stmt.close();
    }catch (SQLException e) {
      throw new DatabaseException("Unable to drop Acid table", e);
    }
    
    
  }
  private static List<AcidDTO> toDTOList(ResultSet rs){
    List<AcidDTO> acidDTOs = new ArrayList<AcidDTO>();
    try {
      while(rs.next()) {
        int acidID = rs.getInt("acidID");
        String name = rs.getString("name");
        int solute = rs.getInt("solute");
        double inventory = rs.getDouble("inventory");
        AcidDTO a = new AcidDTO(acidID, solute, name, inventory);
        acidDTOs.add(a);
        return acidDTOs;
      }
      
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return acidDTOs;
  }
  /**
   * Gets all Acids.
   * @return A list of all rows in Acid.
   */
  public static List<AcidDTO> getAll(){
    try {
      PreparedStatement stmt = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("SELECT acidID FROM Acid;");
      stmt.execute();
      ResultSet rs = stmt.executeQuery();
      return toDTOList(rs);
      
    } catch(SQLException | DatabaseException e) {
      new DatabaseException("could not get all");
    } 
    return null;
  }
  
  public static List<AcidDTO> filterByWildCardName(String wildCard){
    try {
      PreparedStatement stmt = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("SELECT acidID FROM Acid WHERE name LIKE '%" + wildCard + "%'");
      ResultSet rs = stmt.executeQuery();
      return toDTOList(rs);
      
      
    } catch (SQLException | DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public static List<AcidDTO> filterByInventory(double inventory){
    try {
      PreparedStatement stmt = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("SELECT acidID FROM Acid WHERE inventory = " + inventory);
      ResultSet rs = stmt.executeQuery();
      return toDTOList(rs);
      
    } catch (SQLException | DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public static List<AcidDTO> filterByInventoryRange(double min, double max){
    try {
      PreparedStatement stmt = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("SELECT acidID FROM Acid WHERE inventory BETWEEN " + min + " AND " + max);
      ResultSet rs = stmt.executeQuery();
      return toDTOList(rs);
      
    } catch (SQLException | DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public static List<AcidDTO> filterBySolute(int chemicalID){
    try {
      PreparedStatement stmt = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("SELECT acidID FROM Acid WHERE solute = " + chemicalID);
      ResultSet rs = stmt.executeQuery();
      return toDTOList(rs);
      
    } catch (SQLException | DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
}
