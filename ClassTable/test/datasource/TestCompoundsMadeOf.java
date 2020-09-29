package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestCompoundsMadeOf extends DatabaseTest{

  @Test
  void testGetName() {
    ChemicalRowDataGateway resetChem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway resetElement = new ElementRowDataGatewayRDS();
    CompoundsMadeOfTableDataGatewayRDS resetCompounds = new CompoundsMadeOfTableDataGatewayRDS(); 
    
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");
    ElementRowDataGateway element3 = new ElementRowDataGatewayRDS(61, 22, 2, "element3", "inhabits");
    ElementRowDataGateway element4 = new ElementRowDataGatewayRDS(11, 5, 1, "element4", "inhabits");
    ElementRowDataGateway element5 = new ElementRowDataGatewayRDS(14, 12, 2, "element5", "inhabits");
    ElementRowDataGateway element6 = new ElementRowDataGatewayRDS(16, 15, 3, "element6", "inhabits");
    
    List<Integer> madeOf1 = new ArrayList<Integer>();
    madeOf1.add(55);
    madeOf1.add(23);
    List<Integer> madeOf2 = new ArrayList<Integer>();
    madeOf1.add(61);
    madeOf1.add(11);
    List<Integer> madeOf3 = new ArrayList<Integer>();
    madeOf1.add(16);
    madeOf1.add(14);
    
    
    CompoundsMadeOfTableDataGateway compound1 = new CompoundsMadeOfTableDataGatewayRDS(1, madeOf1, "chemicalname1", "inhabits1");
    CompoundsMadeOfTableDataGateway compound2 = new CompoundsMadeOfTableDataGatewayRDS(2, madeOf2, "chemicalname2", "inhabits2");
    CompoundsMadeOfTableDataGateway compound3 = new CompoundsMadeOfTableDataGatewayRDS(3, madeOf3, "chemicalname3", "inhabits2");
    
    CompoundsMadeOfTableDataGateway compoundsGet1 = new CompoundsMadeOfTableDataGatewayRDS(1);
    CompoundsMadeOfTableDataGateway compoundsGet2 = new CompoundsMadeOfTableDataGatewayRDS(2); 
    CompoundsMadeOfTableDataGateway compoundsGet3 = new CompoundsMadeOfTableDataGatewayRDS(3); 
    
    assertEquals("chemicalname1", compoundsGet1.getCompoundName()); 
    assertEquals("chemicalname2", compoundsGet2.getCompoundName());
    assertEquals("chemicalname3", compoundsGet3.getCompoundName());
  }
  
  @Test
  void testGetInhabits() {
    ChemicalRowDataGateway resetChem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway resetElement = new ElementRowDataGatewayRDS();
    CompoundsMadeOfTableDataGatewayRDS resetCompounds = new CompoundsMadeOfTableDataGatewayRDS(); 
    
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");
    ElementRowDataGateway element3 = new ElementRowDataGatewayRDS(61, 22, 2, "element3", "inhabits");
    ElementRowDataGateway element4 = new ElementRowDataGatewayRDS(11, 5, 1, "element4", "inhabits");
    ElementRowDataGateway element5 = new ElementRowDataGatewayRDS(14, 12, 2, "element5", "inhabits");
    ElementRowDataGateway element6 = new ElementRowDataGatewayRDS(16, 15, 3, "element6", "inhabits");
    
    List<Integer> madeOf1 = new ArrayList<Integer>();
    madeOf1.add(55);
    madeOf1.add(23);
    List<Integer> madeOf2 = new ArrayList<Integer>();
    madeOf1.add(61);
    madeOf1.add(11);
    List<Integer> madeOf3 = new ArrayList<Integer>();
    madeOf1.add(16);
    madeOf1.add(14);
    
    
    CompoundsMadeOfTableDataGateway compound1 = new CompoundsMadeOfTableDataGatewayRDS(1, madeOf1, "chemicalname1", "inhabits1");
    CompoundsMadeOfTableDataGateway compound2 = new CompoundsMadeOfTableDataGatewayRDS(2, madeOf2, "chemicalname2", "inhabits2");
    CompoundsMadeOfTableDataGateway compound3 = new CompoundsMadeOfTableDataGatewayRDS(3, madeOf3, "chemicalname3", "inhabits3");
    
    CompoundsMadeOfTableDataGateway compoundsGet1 = new CompoundsMadeOfTableDataGatewayRDS(1);
    CompoundsMadeOfTableDataGateway compoundsGet2 = new CompoundsMadeOfTableDataGatewayRDS(2); 
    CompoundsMadeOfTableDataGateway compoundsGet3 = new CompoundsMadeOfTableDataGatewayRDS(3); 
    
    assertEquals("inhabits1", compoundsGet1.getInhabits()); 
    assertEquals("inhabits2", compoundsGet2.getInhabits());
    assertEquals("inhabits3", compoundsGet3.getInhabits());
    
  }

  @Test
  void testFindSetElementid() {
    ChemicalRowDataGateway dropChem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway dropElement = new ElementRowDataGatewayRDS();
    CompoundsMadeOfTableDataGateway dropCompounds = new CompoundsMadeOfTableDataGatewayRDS();
    
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");
    
    List<Integer> madeOf = new ArrayList<Integer>(); 
    madeOf.add(55);
    madeOf.add(23);
    
    CompoundsMadeOfTableDataGateway compounds = 
        new CompoundsMadeOfTableDataGatewayRDS(1, madeOf, "chemicalname1", "inhabits1");
    
    List<Integer> list = compounds.findSetElementId(1);
    List<Integer> expected = new ArrayList<>();
    expected.add(55);
    expected.add(23);
    assertEquals(expected, list);
  }
  
  @Test
  void TestFindCompoundId() {
    ChemicalRowDataGateway dropChem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway dropElement = new ElementRowDataGatewayRDS();
    CompoundsMadeOfTableDataGateway dropCompounds = new CompoundsMadeOfTableDataGatewayRDS();
    
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");
    
    List<Integer> madeOf1 = new ArrayList<Integer>(); 
    madeOf1.add(55);
    List<Integer> madeOf2 = new ArrayList<Integer>(); 
    madeOf2.add(23);
    
    CompoundsMadeOfTableDataGateway 
      compound1 = new CompoundsMadeOfTableDataGatewayRDS(1, madeOf1, "chemicalname1", "inhabits1"),
      compound2 = new CompoundsMadeOfTableDataGatewayRDS(2, madeOf2, "chemicalname2", "inhabits2"); 
        
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
   
  }
}
