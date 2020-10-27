package command.element;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementDeleteCommand implements ElementUpdateDeleteInterface {

  private Element element;
  
  public ElementDeleteCommand(Element element) {
    this.element = element;
  }
  
  @Override
  public boolean execute() throws DomainModelException {
    try {
      new ElementDataMapper().delete(element);
      return true;
    } catch (DomainModelException e) {
      throw new DomainModelException("Failed to delete Element", e);
    }
  }

}
