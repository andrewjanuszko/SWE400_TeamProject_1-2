package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompoundMadeOfTableDataGatewatRDS implements CompoundMadeOfTableDataGateway{
	
	public static void createTable() throws DatabaseException {
		String drop = "DROP TABLE IF EXISTS CompoundMadeOf";
		String create = "CREATE TABLE CompoundMadeOf (" + 
				"compoundID INT NOT NULL," + 
				"elementID INT NOT NULL," + 
				"FOREIGN KEY(compoundID) REFERENCES Compound(compoundID) " +
				"FOREIGN KEY(elementID) REFERENCES Element(elementID) ";
				
	
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
			throw new DatabaseException("Unable to create the CompoundMadeOf table", e);
		}
	}
}
