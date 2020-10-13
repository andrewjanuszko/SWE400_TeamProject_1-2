package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Isabella Boone
 * 
 */
class TestAcid extends DatabaseTest {

  @Test
  static void testGetName() throws SQLException, DatabaseException {
    AcidRDG
        acid1 = new AcidRDGRDS(1), 
        acid2 = new AcidRDGRDS(2),
        acid3 = new AcidRDGRDS(3),
        acid4 = new AcidRDGRDS(4);
        

    // Tests
    assertEquals("acidname1", acid1.getName());
    assertEquals("acidname2", acid2.getName());
    assertEquals("acidname3", acid3.getName());
    assertEquals("acidname4", acid4.getName());
  }

  @Test
  static void testGetInventory() throws SQLException, DatabaseException {
    AcidRDG 
        acid1 = new AcidRDGRDS(1), 
        acid2 = new AcidRDGRDS(2),
        acid3 = new AcidRDGRDS(3),
        acid4 = new AcidRDGRDS(4); 

    // Tests
    assertEquals(1.1, acid1.getInventory(), 0.1);
    assertEquals(1.2, acid2.getInventory(), 0.1);
    assertEquals(1.3, acid3.getInventory(), 0.1);
    assertEquals(1.4, acid4.getInventory(), 0.1);
  }

  @Test
  static void testGetSolute() throws SQLException, DatabaseException {
    AcidRDG 
        acid1 = new AcidRDGRDS(1), 
        acid2 = new AcidRDGRDS(2),
        acid3 = new AcidRDGRDS(3),
        acid4 = new AcidRDGRDS(4);

    // Test
    assertEquals(51, acid1.getSolute());
    assertEquals(52, acid2.getSolute());
    assertEquals(53, acid3.getSolute());
    assertEquals(54, acid4.getSolute());
  }

  @Test
  static void testDelete() {
    AcidRDG acid = new AcidRDGRDS(9, 59, "acidname1", 1.9);

    // Ensure it has been added
    assertEquals("acidname1", acid.getName());
    assertEquals(1.9, acid.getInventory(), 0.1);
    assertEquals(59, acid.getSolute());

    acid.delete();

    try {
      acid = new AcidRDGRDS(9);
      fail("");
    } catch (DatabaseException | SQLException e) {
      assertTrue(true);
    }
  }

  @Test
  static void testUpdate() throws SQLException, DatabaseException {
    AcidRDG acid_setter = new AcidRDGRDS(9, 59, "acidname9", 1.9),
        acid_getter = new AcidRDGRDS(9);
    
    assertEquals("acidname9", acid_getter.getName());
    assertEquals(1.9, acid_getter.getInventory(), 0.1);
    assertEquals(59, acid_getter.getSolute());
    
    acid_setter.setName("acidname6");
    acid_setter.setInventory(1.8);
    acid_setter.setSolute(56);
    acid_setter.update();
    
    acid_getter = new AcidRDGRDS(9); 
    
    assertEquals("acidname6", acid_getter.getName());
    assertEquals(1.8, acid_getter.getInventory(), 0.1);
    assertEquals(56, acid_getter.getSolute());
    
    acid_getter.delete();
  }

  @Test
  static void testGetSet() {
    AcidRDG getter = new AcidRDGRDS();
    List<AcidRDGRDS> acidGet = getter.findSet(55); 
    
    assertEquals("acidname5", acidGet.get(0).getName());
    assertEquals("acidname6", acidGet.get(1).getName());
  }

  static void testAll() {
    try {
      insertAcids(); 
      testGetName();
      testGetInventory();
      testGetSolute();
      testDelete();
      testUpdate();
      testGetSet(); 
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

  private static void insertAcids() {
    AcidRDG acid = new AcidRDGRDS(1, 51, "acidname1", 1.1);
    acid = new AcidRDGRDS(2, 52, "acidname2", 1.2);
    acid = new AcidRDGRDS(3, 53, "acidname3", 1.3);
    acid = new AcidRDGRDS(4, 54, "acidname4", 1.4);
    acid = new AcidRDGRDS(5, 55, "acidname5", 1.5);
    acid = new AcidRDGRDS(6, 55, "acidname6", 1.6);
    
  }
}
