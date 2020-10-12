package interfaces;

import java.util.List;

import model.Acid;

public interface AcidDataMapperInterface extends ChemicalDataMapperInterface {
  
  public void addAcid(int acidId, int soluteId, String name, double inventory);
  
  public void deleteAcid(int acidId);
  
  public void updateAcid(int id, int newId, int newSoluteId, String newName, double newInventory);
  
  public Acid fetchAcid(int id);
  
  public List<Acid> getAllAcids();
  
  
}
