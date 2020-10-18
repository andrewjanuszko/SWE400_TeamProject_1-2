package mappers;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import datasource.ElementDTO;
import datasource.ElementRDG;
import datasource.ElementRDGRDS;
import datasource.ElementTDGRDS;
import datasource.MetalDTO;
import datasource.MetalRDG;
import datasource.MetalRDGRDS;
import datasource.MetalTDGRDS;
import model.DomainModelException;
import model.Element;
import model.Metal;
import model.MetalDataMapperInterface;

public class MetalDataMapper implements MetalDataMapperInterface {

  public MetalDataMapper() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public Metal create(String name, double inventory, int atomicNumber, double atomicMass, double acidAmount)
      throws DomainModelException {
    MetalRDG row = new MetalRDGRDS(0, acidAmount, atomicNumber, atomicMass, name, inventory);
    return convertFromDTO(row.getMetal());
  }

  @Override
  public Metal read(int id) {
    MetalRDG row = new MetalRDGRDS(id);
    MetalDTO dto = row.getMetal();
    Metal metal = convertFromDTO(dto);
    return metal;
  }

  @Override
  public void update(Metal metal) {
    MetalRDG row = new MetalRDGRDS(metal.getID());
    row.setName(metal.getName());
    row.setInventory(metal.getInventory());
    row.setMoles(metal.getAcidAmount());
    row.setAtomicNumber(metal.getAtomicNumber());
    row.setAtomicMass(metal.getAtomicMass());
    row.update();

  }

  @Override
  public void delete(Metal metal) {
    MetalRDG row = new MetalRDGRDS(metal.getID());
    row.delete();

  }

  @Override
  public List<Metal> getAll() {
    List<MetalDTO> dtos;
    List<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().executeQuery();
      
      for(MetalDTO e : dtos) {
        metal.add(convertFromDTO(e));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  @Override
  public List<Metal> filterByWildCardName(String wildCardName) {
    List<MetalDTO> dtos;
    ArrayList<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().filterByName(wildCardName).executeQuery();
      for(MetalDTO m : dtos) {
        metal.add(convertFromDTO(m));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  @Override
  public List<Metal> filterByInventory(double inventory) {
    List<MetalDTO> dtos;
    ArrayList<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().filterByInventory(inventory).executeQuery();
      for(MetalDTO m : dtos) {
        metal.add(convertFromDTO(m));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  @Override
  public List<Metal> filterByInventoryRange(double min, double max) {
    List<MetalDTO> dtos;
    ArrayList<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().filterByInventoryRange(max, min).executeQuery();
      for(MetalDTO m : dtos) {
        metal.add(convertFromDTO(m));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  @Override
  public List<Metal> filterByAtomicNumber(int atomicNumber) {
    List<MetalDTO> dtos;
    ArrayList<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().filterByAtomicNumber(atomicNumber).executeQuery();
      for(MetalDTO m : dtos) {
        metal.add(convertFromDTO(m));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  @Override
  public List<Metal> filterByAtomicMass(double atomicMass) {
    List<MetalDTO> dtos;
    ArrayList<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().filterByAtomicMass(atomicMass).executeQuery();
      for(MetalDTO m : dtos) {
        metal.add(convertFromDTO(m));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  @Override
  public List<Metal> filterByAtomicMassRange(double min, double max) {
    List<MetalDTO> dtos;
    ArrayList<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().filterByAtomicMassRange(max, min).executeQuery();
      for(MetalDTO m : dtos) {
        metal.add(convertFromDTO(m));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  @Override
  public List<Metal> filterByAcidRequired(double acidRequired) {
    List<MetalDTO> dtos;
    ArrayList<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().filterByMoles(acidRequired).executeQuery();
      for(MetalDTO m : dtos) {
        metal.add(convertFromDTO(m));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  @Override
  public List<Metal> filterByAcidRequiredRange(double min, double max) {
    List<MetalDTO> dtos;
    ArrayList<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().filterByMolesRange(max, min).executeQuery();
      for(MetalDTO m : dtos) {
        metal.add(convertFromDTO(m));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  @Override
  public List<Metal> filterByDissolvedBy(int acidID) {
    List<MetalDTO> dtos;
    ArrayList<Metal> metal = new ArrayList<>();
    try {
      dtos = MetalTDGRDS.getSingleton().filterByDissolvedBy(acidID).executeQuery();
      for(MetalDTO m : dtos) {
        metal.add(convertFromDTO(m));
      }
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return metal;
  }

  public Metal convertFromDTO(MetalDTO dto) {
    return new Metal(dto.getMetalId(), dto.getName(), dto.getInventory(), dto.getAtomicNumber(), dto.getAtomicMass(),
        dto.getMoles());
  }

  @Override
  public List<Metal> filterByPartOfCompound(int compoundID) throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }
}
