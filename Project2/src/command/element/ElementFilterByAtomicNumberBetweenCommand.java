package command.element;

import java.util.List;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementFilterByAtomicNumberBetweenCommand implements ElementFilterCommandInterface {
  
  private int min;
  private int max;
  
  public ElementFilterByAtomicNumberBetweenCommand(int min, int max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public List<Element> execute() throws DomainModelException {
    return new ElementDataMapper().filterByAtomicNumberBetween(min, max);
  }

}
