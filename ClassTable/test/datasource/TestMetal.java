package datasource;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import database.DatabaseException;

/**
 * 
 * @author kimberlyoneill
 *
 */
class TestMetal {

  /**
   * Test that the getName function in MetalRDGRDS works
   */
  @Test
  static void testGetName() {
    // Fetch metals
    MetalRDG metalGet1 = new MetalRDGRDS(31);
    MetalRDG metalGet2 = new MetalRDGRDS(32);
    MetalRDG metalGet3 = new MetalRDGRDS(33);
    MetalRDG metalGet4 = new MetalRDGRDS(34);

    // Test getName
    assertEquals("metalname1", metalGet1.getMetal().getName());
    assertEquals("metalname2", metalGet2.getMetal().getName());
    assertEquals("metalname3", metalGet3.getMetal().getName());
    assertEquals("metalname4", metalGet4.getMetal().getName());
  }

  /**
   * Test that the getInventory function in MetalRDGRDS works
   */
  @Test
  static void testGetInventory() {
    // Fetch metals
    MetalRDG metalGet1 = new MetalRDGRDS(31);
    MetalRDG metalGet2 = new MetalRDGRDS(32);
    MetalRDG metalGet3 = new MetalRDGRDS(33);
    MetalRDG metalGet4 = new MetalRDGRDS(34);

    // Test getInventory
    assertEquals(41.1, metalGet1.getMetal().getInventory(), 0.1);
    assertEquals(42.2, metalGet2.getMetal().getInventory(), 0.1);
    assertEquals(43.3, metalGet3.getMetal().getInventory(), 0.1);
    assertEquals(44.4, metalGet4.getMetal().getInventory(), 0.1);
  }

  /**
   * Test that the getDissolvedBy function in MetalRDGRDS works
   */
  @Test
  static void testGetDissolvedBy() {
    // Fetch metals
    MetalRDG metalGet1 = new MetalRDGRDS(31);
    MetalRDG metalGet2 = new MetalRDGRDS(32);
    MetalRDG metalGet3 = new MetalRDGRDS(33);
    MetalRDG metalGet4 = new MetalRDGRDS(34);

    // Test that getDissolvedBy works
    assertEquals(11, metalGet1.getMetal().getDissolvedById());
    assertEquals(12, metalGet2.getMetal().getDissolvedById());
    assertEquals(13, metalGet3.getMetal().getDissolvedById());
    assertEquals(14, metalGet4.getMetal().getDissolvedById());
  }
  
  /**
   * Test the getAll function in MetalDGRDS
   */
  @Test
  static void testGetAll() {
    try {
      List<MetalDTO> get = new MetalTDGRDS().getAllMetals().executeQuery();
      
      assertEquals(4, get.size());
      assertEquals(31, get.get(0).getMetalId());
      assertEquals(32, get.get(1).getMetalId());
      assertEquals(33, get.get(2).getMetalId());
      assertEquals(34, get.get(3).getMetalId());
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }

  /**
   * 
   */
  @Test
  static void testFilterByInventory() {
    try {
      List<MetalDTO> get = new MetalTDGRDS().getAllMetals().filterByInventory(41.1).executeQuery();
    
      assertEquals(1, get.size());
      assertEquals(31, get.get(0).getMetalId());
      
      get = new MetalTDGRDS().getAllMetals().filterByInventoryRange(42, 40).executeQuery();
      
      assertEquals(1, get.size());
      assertEquals(31, get.get(0).getMetalId());
      
      get = new MetalTDGRDS().getAllMetals().filterByInventoryRange(43, 40).executeQuery();
      
      assertEquals(2, get.size());
      assertEquals(31, get.get(0).getMetalId());
      assertEquals(32, get.get(1).getMetalId());
      
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  static void testFilterByAtomicNumber() {
    try {
      List<MetalDTO> get = new MetalTDGRDS().getAllMetals().filterByAtomicNumber(1).executeQuery();
      
      assertEquals(31, get.get(0).getMetalId());
      
      get = new MetalTDGRDS().getAllMetals().filterByAtomicNumberRange(2, 0).executeQuery();
      
      assertEquals(2, get.size());
      assertEquals(31, get.get(0).getMetalId());
      assertEquals(32, get.get(1).getMetalId());
      
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  static void testFilterByAtomicMass() {
    try {
      List<MetalDTO> get = new MetalTDGRDS().getAllMetals().filterByAtomicMass(1.1).executeQuery();
      
      assertEquals(31, get.get(0).getMetalId());
      
      get = new MetalTDGRDS().getAllMetals().filterByAtomicMassRange(3, 0).executeQuery();
      
      assertEquals(2, get.size());
      assertEquals(31, get.get(0).getMetalId());
      assertEquals(32, get.get(1).getMetalId());
      
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  static void testFilterByDissolvedBy() {
    try {
      List<MetalDTO> get = new MetalTDGRDS().getAllMetals().filterByDissolvedBy(11).executeQuery();
      
      assertEquals(1, get.size());
      assertEquals(31, get.get(0).getMetalId());
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  static void testFilterByMoles() {
    try {
      List<MetalDTO> get = new MetalTDGRDS().getAllMetals().filterByMoles(11.1).executeQuery();
      
      assertEquals(1, get.size());
      assertEquals(31, get.get(0).getMetalId());
      
      get = new MetalTDGRDS().getAllMetals().filterByMolesRange(14, 11).executeQuery();
      
      assertEquals(3, get.size());
      assertEquals(31, get.get(0).getMetalId());
      assertEquals(32, get.get(1).getMetalId());
      assertEquals(33, get.get(2).getMetalId());
    } catch (DatabaseException e) {
      e.printStackTrace();
    }
  }
  /**
   * Run all tests in TestMetal
   */
  static void testAll() {
    insertMetals();
    testGetName();
    testGetInventory();
    testGetDissolvedBy();
    testGetAll();
    testFilterByInventory();
    testFilterByAtomicMass();
    testFilterByAtomicNumber();
    testFilterByDissolvedBy();
    testFilterByMoles();
  }

  /**
   * Insert data into the database to test metal.
   */
  private static void insertMetals() {
    MetalTDGRDS.create(31, 11, 11.1, 1, 1.1, "metalname1", 41.1);
    MetalTDGRDS.create(32, 12, 12.2, 2, 2.1, "metalname2", 42.1);
    MetalTDGRDS.create(33, 13, 13.3, 3, 3.1, "metalname3", 43.1);
    MetalTDGRDS.create(34, 14, 14.4, 4, 4.1, "metalname4", 44.1);
  }
}
