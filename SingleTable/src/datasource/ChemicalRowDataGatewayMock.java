package datasource;

import java.util.HashMap;

/**
 * Implementation of ChemicalRowDataGateway.
 * @author andrewjanuszko
 *
 */
public class ChemicalRowDataGatewayMock implements ChemicalRowDataGateway {
	
	private static HashMap<Integer, MockChemicalTableRow> chemicalInfo;
	private static int nextKey = 1;
	private long ID;
	private MockChemicalTableRow info;
	
	private class MockChemicalTableRow {
		
		int type;
		String name;
		String inhabits;
		int atomicNumber;
		double atomicMass;
		long acidID;
		long chemicalID;
		
		public MockChemicalTableRow(int type, String name, String inhabits, int atomicNumber, double atomicMass, long acidID, long chemicalID) {
			this.type = type;
			this.name = name;
			this.inhabits = inhabits;
			this.atomicNumber = atomicNumber;
			this.atomicMass = atomicMass;
			this.acidID = acidID;
			this.chemicalID = chemicalID;
		}
	}

	
	public ChemicalRowDataGatewayMock(int ID) throws DatabaseException {
		if (chemicalInfo == null ) {
			resetData();
		}
		if(chemicalInfo.containsKey(ID))
		{
			info = chemicalInfo.get(ID);
			this.ID = ID;
		} else {
			throw new DatabaseException("Couldn't find chemical with ID " + ID);
		}
	}
	
	/**
	 * 
	 * @param type
	 * @param name
	 * @param inhabits
	 * @param atomicNumber
	 * @param atomicMass
	 * @param acidID
	 * @param chemicalID
	 */
	public ChemicalRowDataGatewayMock(int type, String name, String inhabits, int atomicNumber, double atomicMass, long acidID, long chemicalID) {
		if(chemicalInfo == null) {
			resetData();
		}
		ID = nextKey;
		nextKey++;
		MockChemicalTableRow mockInfo = new MockChemicalTableRow(type, name,inhabits, atomicNumber, atomicMass, acidID, chemicalID);
		chemicalInfo.put((int) ID,mockInfo);
		info = mockInfo;
	}
	
	public ChemicalRowDataGatewayMock() {
	}

	@Override
	public void setType(int type) {
		this.info.type = type;
		
	}

	@Override
	public void setName(String name) {
		this.info.name = name;
		
	}

	@Override
	public void setHabitat(String inhabits) {
		this.info.inhabits = inhabits;
		
	}

	@Override
	public void setAtomicNumber(int atomicNumber) {
		this.info.atomicNumber = atomicNumber;
	}

	@Override
	public void setAtomicMass(double atomicMass) {
		this.info.atomicMass = atomicMass;		
	}

	@Override
	public void setDissolvedBy(long acidID) {
		this.info.acidID = acidID;
	}

	@Override
	public void setSolute(long chemicalID) {
		this.info.chemicalID = chemicalID;
	}
	
	@Override
	public long getID() {
		return ID;
	}

	@Override
	public int getType() {
		return info.type;
	}

	@Override
	public String getName() {
		return info.name;
	}

	@Override
	public String getHabitat() {
		return info.inhabits;
	}

	@Override
	public int getAtomicNumber() {
		return info.atomicNumber;
	}

	@Override
	public double getAtomicMass() {
		return info.atomicMass;
	}

	@Override
	public long getDissolvedBy() {
		return info.acidID;
	}

	@Override
	public long getSolute() {
		// TODO Auto-generated method stub
		return info.chemicalID;
	}

	@Override
	public void persistData() throws DatabaseException {
				
	}

	@Override
	public void resetData() {
		//hello
	}

	@Override
	public void deleteInstance() throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

}
