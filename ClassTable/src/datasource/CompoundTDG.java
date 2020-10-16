package datasource;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseException;
/**
 * 
 * @author kimberlyoneill
 *
 */
public interface CompoundTDG {

  ArrayList<CompoundDTO> findMakes(int elementId);

  ArrayList<CompoundDTO> findMadeOf(int compoundId);

  public CompoundDTO getDTO(int id);
 
  public void addCompound(int compoundId, List<Integer> madeOf, String name, double inventory);

  void delete(int compoundId);

  List<CompoundDTO> executeQuery() throws DatabaseException;

  CompoundTDGRDS filterByInventoryRange(double high, double low);

  CompoundTDGRDS filterByInventory(double inventory);

  CompoundTDGRDS filterByElements(int elementId);

  CompoundTDGRDS getAllCompounds();
}
