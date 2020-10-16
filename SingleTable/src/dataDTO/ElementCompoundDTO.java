package dataDTO;

import java.util.List;

public final class ElementCompoundDTO {
  
  private final int id;
  private final List<ChemicalDTO> relations;
  
  public ElementCompoundDTO(int id, List<ChemicalDTO> relations) {
    this.id = id;
    this.relations = relations;
  }
  
  public int getID() {
    return id;
  }
  
  public List<ChemicalDTO> getRelations() {
    return relations;
  }

}
