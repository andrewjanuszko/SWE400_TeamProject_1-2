package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * RDS For Compounds table many-to-many relationship
 * 
 * @author kimberlyoneill
 *
 */
public class CompoundTDGRDS implements CompoundTDG {

  private int compoundId;
  private List<Integer> madeOf; // Element ids
  private String name;
  private double inventory;

  /**
   * Empty constructor drops and re-creates the table
   */
  public CompoundTDGRDS() {
  }

  /**
   * Constructor to find a compound
   * 
   * @param compoundId
   *          to search for
   */
  public CompoundTDGRDS(int compoundId) {
    String sql = "SELECT * FROM Chemical WHERE chemicalId = " + compoundId + ";";

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);

      rs.next();

      this.name = rs.getString("name");
      this.inventory = rs.getDouble("inventory");
      this.compoundId = compoundId;

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Constructor to insert a Compound
   * 
   * @param compoundId
   * @param madeOf
   * @param name
   * @param inhabits
   */
  public CompoundTDGRDS(int compoundId, List<Integer> madeOf, String name, double inventory) {
    try {
      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inventory)" + "VALUES (?, ?, ?);");
      insertChemical.setInt(1, compoundId);
      insertChemical.setString(2, name);
      insertChemical.setDouble(3, inventory);

      insertChemical.execute();

      for (int i = 0; i < madeOf.size(); i++) {
        PreparedStatement insert = DatabaseManager.getSingleton().getConnection()
            .prepareStatement("INSERT INTO CompoundMadeFromElement (compoundId, elementId)" + "VALUES (?, ?);");

        insert.setInt(1, compoundId);
        insert.setInt(2, madeOf.get(i));
        insert.execute();
      }

      this.compoundId = compoundId;
      this.madeOf = madeOf;
      this.name = name;
      this.inventory = inventory;

    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
      System.out.println("Problem inserting CompoundsMadeOfTableDataGatewayRDS");
    }
  }

  /**
   * Get all compoundId's of elementId
   * 
   * @param elementId
   *          to search for
   */
  @Override
  public ArrayList<CompoundDTO> findMakes(int elementId) {
    String sql = "SELECT * FROM CompoundMadeFromElement WHERE elementId = " + elementId + ";";
    ArrayList<CompoundDTO> compounds = new ArrayList<CompoundDTO>();
    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      // While there are still results to search through
      while (rs.next()) {
        // Add each result to compound list
        compounds.add(new CompoundDTO(rs.getInt("compoundId"), elementId));
      }
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    return compounds;
  }

  /**
   * Get all elementId's of of compoundId
   * 
   * @param compoundId
   *          to search for
   */
  @Override
  public ArrayList<CompoundDTO> findMadeOf(int compoundId) {
    String sql = "SELECT * FROM Compound WHERE compoundId = " + compoundId + ";";
    ArrayList<CompoundDTO> compounds = new ArrayList<CompoundDTO>();

    try {
      Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
      ResultSet rs = statement.executeQuery(sql);
      // While there are still results to search through
      while (rs.next()) {
        // Add each result to compound list
        compounds.add(new CompoundDTO(compoundId,rs.getInt("elementId")));
      }
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
    return compounds;
  }

  /**
   * Deletes entry already held by instance of this object
   */
  @Override
  public void delete() {
    try {
      PreparedStatement sql = DatabaseManager.getSingleton().getConnection()
          .prepareStatement("DELETE FROM CompoundMadeFromElement WHERE compoundId = " + this.compoundId + ";");
      sql.execute();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get compound name from compoundId
   * 
   * @param compoundId
   *          compoundId of id searching for
   */
  @Override
  public String getCompoundName() {
    return this.name;
  }

  /**
   * Get inhabits from the Chemical table of a given chemicalId
   * 
   * @param chemicalId
   *          to search for
   */
  @Override
  public double getInventory() {
    return this.inventory;
  }

  @Override
  public void setCompoundId(int compoundId) {
    this.compoundId = compoundId;
  }

  @Override
  public void setMadeOf(List<Integer> madeOf) {
    this.madeOf = madeOf;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setInventory(double inventory) {
    this.inventory = inventory;
  }

}
