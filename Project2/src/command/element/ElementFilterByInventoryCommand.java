package command.element;

import java.util.List;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementFilterByInventoryCommand implements ElementFilterCommandInterface {
  
  private double inventory;
  
  public ElementFilterByInventoryCommand(double inventory) {
    this.inventory = inventory;
  }

  @Override
  public List<Element> execute() throws DomainModelException {
    return new ElementDataMapper().filterByInventory(inventory);
  }

}
