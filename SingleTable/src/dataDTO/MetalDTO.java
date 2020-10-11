package dataDTO;

/**
 * Creates a Metal DTO.
 * 
 * @author andrewjanuszko
 *
 */
public final class MetalDTO {

  private final int chemicalID;
  private final String name;
  private final double inventory;
  private final int atomicNumber;
  private final double atomicMass;
  private final int dissolvedBy;
  private final double moles;

  /**
   * Create a chemical DTO
   * 
   * @param chemicalID   the ID of the Chemical.
   * @param type         the type of the Chemical.
   * @param name         the name of the Chemical.
   * @param inventory    the inventory of the Chemical.
   * @param atomicNumber the atomicNumber of the Chemical.
   * @param atomicMass   the atomicMass of the Chemical.
   * @param dissolvedBy  the dissolvedBy of the Chemical.
   * @param solute       the solute of the Chemical.
   */
  public MetalDTO(int chemicalID, String name, double inventory, int atomicNumber, double atomicMass, int dissolvedBy,
      double moles) {
    this.chemicalID = chemicalID;
    this.name = name;
    this.inventory = inventory;
    this.atomicNumber = atomicNumber;
    this.atomicMass = atomicMass;
    this.dissolvedBy = dissolvedBy;
    this.moles = moles;
  }

  /**
   * @return the chemicalID
   */
  public int getChemicalID() {
    return chemicalID;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the inventory
   */
  public double getInventory() {
    return inventory;
  }

  /**
   * @return the atomicNumber
   */
  public int getAtomicNumber() {
    return atomicNumber;
  }

  /**
   * @return the atomicMass
   */
  public double getAtomicMass() {
    return atomicMass;
  }

  /**
   * @return the dissolvedBy
   */
  public int getDissolvedBy() {
    return dissolvedBy;
  }

  /**
   * @return the moles.
   */
  public double getMoles() {
    return moles;
  }

}
