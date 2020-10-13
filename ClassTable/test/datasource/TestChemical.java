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
  static void testGetName() throws SQLException, DatabaseException {
    ChemicalRDG 
        chem1 = new ChemicalRDGRDS(1), 
        chem2 = new ChemicalRDGRDS(2),
        chem3 = new ChemicalRDGRDS(11),
        chem4 = new ChemicalRDGRDS(12);

    assertEquals("acidname1", chem1.getName());
    assertEquals("acidname2", chem2.getName());
    assertEquals("basename1", chem3.getName());
    assertEquals("basename2", chem4.getName());
  }
  
  /**
   * Test getInhabits of ChemicalRowDataGateway
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  static void testGetInventory() throws SQLException, DatabaseException {
    ChemicalRDG 
        chem1 = new ChemicalRDGRDS(1), 
        chem2 = new ChemicalRDGRDS(2),
        chem3 = new ChemicalRDGRDS(11),
        chem4 = new ChemicalRDGRDS(12);

    assertEquals(1.1, chem1.getInventory(), 0.1);
    assertEquals(1.2, chem2.getInventory(), 0.1);
    assertEquals(1.1, chem3.getInventory(), 0.1);
    assertEquals(1.2, chem4.getInventory(), 0.1);
  }
  
  /**
   * Test the update function of ChemicalRowDataGateway
   * @throws DatabaseException 
   * @throws SQLException 
   */
  @Test
  static void testUpdate() throws SQLException, DatabaseException {
    ChemicalRDG chem_setter = new ChemicalRDGRDS(9, "chemname9", 1.9),
        chem_getter = new ChemicalRDGRDS(9);

    assertEquals("chemname9", chem_getter.getName());
    assertEquals(1.9, chem_getter.getInventory(), 0.1);
    
    chem_setter.setName("chemname6");
    chem_setter.setInventory(1.8);
    
    chem_setter.update();
    chem_getter = new ChemicalRDGRDS(9);
    
    assertEquals("chemname6", chem_getter.getName());
    assertEquals(1.8, chem_getter.getInventory(), 0.1);
    
    chem_getter = new ChemicalRDGRDS(9);
    chem_getter.delete();
  }
  
  /**
   * Test the delete method of ChemicalRowDataGateway
   */
  @Test
  static void testDelete() {
    ChemicalRDG chem = new ChemicalRDGRDS(9, "chemname9", 1.9);
    
    // Ensure it has been added
    assertEquals("chemname9", chem.getName());
    assertEquals(1.9, chem.getInventory(), 0.1);
    
    chem.delete();
    
    try { 
      chem = new ChemicalRDGRDS(9);
      fail("");
    } catch(DatabaseException | SQLException e) {
      assertTrue(true); 
    }
  }
  
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
