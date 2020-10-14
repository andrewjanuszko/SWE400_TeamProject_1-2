package datasource;

public class MetalDTO {
  int metalId, dissolvedById; 
  String name;
  double inventory;
  
  public MetalDTO(int metalId, int dissolvedById, String name, double inventory) {
    this.metalId = metalId;
    this.dissolvedById = dissolvedById; 
    this.name = name;
    this.inventory = inventory;
  }
  
  public int getMetalId() { 
    return metalId;
  }
  
  public int getDissolvedById() {
    return dissolvedById; 
  }
  
  public String getName() {
    return name;
  }
  
  public double getInventory() {
    return inventory;
  }
  
  public void setMetalId(int metalId) {
    this.metalId = metalId;
  }
  
  public void setDissolvedById(int dissolvedById) {
    this.dissolvedById = dissolvedById;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setInventory(double inventory) {
    this.inventory = inventory; 
  }
}
