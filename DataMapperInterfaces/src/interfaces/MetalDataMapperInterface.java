package interfaces;

import java.util.List;

import model.Metal;

public interface MetalDataMapperInterface extends ChemicalDataMapperInterface{
  
  //add using constructor (all instance variables)
  
  //add using constructor(id)
  
  public void addCompound();
  
  public void deleteCompound();
  
  public void updateCompound();
  
  public Metal fetchMetal();
  
  public List<Metal> getAllMetals();
  
  public double getMolesRequiredToDissolve();
  

}
