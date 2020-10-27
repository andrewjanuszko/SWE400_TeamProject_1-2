package command.element;

import java.util.List;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementFilterByLowInventoryCommand implements ElementFilterCommandInterface {
  
  public ElementFilterByLowInventoryCommand() {
    
  }

  @Override
  public List<Element> execute() throws DomainModelException {
    return new ElementDataMapper().filterByLowInventory();
  }

}
