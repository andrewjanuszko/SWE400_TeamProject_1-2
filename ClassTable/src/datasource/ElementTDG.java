package datasource;

import java.util.List;

public interface ElementTDG {
  public List<ElementDTO> getAllElements();
  
  public List<ElementDTO> findSetAtomicMass(double lowerLimit, double upperLimit);
  
  public ElementDTO getDTO(int id);
}
