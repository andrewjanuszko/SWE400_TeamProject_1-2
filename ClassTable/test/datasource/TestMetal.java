package datasource;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

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
    assertEquals(1.1, metalGet1.getMetal().getInventory(), 0.1);
    assertEquals(1.2, metalGet2.getMetal().getInventory(), 0.1);
    assertEquals(1.3, metalGet3.getMetal().getInventory(), 0.1);
    assertEquals(1.4, metalGet4.getMetal().getInventory(), 0.1);
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
    assertEquals(1, metalGet1.getMetal().getDissolvedById());
    assertEquals(2, metalGet2.getMetal().getDissolvedById());
    assertEquals(3, metalGet3.getMetal().getDissolvedById());
    assertEquals(4, metalGet4.getMetal().getDissolvedById());
  }

  /**
   * Test that the getSet function in MetalRDGRDS works
   */
  @Test
  static void testGetSet() {
    MetalRDG metal = new MetalRDGRDS();
    List<MetalRDGRDS> metalGet = metal.findSet(5);

    // Test that we found the set
    assertEquals("metalname5", metalGet.get(0).getMetal().getName());
    assertEquals("metalname6", metalGet.get(1).getMetal().getName());
  }

  /**
   * Run all tests in TestMetal
   */
  static void testAll() {
    insertMetals();
    testGetName();
    testGetInventory();
    testGetDissolvedBy();
    testGetSet();
  }

  /**
   * Insert data into the database to test metal.
   */
  private static void insertMetals() {
    MetalRDG metal = new MetalRDGRDS(31, 1, "metalname1", 1.1);
    metal = new MetalRDGRDS(32, 2, "metalname2", 1.2);
    metal = new MetalRDGRDS(33, 3, "metalname3", 1.3);
    metal = new MetalRDGRDS(34, 4, "metalname4", 1.4);
    metal = new MetalRDGRDS(35, 5, "metalname5", 1.5);
    metal = new MetalRDGRDS(36, 5, "metalname6", 1.6);
  }
}
