package datasource;

/**
 * An enum to hold all the chemical types.
 * @author andrewjanuszko
 */
public enum ChemicalEnum {

	CHEMICAL (00),
	ELEMENT (01),
	METAL (02),
	COMPOUND (03),
	BASE (04),
	ACID (05);
	
	private final int chemicalType;
	
	/**
	 * Constructor for type.
	 * @param chemicalType the chemical.
	 */
	ChemicalEnum(int chemicalType) {
		this.chemicalType = chemicalType;
	}
	
	/**
	 * Get the type of the chemical.
	 * @return the integer value of the chemical.
	 */
	public int getChemicalType() {
		return this.chemicalType;
	}
	
}
