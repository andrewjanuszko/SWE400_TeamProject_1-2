package command.element;

import model.DomainModelException;
import model.Element;

public interface ElementCreateReadCommandInterface {
  
  public Element execute() throws DomainModelException;

}
