package model;

import java.util.List;

public class CompoundDataMapper implements CompoundDataMapperInterface {

  @Override
  public Compound create(String name, double inventory, List<Element> madeOf) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Compound read(int id) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(Compound compound) throws DomainModelException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(Compound compound) throws DomainModelException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Compound> getAll() throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Compound> filterByWildCardName(String wildCard) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Compound> filterByInventory(double inventory) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Compound> filterByInventoryRange(double min, double max) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Compound> filterByMadeOf(int elementID) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

}
