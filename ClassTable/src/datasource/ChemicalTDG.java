package datasource;

import java.util.List;

import database.DatabaseException;

public interface ChemicalTDG {

  public void getAllChemicals();

  public void filterByName(String name);

  public void filterByInventory(double inventory);

  public void filterByInventoryRange(double high, double low);

  public List<ChemicalDTO> executeQuery() throws DatabaseException;
}
