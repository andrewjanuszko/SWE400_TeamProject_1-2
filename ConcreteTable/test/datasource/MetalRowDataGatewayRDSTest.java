package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MetalRowDataGatewayRDSTest extends DatabaseTest{

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
	
	@Test
	void testDelete() throws DatabaseException {
	    MetalRowDataGateway metal1 = new MetalRowDataGatewayRDS(1, "metal", "inhabit", 5, 10.0, 10000);
	    
	    assertTrue(metal1.delete());
	}
	
	@Test
	void testConstructors() throws DatabaseException{
	    MetalRowDataGateway metal1 = new MetalRowDataGatewayRDS(1, "metal", "inhabit", 5, 10.0, 10000);
	    MetalRowDataGateway metal1FindByID = new MetalRowDataGatewayRDS(1);
	    MetalRowDataGateway metal1FindByName = new MetalRowDataGatewayRDS("metal");
	    
	    assertEquals(metal1.getMetalID(), metal1FindByID.getMetalID());
	    assertEquals(metal1.getName(), metal1FindByID.getName());
	    assertEquals(metal1.getInhabits(), metal1FindByID.getInhabits());
	    assertEquals(metal1.getAtomicNumber(), metal1FindByID.getAtomicNumber());
	    assertEquals(metal1.getAtomicMass(), metal1FindByID.getAtomicMass());
	    assertEquals(metal1.getDissolvedBy(), metal1FindByID.getDissolvedBy());
	    
	    assertEquals(metal1.getMetalID(), metal1FindByName.getMetalID());
	    assertEquals(metal1.getName(), metal1FindByName.getName());
	    assertEquals(metal1.getInhabits(), metal1FindByName.getInhabits());
	    assertEquals(metal1.getAtomicNumber(), metal1FindByName.getAtomicNumber());
	    assertEquals(metal1.getAtomicMass(), metal1FindByName.getAtomicMass());
	    assertEquals(metal1.getDissolvedBy(), metal1FindByName.getDissolvedBy());
	}
	
	@Test
	void testPersist() throws DatabaseException {
		MetalRowDataGateway metal1 = new MetalRowDataGatewayRDS(1, "metal", "inhabit", 5, 10.0, 10000);
	    metal1.setName("newName");
		assertTrue(metal1.persist());
	    metal1 = null;
	    
	    MetalRowDataGateway metal1Copy = new MetalRowDataGatewayRDS(1);
	    assertEquals("newName", metal1Copy.getName());
	}
}
