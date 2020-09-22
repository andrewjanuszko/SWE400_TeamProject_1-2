package datasource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Implementation of ChemicalRowDataGateway.
 * @author andrewjanuszko
 *
 */
public class ChemicalRowDataGatewayMock implements ChemicalRowDataGateway {

	@Override
	public void createTableChemical() throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getType(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInhabits(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAtomicNumber(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAtomicMass(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDissolvedBy(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSolute(int chemicalID) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(int chemicalID, String name, String inhabits, int atomicNumber, double atomicMass,
			int dissolvedBy, int solute) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

}
