package datasource;

import java.util.List;

public class CompoundDTO {
  int compoundId;
  List<ElementDTO> elements;
  String name;
  double inventory;

  public CompoundDTO(int cId, List<ElementDTO> elements, String name, double inv) {
    this.compoundId = cId;
    this.elements = elements;
    this.name = name;
    this.inventory = inv;
  }

  public int getCompoundId() {
    return compoundId;
  }

  public List<ElementDTO> getElements() {
    return elements;
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

  public void setElements(List<ElementDTO> elements) {
    this.elements = elements;
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
    result = prime * result + ((elements == null) ? 0 : elements.hashCode());
    long temp;
    temp = Double.doubleToLongBits(inventory);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    if (elements == null) {
      if (other.elements != null)
        return false;
    } else if (!elements.equals(other.elements))
      return false;
    if (Double.doubleToLongBits(inventory) != Double.doubleToLongBits(other.inventory))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }


}
