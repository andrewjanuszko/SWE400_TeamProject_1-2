package datasource;

public class CompoundDTO {

  int compoundId;
  int elementId;
  String name;
  double inventory;

  public CompoundDTO(int cId, int eId, String name, double inv) {
    this.compoundId = cId;
    this.elementId = eId;
    this.name = name;
    this.inventory = inv;
  }

  public int getCompoundId() {
    return compoundId;
  }

  public int getElementId() {
    return elementId;
  }

  public String getName() {
    return name;
  }

  public double getInventory() {
    return inventory;
  }

  public void setCompoundId(int compoundId) {
    this.compoundId = compoundId;
  }

  public void setElementId(int elementId) {
    this.elementId = elementId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setInventory(double inventory) {
    this.inventory = inventory;
  }

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

}
