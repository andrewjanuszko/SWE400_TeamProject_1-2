package interfaces;

import java.util.List;

import model.Base;

public interface BaseDataMapperInterface extends ChemicalDataMapperInterface{

  // add using constructor (all instance variables)

  // add using constructor(id)
  public void addBase();
  
  public void deleteBase();
  
  public void updateBase();
  
  public Base fetchBase();

  public List<Base> getAllBases();
}
