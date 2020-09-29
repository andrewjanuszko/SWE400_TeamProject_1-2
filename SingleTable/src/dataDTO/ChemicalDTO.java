package dataDTO;

/**
 * Contains the information of a chemical.
 * @author andrewjanuszko
 */
public final class ChemicalDTO {
	
	private final int chemicalID;
	private final int type;
	private final String name;
	private final String inhabits;
	private final int atomicNumber;
	private final double atomicMass;
	private final int dissolvedBy;
	private final int solute;
	
	public ChemicalDTO(int chemicalID, int type, String name, String inhabits, int atomicNumber, 
			double atomicMass, int dissolvedBy,int solute) {
		
		this.chemicalID = chemicalID;
		this.type = type;
		this.name = name;
		this.inhabits = inhabits;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.dissolvedBy = dissolvedBy;
		this.solute = solute;
	}

	/**
	 * @return the chemicalID
	 */
	public int getChemicalID() {
		return chemicalID;
	}


	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the inhabits
	 */
	public String getInhabits() {
		return inhabits;
	}

	/**
	 * @return the atomicNumber
	 */
	public int getAtomicNumber() {
		return atomicNumber;
	}

	/**
	 * @return the atomicMass
	 */
	public double getAtomicMass() {
		return atomicMass;
	}

	/**
	 * @return the dissolvedBy
	 */
	public int getDissolvedBy() {
		return dissolvedBy;
	}

	/**
	 * @return the solute
	 */
	public int getSolute() {
		return solute;
	}


}
