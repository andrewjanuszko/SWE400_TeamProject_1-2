package interfaces;

import java.util.List;

import model.Compound;
import model.Element;

public interface CompoundDataMapperInterface extends ChemicalDataMapperInterface{

  //add using constructor (all instance variables)
  
  //add using constructor(id)
  
  public void addCompound();
  
  public void deleteCompound();
  
  public void updateCompound();
  
  public Compound fetchCompound();
  
  public List<Compound> getAllCompounds();
  
  public List<Element> getCompoundsMadeOf();
  
  public List<Compound> getElementsMake();
  
  
}
