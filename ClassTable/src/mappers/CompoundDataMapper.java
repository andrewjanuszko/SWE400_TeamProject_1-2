package mappers;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import datasource.CompoundDTO;
import datasource.CompoundTDGRDS;
import datasource.ElementDTO;
import datasource.ElementRDG;
import datasource.ElementRDGRDS;
import model.Compound;
import model.CompoundDataMapperInterface;
import model.Element;

public class CompoundDataMapper implements CompoundDataMapperInterface {

  public CompoundDataMapper() {

  }

  @Override
  public void create(Compound compound) {
    List<Integer> elementIds = new ArrayList<>();
    List<Element> elements = compound.getMadeOf();
    for(Element e : elements) {
      elementIds.add(e.getID());
    }
    CompoundTDGRDS.getSingleton().addCompound(compound.getID(), elementIds, compound.getName(),
        compound.getInventory());

  }

  @Override
  public Compound read(int id) {
    Compound compound = null;
    List<CompoundDTO> dtos;
    List<Element> elements = new ArrayList<>();
      CompoundDTO dto = CompoundTDGRDS.getSingleton().getDTO(id);
      dtos = CompoundTDGRDS.getSingleton().findMadeOf(id);
      for(CompoundDTO c : dtos) {
        ElementRDG row = new ElementRDGRDS(c.getElementId());
        ElementDTO eDTO = row.getElement();
        elements.add(new Element(eDTO.getElementId(), eDTO.getName(), eDTO.getInventory(), eDTO.getAtomicNumber(), eDTO.getAtomicMass()));
      }
      compound = new Compound(dto.getCompoundId(), dto.getName(), dto.getInventory(), elements);
      
    return compound;
  }

  @Override
  public void update(Compound compound) {
    delete(compound);
    List<Integer> elementIds = new ArrayList<>();
    List<Element> elements = compound.getMadeOf();
    for(Element e : elements) {
      elementIds.add(e.getID());
    }
    CompoundTDGRDS.getSingleton().addCompound(compound.getID(), elementIds, compound.getName(), compound.getInventory());

  }

  @Override
  public void delete(Compound compound) {
    CompoundTDGRDS.getSingleton().delete(compound.getID());

  }

  @Override
  public List<Compound> getAll() {
    List<Compound> compounds = new ArrayList<>();
    try {
      List<CompoundDTO> dtos = CompoundTDGRDS.getSingleton().executeQuery();
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Compound> filterByWildCardName(String wildCardName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Compound> filterByInventory(double inventory) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Compound> filterByInventoryRange(double min, double max) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Compound> filterByMadeOf(int elementID) {
    // TODO Auto-generated method stub
    return null;
  }

}
