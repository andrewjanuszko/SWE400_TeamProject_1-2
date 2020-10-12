package interfaces;

import java.util.List;

import model.Element;

public interface ElementDataMapperInterface extends ChemicalDataMapperInterface {
  
  public void addElement(Element element);
  
  public void deleteElement(Element element);
  
  public void updateElement(int id, Element newElement);
  
  public Element fetchElement(int id);
  
  public List<Element> getAllElements();
  
  public List<Element> getElementsAtomicMassRange(double high, double low);
  
  public List<Element> getElementsAtomicNumberRange(double high, double low);
  
  public Element getByAtomicMass(double mass);
  
  public Element getByAtomicNumber(double num);
  
}
