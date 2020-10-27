package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A mapper for Chemicals.
 * 
 * @author andrewjanuszko
 *
 */
public class ChemicalDataMapper implements ChemicalDataMapperInterface {

  /**
   * @see model.ChemicalDataMapperInterface#getAll().
   */
  @Override
  public List<Chemical> getAll() throws DomainModelException {
    List<Chemical> chemicals = new ArrayList<>();
    chemicals.addAll(new ElementDataMapper().getAll());
    chemicals.addAll(new CompoundDataMapper().getAll());
    chemicals.addAll(new BaseDataMapper().getAll());
    chemicals.addAll(new AcidDataMapper().getAll());
    return chemicals;
  }

  /**
   * @see model.ChemicalDataMapperInterface#filterByNameLike(String).
   */
  @Override
  public List<Chemical> filterByNameLike(String nameLike) throws DomainModelException {
    List<Chemical> chemicals = new ArrayList<>();
    chemicals.addAll(new ElementDataMapper().filterByNameLike(nameLike));
    chemicals.addAll(new CompoundDataMapper().filterByNameLike(nameLike));
    chemicals.addAll(new BaseDataMapper().filterByNameLike(nameLike));
    chemicals.addAll(new AcidDataMapper().filterByNameLike(nameLike));
    return chemicals;
  }

  /**
   * @see model.ChemicalDataMapperInterface#filterByInventory(double).
   */
  @Override
  public List<Chemical> filterByInventory(double inventory) throws DomainModelException {
    List<Chemical> chemicals = new ArrayList<>();
    chemicals.addAll(new ElementDataMapper().filterByInventory(inventory));
    chemicals.addAll(new CompoundDataMapper().filterByInventory(inventory));
    chemicals.addAll(new BaseDataMapper().filterByInventory(inventory));
    chemicals.addAll(new AcidDataMapper().filterByInventory(inventory));
    return chemicals;
  }

  /**
   * @see model.ChemicalDataMapperInterface#filterByInventoryBetween(double,
   *      double).
   */
  @Override
  public List<Chemical> filterByInventoryBetween(double min, double max) throws DomainModelException {
    List<Chemical> chemicals = new ArrayList<>();
    chemicals.addAll(new ElementDataMapper().filterByInventoryBetween(min, max));
    chemicals.addAll(new CompoundDataMapper().filterByInventoryBetween(min, max));
    chemicals.addAll(new BaseDataMapper().filterByInventoryBetween(min, max));
    chemicals.addAll(new AcidDataMapper().filterByInventoryBetween(min, max));
    return chemicals;
  }

  @Override
  public List<Chemical> getAllWithLowInventory() throws DomainModelException {
    // TODO Auto-generated method stub
    return null;
  }

}
