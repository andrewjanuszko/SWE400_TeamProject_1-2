package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompoundRowDataGatewayRDS {

	public static void createTable() throws DatabaseException{
		
		String drop = "DROP TABLE IF EXISTS Compound";
		String create = "CREATE TABLE Compound (" + 
				"compoundID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + 
				"name VARCHAR(30) NOT NULL, " +                      
				"inhabits VARCHAR(30), " + 
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
	
	private int compoundID;
	private String name;
	private String inhabits;
	private List<Integer> madeOf;
	
	
	public CompoundRowDataGatewayRDS(String name) throws DatabaseException{
		conn = DatabaseManager.getSingleton().getConnection();
		this.name = name;
		findByName(name);
	}
	
	private void findByName(String name) throws DatabaseException{
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Compound WHERE name = " + name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			compoundID = rs.getInt("compoundID");
			inhabits = rs.getString("inhabits");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find element with that name", e);
		}
		
		try {
			PreparedStatement stmt = conn.prepareStatement("");
		} catch(SQLException e) {
			
		}
	}
}
