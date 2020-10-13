package datasource;

import java.util.List;

public class MetalTDGRDS implements MetalTDG {

  private static MetalTDGRDS singleton;
  
  public MetalTDGRDS() {
    // TODO Auto-generated constructor stub
  }
  
  public static MetalTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new MetalTDGRDS();
    }
    return singleton;
  }

  @Override
  public List<MetalDTO> getAllMetals() {
    // TODO Auto-generated method stub
    return null;
  }

}
