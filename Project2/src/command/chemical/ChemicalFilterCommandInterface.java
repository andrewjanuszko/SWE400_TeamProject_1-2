package command.chemical;

import java.util.List;

import model.Chemical;
import model.DomainModelException;

/**
 * All commands implement the Command Class and implement the execute method
 * 
 * @author kim & isabella
 *
 */
public interface ChemicalFilterCommandInterface {
  
  /**
   * Execute method for Commands.
   */
  public abstract List<Chemical> execute() throws DomainModelException;
}
