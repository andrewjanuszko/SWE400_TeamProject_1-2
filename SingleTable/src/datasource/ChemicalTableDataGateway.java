package datasource;

import dataDTO.ChemicalDTO;
import java.util.List;

/**
 * Table Data Gateway for Chemical.
 * 
 * @author andrewjanuszko
 */
public abstract class ChemicalTableDataGateway {

  /**
   * Get all Chemicals.
   * @return all Chemicals.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway getAll();
=======
  public abstract ChemicalTableDataGateway getAll();
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Elements.
   * @return all Elements.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway getElements();
=======
  public abstract ChemicalTableDataGateway getElements();
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Metals.
   * @return all Metals.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway getMetals();
=======
  public abstract ChemicalTableDataGateway getMetals();
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Compounds.
   * @return all Compounds.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway getCompounds();
=======
  public abstract ChemicalTableDataGateway getCompounds();
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Bases.
   * @return all Bases.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway getBases();
=======
  public abstract ChemicalTableDataGateway getBases();
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Acids.
   * @return all Acids.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway getAcids();
=======
  public abstract ChemicalTableDataGateway getAcids();
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Chemicals with a name like String.
   * @param nameLike the name we are looking for.
   * @return all Chemicals with a name like String.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByNameLike(String nameLike);
=======
  public abstract ChemicalTableDataGateway filterByNameLike(String nameLike);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Chemicals with a given inventoryValue.
   * @param inventoryValue the amount of a chemical in stock.
   * @return all chemical with a given inventory
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByInventory(double inventoryValue);
=======
  public abstract ChemicalTableDataGateway filterByInventory(double inventoryValue);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git

  /**
   * Get all Chemicals with an inventoryValue between the given range.
   * @param min the minimum amount of a chemical.
   * @param max the maximum amount of a chemical.
   * @return all chemical in a given inventory range
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByInventoryBetween(double min, double max);
=======
  public abstract ChemicalTableDataGateway filterByInventoryBetween(double min, double max);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Chemicals with an atomicNumberValue.
   * @param atomicNumberValue the atomic number you are looking for.
   * @return all chemical with a given atomic number
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByAtomicNumber(int atomicNumberValue);
=======
  public abstract ChemicalTableDataGateway filterByAtomicNumber(int atomicNumberValue);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Chemicals with an atomic number between the given range.
   * @param min the minimum amount for the atomic number range.
   * @param max the maximum amount the atomic number range.
   * @return all chemical in a given atomic number range
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByAtomicNumberBetween(int min, int max);
=======
  public abstract ChemicalTableDataGateway filterByAtomicNumberBetween(int min, int max);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Chemicals with a given atomicMassValue.
   * @param atomicMassValue the atomic mass you are searching by.
   * @return
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByAtomicMass(double atomicMassValue);
=======
  public abstract ChemicalTableDataGateway filterByAtomicMass(double atomicMassValue);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all Chemicals with an atomic mass between the given range.
   * @param min the minimum amount for the atomic mass range.
   * @param max the maximum amount the atomic mass range.
   * @return all chemical in a given atomic mass reange.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByAtomicMassBetween(double min, double max);
=======
  public abstract ChemicalTableDataGateway filterByAtomicMassBetween(double min, double max);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all chemical with a given dissolvedByID.
   * @param dissolvedByID the id of an acid the dissolves the a metal.
   * @return all chemical that are dissolved by a the given id.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByDissolvedBy(int dissolvedByID);
=======
  public abstract ChemicalTableDataGateway filterByDissolvedBy(int dissolvedByID);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all chemical that need a given acidAmount to be dissolved.
   * @param acidAmount the amount of acid needed to dissolve a chemical.
   * @return all chemical with that need the given acid amount to be dissolved.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByAcidAmount(double acidAmount);
=======
  public abstract ChemicalTableDataGateway filterByAcidAmount(double acidAmount);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all chemicals in a given acid amount range.
   * @param min the minimum amount for the acid amount range.
   * @param max the maximum amount for the acid amount range.
   * @return all chemical in the given acid amount range.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterByAcidAmountBetween(double min, double max);
=======
  public abstract ChemicalTableDataGateway filterByAcidAmountBetween(double min, double max);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Get all chemicals with given soluteId.
   * @param soluteID the id of a solute.
   * @return all chemicals with the given soluteId.
   */
<<<<<<< HEAD
  public ChemicalTableDataGateway filterBySolute(int soluteID);
=======
  public abstract ChemicalTableDataGateway filterBySolute(int soluteID);
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  
  /**
   * Runs the query
   * @return the results of the constructed query
   * @throws DatabaseException
   */
<<<<<<< HEAD
  public List<ChemicalDTO> executeQuery() throws DatabaseException;
  
  /**
   * Gets all the chemicals that are low inventory.
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getAllWithLowInventory() throws DatabaseException;
  
  /**
   * Gets all the elements that are low inventory.
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getElementsWithLowInventory() throws DatabaseException;
  
  /**
   * Gets all the metals that are low inventory
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getMetalsWithLowInventory() throws DatabaseException;
  
  /**
   *  Gets all the bases that are low inventory
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getBasesWithLowInventory() throws DatabaseException;
  
  /**
   *  Gets all the acids that are low inventory
   * @return
   * @throws DatabaseException
   */
  public List<ChemicalDTO> getAcidsWithLowInventory() throws DatabaseException;
=======
  public abstract List<ChemicalDTO> executeQuery() throws DatabaseException;
  
  /**
   * Gets all the chemicals that are low inventory.
   * @return
   * @throws DatabaseException
   */
  public abstract List<ChemicalDTO> getAllWithLowInventory() throws DatabaseException;
  
  /**
   * Gets all the elements that are low inventory.
   * @return
   * @throws DatabaseException
   */
  public abstract List<ChemicalDTO> getElementsWithLowInventory() throws DatabaseException;
  
  /**
   * Gets all the metals that are low inventory
   * @return
   * @throws DatabaseException
   */
  public abstract List<ChemicalDTO> getMetalsWithLowInventory() throws DatabaseException;
  
  /**
   *  Gets all the bases that are low inventory
   * @return
   * @throws DatabaseException
   */
  public abstract List<ChemicalDTO> getBasesWithLowInventory() throws DatabaseException;
  
  /**
   *  Gets all the acids that are low inventory
   * @return
   * @throws DatabaseException
   */
  public abstract List<ChemicalDTO> getAcidsWithLowInventory() throws DatabaseException;
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git

}
