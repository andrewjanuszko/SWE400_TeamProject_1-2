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

  void filterByInventoryRange(double high, double low);

  void filterByInventory(double inventory);

  void filterByElements(int elementId);

  void getAllCompounds();
}
