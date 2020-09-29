package dataDTO;

public final class CompoundMadeFromElementDTO {
	
	private final int compoundID;
	private final int elementID;
	
	public CompoundMadeFromElementDTO(int compoundId, int elementId) {
		
		this.compoundID = compoundId;
		this.elementID = elementId;
	}

	/**
	 * getter for a compound's ID.
	 * @return compoundId
	 */
	public int getCompoundId() {
		return compoundID;
	}


	/**
	 * getter for a compound's elementID.
	 * @return
	 */
	public int getElementId() {
		return elementID;
	}


	
	
	
	
}
