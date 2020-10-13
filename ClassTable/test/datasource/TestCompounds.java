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
  static void testGetInventory() {
    CompoundTDG compound1 = new CompoundTDGRDS(41);
    CompoundTDG compound2 = new CompoundTDGRDS(42);

    assertEquals(1.1, compound1.getInventory(), 0.1);
    assertEquals(1.2, compound2.getInventory(), 0.1);
  }

  @Test
  static void testFindMadeOf() {   
    CompoundTDG compounds = new CompoundTDGRDS();

    List<CompoundDTO> list1 = compounds.findMadeOf(41);
    List<CompoundDTO> expected1 = new ArrayList<>();
    CompoundDTO c1 = new CompoundDTO(21, 41); 
    CompoundDTO c2 = new CompoundDTO(22, 41); 
    expected1.add(c1);
    expected1.add(c2);
    for(int i = 0; i < list1.size(); i++) {
      assertEquals(expected1.get(i).getCompoundId(), list1.get(i).getCompoundId());
      assertEquals(expected1.get(i).getElementId(), list1.get(i).getElementId());
    }
    
    
    List<CompoundDTO> list2 = compounds.findMadeOf(42);
    List<CompoundDTO> expected2 = new ArrayList<>();
    CompoundDTO c3 = new CompoundDTO(23, 42); 
    CompoundDTO c4 = new CompoundDTO(24, 42); 
    expected2.add(c3);
    expected2.add(c4);
    
    for(int i = 0; i < list2.size(); i++) {
      assertEquals(expected2.get(i).getCompoundId(), list2.get(i).getCompoundId());
      assertEquals(expected2.get(i).getElementId(), list2.get(i).getElementId());
    }
    
  }

  @Test
  static void testFindMakes() {    
    CompoundTDG compounds = new CompoundTDGRDS();
    
    List<CompoundDTO> list1 = compounds.findMakes(21);
    List<CompoundDTO> expected1 = new ArrayList<>();
    CompoundDTO c1 = new CompoundDTO(41, 21); 
    expected1.add(c1);
    
//    assertEquals(1, list1.size());
    assertEquals(expected1.get(0).getCompoundId(), list1.get(0).getCompoundId());
    assertEquals(expected1.get(0).getElementId(), list1.get(0).getElementId());

    List<CompoundDTO> list2 = compounds.findMakes(22);
    List<CompoundDTO> expected2 = new ArrayList<>();
    CompoundDTO c2 = new CompoundDTO(41, 22); 
    expected2.add(c2);
    
//    assertEquals(1, list2.size());
    assertEquals(expected2.get(0).getCompoundId(), list2.get(0).getCompoundId());
    assertEquals(expected2.get(0).getElementId(), list2.get(0).getElementId());
    
    List<CompoundDTO> list3 = compounds.findMakes(23);
    List<CompoundDTO> expected3 = new ArrayList<>();
    CompoundDTO c3 = new CompoundDTO(42, 23); 
    expected3.add(c3);

//    assertEquals(1, list3.size());
    assertEquals(expected3.get(0).getCompoundId(), list3.get(0).getCompoundId());
    assertEquals(expected3.get(0).getElementId(), list3.get(0).getElementId());

    
    List<CompoundDTO> list4 = compounds.findMakes(24);
    List<CompoundDTO> expected4 = new ArrayList<>();
    CompoundDTO c4 = new CompoundDTO(42, 24); 
    expected4.add(c4);

//    assertEquals(1, list4.size());
    assertEquals(expected4.get(0).getCompoundId(), list4.get(0).getCompoundId());
    assertEquals(expected4.get(0).getElementId(), list4.get(0).getElementId());

  }
  
  static void testAll() {
    insertCompounds();
    testGetName();
    testGetInventory();
    testFindMadeOf();
    testFindMakes();
  }
  
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
