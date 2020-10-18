package model;

import mappers.ElementDataMapper;

public class CreateNewElementCommand implements Command {

  Element element;
  public CreateNewElementCommand(Element element) {
    this.element = element;
  }
  @Override
  public void execute() {
    ElementDataMapperInterface elementMapper = new ElementDataMapper();
    try {
      elementMapper.create(element.getName(), element.getInventory(), element.getAtomicNumber(), element.getAtomicMass());
    } catch (DomainModelException e) {
      e.printStackTrace();
    }
    
  }

}
