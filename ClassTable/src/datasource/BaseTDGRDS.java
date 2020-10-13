package datasource;

import java.util.List;

public class BaseTDGRDS implements BaseTDG {

  private static BaseTDGRDS singleton;
  
  public BaseTDGRDS() {
    // TODO Auto-generated constructor stub
  }
  
  public static BaseTDGRDS getSingleton() {
    if(singleton == null) {
      singleton = new BaseTDGRDS();
    }
    return singleton;
  }

  @Override
  public List<BaseDTO> getAllBases() {
    // TODO Auto-generated method stub
    return null;
  }

}
