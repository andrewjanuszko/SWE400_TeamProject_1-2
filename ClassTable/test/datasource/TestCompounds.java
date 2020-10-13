package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author kimberlyoneill
 *
 */
class TestCompoundsMadeOf extends DatabaseTest {

  /**
   * Test that the getName function of compoundTDGRDS works
   */
  @Test
  static void testGetName() {
    // Fetch compounds
    CompoundTDG compound1 = new CompoundTDGRDS(41);
    CompoundTDG compound2 = new CompoundTDGRDS(42);

    // Test
    assertEquals("compoundname1", compound1.getCompoundName());
    assertEquals("compoundname2", compound2.getCompoundName());
  }

  /**
   * Test that the getInventory function of compoundTDGRDS works
   */
  @Test
  static void testGetInventory() {
    // Fetch compounds
    CompoundTDG compound1 = new CompoundTDGRDS(41);
    CompoundTDG compound2 = new CompoundTDGRDS(42);

    // Tests
    assertEquals(1.1, compound1.getInventory(), 0.1);
    assertEquals(1.2, compound2.getInventory(), 0.1);
  }

  /**
   * Test that the madeOf function of compoundTDGRDS works
   */
  @Test
  static void testFindMadeOf() {
    // initialize elements
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", 1.0);
    ElementRDG element2 = new ElementRDGRDS(53, 44, 6, "element2", 1.0);

    List<Integer> madeOf = new ArrayList<Integer>();
    madeOf.add(55);
    madeOf.add(53);

    // initialize compounds
    CompoundTDG compounds = new CompoundTDGRDS(61, madeOf, "chemicalname1", 1.0);

    ArrayList<CompoundDTO> list = compounds.findMadeOf(61);
    ArrayList<CompoundDTO> expected = new ArrayList<CompoundDTO>();

    CompoundDTO c1 = new CompoundDTO(61, 55);
    CompoundDTO c2 = new CompoundDTO(61, 53);
    expected.add(c1);
    expected.add(c2);
    assertEquals(expected, list);

  }

  /**
   * TODO: THIS, it's not working rn with new implementation
   * Test that the findMakes function of compoundTDGRDS works
   */
  @Test
  static void testFindMakes() {
    // initialize elements
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", 1.0);
    ElementRDG element2 = new ElementRDGRDS(53, 44, 6, "element2", 1.0);

    List<Integer> madeOf1 = new ArrayList<Integer>();
    madeOf1.add(55);
    List<Integer> madeOf2 = new ArrayList<Integer>();
    madeOf2.add(53);

    // initialize compounds
    CompoundTDG compound1 = new CompoundTDGRDS(61, madeOf1, "chemicalname1", 1.0);
    CompoundTDG compound2 = new CompoundTDGRDS(62, madeOf2, "chemicalname2", 1.0);

    // Compound1
    CompoundDTO c1 = new CompoundDTO(61, 55);
    CompoundDTO c2 = new CompoundDTO(61, 53);

    ArrayList<CompoundDTO> list1 = compound1.findMakes(61);
    ArrayList<CompoundDTO> expected1 = new ArrayList<CompoundDTO>();
    expected1.add(c1);
    assertEquals(expected1, list1);

    // Compound2
    ArrayList<CompoundDTO> list2 = compound1.findMakes(62);
    ArrayList<CompoundDTO> expected2 = new ArrayList<CompoundDTO>();
    expected2.add(c2);
    assertEquals(expected2, list2);
  }

  /**
   * Run all tests in TestCompounds
   */
  static void testAll() {
    insertCompounds();
    testGetName();
    testGetInventory();
    testFindMadeOf();
    testFindMakes();
  }

  /**
   * Insert data into database to modify
   */
  private static void insertCompounds() {
    List<Integer> madeOf1 = new ArrayList<Integer>(), madeOf2 = new ArrayList<Integer>();
    madeOf1.add(21);
    madeOf1.add(22);
    madeOf2.add(23);
    madeOf2.add(24);

    CompoundTDG compounds1 = new CompoundTDGRDS(41, madeOf1, "compoundname1", 1.1);
    CompoundTDG compounds2 = new CompoundTDGRDS(42, madeOf2, "compoundname2", 1.2);
  }
}
