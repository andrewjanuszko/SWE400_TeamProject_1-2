package datasource;

/**
 * Implementation of ChemicalRowDataGateway.
 * @author andrewjanuszko
 *
 */
public class ChemicalRowDataGatewayMock implements ChemicalRowDataGateway {
	
	private class MockChemicalTableRow {
		
		String name;
		String inhabits;
		int atomicNumber;
		double atomicMass;
		long acidID;
		long chemicalID;
		
		public MockChemicalTableRow(String name, String inhabits, int atomicNumber, double atomicMass, long acidID, long chemicalID) {
			this.name = name;
			this.inhabits = inhabits;
			this.atomicNumber = atomicNumber;
			this.atomicMass = atomicMass;
			this.acidID = acidID;
			this.chemicalID = chemicalID;
		}
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
	public void setHabitat(String inhabits) {
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
	public void setDissolvedBy(long acidID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSolute(long chemicalID) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public long getID() {
		// TODO Auto-generated method stub
		return 0;
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
	public String getHabitat() {
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
	public long getDissolvedBy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSolute() {
		// TODO Auto-generated method stub
		return 0;
	}

}
