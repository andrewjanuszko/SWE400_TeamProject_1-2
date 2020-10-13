package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import datasource.ChemicalRDG;

/**
 * 
 * @author Isabella Boone
 *
 */
class TestChemical extends DatabaseTest {
  
  /**
   * Test getName method of ChemicalRowDataGateway
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  void testGetName() throws SQLException, DatabaseException {
    ChemicalRDG initialize = new ChemicalRDGRDS(),
        chem1 = new ChemicalRDGRDS(1, "chemname1", 1.1),
        chem2 = new ChemicalRDGRDS(2, "chemname2", 1.2),
        chem3 = new ChemicalRDGRDS(3, "chemname3", 1.3),
        chem1_fetch = new ChemicalRDGRDS(1), chem2_fetch = new ChemicalRDGRDS(2),
        chem3_fetch = new ChemicalRDGRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals("chemname1", chem1.getName());
    assertEquals("chemname2", chem2.getName());
    assertEquals("chemname3", chem3.getName());

    // Testing to see if new gateways can properly fetch
    assertEquals("chemname1", chem1_fetch.getName());
    assertEquals("chemname2", chem2_fetch.getName());
    assertEquals("chemname3", chem3_fetch.getName());

    initialize.dropTable();
  }
  
  /**
   * Test getInhabits of ChemicalRowDataGateway
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  void testGetInhabits() throws SQLException, DatabaseException {
    ChemicalRDG initialize = new ChemicalRDGRDS(),
        chem1 = new ChemicalRDGRDS(1, "chemname1", 1.1),
        chem2 = new ChemicalRDGRDS(2, "chemname2", 1.2),
        chem3 = new ChemicalRDGRDS(3, "chemname3", 1.3),
        chem1_fetch = new ChemicalRDGRDS(1), chem2_fetch = new ChemicalRDGRDS(2),
        chem3_fetch = new ChemicalRDGRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals(1.1, chem1.getInventory(), 0.1);
    assertEquals(1.2, chem2.getInventory(), 0.1);
    assertEquals(1.3, chem3.getInventory(), 0.1);

    // Testing to see if new gateways can properly fetch
    assertEquals(1.1, chem1_fetch.getInventory(), 0.1);
    assertEquals(1.2, chem2_fetch.getInventory(), 0.1);
    assertEquals(1.3, chem3_fetch.getInventory(), 0.1);

    initialize.dropTable();
  }
  
  /**
   * Test the update function of ChemicalRowDataGateway
   */
  @Test
  void testUpdate() {
    ChemicalRDG initialize = new ChemicalRDGRDS(),
        chem = new ChemicalRDGRDS(1, "chemname1", 1.1);
    
    // Test name
    assertEquals("chemname1", chem.getName());
    chem.setName("chemname2");
    chem.update();
    assertEquals("chemname2", chem.getName());
    
    // Test inhabits
    assertEquals(1.1, chem.getInventory(), 0.1);
    chem.setInventory(1.2);
    chem.update();
    assertEquals(1.2, chem.getInventory(), 0.1);
    
    initialize.dropTable();
  }
  
  /**
   * Test the delete method of ChemicalRowDataGateway
   */
  @Test
  void testDelete() {
    ChemicalRDG initialize = new ChemicalRDGRDS(),
        chem = new ChemicalRDGRDS(1, "chemname1", 1.1);
    
    // Ensure it has been added
    assertEquals("chemname1", chem.getName());
    assertEquals(1.1, chem.getInventory(), 0.1);
    
    chem.delete();
    
    try { 
      chem = new ChemicalRDGRDS(1);
      fail("");
    } catch(DatabaseException | SQLException e) {
      assertTrue(true); 
    }
  }
  
}
