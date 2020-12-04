package model;

import java.util.ArrayList;
import java.util.List;

import datadto.BaseDTO;
import datasource.BaseRowDataGateway;
import datasource.BaseRowDataGatewayRDS;
import datasource.BaseTableDataGatewayRDS;
import datasource.DatabaseException;
import model.BaseDataMapperInterface;

public class BaseDataMapper implements BaseDataMapperInterface {
  public static IdentityMap<Base> baseMap = new IdentityMap<Base>();

  @Override
  public Base create(String name, double inventory, Chemical solute) throws DomainModelException {

    // TODO distribute dissolves

    try {
      BaseRowDataGateway gateway = new BaseRowDataGatewayRDS(name, inventory, solute.getID(), solute.getClass().getName());
      Base a = new Base(gateway.getBaseID(), name, inventory, solute);
      baseMap.add(a);
      return a;
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Base read(int id) throws DomainModelException {
    try {
      if (baseMap.get(id) == null) {
        BaseRowDataGateway gateway = new BaseRowDataGatewayRDS(id);
        Base base = new Base(gateway.getBaseID(), gateway.getName(), gateway.getInventory(), soluteType(gateway.getSoluteType(), gateway.getSolute()));

        baseMap.add(base);
        return base;
      } else {
        return baseMap.get(id);
      }

    } catch (DatabaseException e) {
      // e.printStackTrace();
    }
    return null;
  }

  @Override
  public void update(Base base) throws DomainModelException {
    try {
      BaseRowDataGateway gateway = new BaseRowDataGatewayRDS(base.getID());
      gateway.setName(base.getName());
      gateway.setInventory(base.getInventory());
      gateway.setSolute(base.getSolute().getID());
      gateway.setSoluteType(base.getSolute().getClass().getName());
      gateway.persist();
      baseMap.replace(base);

    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Base base) throws DomainModelException {
    try {
      BaseRowDataGateway gateway = new BaseRowDataGatewayRDS(base.getID());
      gateway.delete();
      baseMap.remove(base);
    } catch (DatabaseException e) {
      e.printStackTrace();
    }

  }

  /**
   * Fetches a solute by it's type
   * 
   * @param s Solute Type
   * @param i ID
   * @return Solute
   */
  private Chemical soluteType(String s, int i) {
    // very possible there is infinite loading shenanigans
    try {
      if (s.contains("Acid")) {
        AcidDataMapper m = new AcidDataMapper();
        return m.read(i);
      } else if (s.contains("Base")) {
        BaseDataMapper m = new BaseDataMapper();
        return m.read(i);
      } else if (s.contains("Compound")) {
        CompoundDataMapper m = new CompoundDataMapper();
        return m.read(i);
      } else if (s.contains("Element")) {
        ElementDataMapper m = new ElementDataMapper();
        return m.read(i);
      } else if (s.contains("Metal")) {
        BaseDataMapper m = new BaseDataMapper();
        return m.read(i);
      }

    } catch (DomainModelException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public List<Base> DTOListToBaseList(List<BaseDTO> baseDTOList) {
    List<Base> bases = new ArrayList<Base>();
    for (BaseDTO dto : baseDTOList) {
      int baseID = dto.getBaseID();
      String name = dto.getName();
      double inventory = dto.getInventory();
      int solute = dto.getSoluteID();
      String soluteType = dto.getSoluteType();

      Base base = new Base(baseID, name, inventory, soluteType(soluteType, solute));
      bases.add(base);
      baseMap.add(base);
    }
    return bases;
  }

  @Override
  public List<Base> getAll() throws DomainModelException {
    List<BaseDTO> DTOList = BaseTableDataGatewayRDS.getAll();
    return DTOListToBaseList(DTOList);
  }

  @Override
  public List<Base> filterByNameLike(String nameLike) throws DomainModelException {
    List<BaseDTO> DTOList = BaseTableDataGatewayRDS.filterByNameLike(nameLike);
    return DTOListToBaseList(DTOList);
  }

  @Override
  public List<Base> filterByInventory(double inventory) throws DomainModelException {
    List<BaseDTO> DTOList = BaseTableDataGatewayRDS.filterByInventory(inventory);
    return DTOListToBaseList(DTOList);
  }

  @Override
  public List<Base> filterByLowInventory() throws DomainModelException {
    List<BaseDTO> DTOList = BaseTableDataGatewayRDS.filterByLowInventory();
    return DTOListToBaseList(DTOList);
  }

  @Override
  public List<Base> filterByInventoryBetween(double min, double max) throws DomainModelException {
    List<BaseDTO> DTOList = BaseTableDataGatewayRDS.filterByInventoryBetween(min, max);
    return DTOListToBaseList(DTOList);
  }

  @Override
  public List<Base> filterBySolute(int chemicalID) throws DomainModelException {
    List<BaseDTO> DTOList = BaseTableDataGatewayRDS.filterBySolute(chemicalID);
    return DTOListToBaseList(DTOList);
  }
}
