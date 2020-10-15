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
  
  public double getInventory() {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + chemicalId;
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
    ChemicalDTO other = (ChemicalDTO) obj;
    if (chemicalId != other.chemicalId)
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
