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
	 * @param type - the type of the Chemical.
	 * @param name - the name of the Chemical.
	 * @param inhabits - the location where the Chemical is found.
	 * @param atomicNumber - the atomic number of the Chemical.
	 * @param atomicMass - the atomic mass of the Chemical.
	 * @param acidID - the ID of the acid that a metal can be dissolved by.
	 * @param chemicalID - the ID of the solute.
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
	
	/*
	 * the setter for a chemical's type.
	 */
	@Override
	public void setType(int type) {
		this.info.type = type;	
	}
	/*
	 * the setter for a chemcial's name.
	 */
	@Override
	public void setName(String name) {
		this.info.name = name;
	}
	/*
	 * the setter for a chemical's habitat.
	 */
	@Override
	public void setHabitat(String inhabits) {
		this.info.inhabits = inhabits;	
	}

	/*
	 * the setter for a chemical's atomic number.
	 */
	@Override
	public void setAtomicNumber(int atomicNumber) {
		this.info.atomicNumber = atomicNumber;
	}
	
	/*
	 * the setter for a chemical's atomic mass.
	 */
	@Override
	public void setAtomicMass(double atomicMass) {
		this.info.atomicMass = atomicMass;		
	}
	
	/*
	 * the setter for a acid the is a dissolve by a chemical.
	 */
	@Override
	public void setDissolvedBy(long acidID) {
		this.info.acidID = acidID;
	}

	/*
	 * the setter for a chemical's soulute.
	 */
	@Override
	public void setSolute(long chemicalID) {
		this.info.chemicalID = chemicalID;
	}
	
	/*
	 * the getter for a chemical's ID.
	 */
	@Override
	public long getID() {
		return ID;
	}

	/*
	 * the getter for a chemical's type.
	 */
	@Override
	public int getType() {
		return info.type;
	}

	/*
	 * the getter for a chemical's name
	 */
	@Override
	public String getName() {
		return info.name;
	}

	/*
	 * the getter for a chemical's habitat.
	 */
	@Override
	public String getHabitat() {
		return info.inhabits;
	}

	/*
	 * the getter for a chemical's atomic number.
	 */
	@Override
	public int getAtomicNumber() {
		return info.atomicNumber;
	}
	
	/*
	 * the getter for a chemical's atomic mass.
	 */
	@Override
	public double getAtomicMass() {
		return info.atomicMass;
	}

	/*
	 * the getter for a chemical's dissolved by 
	 */
	@Override
	public long getDissolvedBy() {
		return info.acidID;
	}
	/*
	 * the getter for a chemical's solute
	 */
	@Override
	public long getSolute() {
		// TODO Auto-generated method stub
		return info.chemicalID;
	}

	@Override
	public void persistData() throws DatabaseException {
		//chemicalInfo.put(ID,info);	
	}

	@Override
	public void resetData() {
		//to be done for testing
	}

	@Override
	public void deleteInstance() throws DatabaseException {
		chemicalInfo.remove(ID);
	}

}
