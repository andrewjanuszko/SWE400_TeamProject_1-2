package datasource;

public class ElementDTO {
  int elementId, atomicNumber;
  double atomicMass, inventory;
  String name;

  public ElementDTO(int elementId, int atomicNumber, double atomicMass, double inventory, String name) {
    this.elementId = elementId;
    this.atomicNumber = atomicNumber;
    this.atomicMass = atomicMass;
    this.inventory = inventory;
    this.name = name;
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

}
