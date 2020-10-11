package interfaces;

import java.util.List;

import model.Element;

public interface ElementDataMapperInterface extends ChemicalDataMapperInterface {

  //add using constructor (all instance variables)
  
  //add using constructor(id)
  
  public void addElement();
  
  public void deleteElement();
  
  public void updateElement();
  
  public Element fetchElement();
  
  public List<Element> getAllElements();
  
  public List<Element> getElementsAtomicMassRange();
  
  public List<Element> getElementsAtomicNumberRange();
  
  public Element getByAtomicMass();
  
  public Element getByAtomicNumber();
  
}
