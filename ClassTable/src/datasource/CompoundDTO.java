package datasource;

public class CompoundDTO {

  int compoundId;
  int elementId;
  
  public CompoundDTO(int cId, int eId) {
    this.compoundId = cId;
    this.elementId = eId;
    
  }

  public int getCompoundId() {
    return compoundId;
  }

  public int getElementId() {
    return elementId;
  }

  public void setCompoundId(int compoundId) {
    this.compoundId = compoundId;
  }

  public void setElementId(int elementId) {
    this.elementId = elementId;
  }
  
  
}
