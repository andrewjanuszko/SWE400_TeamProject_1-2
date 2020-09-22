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


	private static HashMap<Integer, MockPlayerTableRow> ChemicalInfo;
	private static int nextKey = 1;
	private int key;
	private MockPlayerTableRow info;
	
	private class MockPlayerTableRow {
		private int chemicalID;
		private int type;
		private String name;
		private String inhabits;
		private int atomicNumber;
		private double atomicMass;
		private int dissolvedBy;
		private int solute;
		
		public MockPlayerTableRow(int chemicalID, int type, String name, String inhabits,
				int atomicNumber, double atomicMass, int dissolvedBy, int soulute) {
			this.chemicalID = chemicalID;
			this.type = type;
			this.name = name;
			this.inhabits = inhabits;
			this.atomicNumber = atomicNumber;
			this.atomicMass = atomicMass;
			this.dissolvedBy =dissolvedBy;
			this.solute = soulute;
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
	public void setName(String name) {
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
	public void setDissolvedBy(int dissolvedBy) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSolute(int solute) {
		// TODO Auto-generated method stub
		
	}

}
