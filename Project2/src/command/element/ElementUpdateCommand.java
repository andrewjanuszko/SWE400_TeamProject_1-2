package command.element;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementUpdateCommand implements ElementUpdateDeleteInterface {
  
  private Element element;
  
  public ElementUpdateCommand(Element element) {
    this.element = element;
  }

  @Override
  public boolean execute() throws DomainModelException {
    try {
      new ElementDataMapper().update(element);
      return true;
    } catch (DomainModelException e) {
      throw new DomainModelException("Failed to update Element", e);
    }
  }

}
