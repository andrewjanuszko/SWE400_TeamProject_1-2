package datasource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataDTO.ChemicalDTO;

public abstract class ChemicalTableDataGatewayInterfaceTest extends DatabaseTest {

  protected ChemicalTableDataGatewayInterface gateway = getGateway();

  public abstract ChemicalTableDataGatewayInterface getGateway();

  /**
   * Test pulling everything out of the table.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchAll() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getAll().executeQuery();
    assertEquals(13, chemicals.size());
    assertEquals("Aqua Regia", chemicals.get(0).getName());
  }

  /**
   * Test pulling elements out of the table.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchElements() throws DatabaseException {
    List<ChemicalDTO> elements = gateway.getElements().executeQuery();
    assertEquals(7, elements.size());
    assertEquals("Gold", elements.get(0).getName());
  }

  /**
   * Test pulling metals out of the table.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchMetals() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getMetals().executeQuery();
    assertEquals(3, chemicals.size());
    assertEquals("Platinum", chemicals.get(1).getName());
  }

  /**
   * Test pulling compounds out of the table.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchCompounds() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getCompounds().executeQuery();
    assertEquals(4, chemicals.size());
    assertEquals("Compound #1", chemicals.get(0).getName());
  }

  /**
   * Test pulling bases out of the table.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchBases() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getBases().executeQuery();
    assertEquals(1, chemicals.size());
    assertEquals("base", chemicals.get(0).getName());
  }

  /**
   * Test pulling acids out of the table.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchAcids() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getAcids().executeQuery();
    assertEquals(1, chemicals.size());
    assertEquals("Aqua Regia", chemicals.get(0).getName());
  }

  /**
   * Test finding things by name.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchByName() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getAll().filterByNameLike("Compound").executeQuery();
    assertEquals(4, chemicals.size());
    assertEquals("Compound #1", chemicals.get(0).getName());
  }

  /**
   * Test finding things by habitat.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchByInventory() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getAll().filterByInventory(66.6).executeQuery();
    assertEquals(1, chemicals.size());
    assertEquals("Compound #1", chemicals.get(0).getName());
  }

  /**
   * Test finding things by atomic number.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchByAtomicNumber() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getElements().filterByAtomicNumber(6).executeQuery();
    assertEquals(1, chemicals.size());
    assertEquals("Carbon", chemicals.get(0).getName());
  }

  /**
   * Test finding things by atomic mass.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchByAtomicMassValue() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getElements().filterByAtomicMass(12.011).executeQuery();
    assertEquals(1, chemicals.size());
    assertEquals("Carbon", chemicals.get(0).getName());
  }

  /**
   * Test finding things by dissolved by.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchByDissolvedBy() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getMetals().filterByDissolvedBy(1).executeQuery();
    assertEquals(3, chemicals.size());
    assertEquals("Palladium", chemicals.get(2).getName());
  }
  
  /**
   * Test getting things by their moles.
   * @throws DatabaseException
   */
  @Test
  void testFetchByMoles() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getMetals().filterByAcidAmountBetween(16, 18).executeQuery();
    assertEquals(2, chemicals.size());
    assertEquals("Palladium", chemicals.get(1).getName());
  }

  /**
   * Test finding things by solute.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchBySolute() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getAll().filterBySolute(2).executeQuery();
    assertEquals(1, chemicals.size());
    assertEquals("base", chemicals.get(0).getName());
  }
  
  /**
   * Test getting things with low inventory.
   * @throws DatabaseException
   */
  @Test
  void testGetLowAll() throws DatabaseException {
    List<ChemicalDTO> chemicals = gateway.getAllWithLowInventory();
    assertEquals(4, chemicals.size());
    assertEquals("Carbon", chemicals.get(1).getName());
  }

}
