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
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");
    
    List<Integer> madeOf = new ArrayList<Integer>();
    madeOf.add(55);
    madeOf.add(23);

    // initialize compounds
    CompoundsMadeOfTableDataGateway compound1 = new CompoundsMadeOfTableDataGatewayRDS(1, madeOf, "chemicalname1",
        "inhabits1");

    // compound getters
    CompoundsMadeOfTableDataGateway compoundsGet1 = new CompoundsMadeOfTableDataGatewayRDS(1);

    // check
    assertEquals("chemicalname1", compoundsGet1.getCompoundName());
    
    compoundsGet1.delete();
    
    element1.delete();
    element2.delete();
    
  }
  
  @Test
  static void testGetInhabits() {
    // initialize elements
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");

    List<Integer> madeOf1 = new ArrayList<Integer>();
    madeOf1.add(55);
    madeOf1.add(23);

    // initialize compounds
    CompoundsMadeOfTableDataGateway compound1 = new CompoundsMadeOfTableDataGatewayRDS(1, madeOf1, "chemicalname1",
        "inhabits1");
   

    // compound getters
    CompoundsMadeOfTableDataGateway compoundsGet1 = new CompoundsMadeOfTableDataGatewayRDS(1);

    assertEquals("inhabits1", compoundsGet1.getInhabits());
    
    
    compoundsGet1.delete();
  }

  @Test
  static void testFindSetElementid() {    
    // initialize elements
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");

    List<Integer> madeOf = new ArrayList<Integer>();
    madeOf.add(55);
    madeOf.add(23);

    //initialize compounds
    CompoundsMadeOfTableDataGateway compounds = new CompoundsMadeOfTableDataGatewayRDS(1, madeOf, "chemicalname1",
        "inhabits1");

    List<Integer> list = compounds.findSetElementId(1);
    List<Integer> expected = new ArrayList<>();
    expected.add(55);
    expected.add(23);
    assertEquals(expected, list);
    
    compounds.delete();
    element1.delete();
    element2.delete();
   
  }

  @Test
  static void testFindSetCompoundId() {    
    // initialize elements
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");

    List<Integer> madeOf1 = new ArrayList<Integer>();
    madeOf1.add(55);
    List<Integer> madeOf2 = new ArrayList<Integer>();
    madeOf2.add(23);

    //initialize compounds
    CompoundsMadeOfTableDataGateway compound1 = new CompoundsMadeOfTableDataGatewayRDS(1, madeOf1, "chemicalname1",
        "inhabits1"), compound2 = new CompoundsMadeOfTableDataGatewayRDS(2, madeOf2, "chemicalname2", "inhabits2");

    // Compound1
    List<Integer> list1 = compound1.findSetElementId(1);
    List<Integer> expected1 = new ArrayList<>();
    expected1.add(55);
    assertEquals(expected1, list1);

    // Compound2
    List<Integer> list2 = compound1.findSetElementId(2);
    List<Integer> expected2 = new ArrayList<>();
    expected2.add(23);
    assertEquals(expected2, list2);
    
    element1.delete();
    element2.delete();
    compound1.delete();
    compound2.delete();

  }
  
  static void testAll() {
    testGetName();
    testGetInhabits();
    testFindSetElementid();
    testFindSetCompoundId();
  }
}
