package command.element;

import java.util.List;
import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

/**
 * Command.
 * @author andrewjanuszko
 *
 */
public class ElementFilterByNameCommand implements ElementFilterCommandInterface {
  
  private String name;
  
  /**
   * Constructor for a ElementFilterByNameCommand.
   * @param name the name to filter by.
   */
  public ElementFilterByNameCommand(String name) {
    this.name = name;
  }

  /**
   * @see command.ElementFilterCommand#execute().
   */
  @Override
  public List<Element> execute() throws DomainModelException {
    return new ElementDataMapper().filterByNameLike(name);
  }

}
