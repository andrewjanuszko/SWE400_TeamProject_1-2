package datasource;

import java.util.List;

public class AcidTDGRDS implements AcidTDG{

  private static AcidTDGRDS singleton;
  
  public AcidTDGRDS() {
 
  }
  
  public static AcidTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new AcidTDGRDS();
    }
    return singleton;
  }

  @Override
  public List<AcidDTO> getAllAcids() {
    // TODO Auto-generated method stub
    return null;
  }

}
