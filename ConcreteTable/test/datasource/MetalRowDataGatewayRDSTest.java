package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MetalRowDataGatewayRDSTest {

	@Test
	void testGetters() {
		MetalRowDataGateway Metal = new MetalRowDataGatewayRDS(1, "Metal", "inhabit", 5, 10.0, 10000);
		
		assertEquals(1, Metal.getMetalID());
		assertEquals("Metal", Metal.getName());
		assertEquals("inhabit", Metal.getInhabits());
		assertEquals(5, Metal.getAtomicNumber());
		assertEquals(10.0, Metal.getAtomicMass());
		assertEquals(10000, Metal.getDissolvedBy());
	}
	
	@Test
	void testSetters() {
		MetalRowDataGateway Metal = new MetalRowDataGatewayRDS(1, "Metal", "inhabit", 5, 10.0, 10000);
		
		Metal.setName("name");
		Metal.setInhabits("something");
		Metal.setAtomicNumber(10);
		Metal.setAtomicMass(20.0);
		Metal.setDissolvedBy(12);
		
		assertEquals(1, Metal.getMetalID());
		assertEquals("name", Metal.getName());
		assertEquals("something", Metal.getInhabits());
		assertEquals(10, Metal.getAtomicNumber());
		assertEquals(20.0, Metal.getAtomicMass());
		assertEquals(12, Metal.getDissolvedBy());
	}
}
