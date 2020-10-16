package dataDTO;

import java.util.List;

/**
 * A DTO for Compounds and their Elements.
 * @author andrewjanuszko
 */
public final class CompoundDTO {
	
	private final int compoundID;
	private final List<Integer> elements;
	
	/**
	 * Constructor for a CompoundDTO.
	 * @param compoundID is the ID of the Compound.
	 * @param elements is the Elements that make up the Compound.
	 */
	public CompoundDTO(int compoundID, List<Integer> elements) {
		this.compoundID = compoundID;
		this.elements = elements;
	}
	
	/**
	 * Return the ID of the Compound.
	 * @return the ID of the Compound.
	 */
	public int getCompoundID() {
		return this.compoundID;
	}
	
	/**
	 * Return the Elements in the Compound.
	 * @return the Elements in the Compound.
	 */
	public List<Integer> madeOf() {
		return this.elements;
	}

}
