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
public class ChemicalGetAllCommand implements ChemicalFilterCommandInterface {
  
  /**
   * Constructor for ChemicalGetAllCommand.
   */
  public ChemicalGetAllCommand() {
    //Empty Constructor.
  }

  /**
   * @see command.chemical.ChemicalFilterCommandInterface#execute().
   */
  @Override
  public List<Chemical> execute() throws DomainModelException {
    return new ChemicalDataMapper().getAll();
  }

}
