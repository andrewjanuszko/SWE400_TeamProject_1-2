package interfaces;

import java.util.List;

import model.Compound;
import model.Element;

public interface CompoundDataMapperInterface extends ChemicalDataMapperInterface{
  
  public void addCompound(Compound compound);
  
  public void deleteCompound(Compound compound);
  
  public void updateCompound(int oldId, Compound newCompound);
  
  public Compound fetchCompound(int compoundId);
  
  public List<Compound> getAllCompounds();
  
  public List<Element> getCompoundsMadeOf(int compoundId);
  
  public List<Compound> getElementsMake(int elementId);
  
}
