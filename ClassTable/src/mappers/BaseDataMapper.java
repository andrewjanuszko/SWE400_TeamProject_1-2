package mappers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import datasource.BaseDTO;
import datasource.BaseRDG;
import datasource.BaseRDGRDS;
import datasource.BaseTDGRDS;
import model.Base;
import model.BaseDataMapperInterface;

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

      base = new Base(dto.getBaseId(), dto.getName(), dto.getInventory(), dto.getSoluteId());
<<<<<<< HEAD
    
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
        base.add(new Base(b.getBaseId(), b.getName(), b.getInventory(), b.getSoluteId()));
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
        base.add(new Base(b.getBaseId(), b.getName(), b.getInventory(), b.getSoluteId()));
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
        base.add(new Base(b.getBaseId(), b.getName(), b.getInventory(), b.getSoluteId()));
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
        base.add(new Base(b.getBaseId(), b.getName(), b.getInventory(), b.getSoluteId()));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

  @Override
  public List<Base> filterBySolute(int chemicalID) {
=======
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
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
  public ArrayList<Base> getAll() {
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().executeQuery();

      for (BaseDTO b : dtos) {
        base.add(new Base(b.getBaseId(), b.getName(), b.getInventory(), b.getSoluteId()));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

  @Override
  public ArrayList<Base> filterByWildCardName(String wildCardName) {
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().filterByName(wildCardName).executeQuery();

      for (BaseDTO b : dtos) {
        base.add(new Base(b.getBaseId(), b.getName(), b.getInventory(), b.getSoluteId()));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

  @Override
  public ArrayList<Base> filterByInventory(double inventory) {
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().filterByInventory(inventory).executeQuery();

      for (BaseDTO b : dtos) {
        base.add(new Base(b.getBaseId(), b.getName(), b.getInventory(), b.getSoluteId()));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

  @Override
  public ArrayList<Base> filterByInventoryRange(double min, double max) {
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().filterByInventoryRange(max, min).executeQuery();

      for (BaseDTO b : dtos) {
        base.add(new Base(b.getBaseId(), b.getName(), b.getInventory(), b.getSoluteId()));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

  @Override
  public ArrayList<Base> filterBySolute(int chemicalID) {
>>>>>>> branch '85-class-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
    List<BaseDTO> dtos;
    ArrayList<Base> base = new ArrayList<>();
    try {
      dtos = BaseTDGRDS.getSingleton().filterBySolute(chemicalID).executeQuery();

      for (BaseDTO b : dtos) {
        base.add(new Base(b.getBaseId(), b.getName(), b.getInventory(), b.getSoluteId()));
      }
    } catch (DatabaseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return base;
  }

}
