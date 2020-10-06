package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datadto.CompoundMadeOfDTO;

/**
 * Table Data Gateway for CompoundMadeOf Table.
 * @author Joel
 *
 */
public class CompoundMadeOfTableDataGatewayRDS implements CompoundMadeOfTableDataGateway{
	
	Connection conn;
	
	/**
	 * Creates the table in the database. Drops the table if it already exists.
	 * @throws DatabaseException
	 */
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
	
	/**
	 * Only drops the table.
	 * @throws DatabaseException
	 */
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
	
	/**
	 * Constructs CompoundMadeOf Gateway view based off of matches with given ID.
	 * @param id
	 * @throws DatabaseException
	 */
	public CompoundMadeOfTableDataGatewayRDS(int id) throws DatabaseException {
		compoundID = id;
		findCompounds();
	}
	
	/**
	 * Finds compounds and adds to dtoList.
	 * @throws DatabaseException
	 */
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
	
	/**
	 * Links element to current compound.
	 */
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
