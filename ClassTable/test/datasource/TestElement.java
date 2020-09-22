package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestElement {

  @Test
  void testInsert() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS();
    
    chem.createTableChemcial();
    element.createTableElement();
    
    element.insert(1, 55, 123, "chemicalname1", "inhabits1");
    
    assertEquals(55, element.getAtomicNumber(1));
    assertEquals(123, element.getAtomicMass(1));
  }
  
  @Test
  void testGetAtomicNumber() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS();
    
    chem.createTableChemcial();
    element.createTableElement();
    
    element.insert(1, 55, 123, "chemicalname1", "inhabits1");
    element.insert(2, 2, 22, "chemicalname2", "inhabits2");
    
    assertEquals(55, element.getAtomicNumber(1));
    assertEquals(2, element.getAtomicNumber(2));
  }
  
  @Test
  void testGetAtomicMass() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS();
    
    chem.createTableChemcial();
    element.createTableElement();
    
    element.insert(1, 55, 123, "chemicalname1", "inhabits1");
    element.insert(2, 2, 22, "chemicalname2", "inhabits2");
    
    assertEquals(123, element.getAtomicMass(1));
    assertEquals(22, element.getAtomicMass(2));
  }
  
  @Test
  void testGetName() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS();
    
    chem.createTableChemcial();
    element.createTableElement();
    
    element.insert(1, 55, 123, "chemicalname1", "inhabits1");
    
    element.insert(2, 2, 22, "chemicalname2", "inhabits2");
    
    assertEquals("chemicalname1", element.getName(1));
    assertEquals("chemicalname2", element.getName(2));  
  }
  
  @Test
  void testGetInhabits() {
    // Create row data gateways
    ElementRowDataGateway element = new ElementRowDataGatewayRDS();
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    
    // Create base, chemical tables
    element.createTableElement();
    chem.createTableChemcial();
    
    // Insert element
    element.insert(1, 15, 18, "chemicalname1", "inhabits1");
    element.insert(2, 50, 42, "chemicalname2", "inhabits2");
    element.insert(3, 7, 21, "chemicalname3", "inhabits3");
    
    // Test
    assertEquals("inhabits1", element.getInhabits(1));
    assertEquals("inhabits2", element.getInhabits(2));
    assertEquals("inhabits3", element.getInhabits(3));
  }

}
