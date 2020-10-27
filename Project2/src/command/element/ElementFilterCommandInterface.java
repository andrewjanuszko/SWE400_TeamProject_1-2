package command.element;

import java.util.List;
import model.DomainModelException;
import model.Element;

public interface ElementFilterCommandInterface {

  /**
   * Execute method for Commands.
   */
  public abstract List<Element> execute() throws DomainModelException;
}
