package interfaces;

import java.util.List;

import model.Base;

public interface BaseDataMapperInterface extends ChemicalDataMapperInterface{
  
  public void addBase(Base base);
  
  public void deleteBase(Base base);
  
  public void updateBase(int id, Base base);
  
  public Base fetchBase(int id);

  public List<Base> getAllBases();
}
