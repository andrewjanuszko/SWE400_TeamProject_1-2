package mappers;

import java.util.List;

import datasource.DatabaseException;
import datasource.MetalRowDataGateway;
import datasource.MetalRowDataGatewayRDS;
import model.DomainModelException;
import model.Metal;
import model.MetalDataMapperInterface;

public class MetalDataMapper implements MetalDataMapperInterface {
  public static IdentityMap<Metal> metalMap = new IdentityMap<Metal>();

  @Override
  public Metal create(String name, double inventory, int atomicNumber, double atomicMass, double acidAmount)
      throws DomainModelException {
    try {
      int id = 101010101;//// ohnooo
      Metal m = new Metal(id, name, inventory, atomicNumber, atomicMass, acidAmount);
      
      // HOW DO I GET THE DISOLVED BY???? can it just be null?
      MetalRowDataGateway gateway = new MetalRowDataGatewayRDS(id, name, inventory, atomicNumber, atomicMass,
          acidAmount, 0);
      return m;
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Metal read(int id) throws DomainModelException {
    try {
      if (metalMap.get(id) == null) {
        MetalRowDataGateway gateway = new MetalRowDataGatewayRDS(id);
        Metal metal = new Metal(gateway.getMetalID(), gateway.getName(), gateway.getInventory(),
            gateway.getAtomicNumber(), gateway.getAtomicMass(), gateway.getAcidAmount());

        metalMap.add(metal);
        return metal;
      } else {
        return metalMap.get(id);
      }

    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void update(Metal metal) throws DomainModelException {
    try {
      MetalRowDataGateway gateway = new MetalRowDataGatewayRDS(metal.getID());
      gateway.setName(metal.getName());
      gateway.setInventory(metal.getInventory());
      gateway.setAtomicNumber(metal.getAtomicNumber());
      gateway.setAtomicMass(metal.getAtomicMass());
      gateway.setAcidAmount(metal.getAcidAmount());

      // how to update dissolvedMetals???
      gateway.persist();
      metalMap.replace(metal);

    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Metal metal) throws DomainModelException {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Metal> getAll() throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Metal> filterByWildCardName(String wildCard) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Metal> filterByInventory(double inventory) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Metal> filterByInventoryRange(double min, double max) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Metal> filterByAtomicNumber(int atomicNumber) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Metal> filterByAtomicMass(double atomicMass) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Metal> filterByAtomicMassRange(double min, double max) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Metal> filterByAcidRequired(double acidRequired) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Metal> filterByAcidRequiredRange(double min, double max) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Metal> filterByDissolvedBy(int acidID) throws DomainModelException {
 // possibly lazy load metals?
//    ArrayList<MetalRowDataGatewayRDS> metalGateways = MetalRowDataGatewayRDS.findDissolves(id);
//    ArrayList<Metal> dissolvedMetals = new ArrayList<Metal>();
//    for (MetalRowDataGatewayRDS m : metalGateways) {
//      Metal metal = new Metal(m.getMetalID(), m.getName(), m.getInventory(), m.getAtomicNumber(), m.getAtomicMass(),
//          m.getAcidAmount());
//      dissolvedMetals.add(metal);
//      MetalDataMapper.metalMap.add(metal);
//    }
    return null;
  }

  @Override
  public List<Metal> filterByPartOfCompound(int compoundID) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

}
