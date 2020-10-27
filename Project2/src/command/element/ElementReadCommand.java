package command.element;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementReadCommand implements ElementCreateReadCommandInterface {
  
  private int id;
  
  public ElementReadCommand(int id) {
    this.id = id;
  }

  @Override
  public Element execute() throws DomainModelException {
    return new ElementDataMapper().read(id);
  }

}
