package mappers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import datasource.AcidDTO;
import datasource.AcidRDG;
import datasource.AcidRDGRDS;
import datasource.AcidTDGRDS;
import datasource.MetalDTO;
import datasource.MetalRDG;
import datasource.MetalRDGRDS;
import model.Acid;
import model.AcidDataMapperInterface;
import model.DomainModelException;
import model.Metal;

public class AcidDataMapper implements AcidDataMapperInterface {

  @Override
  public Acid create(String name, double inventory, List<Metal> dissolves, int solute) throws DomainModelException {
    AcidRDG row = new AcidRDGRDS(solute, name, inventory);
    Acid a = convertFromDTO(row.getAcid());
    for (Metal m : dissolves) {
      MetalRDG metal = new MetalRDGRDS(m.getID());
      metal.setDissolvedById(a.getID());
    }
    return a;
  }

  @Override
  public Acid read(int id) {
    Acid acid = null;
    try {
      AcidRDGRDS row = new AcidRDGRDS(id);
      AcidDTO dto = row.getAcid();
      acid = convertFromDTO(dto);
    } catch (DatabaseException | SQLException e) {
      e.printStackTrace();
    }
    return acid;
  }

  @Override
  public void update(Acid acid) {
    try {
      AcidRDG row = new AcidRDGRDS(acid.getID());
      row.setName(acid.getName());
      row.setInventory(acid.getInventory());
      row.setSolute(acid.getSolute());
      row.update();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Acid acid) {
    try {
      AcidRDG row = new AcidRDGRDS(acid.getID());
      row.delete();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ArrayList<Acid> getAll() {
    List<AcidDTO> dtos;
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      dtos = AcidTDGRDS.getSingleton().executeQuery();
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }

    return acids;
  }

  @Override
  public ArrayList<Acid> filterByWildCardName(String wildCardName) {
    List<AcidDTO> dtos;
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      dtos = AcidTDGRDS.getSingleton().filterByName(wildCardName).executeQuery();
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }

    return acids;
  }

  @Override
  public ArrayList<Acid> filterByInventory(double inventory) {
    List<AcidDTO> dtos;
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      dtos = AcidTDGRDS.getSingleton().filterByInventory(inventory).executeQuery();
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }

    return acids;
  }

  @Override
  public ArrayList<Acid> filterByInventoryRange(double min, double max) {
    List<AcidDTO> dtos;
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      dtos = AcidTDGRDS.getSingleton().filterByInventoryRange(max, min).executeQuery();
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }

    return acids;
  }

  @Override
  public ArrayList<Acid> filterBySolute(int chemicalID) {
    List<AcidDTO> dtos;
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      dtos = AcidTDGRDS.getSingleton().filterBySolute(chemicalID).executeQuery();
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }

    return acids;
  }

  public Acid convertFromDTO(AcidDTO dto) {
    ArrayList<MetalDTO> metals = AcidTDGRDS.getMetals(dto.getAcidId());
    ArrayList<Metal> betterMetals = new ArrayList<>();
    for (MetalDTO m : metals) {
      betterMetals.add(new Metal(m.getMetalId(), m.getName(), m.getInventory(), m.getAtomicNumber(), m.getAtomicMass(),
          m.getMoles()));
    }
    return new Acid(dto.getAcidId(), dto.getName(), dto.getInventory(), betterMetals, dto.getSoluteId());
  }

  public List<Acid> filterByLowInventory() {
    
    
    return null;
  }

}
