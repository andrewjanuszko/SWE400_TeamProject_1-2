package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author kimberlyoneill
 *
 */
class TestElement {

  
  @Test
  void testGetAtomicNumber() {
    //create table
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    
    //initialize element
    ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, 55, 123, "chemicalname1", "inhabits1");
    
    //element getter
    ElementRowDataGateway elementGet = new ElementRowDataGatewayRDS(1);
    
    assertEquals(55, elementGet.getAtomicNumber());
  }
  
  @Test
  void testGetAtomicMass() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, 55, 123, "chemicalname1", "inhabits1");
    
    ElementRowDataGateway elementGet = new ElementRowDataGatewayRDS(1);
    
    assertEquals(123, elementGet.getAtomicMass(), .01);
  }
  
  @Test
  void testGetName() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, 55, 123, "chemicalname1", "inhabits1");
    
    ElementRowDataGateway elementGet = new ElementRowDataGatewayRDS(1);
    
    assertEquals("chemicalname1", elementGet.getName());
  }
  
  @Test
  void testGetInhabits() {
    // Create row data gateways
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, 15, 18, "chemicalname1", "inhabits1");
    
    ElementRowDataGateway elementGet = new ElementRowDataGatewayRDS(1);
    
    // Test
    assertEquals("inhabits1", elementGet.getInhabits());
  }

}
