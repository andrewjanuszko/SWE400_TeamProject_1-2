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

  @Test
  static void testGetName() {
    CompoundTDG compound1 = new CompoundTDGRDS(41);
    CompoundTDG compound2 = new CompoundTDGRDS(42);

    assertEquals("compoundname1", compound1.getCompoundName());
    assertEquals("compoundname2", compound2.getCompoundName());
  }
  
  @Test
  static void testGetInhabits() {
    CompoundTDG compound1 = new CompoundTDGRDS(41);
    CompoundTDG compound2 = new CompoundTDGRDS(42);

    assertEquals("compoundinhabits1", compound1.getInhabits());
    assertEquals("compoundinhabits2", compound2.getInhabits());
  }

  @Test
  static void testFindMadeOf() {   
    CompoundTDG compounds = new CompoundTDGRDS();

    List<Integer> list1 = compounds.findMadeOf(41);
    List<Integer> expected1 = new ArrayList<>();
    expected1.add(21);
    expected1.add(22);
    assertEquals(expected1, list1);
    
    List<Integer> list2 = compounds.findMadeOf(42);
    List<Integer> expected2 = new ArrayList<>();
    expected2.add(23);
    expected2.add(24);
    assertEquals(expected2, list2);
  }

  @Test
  static void testFindMakes() {    
    CompoundTDG compounds = new CompoundTDGRDS();
    
    List<Integer> list1 = compounds.findMakes(21);
    List<Integer> expected1 = new ArrayList<>();
    expected1.add(41);
    assertEquals(expected1, list1);

    List<Integer> list2 = compounds.findMakes(22);
    List<Integer> expected2 = new ArrayList<>();
    expected2.add(41);
    assertEquals(expected2, list2);
    
    List<Integer> list3 = compounds.findMakes(23);
    List<Integer> expected3 = new ArrayList<>();
    expected3.add(42);
    assertEquals(expected3, list3);
    
    List<Integer> list4 = compounds.findMakes(24);
    List<Integer> expected4 = new ArrayList<>();
    expected4.add(42);
    assertEquals(expected4, list4);
  }
  
  static void testAll() {
    insertCompounds();
    testGetName();
    testGetInhabits();
    testFindMadeOf();
    testFindMakes();
  }
  
  private static void insertCompounds() {
    List<Integer> madeOf1 = new ArrayList<Integer>(), madeOf2 = new ArrayList<Integer>(); 
    madeOf1.add(21);
    madeOf1.add(22);
    madeOf2.add(23);
    madeOf2.add(24);
    
    CompoundTDG compounds1 = new CompoundTDGRDS(41, madeOf1, "compoundname1", "compoundinhabits1"); 
    CompoundTDG compounds2 = new CompoundTDGRDS(42, madeOf2, "compoundname2", "compoundinhabits2"); 

  }
}
