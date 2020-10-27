package command.chemical;

import java.util.List;

import model.Chemical;
import model.ChemicalDataMapper;
import model.DomainModelException;

/**
 * Command for Chemicals.
 * @author andrewjanuszko
 *
 */
public class ChemicalFilterByInventoryCommand implements ChemicalFilterCommandInterface {
  
  private double inventory;
  
  /**
   * Constructor for a ChemicalFilterByInventoryCommand.
   * @param inventory the inventory value.
   */
  public ChemicalFilterByInventoryCommand(double inventory) {
    this.inventory = inventory;
  }

  /**
   * @see command.FilterCommand#execute().
   */
  @Override
  public List<Chemical> execute() throws DomainModelException {
    return new ChemicalDataMapper().filterByInventory(inventory);
  }

}
