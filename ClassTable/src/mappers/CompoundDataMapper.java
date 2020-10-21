package mappers;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
import datasource.CompoundDTO;
import datasource.CompoundRDG;
import datasource.CompoundRDGRDS;
import datasource.CompoundTDG;
import datasource.CompoundTDGRDS;
import datasource.ElementDTO;
import datasource.ElementRDG;
import datasource.ElementRDGRDS;
import model.Compound;
import model.CompoundDataMapperInterface;
import model.DomainModelException;
import model.Element;

public class CompoundDataMapper implements CompoundDataMapperInterface {

  @Override
  public Compound create(String name, double inventory, List<Element> madeOf) throws DomainModelException {
    CompoundRDG create = new CompoundRDGRDS(madeOf, name, inventory);
    return new Compound(create.getCompound().getCompoundId(), name, inventory, madeOf);
  }

  @Override
  public Compound read(int id) throws DomainModelException {
    CompoundRDG read = new CompoundRDGRDS(id);
    return new Compound(read.getCompound().getCompoundId(), read.getCompound().getName(),
        read.getCompound().getInventory(), DTOToElement(read.getCompound().getElements()));
  }

  @Override
  public void update(Compound compound) throws DomainModelException {
    CompoundRDG update = new CompoundRDGRDS(compound.getID());
    update.update(CompoundToDTO(compound));
  }

  @Override
  public void delete(Compound compound) throws DomainModelException {
    CompoundRDG delete = new CompoundRDGRDS(compound.getID());
    delete.delete(compound.getID());

  }

  @Override
  public List<Compound> getAll() throws DomainModelException {
    CompoundTDG tdg = new CompoundTDGRDS();
    try {
      return ListDTOToListCompound(tdg.getAllCompounds().executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed getAll()", e);
    }
  }

  @Override
  public List<Compound> filterByWildCardName(String wildCard) throws DomainModelException {
    CompoundTDG tdg = new CompoundTDGRDS();
    try {
      return ListDTOToListCompound(tdg.getAllCompounds().filterByName(wildCard).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed filterByWildCardName()", e);
    }
  }

  @Override
  public List<Compound> filterByInventory(double inventory) throws DomainModelException {
    CompoundTDG tdg = new CompoundTDGRDS();
    try {
      return ListDTOToListCompound(tdg.getAllCompounds().getAllCompounds().filterByInventory(inventory).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed filterByInventory()", e);
    }
  }

  @Override
  public List<Compound> filterByInventoryRange(double min, double max) throws DomainModelException {
    CompoundTDG tdg = new CompoundTDGRDS();
    try {
      return ListDTOToListCompound(
          tdg.getAllCompounds().getAllCompounds().filterByInventoryRange(max, min).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed filterByInventoryRange()", e);
    }
  }

  @Override
  public List<Compound> filterByMadeOf(int elementID) throws DomainModelException {
    CompoundTDG tdg = new CompoundTDGRDS();
    try {
      return ListDTOToListCompound(tdg.getAllCompounds().getAllCompounds().filterByElements(elementID).executeQuery());
    } catch (DatabaseException e) {
      throw new DomainModelException("Failed filterByMadeOf()", e);
    }
  }

  /**
   * Convert a DTO to an Element
   * 
   * @param dto List<ElementDTO> to convert
   * @return converted List<Element>
   */
  private List<Element> DTOToElement(List<ElementDTO> dto) {
    List<Element> elements = new ArrayList<>();

    for (ElementDTO e : dto) {
      elements
          .add(new Element(e.getElementId(), e.getName(), e.getInventory(), e.getAtomicNumber(), e.getAtomicMass()));
    }

    return elements;
  }

  /**
   * Convert a list of Elements to a list of ElementDTOs
   * 
   * @param elements List<Element> to convert
   * @return converted List<ElementDTO>
   */
  private List<ElementDTO> ElementToDTO(List<Element> elements) {
    List<ElementDTO> dto = new ArrayList<>();

    for (Element e : elements) {
      dto.add(new ElementDTO(e.getID(), e.getAtomicNumber(), e.getAtomicMass(), e.getName(), e.getInventory()));
    }

    return dto;

  }

  /**
   * Convert a compound to a compoundDTO
   * 
   * @param c Compound
   * @return CompoundDTO
   */
  private CompoundDTO CompoundToDTO(Compound c) {
    return new CompoundDTO(c.getID(), ElementToDTO(c.getMadeOf()), c.getName(), c.getInventory());
  }

  /**
   * Convert a list of CompoundDTOs to a list of Compounds
   * 
   * @param listCompoundDTOs list of compound DTOs to convert
   * @return converted list of compounds
   */
  private List<Compound> ListDTOToListCompound(List<CompoundDTO> listCompoundDTOs) {
    List<Compound> listCompounds = new ArrayList<>();
    for (CompoundDTO c : listCompoundDTOs) {
      listCompounds.add(new Compound(c.getCompoundId(), c.getName(), c.getInventory(), DTOToElement(c.getElements())));
    }
    return listCompounds;
  }

}
