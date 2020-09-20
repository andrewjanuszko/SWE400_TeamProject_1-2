package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AcidRowDataGatewayRDS {

	public static void createTable() throws DatabaseException{
		String drop = "DROP TABLE IF EXISTS Acid";
		String create = "CREATE TABLE Acid (" + 
				"acidID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + 
				"name VARCHAR(30) NOT NULL, " +                      
				"inhabits VARCHAR(30)," +
				"solute VARCHAR(30)," +
				"UNIQUE(name);";
	
		Connection conn = DatabaseManager.getSingleton().getConnection();

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
			throw new DatabaseException("Unable to create InteractableItem table", e);
		}
	}
	

	private Connection conn;
	
	private int acidID;
	private String name;
	private String inhabits;
	private String solute;
	private List<Integer> dissolves;
	
	public AcidRowDataGatewayRDS(String name) throws DatabaseException{
		conn = DatabaseManager.getSingleton().getConnection();
		this.name = name;
		findByName(name);
	}
	
	private void findByName(String name) throws DatabaseException{
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Acid WHERE name = " + name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			acidID = rs.getInt("acidID");
			inhabits = rs.getString("inhabits");
			solute = rs.getString("solute");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find element with that name", e);
		}
		
		try {
			PreparedStatement stmt = conn.prepareStatement("");
		} catch(SQLException e) {
			
		}
	}
}
