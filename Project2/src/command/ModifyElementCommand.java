package command;

import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;
import model.ElementDataMapperInterface;

public class ModifyElementCommand implements Command {
  private Element element; 
  
  public ModifyElementCommand(Element element) {
    this.element = element;
  }
  
  @Override
  public void execute() {
    ElementDataMapperInterface elementMapper = new ElementDataMapper(); 
    
    try {
      elementMapper.update(element);
    } catch (DomainModelException e) {
      //
    }
  }
}
