package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestElement {

  @Test
  void testInsert() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS();
    
    chem.createTableChemcial();
    element.createTableElement();
    
    chem.insert(1, "chemicalname1", "inhabits1");
    element.insert(1, 55, 123);
    
    assertEquals(55, element.getAtomicNumber(1));
    assertEquals(123, element.getAtomicMass(1));
  }
  
  @Test
  void testGetAtomicNumber() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS();
    
    chem.createTableChemcial();
    element.createTableElement();
    
    chem.insert(1, "chemicalname1", "inhabits1");
    element.insert(1, 55, 123);
    
    chem.insert(2, "chemicalname2", "inhabits2");
    element.insert(2, 2, 22);
    
    assertEquals(55, element.getAtomicNumber(1));
    assertEquals(2, element.getAtomicNumber(2));
  }
  
  @Test
  void testGetAtomicMass() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS();
    
    chem.createTableChemcial();
    element.createTableElement();
    
    chem.insert(1, "chemicalname1", "inhabits1");
    element.insert(1, 55, 123);
    
    chem.insert(2, "chemicalname2", "inhabits2");
    element.insert(2, 2, 22);
    
    assertEquals(123, element.getAtomicMass(1));
    assertEquals(22, element.getAtomicMass(2));
  }
  
  @Test
  void testGetName() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS();
    
    chem.createTableChemcial();
    element.createTableElement();
    
    chem.insert(1, "chemicalname1", "inhabits1");
    element.insert(1, 55, 123);
    
    chem.insert(2, "chemicalname2", "inhabits2");
    element.insert(2, 2, 22);
    
    assertEquals("chemicalname1", element.getName(1));
    assertEquals("chemicalname2", element.getName(2));  }
  
  @Test
  void testGetInhabits() {
    fail("Not yet implemented");
  }

}
