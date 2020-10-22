package mappers;

import java.util.ArrayList;

import java.util.List;

import datadto.AcidDTO;
import datasource.AcidRowDataGateway;
import datasource.AcidRowDataGatewayRDS;
import datasource.AcidTableDataGatewayRDS;
import datasource.DatabaseException;
import datasource.MetalRowDataGateway;
import datasource.MetalRowDataGatewayRDS;
import model.Acid;
import model.AcidDataMapperInterface;
import model.DomainModelException;
import model.Metal;

public class AcidDataMapper implements AcidDataMapperInterface {
  private static IdentityMap<Acid> acidMap = new IdentityMap<Acid>();

  @Override
  public Acid create(String name, double inventory, List<Metal> dissolves, int solute) throws DomainModelException {

    int id = 12; // FIGURE OUT HOW TO GET ID!!!
    //TODO distribute dissolves
    Acid a = new Acid(id, name, inventory, dissolves, solute);
    try {
      @SuppressWarnings("unused")
      AcidRowDataGateway gateway = new AcidRowDataGatewayRDS(id, name, inventory, solute);
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return a;
  }

  @Override
  public Acid read(int id) throws DomainModelException {

    try {
      if (acidMap.get(id) == null) {
        AcidRowDataGateway gateway = new AcidRowDataGatewayRDS(id);
        MetalDataMapper metalMapper = new MetalDataMapper();
        Acid acid = new Acid(gateway.getAcidID(), gateway.getName(), gateway.getInventory(),
            metalMapper.filterByDissolvedBy(id), gateway.getSolute());

        acidMap.add(acid);
        return acid;
      } else {
        return acidMap.get(id);
      }

    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    }
    return null;
  }

  @Override
  public void update(Acid acid) throws DomainModelException {
    try {
      AcidRowDataGateway gateway = new AcidRowDataGatewayRDS(acid.getID());
      gateway.setName(acid.getName());
      gateway.setInventory(acid.getInventory());
      gateway.setSolute(acid.getSolute());

      // how to update dissolvedMetals???
      MetalDataMapper metalMapper = new MetalDataMapper();
      for (Metal m : acid.getDissolves()) {
        MetalRowDataGateway metalGate = new MetalRowDataGatewayRDS(m.getID());
        metalGate.setDissolvedBy(acid.getID());
        metalGate.persist();
        metalMapper.update(m);
      }

      gateway.persist();
      acidMap.replace(acid);

    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Acid acid) throws DomainModelException {
    try {
      AcidRowDataGateway gateway = new AcidRowDataGatewayRDS(acid.getID());
      gateway.delete();
      acidMap.remove(acid);
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * Converts a list of AcidDTOs to a list of Acids.
   * @param acidDTOList the list of DTOs.
   * @return the converted list of acids.
   */
  public List<Acid> DTOListToAcidList(List<AcidDTO> acidDTOList) {
    List<Acid> acids = new ArrayList<Acid>();
    try {
      for (AcidDTO dto : acidDTOList) {
        int acidID = dto.getAcidID();
        String name = dto.getName();
        int solute = dto.getSoluteID();
        MetalDataMapper metalMapper = new MetalDataMapper();
        List<Metal> dissolves = metalMapper.filterByDissolvedBy(dto.getAcidID());
        double inventory = dto.getInventory();
        
        Acid acid = new Acid(acidID, name, inventory, dissolves, solute);
        acidMap.add(acid);
        return acids;
      }
    } catch (DomainModelException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return acids;
  }
  
  @Override
  public List<Acid> getAll() throws DomainModelException {
    List<AcidDTO> DTOList = AcidTableDataGatewayRDS.getAll();
    return DTOListToAcidList(DTOList);
  }

  @Override
  public List<Acid> filterByWildCardName(String wildCard) throws DomainModelException {
    List<AcidDTO> DTOList = AcidTableDataGatewayRDS.filterByWildCardName(wildCard);
    return DTOListToAcidList(DTOList);
  }

  @Override
  public List<Acid> filterByInventory(double inventory) throws DomainModelException {
    List<AcidDTO> DTOList = AcidTableDataGatewayRDS.filterByInventory(inventory);
    return DTOListToAcidList(DTOList);
  }

  @Override
  public List<Acid> filterByInventoryRange(double min, double max) throws DomainModelException {
    List<AcidDTO> DTOList = AcidTableDataGatewayRDS.filterByInventoryRange(min, max);
    return DTOListToAcidList(DTOList);
  }

  @Override
  public List<Acid> filterBySolute(int chemicalID) throws DomainModelException {
    List<AcidDTO> DTOList = AcidTableDataGatewayRDS.filterBySolute(chemicalID);
    return DTOListToAcidList(DTOList);
  }

}
