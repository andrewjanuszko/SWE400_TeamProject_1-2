package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class for creating an Acid.
 * 
 * @author andrewjanuszko & morgan williams-burrell
 */
public class Acid extends Chemical {

  private List<Metal> dissolves;
  private int solute;

  /**
   * Constructor for creating an Acid object.
   * 
   * @param id        the ID of the Acid.
   * @param name      the name of the Acid.
   * @param inventory the inventory of the Acid.
   * @param dissolves the Metals dissolved by this Acid.
   * @param solute    the Chemical that creates a solution.
   */
  public Acid(int id, String name, double inventory, List<Metal> dissolves, int solute) {
    super(id, name, inventory);
    this.dissolves = dissolves;
    setSolute(solute);
  }

  /**
   * Gets the Metals that are dissolved by this Acid.
   * 
   * @return the Metals that are dissolved by this Acid.
   */
  public List<Metal> getDissolves() {
    return dissolves;
  }

  /**
   * Get the solute for the Acid.
   * 
   * @return the solute for the Acid.
   */
  public int getSolute() {
    return solute;
  }

  /**
   * Set the Metals that are dissolved by this Acid.
   * 
   * @param dissolves the Metals that are dissolved by this Acid.
   * @throws DomainModelException 
   */
  public void setDissolves(List<Integer> dissolvesID) throws DomainModelException {
    Set<Metal> metals = new HashSet<Metal>();
    for (Integer metalID : dissolvesID) {
      Metal metal = new MetalDataMapper().read(metalID);
      metals.add(metal);
    }
    this.dissolves = new ArrayList<Metal>(metals);
  }

  /**
   * Set the solute for the Acid.
   * 
   * @param solute the solute for the Acid.
   */
  public void setSolute(int solute) {
    this.solute = solute;
  }

}
