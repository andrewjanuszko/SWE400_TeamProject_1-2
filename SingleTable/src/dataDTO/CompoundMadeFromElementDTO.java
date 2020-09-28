package dataDTO;

public class CompoundMadeFromElementDTO {
	
	private int compoundID;
	private int elementID;
	
	public CompoundMadeFromElementDTO(int compoundId, int elementId) {
		this.setCompoundId(compoundId);
		this.setElementId(elementId);
	}

	/**
	 * getter for a compound's ID
	 * @return compoundId
	 */
	public int getCompoundId() {
		return compoundID;
	}

	public void setCompoundId(int compoundId) {
		this.compoundID = compoundId;
	}

	public int getElementId() {
		return elementID;
	}

	public void setElementId(int elementId) {
		this.elementID = elementId;
	}
	
	
	
	
	
}
