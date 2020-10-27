package command.element;

import java.util.List;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementFilterByInventoryBetweenCommand implements ElementFilterCommandInterface {
  
  private double min;
  private double max;
  
  public ElementFilterByInventoryBetweenCommand(double min, double max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public List<Element> execute() throws DomainModelException {
    return new ElementDataMapper().filterByInventoryBetween(min, max);
  }

}
