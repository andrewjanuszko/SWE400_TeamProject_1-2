package command.element;

import java.util.List;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementFilterByPartOfCommand implements ElementFilterCommandInterface {
  
  private int compoundID;
  
  public ElementFilterByPartOfCommand(int compoundID) {
    this.compoundID = compoundID;
  }

  @Override
  public List<Element> execute() throws DomainModelException {
    return new ElementDataMapper().filterByPartOfCompound(compoundID);
  }

}
