package dataDTO;

/**
 * Contains the information of a chemical.
 * @author andrewjanuszko
 */
public class ChemicalDTO {
	private int chemicalID;
	private int type;
	private String name;
	private String inhabits;
	private int atomicNumber;
	private double atomicMass;
	private int dissolvedBy;
	private int solute;
	
	public ChemicalDTO(int chemicalID, int type, String name, String inhabits, int atomicNumber, 
			double atomicMass, int dissolvedBy,int solute) {
		
		this.setChemicalID(chemicalID);
		this.setType(type);
		this.setName(name);
		this.setInhabits(inhabits);
		this.setAtomicNumber(atomicNumber);
		this.setAtomicMass(atomicMass);
		this.setDissolvedBy(dissolvedBy);
		this.setSolute(solute);
	}

	/**
	 * @return the chemicalID
	 */
	public int getChemicalID() {
		return chemicalID;
	}

	/**
	 * @param chemicalID the chemicalID to set
	 */
	public void setChemicalID(int chemicalID) {
		this.chemicalID = chemicalID;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the inhabits
	 */
	public String getInhabits() {
		return inhabits;
	}

	/**
	 * @param inhabits the inhabits to set
	 */
	public void setInhabits(String inhabits) {
		this.inhabits = inhabits;
	}

	/**
	 * @return the atomicNumber
	 */
	public int getAtomicNumber() {
		return atomicNumber;
	}

	/**
	 * @param atomicNumber the atomicNumber to set
	 */
	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	/**
	 * @return the atomicMass
	 */
	public double getAtomicMass() {
		return atomicMass;
	}

	/**
	 * @param atomicMass the atomicMass to set
	 */
	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	/**
	 * @return the dissolvedBy
	 */
	public int getDissolvedBy() {
		return dissolvedBy;
	}

	/**
	 * @param dissolvedBy the dissolvedBy to set
	 */
	public void setDissolvedBy(int dissolvedBy) {
		this.dissolvedBy = dissolvedBy;
	}

	/**
	 * @return the solute
	 */
	public int getSolute() {
		return solute;
	}

	/**
	 * @param solute the solute to set
	 */
	public void setSolute(int solute) {
		this.solute = solute;
	}
	

}
