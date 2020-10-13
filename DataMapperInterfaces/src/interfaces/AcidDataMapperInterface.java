package interfaces;

import java.util.ArrayList;
import model.Acid;

public interface AcidDataMapperInterface extends ChemicalDataMapperInterface {
  
  public ArrayList<Acid> filterBySolute(int chemicalID);
  
}
