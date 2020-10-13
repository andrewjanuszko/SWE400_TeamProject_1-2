package interfaces;

import java.util.ArrayList;
import model.Element;

public interface ElementDataMapperInterface extends ChemicalDataMapperInterface {
  
  public ArrayList<Element> filterByAtomicNumber(int atomicNumber);
  
  public ArrayList<Element> filterByAtomicMass(double atomicMass);
  
  public ArrayList<Element> filterByAtomicMassRange(double min, double max);
  
  public ArrayList<Element> filterByPartOfCompound(int compoundID);
  
}
