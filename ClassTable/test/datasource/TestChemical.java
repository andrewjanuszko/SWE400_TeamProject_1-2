package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import database.DatabaseException;

/**
 * 
 * @author Isabella Boone
 *
 */
class TestChemical extends DatabaseTest {

  /**
   * Test that the getName function of ChemicalRDGRDS works
   * 
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  static void testGetName() throws SQLException, DatabaseException {
    // Fetch chemicals
    ChemicalRDG chem1 = new ChemicalRDGRDS(1), chem2 = new ChemicalRDGRDS(2), chem3 = new ChemicalRDGRDS(11),
        chem4 = new ChemicalRDGRDS(12);

    // Tests
    assertEquals("acidname1", chem1.getChemical().getName());
    assertEquals("acidname2", chem2.getChemical().getName());
    assertEquals("basename1", chem3.getChemical().getName());
    assertEquals("basename2", chem4.getChemical().getName());
  }

  /**
   * Test that the getInventory function of ChemicalRDGRDS works
   * 
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  static void testGetInventory() throws SQLException, DatabaseException {
    // Fetch chemicals
    ChemicalRDG chem1 = new ChemicalRDGRDS(1), chem2 = new ChemicalRDGRDS(2), chem3 = new ChemicalRDGRDS(11),
        chem4 = new ChemicalRDGRDS(12);

    // Tests
    assertEquals(1.1, chem1.getChemical().getInventory(), 0.1);
    assertEquals(1.2, chem2.getChemical().getInventory(), 0.1);
    assertEquals(1.1, chem3.getChemical().getInventory(), 0.1);
    assertEquals(1.2, chem4.getChemical().getInventory(), 0.1);
  }

  /**
   * Test that the update function of ChemicalRDGRDS works
   * 
   * @throws DatabaseException
   * @throws SQLException
   */
  @Test
  static void testUpdate() throws SQLException, DatabaseException {
    // Create acid and getter for it
    ChemicalRDG chem_setter = new ChemicalRDGRDS(9, "chemname9", 1.9), chem_getter = new ChemicalRDGRDS(9);

    // Ensure it was added
    assertEquals("chemname9", chem_getter.getChemical().getName());
    assertEquals(1.9, chem_getter.getChemical().getInventory(), 0.1);

    // Change it, update, and refresh gateway
    chem_setter.setName("chemname6");
    chem_setter.setInventory(1.8);
    chem_setter.update();
    chem_getter = new ChemicalRDGRDS(9);

    // Make sure update worked
    assertEquals("chemname6", chem_getter.getChemical().getName());
    assertEquals(1.8, chem_getter.getChemical().getInventory(), 0.1);

    chem_getter.delete(); // Delete the chemical
  }

  /**
   * Test that the delete function in ChemicalRDGRDS works
   */
  @Test
  static void testDelete() {
    // Create a new chemical
    ChemicalRDG chem = new ChemicalRDGRDS(9, "chemname9", 1.9);

    // Ensure it has been added
    assertEquals("chemname9", chem.getChemical().getName());
    assertEquals(1.9, chem.getChemical().getInventory(), 0.1);

    // Delete it
    chem.delete();

    // When we try to retrieve it, it will fail
    try {
      chem = new ChemicalRDGRDS(9);
    } catch (DatabaseException | SQLException e) {
      assertTrue(true);
    }
  }

  /**
   * Test the getAll function in ChemicalRDGRDS
   * 
   * Note: this test is run after TestAcid and TestBase, but before the others, so
   * at this point in the test only acids and bases have been inserted.
   */
  @Test
  static void testGetAll() {
    
    List<ChemicalDTO> getAll;
    try {
      getAll = new ChemicalTDGRDS().getAllChemicals().executeQuery();
      
      assertEquals(12, getAll.size());
      assertEquals(1, getAll.get(0).getChemicalId());
      assertEquals(2, getAll.get(1).getChemicalId());
      assertEquals(3, getAll.get(2).getChemicalId());
      assertEquals(4, getAll.get(3).getChemicalId());
      assertEquals(5, getAll.get(4).getChemicalId());
      assertEquals(6, getAll.get(5).getChemicalId());
      assertEquals(11, getAll.get(6).getChemicalId());
      assertEquals(12, getAll.get(7).getChemicalId());
      assertEquals(13, getAll.get(8).getChemicalId());
      assertEquals(14, getAll.get(9).getChemicalId());
      assertEquals(15, getAll.get(10).getChemicalId());
      assertEquals(16, getAll.get(11).getChemicalId());
    } catch(DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  public static void testFilters() {
    // Insert sample chemicals to mess with
    ChemicalRDG gateway1 = new ChemicalRDGRDS(80, "funkyacid1", 41.2);
    ChemicalRDG gateway2 = new ChemicalRDGRDS(81, "funkyacid21", 42.4);
    
    try {
      assertEquals(80, new ChemicalRDGRDS(80).getChemical().getChemicalId());
      assertEquals(81, new ChemicalRDGRDS(81).getChemical().getChemicalId());
    } catch (DatabaseException | SQLException e) {
      e.printStackTrace();
    }
    
    // filter by name
    try {
      List<ChemicalDTO> get = new ChemicalTDGRDS().getAllChemicals().filterByName("funky").executeQuery();
      
      assertEquals(2, get.size());
      assertEquals(80, get.get(0).getChemicalId());
      assertEquals(81, get.get(1).getChemicalId());
      
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    
    // filter by inventory & inventory range
    try {
      List<ChemicalDTO> get = new ChemicalTDGRDS().getAllChemicals().filterByInventory(41.2).executeQuery();
      
      assertEquals(80, get.get(0).getChemicalId());
      
      get = new ChemicalTDGRDS().getAllChemicals().filterByInventoryRange(42, 40).executeQuery();
      
      assertEquals(80, get.get(0).getChemicalId());
      
      get = new ChemicalTDGRDS().getAllChemicals().filterByInventoryRange(43, 40).executeQuery();
      
      assertEquals(80, get.get(0).getChemicalId());
      assertEquals(81, get.get(1).getChemicalId());
      
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
    
    ChemicalTDGRDS.delete(80);
    ChemicalTDGRDS.delete(81);
  }

  /**
   * Run every test in TestChemical
   */
  static void testAll() {
    try {
      testGetName();
      testGetInventory();
      testDelete();
      testUpdate();
      testGetAll();
      testFilters(); 
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

}
