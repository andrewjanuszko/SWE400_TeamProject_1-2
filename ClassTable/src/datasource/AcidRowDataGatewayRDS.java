package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcidRowDataGatewayRDS implements AcidRowDataGateway {

  /**
   * Create acid table.
   */
	@Override
	public void createTableAcid() {
		String dropTable = "DROP TABLE IF EXISTS Acid;";
		String createTable = "CREATE TABLE Acid" + "(acidId INT NOT NULL, " + 
		"solute INT, " + "FOREIGN KEY(acidId) REFERENCES Chemical(chemicalId)" + ");";
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			
			// Drop the table if exists first
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
			statement.executeUpdate(dropTable);
			
			statement.executeUpdate(createTable);
			
		} catch (Exception e) {
		  // If the drop or create failed
			e.printStackTrace();
			System.out.println("Failed to create acid table.");
		}
	}

	/**
	 * Get solute from a given id.
	 * 
	 * @param id get
	 */
	@Override
	public int getSoluteId(int id) {
		int solute = 0;
		String sql = new String("SELECT * FROM Acid WHERE acidId = " + id + ";");
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next(); // Get result
			solute = rs.getInt("solute"); // Get name from "name" column
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to fetch solute id");
		}
		return solute;
	}

	/**
	 * Get the name of the acid
	 * 
	 * @param id to fetch name of
	 */
	@Override
	public String getName(int id) {
		String name = "";
		String sql =
				"SELECT Chemical.name FROM Chemical INNER JOIN Acid ON Chemical.chemicalId = " + id + ";";
		
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next(); // Get result
			name = rs.getString("name"); // Get name from "name" column
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to fetch name of acid");
		}
		return name;
	}

	/**
	 * Get inhabits from compoundID
	 * 
	 * @param compoundId to find inhabits from
	 */
	@Override
	public String getInhabits(int id) {
		String inhabits = "";
		String sql = new String(
				"SELECT Chemical.inhabits FROM Chemical INNER JOIN Acid ON Chemical.chemicalId = " + id + ";");
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql); 
			rs.next();
			inhabits = rs.getString("inhabits");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to fetch inhabits of acid");
		}
		
		return inhabits;
	}

	/**
	 * Insert into acid table.
	 * @param id
	 * @param soluteId
	 */
	public void insert(int id, int soluteId) {
	  try {
	    // Insert chemical
	    PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
        .prepareStatement("INSERT INTO Acid (acidId, solute)" + "VALUES (?, ?);");
	    insertChemical.setInt(1, id); // Set acid id
	    insertChemical.setInt(2, soluteId); // set solute id
	    
	    insertChemical.execute(); // Insert acid
	    
	  } catch(Exception e) {
	    e.printStackTrace();
	  }
	}
	
//	public void insert(int id, String name, String inhabits, int soluteId) {
//	  try {
//	    // Insert chemical
//      PreparedStatement insertChemical = DatabaseManager.getSingleton().getConnection()
//          .prepareStatement("INSERT INTO Chemical (chemicalId, name, inhabits)" + "VALUES (?, ?, ?);");
//      insertChemical.setInt(1, id); // Set chemicalId
//      insertChemical.setString(2, name); // Set name
//      insertChemical.setString(3, inhabits); // Set inhabits
//      
//      insertChemical.execute(); // Insert chemical
//      
//      // Insert acid
//      PreparedStatement insertAcid = DatabaseManager.getSingleton().getConnection()
//          .prepareStatement("INSERT INTO Acid (acidId, solute)" + "VALUES (?, ?);");
//      insertAcid.setInt(1, id); // Set chemicalId
//      insertAcid.setInt(2, soluteId); // Set name
//
//      insertAcid.execute(); // Insert acid
//      
//    } catch (SQLException | DatabaseException e) {
//      e.printStackTrace();
//    } 
//	}
}
