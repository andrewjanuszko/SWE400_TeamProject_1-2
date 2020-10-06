package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datadto.CompoundMadeOfDTO;

public class CompoundMadeOfTableDataGatewayRDS implements CompoundMadeOfTableDataGateway{
	
	Connection conn;
	
	public static void createTable() throws DatabaseException {
		String drop = "DROP TABLE IF EXISTS CompoundMadeOf";
		String create = "CREATE TABLE CompoundMadeOf (" + 
				"compoundID INT NOT NULL," + 
				"elementID INT NOT NULL," + 
				"FOREIGN KEY(compoundID) REFERENCES Compound(compoundID), " +
				"FOREIGN KEY(elementID) REFERENCES Element(elementID)); ";
				
	

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
	public static void dropTable() throws DatabaseException {
	  try
    {
  	  String drop = "DROP TABLE IF EXISTS CompoundMadeOf";
  	  PreparedStatement stmt;
      stmt = DatabaseManager.getSingleton().getConnection().prepareStatement(drop);
      stmt.execute();
      stmt.close();
    }catch (SQLException e) {
      throw new DatabaseException("Unable to drop the CompoundMadeOf table", e);
    }
	}

	List<CompoundMadeOfDTO> dtoList = new ArrayList<CompoundMadeOfDTO>();
	private int compoundID;
	
	public CompoundMadeOfTableDataGatewayRDS(int id) throws DatabaseException {
		compoundID = id;
		findCompounds();
	}
	 
	private void findCompounds() throws DatabaseException {
		conn = DatabaseManager.getSingleton().getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CompoundMadeOf WHERE compoundID = " + compoundID);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				dtoList.add(new CompoundMadeOfDTO(rs.getInt("compoundID"), rs.getInt("elementID")));
			}
		} catch (SQLException e) {
			new DatabaseException("could not get CompoundMadeOf");
		}
	}
	
	@Override
	public List<CompoundMadeOfDTO> getCompoundMadeOf(){
		return dtoList;
	}
	
	public synchronized void addElementToCompound(int elementID) {
		dtoList.add(new CompoundMadeOfDTO(compoundID, elementID));
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO CompoundMadeOf Values (" + compoundID + ", " + elementID + ");");
			stmt.execute();
		}catch(SQLException e) {
			new DatabaseException("couldn't insert into CompoundMadeOf");
		}
	}
	
	public int getCompoundID(){
		return compoundID;
	}

}
