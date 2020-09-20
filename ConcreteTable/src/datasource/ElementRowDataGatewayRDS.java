package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElementRowDataGatewayRDS {
	
	public static void createTable() throws DatabaseException{
		String drop = "DROP TABLE IF EXISTS Element";
		String create = "CREATE TABLE Element (" + 
				"elementID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + 
				"name VARCHAR(30) NOT NULL, " +                      //maybe Unique
				"inhabits VARCHAR(30)" +
				"atomicNumer INT NOT NULL, " +
				"atomicMass DOUBLE NOT NULL;";
		
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
}
