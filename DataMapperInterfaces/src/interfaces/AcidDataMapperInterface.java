package interfaces;

import java.util.List;

import model.Acid;

public interface AcidDataMapperInterface extends ChemicalDataMapperInterface {

  //add using constructor (all instance variables)
  
  //add using constructor(id)
  
  public void addAcid();
  
  public void deleteAcid();
  
  public void updateAcid();
  
  public Acid fetchAcid();
  
  public List<Acid> getAllAcids();
  
}
