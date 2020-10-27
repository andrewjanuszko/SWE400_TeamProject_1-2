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
public class ChemicalFilterByInventoryBetweenCommand implements ChemicalFilterCommandInterface {
  
  private double min;
  private double max;
  
  /**
   * Constructor for a ChemicalFilterByInventoryRangeCommand.
   * @param min the minimum value.
   * @param max the maximum value.
   */
  public ChemicalFilterByInventoryBetweenCommand(double min, double max) {
    this.min = min;
    this.max = max;
  }

  /**
   * @see command.FilterCommand#execute().
   */
  @Override
  public List<Chemical> execute() throws DomainModelException {
    return new ChemicalDataMapper().filterByInventoryBetween(min, max);
  }

}
