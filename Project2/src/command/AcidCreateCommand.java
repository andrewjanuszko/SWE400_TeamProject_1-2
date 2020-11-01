package command;

import java.util.ArrayList;
import java.util.List;
import model.Acid;
import model.AcidDataMapper;
import model.Base;
import model.BaseDataMapper;
import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

/**
 * Command for creating an Acid object.
 * 
 * @author andrewjanuszko, isabella boone, & kimberly o'neill
 */
public class AcidCreateCommand implements CreateCommandInterface {

  private String name;
  private double inventory;
  private List<Integer> dissolvesID;
  private int solute;

  /**
   * Constructor for AcidCreateCommand(String, double, List<Metal>, int).
   * 
   * @param name,      the name of the Acid.
   * @param inventory, the inventory of the Acid.
   * @param dissolves, the Metals dissolved by the Acid.
   * @param solute,    the ID of the solute for the Acid.
   */
  public AcidCreateCommand(String name, double inventory, List<Integer> dissolvesID, int solute) {
    this.name = name;
    this.inventory = inventory;
    this.dissolvesID = dissolvesID;
    this.solute = solute;
  }

  /**
   * @see command.CreateCommandInterface#execute().
   */
  @Override
  public Acid execute() throws DomainModelException {
    List<Metal> dissolves = new ArrayList<>();
    try {
      for (Integer metalID : dissolvesID) {
        Metal metal = new MetalDataMapper().read(metalID);
        dissolves.add(metal);
      }
      Base base = new BaseDataMapper().read(solute);
      if (name.split(" ").length < 2 || name.isBlank()) {
        throw new DomainModelException("Name is invalid. Must be >= 2 words.");
      } else if (inventory < 0) {
        throw new DomainModelException("Inventory is invalid. Must be >= 0.");
      } else {
        return new AcidDataMapper().create(name, inventory, dissolves, base.getID());
      }
    } catch (DomainModelException e) {
      throw new DomainModelException("Failed to create Acid.", e);
    }
  }

}
