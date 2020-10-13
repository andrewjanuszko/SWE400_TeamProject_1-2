package interfaces;

import java.util.List;

import model.Acid;

public interface AcidDataMapperInterface extends ChemicalDataMapperInterface {
  
  public void addAcid(Acid acid);
  
  public void deleteAcid(int acidId);
  
  public void updateAcid(int id, Acid acid);
  
  public Acid fetchAcid(int id);
  
  public List<Acid> getAllAcids();
  
  
}
