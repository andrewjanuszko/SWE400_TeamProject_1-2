package datasource;

import dataDTO.ChemicalDTO;
import java.util.ArrayList;

/**
 * Table Data Gateway for Chemical.
 * 
 * @author andrewjanuszko
 */
public interface ChemicalTableDataGateway {

  public ArrayList<ChemicalDTO> fetchAll() throws DatabaseException;

  /**
   * List all Chemicals that have a given name.
   * 
   * @param name the name.
   * @return a list of Chemicals.
   * 
   * @throws DatabaseException when things go wrong.
   */
  public ArrayList<ChemicalDTO> filterAllByName(String name) throws DatabaseException;

  /**
   * List all Chemicals that have a name similar to the one provided.
   * 
   * @param partialName the wild card.
   * @return a list of Chemicals.
   * 
   * @throws DatabaseException when things go wrong.
   */
  public ArrayList<ChemicalDTO> filterAllByPartialName(String partialName) throws DatabaseException;

  /**
   * List all Chemicals that have a specific inventory value.
   * 
   * @param inventory the specific inventory value
   * @return a list of Chemicals.
   * 
   * @throws DatabaseException when things go wrong.
   */
  public ArrayList<ChemicalDTO> filterAllByInventory(double inventory) throws DatabaseException;

  /**
   * List all Chemicals that have an inventory value in the provided range.
   * 
   * @param min the minimum inventory
   * @param max the maximum inventory
   * @return a list of Chemicals.
   * 
   * @throws DatabaseException when things go wrong.
   */
  public ArrayList<ChemicalDTO> filterAllByInventoryRange(double min, double max) throws DatabaseException;

  /**
   * List all Chemicals that have low inventory.
   * 
   * @return a list of Chemicals.
   * 
   * @throws DatabaseException when things go wrong.
   */
  public ArrayList<ChemicalDTO> filterAllByLowInventory() throws DatabaseException;

}
