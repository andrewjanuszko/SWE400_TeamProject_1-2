package datadto;

public class CompoundMadeOfDTO {
	
	private int compoundID;
	private int elementID;
	
	public CompoundMadeOfDTO(int compoundID, int elementID) {
		this.compoundID = compoundID;
		this.elementID = elementID;
	}
	
	public int getCompoundID() {
		return compoundID;
	}
	
	public int getElementID() {
		return elementID;
	}
	
	public void setCompoundID(int comID) {
		compoundID = comID;
	}
	
	public void setElementID(int eleID) {
		elementID = eleID;
	}
}
