package model;

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

public class AcidDataMapper implements AcidDataMapperInterface {

  @Override
  public Acid create(String name, double inventory, List<Metal> dissolves, int solute) throws DomainModelException {
    // Create acid using AcidRDG
    AcidRDG row = new AcidRDGRDS(solute, name, inventory);

    // Set dissolvedById for metals
    Acid a = convertFromDTO(row.getAcid());
    for (Metal m : dissolves) {
      MetalRDG metal = new MetalRDGRDS(m.getID());
      metal.setDissolvedById(a.getID());
    }

    return a;
  }

  @Override
  public Acid read(int id) throws DomainModelException {
    try {
      // Use AcidRDG to read acid
      AcidRDGRDS row = new AcidRDGRDS(id);
      return convertFromDTO(row.getAcid());
    } catch (DatabaseException | SQLException e) {
      throw new DomainModelException("Problem reading acid id = " + id, e);
    }
  }

  @Override
  public void update(Acid acid) {
    try {
      // Use AcidRDG to fetch acid
      AcidRDG row = new AcidRDGRDS(acid.getID());

      // Use setters to update values
      row.setName(acid.getName());
      row.setInventory(acid.getInventory());
      row.setSolute(acid.getSolute());

      // Update the acid
      row.update();
    } catch (SQLException | DatabaseException e) {
      System.out.println("Problem updating Acid " + acid.getID());
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Acid acid) {
    try {
      AcidRDG row = new AcidRDGRDS(acid.getID());
      // Use AcidRDG to fetch acid
      row.delete();
      // Delete acid
    } catch (SQLException | DatabaseException e) {
      System.out.println("Failed to delete Acid " + acid.getID());
      e.printStackTrace();
    }
  }

  @Override
  public ArrayList<Acid> getAll() {
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      // Get all acids 
      List<AcidDTO> dtos = AcidTDGRDS.getSingleton().executeQuery();
      // For all acids, convert dto to acid and add to list
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      // Problem with executeQuery()
      e.printStackTrace();
    }

    return acids;
  }

  @Override
  public ArrayList<Acid> filterByWildCardName(String wildCardName) {
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      // Get all acids with specific name
      List<AcidDTO> dtos = AcidTDGRDS.getSingleton().filterByName(wildCardName).executeQuery();
      // For all acids, convert dto to acid and add to list
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      // Problem with executeQuery()
      e.printStackTrace();
    }

    return acids;
  }

  @Override
  public ArrayList<Acid> filterByInventory(double inventory) {
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      // Get all acids with specific inventory value
      List<AcidDTO> dtos = AcidTDGRDS.getSingleton().filterByInventory(inventory).executeQuery();
      // For all acids, convert dto to acid and add to list
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      // Problem with executeQuery()
      e.printStackTrace();
    }

    return acids;
  }

  @Override
  public ArrayList<Acid> filterByInventoryRange(double min, double max) {
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      // Get all acids with specific inventory range
      List<AcidDTO> dtos = AcidTDGRDS.getSingleton().filterByInventoryRange(max, min).executeQuery();
      // For all acids, convert dto to acid and add to list
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      // Problem with executeQuery()
      e.printStackTrace();
    }

    return acids;
  }

  @Override
  public ArrayList<Acid> filterBySolute(int chemicalID) {
    ArrayList<Acid> acids = new ArrayList<>();
    try {
      // Get all acids  with specific solute id
      List<AcidDTO> dtos = AcidTDGRDS.getSingleton().filterBySolute(chemicalID).executeQuery();
      // Get all acids 
      for (AcidDTO a : dtos) {
        acids.add(convertFromDTO(a));
      }
    } catch (DatabaseException e) {
      // Problem with executeQuery()
      e.printStackTrace();
    }

    return acids;
  }

  /**
   * Convert dto to acid
   * @param dto to convert
   * @return converted acid
   */
  private Acid convertFromDTO(AcidDTO dto) {
    ArrayList<MetalDTO> metals = AcidTDGRDS.getMetals(dto.getAcidId());
    ArrayList<Metal> betterMetals = new ArrayList<>();
    for (MetalDTO m : metals) {
      betterMetals.add(new Metal(m.getMetalId(), m.getName(), m.getInventory(), m.getAtomicNumber(), m.getAtomicMass(),
          m.getMoles()));
    }
    return new Acid(dto.getAcidId(), dto.getName(), dto.getInventory(), betterMetals, dto.getSoluteId());
  }

}
