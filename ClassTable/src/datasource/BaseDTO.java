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

}
