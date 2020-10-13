package datasource;

import java.util.List;

public class ElementTDGRDS implements ElementTDG {

  private static ElementTDGRDS singleton;
  
  public ElementTDGRDS() {
    // TODO Auto-generated constructor stub
  }

  public static ElementTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new ElementTDGRDS();
    }
    return singleton;
  }
  
  @Override
  public List<ElementDTO> getAllElements() {
    // TODO Auto-generated method stub
    return null;
  }

}
