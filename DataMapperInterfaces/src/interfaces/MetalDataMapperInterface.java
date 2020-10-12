package interfaces;

import java.util.List;

import model.Metal;

public interface MetalDataMapperInterface extends ChemicalDataMapperInterface{
  
  public void addMetald(Metal metal);
  
  public void deleteMetal(Metal metal);
  
  public void updateMetal(int id, Metal newMetal);
  
  public Metal fetchMetal(int id);
  
  public List<Metal> getAllMetals();
  
  public double getMolesRequiredToDissolve(Metal metal);
  

}
