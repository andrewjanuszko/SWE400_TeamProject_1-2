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
  
  /**
   * List all elements with a like name the given partialName
   * @param partialName a segment of a name to search by
   * @return
   * @throws DatabaseException
   */
  public ArrayList<ChemicalDTO> filterElementByNameLike(String partialName) throws DatabaseException;
  
  /**
   * List all elements with the given inventory value
   * @param inventory
   * @return
   * @throws DatabaseException
   */
  public ArrayList<ChemicalDTO> filterElementByInventory(double inventory) throws DatabaseException;
  
  /**
   * List all elements in a given inventory range
   * @param min
   * @param max
   * @return
   * @throws DatabaseException
   */
  public ArrayList<ChemicalDTO> filterElementByInvetoryRange(double min, double max) throws DatabaseException;
  
  /**
   * List all elements with the given atomic mass value
   * @param atomicMass
   * @return
   * @throws DatabaseException
   */
  public ArrayList<ChemicalDTO> filterElementByAtomicMass(double atomicMass) throws DatabaseException;

  /**
   * List all elements in the given atomic mass range 
   * @param min
   * @param max
   * @return
   * @throws DatabaseException
   */
  public ArrayList<ChemicalDTO> filterElementByAtomicMassRange(double min, double max) throws DatabaseException;
  
  /**
   * List all elements with a given atomic number
   * @param atomicNumber
   * @return
   * @throws DatabaseException
   */
  public ArrayList<ChemicalDTO> filterElementByAtomicNumber(int atomicNumber) throws DatabaseException;
  
  /**
   * List all elements in a given atomic number range
   * @param min
   * @param max
   * @return
   * @throws DatabaseException
   */
  public ArrayList<ChemicalDTO> filterElementByAtomicNumberRange(int min, int max) throws DatabaseException;
  
  

}
