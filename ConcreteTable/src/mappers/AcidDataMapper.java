package mappers;

import java.util.ArrayList;

import datasource.AcidRowDataGatewayRDS;
import datasource.DatabaseException;
import datasource.MetalRowDataGatewayRDS;
import model.Acid;
import model.AcidDataMapperInterface;
import model.Metal;

public class AcidDataMapper implements AcidDataMapperInterface {

  private AcidRowDataGatewayRDS acidGateway;
  // private MetalRowDataGatewayRDS metalGateway;
  private Acid acid;

  public AcidDataMapper(int acidID) throws DatabaseException {
    //if acid is not in map
    if(AcidIdentityMap.getAcid(acidID) == null) {
      this.acidGateway = new AcidRowDataGatewayRDS(acidID);       //find Acid using gateway
      this.acid = gatewayToAcid(acidGateway, acidID);             //Make Acid from gateway
      AcidIdentityMap.addAcid(acid);                              //add Acid to the map
    }
    else {
      this.acid = AcidIdentityMap.getAcid(acidID);                //get Acid from the map
    }
    
  }

  public void create(Acid acid) {
    try {
      acidGateway = new AcidRowDataGatewayRDS(acid.getID(), acid.getName(), acid.getInventory(), acid.getSolute());
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private Acid gatewayToAcid(AcidRowDataGatewayRDS acidGateway, int acidID) throws DatabaseException {
    ArrayList<MetalRowDataGatewayRDS> metalGateways = MetalRowDataGatewayRDS.findDissolves(acidID);
    ArrayList<Metal> dissolvedMetals = new ArrayList<Metal>();

    for (MetalRowDataGatewayRDS m : metalGateways) {
      // dissolvedMetals.add(new MetalDataMapper(m));
    }

    return new Acid(acidGateway.getAcidID(), acidGateway.getName(), acidGateway.getInventory(), dissolvedMetals,
        acidGateway.getSolute());
  }

  @Override
  public Acid read(int id) {
    try {
      this.acidGateway = new AcidRowDataGatewayRDS(id);
      this.acid = gatewayToAcid(acidGateway, id);

    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return acid;

  }

//  private AcidRowDataGatewayRDS acidToGateway(Acid acid) throws DatabaseException {
//    //is solute a string??
//    return new AcidRowDataGatewayRDS(acid.getID(), acid.getName(), acid.getInventory(), String.valueOf(acid.getSolute()));
//  }
  @Override
  public void update(Acid acid) {
    try {
      acidGateway = new AcidRowDataGatewayRDS(acid.getID());
      acidGateway.setInventory(acid.getInventory());
      acidGateway.setName(acid.getName());
      acidGateway.setSolute(acid.getSolute());
      acidGateway.persist();
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Acid acid) {
    try {
      acidGateway = new AcidRowDataGatewayRDS(acid.getID());
      acidGateway.delete();
      acidGateway = null;
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  public ArrayList<Acid> getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Acid> filterByWildCardName(String wildCardName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Acid> filterByInventory(double inventory) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Acid> filterByInventoryRange(double min, double max) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Acid> filterBySolute(int chemicalID) {
    // TODO Auto-generated method stub
    return null;
  }

}
