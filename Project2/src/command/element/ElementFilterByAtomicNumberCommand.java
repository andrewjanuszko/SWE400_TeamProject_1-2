package command.element;

import java.util.List;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementFilterByAtomicNumberCommand implements ElementFilterCommandInterface {
  
  private int atomicNumber;
  
  public ElementFilterByAtomicNumberCommand(int atomicNumber) {
    this.atomicNumber = atomicNumber;
  }

  @Override
  public List<Element> execute() throws DomainModelException {
    return new ElementDataMapper().filterByAtomicNumber(atomicNumber);
  }

}
