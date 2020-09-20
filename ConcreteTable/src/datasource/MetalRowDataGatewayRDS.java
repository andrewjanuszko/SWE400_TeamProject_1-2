package datasource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MetalRowDataGatewayRDS implements MetalRowDataGateway{
	/*
	 * Creates Table
	 * 
	 * @throws DatabaseException if it can't create or drop table.
	 */
	
	public static void createTable() throws DatabaseException
	{
		String drop = "DROP TABLE IF EXISTS Metal";
		String create = "CREATE TABLE Metal (" + 
				"metalID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + 
				"name VARCHAR(30) NOT NULL, " +                      //maybe Unique
				"atomicNumer INT NOT NULL, " +
				"atomicMass DOUBLE NOT NULL, " +
				"disslovedBy INT REFERENCES Acid(acidID)";
		
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
		} catch (SQLException e)
		{
			throw new DatabaseException("Unable to create InteractableItem table", e);
		}
	}
}