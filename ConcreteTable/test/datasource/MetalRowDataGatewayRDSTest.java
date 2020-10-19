package datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MetalRowDataGatewayRDSTest extends DatabaseTest{
  
  /**
   * Setup.
   * @throws DatabaseException
   */
  @BeforeEach
  void setup() throws DatabaseException{
    MetalRowDataGatewayRDS.dropTable();
    AcidRowDataGatewayRDS.dropTable();
    AcidRowDataGatewayRDS.createTable();
    MetalRowDataGatewayRDS.createTable();
  }
  
	@Test
	void testGetters() throws DatabaseException {
		MetalRowDataGateway Metal = new MetalRowDataGatewayRDS(1, "Metal", 1.0, 5, 10.0, 10000);
		
		assertEquals(1, Metal.getMetalID());
		assertEquals("Metal", Metal.getName());
		assertEquals(1.0, Metal.getInventory());
		assertEquals(5, Metal.getAtomicNumber());
		assertEquals(10.0, Metal.getAtomicMass());
		assertEquals(10000, Metal.getDissolvedBy());
	}
	
	@Test
	void testSetters() throws DatabaseException {
		MetalRowDataGateway Metal = new MetalRowDataGatewayRDS(1, "Metal", 1.0, 5, 10.0, 10000);
		
		Metal.setName("name");
		Metal.setInventory(2.0);
		Metal.setAtomicNumber(10);
		Metal.setAtomicMass(20.0);
		Metal.setDissolvedBy(12);
		
		assertEquals(1, Metal.getMetalID());
		assertEquals("name", Metal.getName());
		assertEquals(2.0, Metal.getInventory());
		assertEquals(10, Metal.getAtomicNumber());
		assertEquals(20.0, Metal.getAtomicMass());
		assertEquals(12, Metal.getDissolvedBy());
	}
	
	@Test
  void testFindDissolves() throws DatabaseException, SQLException {
	  AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(10, "acid", 1.0, 1);
	  MetalRowDataGateway metal1 = new MetalRowDataGatewayRDS(1, "metal1", 1.0, 5, 10.0, 10);
	  MetalRowDataGateway metal2 = new MetalRowDataGatewayRDS(2, "metal2", 1.0, 5, 10.0, 10);
	  MetalRowDataGateway metal3 = new MetalRowDataGatewayRDS(3, "metal3", 1.0, 5, 10.0, 50);
	  
	  ArrayList<MetalRowDataGatewayRDS> metals = MetalRowDataGatewayRDS.findDissolves(10);
	  
	  assertEquals(2, metals.size());
	  assertEquals(metal1.getMetalID(), metals.get(0).getMetalID());
	  assertEquals(metal2.getMetalID(), metals.get(1).getMetalID());
	}
	
	@Test
	void testDelete() throws DatabaseException {
	    MetalRowDataGateway metal1 = new MetalRowDataGatewayRDS(1, "metal", 1.0, 5, 10.0, 10000);
	    
	    assertTrue(metal1.delete());
	}
	
	@Test
	void testConstructors() throws DatabaseException{
	  AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(10000, "acid", 1.0, 1);
	    MetalRowDataGateway metal1 = new MetalRowDataGatewayRDS(1, "metal", 1.0, 5, 10.0, 10000);
	    MetalRowDataGateway metal1FindByID = new MetalRowDataGatewayRDS(1);
	    MetalRowDataGateway metal1FindByName = new MetalRowDataGatewayRDS("metal");
	    
	    assertEquals(metal1.getMetalID(), metal1FindByID.getMetalID());
	    assertEquals(metal1.getName(), metal1FindByID.getName());
	    assertEquals(metal1.getInventory(), metal1FindByID.getInventory());
	    assertEquals(metal1.getAtomicNumber(), metal1FindByID.getAtomicNumber());
	    assertEquals(metal1.getAtomicMass(), metal1FindByID.getAtomicMass());
	    assertEquals(metal1.getDissolvedBy(), metal1FindByID.getDissolvedBy());
	    
	    assertEquals(metal1.getMetalID(), metal1FindByName.getMetalID());
	    assertEquals(metal1.getName(), metal1FindByName.getName());
	    assertEquals(metal1.getInventory(), metal1FindByName.getInventory());
	    assertEquals(metal1.getAtomicNumber(), metal1FindByName.getAtomicNumber());
	    assertEquals(metal1.getAtomicMass(), metal1FindByName.getAtomicMass());
	    assertEquals(metal1.getDissolvedBy(), metal1FindByName.getDissolvedBy());
	}
	
	@Test
	void testPersist() throws DatabaseException {
	  AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(10000, "acid", 1.0, 1);
		MetalRowDataGateway metal1 = new MetalRowDataGatewayRDS(1, "metal", 1.0, 5, 10.0, 10000);
	    metal1.setName("newName");
		assertTrue(metal1.persist());
	    metal1 = null;
	    
	    MetalRowDataGateway metal1Copy = new MetalRowDataGatewayRDS(1);
	    assertEquals("newName", metal1Copy.getName());
	}
}
