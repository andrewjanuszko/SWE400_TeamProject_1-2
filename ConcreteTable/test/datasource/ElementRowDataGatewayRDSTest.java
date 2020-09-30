package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ElementRowDataGatewayRDSTest {

	@Test
	void testGetters() {
		ElementRowDataGateway element = new ElementRowDataGatewayRDS(1, "element", "inhabit", 5, 10.0);
		
		assertEquals(1, element.getElementID());
		assertEquals("element", element.getName());
		assertEquals("inhabit", element.getInhabits());
		assertEquals(5, element.getAtomicNumber());
		assertEquals(10.0, element.getAtomicMass());
	}
	
	@Test
	void testSetters() {
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

}
