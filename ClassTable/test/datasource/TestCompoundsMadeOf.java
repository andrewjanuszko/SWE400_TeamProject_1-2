package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestCompoundsMadeOf {

  @Test
  void testGetName() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway dropElement = new ElementRowDataGatewayRDS();
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");
    CompoundsMadeOfTableDataGateway compound1 = new CompoundsMadeOfTableDataGatewayRDS(1, 55, "chemicalname1", "inhabits1");
    CompoundsMadeOfTableDataGateway compound2 = new CompoundsMadeOfTableDataGatewayRDS(2, 2, "chemicalname2", "inhabits2");
    
    CompoundsMadeOfTableDataGateway compoundsGet1 = new CompoundsMadeOfTableDataGatewayRDS(1);
    CompoundsMadeOfTableDataGateway compoundsGet2 = new CompoundsMadeOfTableDataGatewayRDS(2);
    
    assertEquals("chemicalname1", compoundsGet1.getCompoundName());
    assertEquals("chemicalname2", compoundsGet2.getCompoundName());  
  }
  
  @Test
  void testGetInhabits() {
    // Create row data gateways
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway dropElement = new ElementRowDataGatewayRDS();
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");
    CompoundsMadeOfTableDataGateway compound1 = new CompoundsMadeOfTableDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");
    CompoundsMadeOfTableDataGateway compound2 = new CompoundsMadeOfTableDataGatewayRDS(2, 50, "chemicalname2", "inhabits2");
    CompoundsMadeOfTableDataGateway compound3 = new CompoundsMadeOfTableDataGatewayRDS(3, 7, "chemicalname3", "inhabits3");

    
    CompoundsMadeOfTableDataGateway compoundGet1 = new CompoundsMadeOfTableDataGatewayRDS(1);
    CompoundsMadeOfTableDataGateway compoundGet2 = new CompoundsMadeOfTableDataGatewayRDS(2);
    CompoundsMadeOfTableDataGateway compoundGet3 = new CompoundsMadeOfTableDataGatewayRDS(3);
    // Test
    assertEquals("inhabits1", compoundGet1.getInhabits());
    assertEquals("inhabits2", compoundGet2.getInhabits());
    assertEquals("inhabits3", compoundGet3.getInhabits());
  }

  @Test
  void testFindSetElementid() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway dropElement = new ElementRowDataGatewayRDS();
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");
    CompoundsMadeOfTableDataGateway drop = new CompoundsMadeOfTableDataGatewayRDS();
    CompoundsMadeOfTableDataGateway compounds = new CompoundsMadeOfTableDataGatewayRDS(1, 55, "chemicalname1", "inhabits1");
    
    compounds.insert(1, 23);
 
    List<Integer> list = compounds.findSetElementId(1);
    List<Integer> expected = new ArrayList<>();
    expected.add(55);
    expected.add(23);
    assertEquals(expected, list);
  }
  
  @Test
  void TestFindCompoundId() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway dropElement = new ElementRowDataGatewayRDS();
    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(55, 12, 5, "element1", "inhabits");
    ElementRowDataGateway element2 = new ElementRowDataGatewayRDS(23, 44, 6, "element2", "inhabits");
    CompoundsMadeOfTableDataGateway drop = new CompoundsMadeOfTableDataGatewayRDS();
    CompoundsMadeOfTableDataGateway compound1 = new CompoundsMadeOfTableDataGatewayRDS(1, 55, "chemicalname1", "inhabits1");
    CompoundsMadeOfTableDataGateway compound2 = new CompoundsMadeOfTableDataGatewayRDS(2, 55, "chemicalname2", "inhabits2");
    
 
    List<Integer> list = compound2.findSetCompoundId(55);
    List<Integer> expected = new ArrayList<>();
    expected.add(1);
    expected.add(2);
    assertEquals(expected, list);
  }
}
