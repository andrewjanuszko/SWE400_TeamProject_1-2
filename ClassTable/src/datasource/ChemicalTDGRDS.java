package datasource;

import java.util.List;

public class ChemicalTDGRDS implements ChemicalTDG {

  private static ChemicalTDGRDS singleton;
  public ChemicalTDGRDS() {
    // TODO Auto-generated constructor stub
  }

  public static ChemicalTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new ChemicalTDGRDS();
    }
    return singleton;
  }
  
  @Override
  public List<ChemicalDTO> getAllChemicals() {
    // TODO Auto-generated method stub
    return null;
  }

}
