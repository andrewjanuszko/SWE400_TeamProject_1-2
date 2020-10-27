package command.element;

import java.util.List;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementFilterByAtomicMassCommand implements ElementFilterCommandInterface {
  
  private double atomicMass;
  
  public ElementFilterByAtomicMassCommand(double atomicMass) {
    this.atomicMass = atomicMass;
  }

  @Override
  public List<Element> execute() throws DomainModelException {
    return new ElementDataMapper().filterByAtomicMass(atomicMass);
  }

}
