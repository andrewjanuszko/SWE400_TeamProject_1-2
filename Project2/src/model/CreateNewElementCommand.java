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
      if (element.getAtomicMass() < element.getAtomicNumber()) {
        throw new Exception("Atomic Number cannot be more than Atomic Mass");
      } else if (element.getName().contains(" ")) {
        throw new Exception("Element Names cannot contain a space");
      } else {
        elementMapper.create(element.getName(), element.getInventory(), element.getAtomicNumber(),
            element.getAtomicMass());
      }
    } catch (

    DomainModelException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
