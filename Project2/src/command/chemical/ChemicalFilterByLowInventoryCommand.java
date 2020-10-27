package command.chemical;

import java.util.List;

import model.Chemical;
import model.ChemicalDataMapper;
import model.DomainModelException;

/**
 * Command.
 * @author andrewjanuszko
 *
 */
public class ChemicalFilterByLowInventoryCommand implements ChemicalFilterCommandInterface {
  
  /**
   * Constructor for ChemicalFilterByLowInventoryCommand.
   */
  public ChemicalFilterByLowInventoryCommand() {
    // Empty Constructor.
  }

  /**
   * @see command.chemical.ChemicalFilterCommandInterface#execute().
   */
  @Override
  public List<Chemical> execute() throws DomainModelException {
    return new ChemicalDataMapper().filterByLowInventory();
  }

}
