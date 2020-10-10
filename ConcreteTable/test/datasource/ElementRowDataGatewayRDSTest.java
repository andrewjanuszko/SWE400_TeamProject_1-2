package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElementRowDataGatewayRDSTest extends DatabaseTest{
  
  /**
   * Setup.
   * @throws DatabaseException
   */
  @BeforeEach
  void setup() throws DatabaseException{
    CompoundMadeOfTableDataGatewayRDS.dropTable();
    CompoundRowDataGatewayRDS.createTable();
    ElementRowDataGatewayRDS.createTable();
    CompoundMadeOfTableDataGatewayRDS.createTable();
  }
  
	@Test
	void testGetters() throws DatabaseException {
		ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, "element", "inhabit", 5, 10.0);
		
		assertEquals(1, element.getElementID());
		assertEquals("element", element.getName());
		assertEquals("inhabit", element.getInhabits());
		assertEquals(5, element.getAtomicNumber());
		assertEquals(10.0, element.getAtomicMass());
	}
	
	@Test
	void testSetters() throws DatabaseException {
		ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, "element", "inhabit", 5, 10.0);
		
		element.setName("name");
		element.setInhabits("something");
		element.setAtomicNumber(10);
		element.setAtomicMass(20.0);
		
		assertEquals(1, element.getElementID());
		assertEquals("name", element.getName());
		assertEquals("something", element.getInhabits());
		assertEquals(10, element.getAtomicNumber());
		assertEquals(20.0, element.getAtomicMass());
	}

	@Test
	void testDelete() throws DatabaseException {
	    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(1, "element", "inhabit", 5, 10.0);
	    
	    assertTrue(element1.delete());
	}
	
	@Test
	void testConstructors() throws DatabaseException{
	    ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(1, "element", "inhabit", 5, 10.0);
	   // ElementRowDataGateway element1FindByID = new ElementRowDataGatewayRDS(1);
	    ElementRowDataGateway element1FindByName = new ElementRowDataGatewayRDS("element");
	    
//	    assertEquals(element1.getElementID(), element1FindByID.getElementID());
//	    assertEquals(element1.getName(), element1FindByID.getName());
//	    assertEquals(element1.getInhabits(), element1FindByID.getInhabits());
//	    assertEquals(element1.getAtomicNumber(), element1FindByID.getAtomicNumber());
//	    assertEquals(element1.getAtomicMass(), element1FindByID.getAtomicMass());
//	    
	    assertEquals(element1.getElementID(), element1FindByName.getElementID());
	    assertEquals(element1.getName(), element1FindByName.getName());
	    assertEquals(element1.getInhabits(), element1FindByName.getInhabits());
	    assertEquals(element1.getAtomicNumber(), element1FindByName.getAtomicNumber());
	    assertEquals(element1.getAtomicMass(), element1FindByName.getAtomicMass());
	}
	
	@Test
	void testPersist() throws DatabaseException {
		ElementRowDataGateway element1 = new ElementRowDataGatewayRDS(1, "element", "inhabit", 5, 10.0);
	    element1.setName("newName");
	    assertTrue(element1.persist());
	    element1 = null;
	    
	    ElementRowDataGateway element1Copy = new ElementRowDataGatewayRDS("newName");
	    assertEquals("newName", element1Copy.getName());
	}
}
