package datasource;

/**
 * Okay, theoretically we don't need this BUT I thought if you
 * wanted to get all chemicals from the database, regardless of
 * type, it might be useful. (like seeing inventory of ALL
 * chemicals)
 * 
 * @author Isabella Boone
 */
public class ChemicalDTO {
  int chemicalId;
  String name;
  double inventory;
  
  public ChemicalDTO(int chemicalId, String name, double inventory) {
    this.chemicalId = chemicalId;
    this.name = name;
    this.inventory = inventory; 
  }
  
  public int getChemicalId() {
    return chemicalId;
  }
  
  public String getName() {
    return name;
  }
  
  public double inventory() {
    return inventory;
  }
  
  public void setChemicalId(int chemicalId) {
    this.chemicalId = chemicalId;
  }
  public void setName(String name) {
    this.name = name; 
  }
  public void setInventory(double inventory) {
    this.inventory = inventory;
  }
  
}
