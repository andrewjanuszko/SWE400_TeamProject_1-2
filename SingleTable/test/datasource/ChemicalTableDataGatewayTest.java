package datasource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataDTO.ChemicalDTO;

public abstract class ChemicalTableDataGatewayTest extends DatabaseTest {

  protected ChemicalTableDataGatewayInterface gateway = getGateway();

  public abstract ChemicalTableDataGatewayInterface getGateway();

  /**
   * Fills the database with entries to test on.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @SuppressWarnings("unused")
  @BeforeEach
  public void fillDatabase() throws DatabaseException {
    ChemicalRowDataGateway.createTable();
    ChemicalRowDataGateway aquaRegia = new ChemicalRowDataGateway(5, "Aqua Regia", 123.4, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway gold = new ChemicalRowDataGateway(2, "Gold", 923, 0, 0, 1, 170.0, 0);
    ChemicalRowDataGateway platinum = new ChemicalRowDataGateway(2, "Platinum", 923, 0, 0, 1, 17.0, 0);
    ChemicalRowDataGateway palladium = new ChemicalRowDataGateway(2, "Palladium", 923, 0, 0, 1, 17.0, 0);
    ChemicalRowDataGateway unknown = new ChemicalRowDataGateway(1, "Unknown", 123.0, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway funky = new ChemicalRowDataGateway(1, "Funky", 9.0, 17, 9.810, 0, 0, 0);
    ChemicalRowDataGateway carbon = new ChemicalRowDataGateway(1, "Carbon", 1.0, 6, 12.011, 0, 0, 0);
    ChemicalRowDataGateway cmpd1 = new ChemicalRowDataGateway(3, "Compound #1", 66.6, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway cmpd4 = new ChemicalRowDataGateway(3, "Compound #4", 213.6, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway cmpd2 = new ChemicalRowDataGateway(3, "Compound #2", 621, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway cmpd3 = new ChemicalRowDataGateway(3, "Compound #3", 24.2, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway element1 = new ChemicalRowDataGateway(1, "element1", 777, 7, 14,5, 0, 0);
    ChemicalRowDataGateway base = new ChemicalRowDataGateway(4, "base", 36.6, 0, 0, 0, 0, 2);
  }

  /**
   * 
   * @throws DatabaseException
   */
  @AfterEach
  public void dropDatabase() throws DatabaseException {
    ChemicalRowDataGateway.dropTable();
  }

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
