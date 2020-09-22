package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * The RDS version of the gateway for Chemical.
 * @author andrewjanuszko
 */
public class ChemicalRowDataGatewayRDS implements ChemicalRowDataGateway {
	
	private int chemicalID;
	private int type;
	private String name;
	private String inhabits;
	private int atomicNumber;
	private double atomicMass;
	private int dissolvedBy;
	private int solute;
	
	/**
	 * 
	 * @param chemicalID
	 * @throws DatabaseException
	 */
	public ChemicalRowDataGatewayRDS(int chemicalID) throws DatabaseException {
		String findSQL
	}
	
	public ChemicalRowDataGatewayRDS(int chemicalID, int type, String name, String inhabits, int atomicNumber, double atomicMass,
			int dissolvedBy, int solute) {
		
	}
	
	public void createChemical() throws DatabaseException {
		String dropTableSQL = "DROP TABLE IF EXISTS Chemical, CompoundMadeFromElement;";
		String createTableSQL = "CREATE TABLE Chemical(" +
						   "chemicalID INT NOT NULL," +
						   "type INT NOT NULL, " +
						   "name VARCHAR(20)," +
						   "inhabits VARCHAR(20), " +
						   "atomicNumber INT, " +
						   "atomicMass DOUBLE, " +
						   "dissolvedBy INT, " +
						   "solute INT, " +
						   "PRIMARY KEY (chemicalID));";
		try {
			Statement statement = DatabaseManager.getSingleton().getConnection().createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
			statement.executeUpdate(dropTableSQL);
			
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
			statement.executeUpdate(createTableSQL);
		} catch(SQLException sqle) {
			throw new DatabaseException("Something went really wrong.", sqle);
		}
	}
	
	@Override
	public void deleteChemical() throws DatabaseException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateChemical() throws DatabaseException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getInhabits() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getAtomicNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getAtomicMass() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getDissolvedBy() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getSolute() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setType(int type) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setName() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setInhabits(String inhabits) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setAtomicNumber(int atomicNumber) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setAtomicMass(double atomicMass) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setDissolvedBy(int dissolvedBy) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setSolute(int solute) {
		// TODO Auto-generated method stub
		
	}
	
}
