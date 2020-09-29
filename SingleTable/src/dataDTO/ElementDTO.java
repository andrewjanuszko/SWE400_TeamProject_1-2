package dataDTO;

import java.util.ArrayList;

/**
 * A DTO for Elements and their Compounds.
 * @author andrewjanuszko
 */
public final class ElementDTO {
	
	private final int elementID;
	private final ArrayList<Integer> compounds;
	
	/**
	 * Constructor for a ElementInCompoundsDTO.
	 * @param elementID is the ID of the Element.
	 * @param compounds is the Compounds that contain the Element.
	 */
	public ElementDTO(int elementID, ArrayList<Integer> compounds) {
		this.elementID = elementID;
		this.compounds = compounds;
	}
	
	/**
	 * Return the ID of the Element.
	 * @return the ID of the Element.
	 */
	public int getElementID() {
		return this.elementID;
	}
	
	/**
	 * Return the Compounds that contain the Element.
	 * @return the Compounds that contain the Element.
	 */
	public ArrayList<Integer> partOf() {
		return this.compounds;
	}

}
