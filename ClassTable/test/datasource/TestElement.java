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
    ElementRowDataGateway elementGet1 = new ElementRowDataGatewayRDS(21);
    ElementRowDataGateway elementGet2 = new ElementRowDataGatewayRDS(22);
    ElementRowDataGateway elementGet3 = new ElementRowDataGatewayRDS(23);
    ElementRowDataGateway elementGet4 = new ElementRowDataGatewayRDS(24);

    assertEquals(1, elementGet1.getAtomicNumber());
    assertEquals(2, elementGet2.getAtomicNumber());
    assertEquals(3, elementGet3.getAtomicNumber());
    assertEquals(4, elementGet4.getAtomicNumber());
  }
  
  @Test
  static void testGetAtomicMass() {    
    ElementRowDataGateway elementGet1 = new ElementRowDataGatewayRDS(21);
    ElementRowDataGateway elementGet2 = new ElementRowDataGatewayRDS(22);
    ElementRowDataGateway elementGet3 = new ElementRowDataGatewayRDS(23);
    ElementRowDataGateway elementGet4 = new ElementRowDataGatewayRDS(24);

    assertEquals(9, elementGet1.getAtomicMass(), 0.1);
    assertEquals(8, elementGet2.getAtomicMass(), 0.1);
    assertEquals(7, elementGet3.getAtomicMass(), 0.1);
    assertEquals(6, elementGet4.getAtomicMass(), 0.1);
  }
  
  @Test
  static void testGetName() {    
    ElementRowDataGateway elementGet1 = new ElementRowDataGatewayRDS(21);
    ElementRowDataGateway elementGet2 = new ElementRowDataGatewayRDS(22);
    ElementRowDataGateway elementGet3 = new ElementRowDataGatewayRDS(23);
    ElementRowDataGateway elementGet4 = new ElementRowDataGatewayRDS(24);

    assertEquals("elementname1", elementGet1.getName());
    assertEquals("elementname2", elementGet2.getName());
    assertEquals("elementname3", elementGet3.getName());
    assertEquals("elementname4", elementGet4.getName());
  }
  
  @Test
  static void testGetInhabits() {    
    ElementRowDataGateway elementGet1 = new ElementRowDataGatewayRDS(21);
    ElementRowDataGateway elementGet2 = new ElementRowDataGatewayRDS(22);
    ElementRowDataGateway elementGet3 = new ElementRowDataGatewayRDS(23);
    ElementRowDataGateway elementGet4 = new ElementRowDataGatewayRDS(24);

    assertEquals("elementinhabits1", elementGet1.getInhabits());
    assertEquals("elementinhabits2", elementGet2.getInhabits());
    assertEquals("elementinhabits3", elementGet3.getInhabits());
    assertEquals("elementinhabits4", elementGet4.getInhabits());
  }
  
  static void testAll() {
    insertElements(); 
    testGetAtomicNumber();
    testGetAtomicMass();
    testGetName();
    testGetInhabits();
  }
  
  private static void insertElements() {
    ElementRowDataGateway ele = new ElementRowDataGatewayRDS(21, 1, 9, "elementname1", "elementinhabits1");
    ele = new ElementRowDataGatewayRDS(22, 2, 8, "elementname2", "elementinhabits2");
    ele = new ElementRowDataGatewayRDS(23, 3, 7, "elementname3", "elementinhabits3");
    ele = new ElementRowDataGatewayRDS(24, 4, 6, "elementname4", "elementinhabits4");
  }

}
