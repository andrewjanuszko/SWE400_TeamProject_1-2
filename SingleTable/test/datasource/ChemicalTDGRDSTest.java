package datasource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataDTO.ChemicalDTO;

public abstract class ChemicalTDGRDSTest extends DatabaseTest {

  protected ChemicalTableDataGateway gateway = getSingletonInstance();

  protected abstract ChemicalTableDataGateway getSingletonInstance();

  /**
   * Fills the database with entries to test on.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @SuppressWarnings("unused")
  @BeforeEach
  public void fillDatabase() throws DatabaseException {
    ChemicalRowDataGatewayRDS.createTable();
    ChemicalRowDataGatewayRDS aquaRegia = new ChemicalRowDataGatewayRDS(5, "Aqua Regia", 123.4, 0, 0, 0, 0, 0);
    ChemicalRowDataGatewayRDS gold = new ChemicalRowDataGatewayRDS(2, "Gold", 923, 0, 0, 1, 170.0, 0);
    ChemicalRowDataGatewayRDS platinum = new ChemicalRowDataGatewayRDS(2, "Platinum", 923, 0, 0, 1, 17.0, 0);
    ChemicalRowDataGatewayRDS palladium = new ChemicalRowDataGatewayRDS(2, "Palladium", 923, 0, 0, 1, 17.0, 0);
    ChemicalRowDataGatewayRDS unknown = new ChemicalRowDataGatewayRDS(1, "Unknown", 123.0, 0, 0, 0, 0, 0);
    ChemicalRowDataGatewayRDS funky = new ChemicalRowDataGatewayRDS(1, "Funky", 9.0, 17, 9.810, 0, 0, 0);
    ChemicalRowDataGatewayRDS carbon = new ChemicalRowDataGatewayRDS(1, "Carbon", 1.0, 6, 12.011, 0, 0, 0);
    ChemicalRowDataGatewayRDS cmpd1 = new ChemicalRowDataGatewayRDS(3, "Compound #1", 66.6, 0, 0, 0, 0, 0);
    ChemicalRowDataGatewayRDS cmpd4 = new ChemicalRowDataGatewayRDS(3, "Compound #4", 213.6, 0, 0, 0, 0, 0);
    ChemicalRowDataGatewayRDS cmpd2 = new ChemicalRowDataGatewayRDS(3, "Compound #2", 621, 0, 0, 0, 0, 0);
    ChemicalRowDataGatewayRDS cmpd3 = new ChemicalRowDataGatewayRDS(3, "Compound #3", 24.2, 0, 0, 0, 0, 0);
    ChemicalRowDataGatewayRDS element1 = new ChemicalRowDataGatewayRDS(1, "element1", 777, 7, 14,5, 0, 0);
    ChemicalRowDataGatewayRDS base = new ChemicalRowDataGatewayRDS(4, "base", 36.6, 0, 0, 0, 0, 2);
  }

  /**
   * 
   * @throws DatabaseException
   */
  @AfterEach
  public void dropDatabase() throws DatabaseException {
    ChemicalRowDataGatewayRDS.dropTable();
  }

  /**
   * Test pulling everything out of the table.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  void testFetchAll() throws DatabaseException {
    ArrayList<ChemicalDTO> chemicals = gateway.getAll().executeQuery();
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
    ArrayList<ChemicalDTO> elements = gateway.getElements().executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getMetals().executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getCompounds().executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getBases().executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getAcids().executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getAll().filterByWildCardName("Compound").executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getAll().filterByInventoryValue(66.6).executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getElements().filterByAtomicNumberValue(6).executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getElements().filterByAtomicMassValue(12.011).executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getMetals().filterByDissolvedBy(1).executeQuery();
    assertEquals(3, chemicals.size());
    assertEquals("Palladium", chemicals.get(2).getName());
  }
  
  /**
   * Test getting things by their moles.
   * @throws DatabaseException
   */
  @Test
  void testFetchByMoles() throws DatabaseException {
    ArrayList<ChemicalDTO> chemicals = gateway.getMetals().filterByMolesRange(16, 18).executeQuery();
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
    ArrayList<ChemicalDTO> chemicals = gateway.getAll().filterBySolute(2).executeQuery();
    assertEquals(1, chemicals.size());
    assertEquals("base", chemicals.get(0).getName());
  }
  
  /**
   * Test getting things with low inventory.
   * @throws DatabaseException
   */
  @Test
  void testGetLowAll() throws DatabaseException {
    ArrayList<ChemicalDTO> chemicals = gateway.executeFindAllWithLowInventory();
    assertEquals(4, chemicals.size());
    assertEquals("Carbon", chemicals.get(1).getName());
  }

}
