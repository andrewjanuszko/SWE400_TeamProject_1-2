package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

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
    assertEquals("acidname1", chem1.getName());
    assertEquals("acidname2", chem2.getName());
    assertEquals("basename1", chem3.getName());
    assertEquals("basename2", chem4.getName());
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
    assertEquals(1.1, chem1.getInventory(), 0.1);
    assertEquals(1.2, chem2.getInventory(), 0.1);
    assertEquals(1.1, chem3.getInventory(), 0.1);
    assertEquals(1.2, chem4.getInventory(), 0.1);
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
    assertEquals("chemname9", chem_getter.getName());
    assertEquals(1.9, chem_getter.getInventory(), 0.1);

    // Change it, update, and refresh gateway
    chem_setter.setName("chemname6");
    chem_setter.setInventory(1.8);
    chem_setter.update();
    chem_getter = new ChemicalRDGRDS(9);

    // Make sure update worked
    assertEquals("chemname6", chem_getter.getName());
    assertEquals(1.8, chem_getter.getInventory(), 0.1);

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
    assertEquals("chemname9", chem.getName());
    assertEquals(1.9, chem.getInventory(), 0.1);

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
   * Run every test in TestChemical
   */
  static void testAll() {
    try {
      testGetName();
      testGetInventory();
      testDelete();
      testUpdate();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

}
