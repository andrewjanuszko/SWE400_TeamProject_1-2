package datasource;

public class AcidDTO {
  private int acidId, soluteId;
  private String name;
  private double inventory;

  /**
   * Initialize an acid DTO.
   * @param acidId int
   * @param soluteId int
   * @param name String
   * @param inventory double
   */
  public AcidDTO(int acidId, int soluteId, String name, double inventory) {
    this.acidId = acidId;
    this.soluteId = soluteId;
    this.name = name;
    this.inventory = inventory;
  }

  /**
   * Get AcidId from DTO
   * @return int acidId
   */
  public int getAcidId() {
    return acidId;
  }

  /**
   * Get SoluteId from DTO
   * @return int soluteId
   */ 
  public int getSoluteId() {
    return soluteId;
  }

  /**
   * Get Name of Acid DTO
   * @return String name of acid
   */
  public String getName() {
    return name;
  }

  /**
   * Get Inventory amount of acid 
   * @return double inventory
   */
  public double getInventory() {
    return inventory;
  }

  /**
   * set acid id
   * @param acidId int
   */ 
  public void setAcidId(int acidId) {
    this.acidId = acidId;
  }

  /**
   * set solute id
   * @param soluteId int
   */
  public void setSoluteId(int soluteId) {
    this.soluteId = soluteId;
  }

  /**
   * set name 
   * @param name String
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * set inventory
   * @param inventory double
   */
  public void setInventory(double inventory) {
    this.inventory = inventory;
  }

}
