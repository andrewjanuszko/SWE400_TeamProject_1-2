package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author kimberlyoneill
 *
 */
class TestElement extends DatabaseTest {

  
  @Test
  static void testGetAtomicNumber() {    
    //initialize element
    ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, 55, 123, "chemicalname1", "inhabits1");
    
    //element getter
    ElementRowDataGateway elementGet = new ElementRowDataGatewayRDS(1);
    
    assertEquals(55, elementGet.getAtomicNumber());
  }
  
  @Test
  static void testGetAtomicMass() {    
    ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, 55, 123, "chemicalname1", "inhabits1");
    
    ElementRowDataGateway elementGet = new ElementRowDataGatewayRDS(1);
    
    assertEquals(123, elementGet.getAtomicMass(), .01);
  }
  
  @Test
  static void testGetName() {    
    ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, 55, 123, "chemicalname1", "inhabits1");
    
    ElementRowDataGateway elementGet = new ElementRowDataGatewayRDS(1);
    
    assertEquals("chemicalname1", elementGet.getName());
  }
  
  @Test
  static void testGetInhabits() {    
    ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, 15, 18, "chemicalname1", "inhabits1");
    
    ElementRowDataGateway elementGet = new ElementRowDataGatewayRDS(1);
    
    assertEquals("inhabits1", elementGet.getInhabits());
  }
  
  static void testAll() {
    testGetAtomicNumber();
    testGetAtomicMass();
    testGetName();
    testGetInhabits();
  }

}
