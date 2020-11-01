package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    this.madeOf = madeOf;
  }

  /**
   * Set the Elements that make up the Compound.
   * 
   * @param madeOf the Elements that make up the Compound.
   * @throws DomainModelException 
   */
  public void setMadeOf(List<Integer> madeOfID) throws DomainModelException {
    Set<Element> elements = new HashSet<Element>();
    for (Integer elementID : madeOfID) {
      Element element = new ElementDataMapper().read(elementID);
      elements.add(element);
    }
    this.madeOf = new ArrayList<Element>(elements);
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
