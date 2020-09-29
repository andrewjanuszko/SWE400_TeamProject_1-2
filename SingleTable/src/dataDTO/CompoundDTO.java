package dataDTO;

import java.util.ArrayList;

/**
 * A DTO for Compounds and their Elements.
 * @author andrewjanuszko
 */
public final class CompoundDTO {
	
	private final int compoundID;
	private final ArrayList<Integer> elements;
	
	/**
	 * Constructor for a CompoundDTO.
	 * @param compoundID is the ID of the Compound.
	 * @param elements is the Elements that make up the Compound.
	 */
	public CompoundDTO(int compoundID, ArrayList<Integer> elements) {
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
	public ArrayList<Integer> getMadeOfElements() {
		return this.elements;
	}

}
