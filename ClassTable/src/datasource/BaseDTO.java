package datasource;

public class BaseDTO {
  private int baseId, soluteId;
  private String name;
  private double inventory;

  public BaseDTO(int baseId, int soluteId, String name, double inventory) {
    this.baseId = baseId;
    this.soluteId = soluteId;
    this.name = name;
    this.inventory = inventory;
  }

  public int getBaseId() {
    return baseId;
  }

  public int getSoluteId() {
    return soluteId;
  }

  public String getName() {
    return name;
  }

  public double getInventory() {
    return inventory;
  }

  public void setBaseId(int baseId) {
    this.baseId = baseId;
  }

  public void setSoluteId(int soluteId) {
    this.soluteId = soluteId;
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
    result = prime * result + baseId;
    long temp;
    temp = Double.doubleToLongBits(inventory);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + soluteId;
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
    BaseDTO other = (BaseDTO) obj;
    if (baseId != other.baseId)
      return false;
    if (Double.doubleToLongBits(inventory) != Double.doubleToLongBits(other.inventory))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (soluteId != other.soluteId)
      return false;
    return true;
  }

}
