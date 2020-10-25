package datadto;

public class MetalDTO {
  int id, dissolvedBy, atomicNumber; 
  String name;
  double inventory, atomicMass, acidAmount;
  
  public MetalDTO(int id, String name, double inventory, int atomicNumber, double atomicMass, double acidAmount, int dissolvedBy) {
    this.id = id;
    this.dissolvedBy = dissolvedBy; 
    this.atomicNumber = atomicNumber;
    this.atomicMass = atomicMass;
    this.acidAmount = acidAmount;
    this.name = name;
    this.inventory = inventory;
  }
  
  public int getMetalID() { 
    return id;
  }
  
  public double getAcidAmount() {
    return acidAmount;
  }

  public void setAcidAmount(double acidAmount) {
    this.acidAmount = acidAmount;
  }

  public int getDissolvedById() {
    return dissolvedBy; 
  }
  
  public String getName() {
    return name;
  }
  
  public double getInventory() {
    return inventory;
  }
  
  public void setMetalID(int id) {
    this.id = id;
  }
  
  public void setDissolvedById(int dissolvedBy) {
    this.dissolvedBy = dissolvedBy;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setInventory(double inventory) {
    this.inventory = inventory; 
  }
  
  public int getAtomicNumber() {
    return atomicNumber;
  }

  public void setAtomicNumber(int atomicNumber) {
    this.atomicNumber = atomicNumber;
  }

  public double getAtomicMass() {
    return atomicMass;
  }

  public void setAtomicMass(double atomicMass) {
    this.atomicMass = atomicMass;
  }
}
