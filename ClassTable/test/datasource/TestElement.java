package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author kimberlyoneill
 *
 */
class TestElement extends DatabaseTest {

  /**
   * Test that the getAtomicNumber function in ElementRDGRDS works
   */
  @Test
  static void testGetAtomicNumber() {
    // Fetch elements
    ElementRDG elementGet1 = new ElementRDGRDS(21);
    ElementRDG elementGet2 = new ElementRDGRDS(22);
    ElementRDG elementGet3 = new ElementRDGRDS(23);
    ElementRDG elementGet4 = new ElementRDGRDS(24);

    // Test getAtomicNumber
    assertEquals(1, elementGet1.getAtomicNumber());
    assertEquals(2, elementGet2.getAtomicNumber());
    assertEquals(3, elementGet3.getAtomicNumber());
    assertEquals(4, elementGet4.getAtomicNumber());
  }

  /**
   * Test that the getAtomicMass function in ElementRDGRDS works
   */
  @Test
  static void testGetAtomicMass() {
    // Fetch elements
    ElementRDG elementGet1 = new ElementRDGRDS(21);
    ElementRDG elementGet2 = new ElementRDGRDS(22);
    ElementRDG elementGet3 = new ElementRDGRDS(23);
    ElementRDG elementGet4 = new ElementRDGRDS(24);

    // Test getAtomicMass
    assertEquals(9, elementGet1.getAtomicMass(), 0.1);
    assertEquals(8, elementGet2.getAtomicMass(), 0.1);
    assertEquals(7, elementGet3.getAtomicMass(), 0.1);
    assertEquals(6, elementGet4.getAtomicMass(), 0.1);
  }

  /**
   * Test that the getName function in ElementRDGRDS works
   */
  @Test
  static void testGetName() {
    // Fetch elements
    ElementRDG elementGet1 = new ElementRDGRDS(21);
    ElementRDG elementGet2 = new ElementRDGRDS(22);
    ElementRDG elementGet3 = new ElementRDGRDS(23);
    ElementRDG elementGet4 = new ElementRDGRDS(24);

    // Test getName
    assertEquals("elementname1", elementGet1.getName());
    assertEquals("elementname2", elementGet2.getName());
    assertEquals("elementname3", elementGet3.getName());
    assertEquals("elementname4", elementGet4.getName());
  }

  /**
   * Test that the getInventory function works
   */
  @Test
  static void testGetInventory() {
    // Fetch elements
    ElementRDG elementGet1 = new ElementRDGRDS(21);
    ElementRDG elementGet2 = new ElementRDGRDS(22);
    ElementRDG elementGet3 = new ElementRDGRDS(23);
    ElementRDG elementGet4 = new ElementRDGRDS(24);

    // Test getInventory
    assertEquals(1.1, elementGet1.getInventory(), 0.1);
    assertEquals(1.2, elementGet2.getInventory(), 0.1);
    assertEquals(1.3, elementGet3.getInventory(), 0.1);
    assertEquals(1.4, elementGet4.getInventory(), 0.1);
  }

  /**
   * Run all tests in TestElement
   */
  static void testAll() {
    insertElements();
    testGetAtomicNumber();
    testGetAtomicMass();
    testGetName();
    testGetInventory();
  }

  /**
   * Insert elements into the database to test
   */
  private static void insertElements() {
    ElementRDG ele = new ElementRDGRDS(21, 1, 9, "elementname1", 1.1);
    ele = new ElementRDGRDS(22, 2, 8, "elementname2", 1.2);
    ele = new ElementRDGRDS(23, 3, 7, "elementname3", 1.3);
    ele = new ElementRDGRDS(24, 4, 6, "elementname4", 1.4);
  }
}
