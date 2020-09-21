package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseRowDataGatewayRDS implements BaseRowDataGateway{

	public static void createTable() throws DatabaseException{
		String drop = "DROP TABLE IF EXISTS Base";
		String create = "CREATE TABLE Base (" + 
				"baseID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + 
				"name VARCHAR(30) NOT NULL, " +                      
				"inhabits VARCHAR(30), " +
				"solute VARCHAR(30), " + 
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
	
	private int baseID;
	private String name;
	private String inhabits;
	private String solute;
	
	
	public BaseRowDataGatewayRDS(String name) throws DatabaseException{
		conn = DatabaseManager.getSingleton().getConnection();
		this.name = name;
		findByName(name);
	}
	
	private void findByName(String name) throws DatabaseException{
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Base WHERE name = " + name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			baseID = rs.getInt("baseID");
			inhabits = rs.getString("inhabits");
			solute = rs.getString("solute");
		} catch (SQLException e) {
			throw new DatabaseException("Couldn't find element with that name", e);
		}
	}
}
