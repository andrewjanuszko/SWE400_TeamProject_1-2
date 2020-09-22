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
    CompoundsMadeOfTableDataGateway compounds = new CompoundsMadeOfTableDataGatewayRDS();
    
    chem.createTableChemcial();
    compounds.createTableDataMadeOf();
    
    chem.insert(1, "chemicalname1", "inhabits1");
    compounds.insert(1, 55);
    
    chem.insert(2, "chemicalname2", "inhabits2");
    compounds.insert(2, 2);
    
    assertEquals("chemicalname1", compounds.getCompoundName(1));
    assertEquals("chemicalname2", compounds.getCompoundName(2));  
  }
  
  @Test
  void testGetInhabits() {
    // Create row data gateways
    CompoundsMadeOfTableDataGateway compound = new CompoundsMadeOfTableDataGatewayRDS();
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    
    // Create tables
    chem.createTableChemcial();
    compound.createTableDataMadeOf();
    
    // Insert chemical 
    chem.insert(1, "chemicalname1", "inhabits1");
    chem.insert(2, "chemicalname2", "inhabits2");
    chem.insert(3, "chemicalname3", "inhabits3");
    
    // Insert metal
    compound.insert(1, 15);
    compound.insert(2, 50);
    compound.insert(3, 7);
    
    // Test
    assertEquals("inhabits1", compound.getInhabits(1));
    assertEquals("inhabits2", compound.getInhabits(2));
    assertEquals("inhabits3", compound.getInhabits(3));
  }

  @Test
  void testFindSetElementid() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    CompoundsMadeOfTableDataGateway compounds = new CompoundsMadeOfTableDataGatewayRDS();
    
    chem.createTableChemcial();
    compounds.createTableDataMadeOf();
    
    chem.insert(1, "chemicalname1", "inhabits1");
    compounds.insert(1, 55);
    compounds.insert(1, 23);
 
    List<Integer> list = compounds.findSetElementId(1);
    List<Integer> expected = new ArrayList<>();
    expected.add(55);
    expected.add(23);
    assertEquals(list, expected);
  }
  
  @Test
  void TestFindCompoundId() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    CompoundsMadeOfTableDataGateway compounds = new CompoundsMadeOfTableDataGatewayRDS();
    
    chem.createTableChemcial();
    compounds.createTableDataMadeOf();
    
    chem.insert(1, "chemicalname1", "inhabits1");
    compounds.insert(1, 55);
    compounds.insert(2, 55);
 
    List<Integer> list = compounds.findSetCompoundId(55);
    List<Integer> expected = new ArrayList<>();
    expected.add(1);
    expected.add(2);
    assertEquals(list, expected);
  }
}
