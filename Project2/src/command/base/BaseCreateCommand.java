package command.base;

import model.BaseDataMapper;
import command.CreateCommandInterface;
import model.Acid;
import model.AcidDataMapper;
import model.Base;
import model.DomainModelException;

/**
 * Command for creating a Base object.
 * 
 * @author andrewjanuszko, isabella boone, & kimberly o'neill
 */
public class BaseCreateCommand implements CreateCommandInterface {

  private String name;
  private double inventory;
  private int solute;

  /**
   * Constructor for BaseCreateCommand(String, double, int).
   * 
   * @param name,      the name of the Base.
   * @param inventory, the inventory of the Base.
   * @param solute,    the ID of the solute for the Base.
   */
  public BaseCreateCommand(String name, double inventory, int solute) {
    this.name = name;
    this.inventory = inventory;
    this.solute = solute;
  }

  /**
   * @see command.CreateCommandInterface#execute().
   */
  @Override
  public Base execute() throws DomainModelException {
    try {
      Acid acid = new AcidDataMapper().read(solute);
      if (name.split(" ").length < 2 || name.isBlank()) {
        throw new DomainModelException("Name is invalid. Must be >= 2 words.");
      } else if (inventory < 0) {
        throw new DomainModelException("Inventory is invalid. Must be >= 0.");
      } else {
        return new BaseDataMapper().create(name, inventory, acid.getID());
      }
    } catch (DomainModelException e) {
      throw new DomainModelException("Failed to create Base.", e);
    }
  }

}
