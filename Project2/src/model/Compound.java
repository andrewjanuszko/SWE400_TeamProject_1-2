package model;

import java.util.List;

/**
 * Class for creating a Compound.
 * 
 * @author andrewjanuszko & morgan williams-burrell
 */
public class Compound extends Chemical {

  private List<Element> madeOf;

  /**
   * Constructor for creating a Compound.
   * 
   * @param id        the ID of the Compound.
   * @param name      the name of the Compound.
   * @param inventory the inventory of the Compound.
   * @param madeOf    the Elements that make up the Compound.
   */
  public Compound(int id, String name, double inventory, List<Element> madeOf) {
    super(id, name, inventory);
    setMadeOf(madeOf);
  }

  /**
   * Set the Elements that make up the Compound.
   * 
   * @param madeOf the Elements that make up the Compound.
   */
  public void setMadeOf(List<Element> madeOf) {
    this.madeOf = madeOf;
  }

  /**
   * Get the Elements that make up the Compound.
   * 
   * @return the Elements that make up the Compound.
   */
  public List<Element> getMadeOf() {
    return madeOf;
  }

}
