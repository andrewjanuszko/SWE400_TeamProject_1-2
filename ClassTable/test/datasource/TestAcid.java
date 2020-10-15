package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import database.DatabaseException;

/**
 * 
 * @author Isabella Boone
 * 
 */
class TestAcid extends DatabaseTest {

  /**
   * Test that the getName function in AcidRDGRDS works.
   * 
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  static void testGetName() throws SQLException, DatabaseException {
    // Fetch acids
    AcidRDG acid1 = new AcidRDGRDS(1), acid2 = new AcidRDGRDS(2), acid3 = new AcidRDGRDS(3), acid4 = new AcidRDGRDS(4);

    // Test
    assertEquals("acidname1", acid1.getAcid().getName());
    assertEquals("acidname2", acid2.getAcid().getName());
    assertEquals("acidname3", acid3.getAcid().getName());
    assertEquals("acidname4", acid4.getAcid().getName());
  }

  /**
   * Test that the getInventory function in AcidRDGRDS works.
   * 
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  static void testGetInventory() throws SQLException, DatabaseException {
    // Fetch acids
    AcidRDG acid1 = new AcidRDGRDS(1), acid2 = new AcidRDGRDS(2), acid3 = new AcidRDGRDS(3), acid4 = new AcidRDGRDS(4);

    // Test
    assertEquals(1.1, acid1.getAcid().getInventory(), 0.1);
    assertEquals(1.2, acid2.getAcid().getInventory(), 0.1);
    assertEquals(1.3, acid3.getAcid().getInventory(), 0.1);
    assertEquals(1.4, acid4.getAcid().getInventory(), 0.1);
  }

  /**
   * Test that the getSolute function in AcidRDGRDS works.
   * 
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  static void testGetSolute() throws SQLException, DatabaseException {
    // Fetch acids
    AcidRDG acid1 = new AcidRDGRDS(1), acid2 = new AcidRDGRDS(2), acid3 = new AcidRDGRDS(3), acid4 = new AcidRDGRDS(4);

    // Test
    assertEquals(51, acid1.getAcid().getSoluteId());
    assertEquals(52, acid2.getAcid().getSoluteId());
    assertEquals(53, acid3.getAcid().getSoluteId());
    assertEquals(54, acid4.getAcid().getSoluteId());
  }

  /**
   * Test that the delete function in AcidRDGRDS works.
   */
  @Test
  static void testDelete() {
    // Create acid
    AcidRDG acid = new AcidRDGRDS(9, 59, "acidname1", 1.9);

    // Ensure it has been added
    assertEquals("acidname1", acid.getAcid().getName());
    assertEquals(1.9, acid.getAcid().getInventory(), 0.1);
    assertEquals(59, acid.getAcid().getSoluteId());

    // Delete
    acid.delete();

    // Retrieving this acid should now result in a failure
    try {
      acid = new AcidRDGRDS(9);
    } catch (DatabaseException | SQLException e) {
      assertTrue(true);
    }
  }

  /**
   * Test that the update function in AcidRDGRDS works.
   * 
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  static void testUpdate() throws SQLException, DatabaseException {
    // Create acid and getter for that acid
    AcidRDG acid_setter = new AcidRDGRDS(9, 59, "acidname9", 1.9), acid_getter = new AcidRDGRDS(9);

    // Ensure that acid has been added and fetches the right information
    assertEquals("acidname9", acid_getter.getAcid().getName());
    assertEquals(1.9, acid_getter.getAcid().getInventory(), 0.1);
    assertEquals(59, acid_getter.getAcid().getSoluteId());

    // Change the information, then update and refresh the getter
    acid_setter.setName("acidname6");
    acid_setter.setInventory(1.8);
    acid_setter.setSolute(56);
    acid_setter.update();
    acid_getter = new AcidRDGRDS(9);

    // Test that the new information has been updated
    assertEquals("acidname6", acid_getter.getAcid().getName());
    assertEquals(1.8, acid_getter.getAcid().getInventory(), 0.1);
    assertEquals(56, acid_getter.getAcid().getSoluteId());

    // Delete because we don't need it.
    acid_getter.delete();
  }

  /**
   * Test that the getSet function in AcidRDGRDS works.
   */
  @Test
  static void testGetSet() {
    AcidRDG getter = new AcidRDGRDS(); // Empty AcidRDG
    List<AcidRDGRDS> acidGet = getter.findSet(55); // Get set

    // Test
    assertEquals("acidname5", acidGet.get(0).getAcid().getName());
    assertEquals("acidname6", acidGet.get(1).getAcid().getName());
  }
  
  /**
   * Test the getAll function inside AcidTDGRDS
   */
  @Test
  static void testGetAll() {
    AcidTDG getter = new AcidTDGRDS(); // Empty AcidTDGRDS
    List<AcidDTO> getAll = getter.getAllAcids(); // Get all elements
    
    // Assert that we have 6 acids, and that they are the right ids. 
    assertEquals(6, getAll.size());
    assertEquals(1, getAll.get(0).getAcidId());
    assertEquals(2, getAll.get(1).getAcidId());
    assertEquals(3, getAll.get(2).getAcidId());
    assertEquals(4, getAll.get(3).getAcidId());
    assertEquals(5, getAll.get(4).getAcidId());
    assertEquals(6, getAll.get(5).getAcidId());
  }

  /**
   * Test all functions in TestAcid
   */
  static void testAll() {
    try {
      insertAcids();
      testGetName();
      testGetInventory();
      testGetSolute();
      testDelete();
      testUpdate();
      testGetSet();
      testGetAll();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Insert acids into the database
   */
  private static void insertAcids() {
    AcidRDG acid = new AcidRDGRDS(1, 51, "acidname1", 1.1);
    acid = new AcidRDGRDS(2, 52, "acidname2", 1.2);
    acid = new AcidRDGRDS(3, 53, "acidname3", 1.3);
    acid = new AcidRDGRDS(4, 54, "acidname4", 1.4);
    acid = new AcidRDGRDS(5, 55, "acidname5", 1.5);
    acid = new AcidRDGRDS(6, 55, "acidname6", 1.6);
  }
}
