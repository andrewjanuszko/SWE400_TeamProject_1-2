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


	private static HashMap<Integer, MockPlayerTableRow> chemicalInfo;
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
	
	/**
	 *  Finder constructor
	 * @param ID
	 * @throws DatebaseException
	 */
	public ChemicalRowDataGatewayMock(int ID) throws DatabaseException {
//		if (chemicalInfo == null) {
//			resetDate();        The hashmap will be reset if it empty.
//		}
		
		if(chemicalInfo.containsKey(ID)) {
			info = chemicalInfo.get(ID);
			this.key = ID;
		} else {throw new DatabaseException("Could not find chemical with ID" + ID);}
	}
	
	
	/**
	 * Adds rows to the hashmap.
	 * @param chemicalID
	 * @param type
	 * @param name
	 * @param inhabits
	 * @param atomicNumber
	 * @param atomicMass
	 * @param dissolvedBy
	 * @param soulute
	 */
	public ChemicalRowDataGatewayMock(int chemicalID, int type, String name, String inhabits,
			int atomicNumber, double atomicMass, int dissolvedBy, int soulute) {
//		if (chemicalInfo == null) {
//			resetDate();       // The hashmap will be reset if it empty.
//		}
		
		key = nextKey;
		nextKey++;
		MockPlayerTableRow mockInfo = new MockPlayerTableRow(chemicalID, type, name, inhabits, atomicNumber, atomicMass, dissolvedBy, soulute);
		chemicalInfo.put(key, mockInfo); // puts the mock row into the hashmap.
	}
	
	/*
	 * Constructor
	 */
	public ChemicalRowDataGatewayMock() {}
	
//	public void resetData () { need to make 
//		chemicalInfo = new HashMap<Integer, MockPlayerTableRow>();
//		nextKey = 1;
//		for(ChemicalsForTest c : ChemicalsForTest.values()) {
//			chemicalInfo.put(nextKey, new MockPlayerTableRow())
//			nextKey++;
//		}
//	}
	
	/**
	 * get the number of the chemicals in the hashmap
	 * @return
	 */
	public int getNumberOfMockChemicals() {
		return chemicalInfo.size();
	}
	
	/**
	 * will delete a chemical
	 */
	@Override
	public void deleteChemical() throws DatabaseException {
		chemicalInfo.remove(key);
		
	}

	/**
	 * will update any changes made to a chemical
	 */
	@Override
	public void updateChemical() throws DatabaseException {
		chemicalInfo.put(key, info);
		
	}
	
//Just getters/Setters beyond this point
	
	public int getKey() {
		return key;
	}
	
	public MockPlayerTableRow getInfo() {
		return info;
		
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
	public String getInhabits() {
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
	public int getDissolvedBy() {
		return info.dissolvedBy;
	}

	@Override
	public int getSolute() {
		return info.solute;
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
	public void setInhabits(String inhabits) {
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
	public void setDissolvedBy(int dissolvedBy) throws DatabaseException {
		this.info.dissolvedBy = dissolvedBy;
	}

	@Override
	public void setSolute(int solute) {
		this.info.solute = solute;
	}

}
