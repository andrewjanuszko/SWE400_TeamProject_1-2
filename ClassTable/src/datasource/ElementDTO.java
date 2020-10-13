package datasource;

public class ElementDTO {
  int elementId, atomicNumber;
  double atomicMass, inventory;
  String name;

  public ElementDTO(int elementId, int atomicNumber, double atomicMass, String name, double inventory) {
    this.elementId = elementId;
    this.atomicNumber = atomicNumber;
    this.atomicMass = atomicMass;
    this.name = name;
    this.inventory = inventory;
  }

  public int getElementId() {
    return elementId;
  }

  public int getAtomicNumber() {
    return atomicNumber;
  }

  public double getAtomicMass() {
    return atomicMass;
  }

  public double getInventory() {
    return inventory;
  }

  public String getName() {
    return name;
  }

  public void setElementId(int elementId) {
    this.elementId = elementId;
  }

  public void setAtomicNumber(int atomicNumber) {
    this.atomicNumber = atomicNumber;
  }

  public void setAtomicMass(double atomicMass) {
    this.atomicMass = atomicMass;
  }

  public void setInventory(double inventory) {
    this.inventory = inventory;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(atomicMass);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + atomicNumber;
    result = prime * result + elementId;
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
    ElementDTO other = (ElementDTO) obj;
    if (Double.doubleToLongBits(atomicMass) != Double.doubleToLongBits(other.atomicMass))
      return false;
    if (atomicNumber != other.atomicNumber)
      return false;
    if (elementId != other.elementId)
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
