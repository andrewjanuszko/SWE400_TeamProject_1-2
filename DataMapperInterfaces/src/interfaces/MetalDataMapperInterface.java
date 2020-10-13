package interfaces;

import java.util.ArrayList;
import model.Metal;

public interface MetalDataMapperInterface extends ChemicalDataMapperInterface {
  
  public ArrayList<Metal> filterByAcidRequired(double acidRequired);
  
  public ArrayList<Metal> filterByAcidRequiredRange(double min, double max);
  
  public ArrayList<Metal> filterByDissolvedBy(int acidID);
  

}
