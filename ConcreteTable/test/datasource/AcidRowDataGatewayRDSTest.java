package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author You - the viewer :)
 *
 */
class AcidRowDataGatewayRDSTest extends DatabaseTest {
  @BeforeEach
  void setup() throws DatabaseException{
    MetalRowDataGatewayRDS.dropTable();
    AcidRowDataGatewayRDS.createTable();
  }
  
  /**
   * Tests constructors.
   * @throws DatabaseException
   */
  @Test
  void testConstructors() throws DatabaseException{
    AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", 1.0, "solute");
    AcidRowDataGateway acid1FindByID = new AcidRowDataGatewayRDS(1);
    AcidRowDataGateway acid1FindByName = new AcidRowDataGatewayRDS("acid");
    
    assertEquals(acid1.getAcidID(), acid1FindByID.getAcidID());
    assertEquals(acid1.getName(), acid1FindByID.getName());
    assertEquals(acid1.getInventory(), acid1FindByID.getInventory());
    assertEquals(acid1.getSolute(), acid1FindByID.getSolute());
    
    assertEquals(acid1.getAcidID(), acid1FindByName.getAcidID());
    assertEquals(acid1.getName(), acid1FindByName.getName());
    assertEquals(acid1.getInventory(), acid1FindByName.getInventory());
    assertEquals(acid1.getSolute(), acid1FindByName.getSolute());
  }
  
	/**
	 * Tests getters. 
	 * @throws DatabaseException
	 */
  @Test
  void testGetters() throws DatabaseException{
	 AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", 1.0, "solute");
	 
	 assertEquals(1, acid1.getAcidID());
	 assertEquals("acid", acid1.getName());
	 assertEquals(1.0, acid1.getInventory());
	 assertEquals("solute", acid1.getSolute());
  }
  
  /**
   * Tests setters.
   * @throws DatabaseException
   */
  @Test
  void testSetters() throws DatabaseException{
    AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", 1.0, "solute");
    acid1.setInventory(2.0);
    acid1.setName("new");
    acid1.setSolute("new");
    
    assertEquals("new", acid1.getName());
    assertEquals(2.0, acid1.getInventory());
    assertEquals("new", acid1.getSolute());
  }
  
  /**
   * Tests persist.
   * @throws DatabaseException
   */
  @Test
  void testPersist() throws DatabaseException {
    AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", 1.0, "solute");
    acid1.setName("newName");
    assertTrue(acid1.persist());
    acid1 = null;
    
    AcidRowDataGateway acid1Copy = new AcidRowDataGatewayRDS(1);
    assertEquals("newName", acid1Copy.getName());
  }
  
  /**
   * Tests delete.
   * @throws DatabaseException
   */
  @Test
  void testDelete() throws DatabaseException {
    AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", 1.0, "solute");
    MetalRowDataGatewayRDS.createTable();
    assertTrue(acid1.delete());
  }
}
