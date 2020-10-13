package interfaces;

import java.util.ArrayList;
import model.Base;

public interface BaseDataMapperInterface extends ChemicalDataMapperInterface {
  
  public ArrayList<Base> filterBySolute(int chemicalID);
}
