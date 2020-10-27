package command.element;

import java.util.List;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class ElementGetAllCommand implements ElementFilterCommandInterface {
  
  public ElementGetAllCommand() {
    //Empty constructor.
  }

  @Override
  public List<Element> execute() throws DomainModelException {
    return new ElementDataMapper().getAll();
  }

}
