package command.element;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementCreateCommand implements ElementCreateReadCommandInterface {
  
  private String name;
  private double inventory;
  private int atomicNumber;
  private double atomicMass;
  
  public ElementCreateCommand(String name, double inventory, int atomicNumber, double atomicMass) {
    this.name = name;
    this.inventory = inventory;
    this.atomicNumber = atomicNumber;
    this.atomicMass = atomicMass;
  }

  @Override
  public Element execute() throws DomainModelException {
    return new ElementDataMapper().create(name, inventory, atomicNumber, atomicMass);
  }

}
