package interfaces;

import java.util.ArrayList;
import model.Compound;

public interface CompoundDataMapperInterface extends ChemicalDataMapperInterface {
  
  public ArrayList<Compound> filterByMadeOf(int elementID);
  
}
