package mappers;

import java.util.List;

import model.Base;
import model.BaseDataMapperInterface;
import model.DomainModelException;

public class BaseDataMapper implements BaseDataMapperInterface{

  @Override
  public Base create(String name, double inventory, int solute) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Base read(int id) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(Base base) throws DomainModelException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(Base base) throws DomainModelException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Base> getAll() throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Base> filterByWildCardName(String wildCard) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Base> filterByInventory(double inventory) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Base> filterByInventoryRange(double min, double max) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Base> filterBySolute(int chemicalID) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

}
