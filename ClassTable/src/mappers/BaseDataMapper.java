package mappers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import datasource.AcidDTO;
import datasource.AcidTDGRDS;
import datasource.BaseDTO;
import datasource.BaseRDG;
import datasource.BaseRDGRDS;
import datasource.BaseTDGRDS;
import datasource.MetalDTO;
import model.Acid;
import model.Base;
import model.BaseDataMapperInterface;
import model.Metal;

public class BaseDataMapper implements BaseDataMapperInterface {

  public BaseDataMapper() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void create(Base base) {
    BaseRDG rowGateway = new BaseRDGRDS(base.getID(), base.getSolute(), base.getName(), base.getInventory());

  }

  @Override
  public Base read(int id) {
    Base base = null;
    try {
      BaseRDG row = new BaseRDGRDS(id);
      BaseDTO dto = row.getBase();

      base = convertFromDTO(dto);
    
    } catch (DatabaseException | SQLException e) {
      e.printStackTrace();
    }
    return base;
  }

  @Override
  public void update(Base base) {
    try {
      BaseRDG row = new BaseRDGRDS(base.getID());
      row.setName(base.getName());
      row.setInventory(base.getInventory());
      row.setSolute(base.getSolute());
      row.update();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Base base) {
    try {
      BaseRDG row = new BaseRDGRDS(base.getID());
      row.delete();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<Base> getAll() {
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().executeQuery();

      for (BaseDTO b : dtos) {
        base.add(convertFromDTO(b));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

  @Override
  public List<Base> filterByWildCardName(String wildCardName) {
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().filterByName(wildCardName).executeQuery();

      for (BaseDTO b : dtos) {
        base.add(convertFromDTO(b));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

  @Override
  public List<Base> filterByInventory(double inventory) {
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().filterByInventory(inventory).executeQuery();

      for (BaseDTO b : dtos) {
        base.add(convertFromDTO(b));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

  @Override
  public List<Base> filterByInventoryRange(double min, double max) {
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().filterByInventoryRange(max, min).executeQuery();

      for (BaseDTO b : dtos) {
        base.add(convertFromDTO(b));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

  @Override
  public List<Base> filterBySolute(int chemicalID) {
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().filterBySolute(chemicalID).executeQuery();
      for (BaseDTO b : dtos) {
        base.add(convertFromDTO(b));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }
  
  public Base convertFromDTO(BaseDTO dto) {
    return new Base(dto.getBaseId(), dto.getName(), dto.getInventory(), dto.getSoluteId()); 
  }

}
