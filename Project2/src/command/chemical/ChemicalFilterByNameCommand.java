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
public class ChemicalFilterByNameCommand implements ChemicalFilterCommandInterface {
  
  private String name;
  
  /**
   * Constructor for a ChemicalFilterByNameCommand.
   * @param name the name to filter by.
   */
  public ChemicalFilterByNameCommand(String name) {
    this.name = name;
  }

  /**
   * @see command.FilterCommand#execute().
   */
  @Override
  public List<Chemical> execute() throws DomainModelException {
    return new ChemicalDataMapper().filterByNameLike(name);
  }

}
