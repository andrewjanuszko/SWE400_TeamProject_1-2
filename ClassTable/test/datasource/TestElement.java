package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
    assertEquals(1, elementGet1.getElement().getAtomicNumber());
    assertEquals(2, elementGet2.getElement().getAtomicNumber());
    assertEquals(3, elementGet3.getElement().getAtomicNumber());
    assertEquals(4, elementGet4.getElement().getAtomicNumber());
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
    assertEquals(9, elementGet1.getElement().getAtomicMass(), 0.1);
    assertEquals(8, elementGet2.getElement().getAtomicMass(), 0.1);
    assertEquals(7, elementGet3.getElement().getAtomicMass(), 0.1);
    assertEquals(6, elementGet4.getElement().getAtomicMass(), 0.1);
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
    assertEquals("elementname1", elementGet1.getElement().getName());
    assertEquals("elementname2", elementGet2.getElement().getName());
    assertEquals("elementname3", elementGet3.getElement().getName());
    assertEquals("elementname4", elementGet4.getElement().getName());
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
    assertEquals(1.1, elementGet1.getElement().getInventory(), 0.1);
    assertEquals(1.2, elementGet2.getElement().getInventory(), 0.1);
    assertEquals(1.3, elementGet3.getElement().getInventory(), 0.1);
    assertEquals(1.4, elementGet4.getElement().getInventory(), 0.1);
  }
  
  /**
   * Test the getAll function in ElementTDGRDS
   */
  @Test
  static void testGetAll() {
    ElementTDG getter = new ElementTDGRDS(); // Empty ElementTDG
    List<ElementDTO> getAll = getter.getAllElements(); // Get all elements
    
    // Assert that we have 4 elements, and that they are the right ids. 
    assertEquals(4, getAll.size());
    assertEquals(21, getAll.get(0).getElementId());
    assertEquals(22, getAll.get(1).getElementId());
    assertEquals(23, getAll.get(2).getElementId());
    assertEquals(24, getAll.get(3).getElementId());
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
    testGetAll();
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
