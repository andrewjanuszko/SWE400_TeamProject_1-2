package datasource;

public class CompoundDTO {

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + compoundId;
    result = prime * result + elementId;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CompoundDTO other = (CompoundDTO) obj;
    if (compoundId != other.compoundId)
      return false;
    if (elementId != other.elementId)
      return false;
    return true;
  }

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
