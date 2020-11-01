package command.compound;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import command.CreateCommandInterface;
import model.Chemical;
import model.CompoundDataMapper;
import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

/**
 * Command for creating a Compound object.
 * 
 * @author andrewjanuszko, isabella boone, & kimberly o'neill
 */
public class CompoundCreateCommand implements CreateCommandInterface {

  private String name;
  private double inventory;
  private List<Integer> madeOfID;

  /**
   * Constructor for CompoundCreateCommand(String, double, List<Integer>).
   * 
   * @param name,      the name of the Compound.
   * @param inventory, the inventory of the Compound.
   * @param madeOfID,  the Element IDs that make up the Compound.
   */
  public CompoundCreateCommand(String name, double inventory, List<Integer> madeOfID) {
    this.name = name;
    this.inventory = inventory;
    this.madeOfID = madeOfID;
  }

  /**
   * @see command.CreateCommandInterface#execute().
   */
  @Override
  public Chemical execute() throws DomainModelException {
    try {
      Set<Element> elements = new HashSet<>();
      for (Integer elementID : madeOfID) {
        Element element = new ElementDataMapper().read(elementID);
        elements.add(element);
      }
      if (name.split(" ").length < 2 || name.isBlank()) {
        throw new DomainModelException("Name is invalid. Must be >= 2 words.");
      } else if (inventory < 0) {
        throw new DomainModelException("Inventory is invalid. Must be >= 0.");
      } else {
        List<Element> madeOf = elements.stream().collect(Collectors.toList());
        return new CompoundDataMapper().create(name, inventory, madeOf);
      }
    } catch (DomainModelException e) {
      throw new DomainModelException("Failed to create Compound.", e);
    }
  }

}
