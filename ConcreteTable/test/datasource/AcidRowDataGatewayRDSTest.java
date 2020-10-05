package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author You - the viewer :)
 *
 */
class AcidRowDataGatewayRDSTest extends DatabaseTest {
  /**
   * Tests constructors.
   * @throws DatabaseException
   */
  @Test
  void testConstructors() throws DatabaseException{
    AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", "inhabit", "solute");
    AcidRowDataGateway acid1FindByID = new AcidRowDataGatewayRDS(1);
    AcidRowDataGateway acid1FindByName = new AcidRowDataGatewayRDS("acid");
    
    assertEquals(acid1.getAcidID(), acid1FindByID.getAcidID());
    assertEquals(acid1.getName(), acid1FindByID.getName());
    assertEquals(acid1.getInhabits(), acid1FindByID.getInhabits());
    assertEquals(acid1.getSolute(), acid1FindByID.getSolute());
    
    assertEquals(acid1.getAcidID(), acid1FindByName.getAcidID());
    assertEquals(acid1.getName(), acid1FindByName.getName());
    assertEquals(acid1.getInhabits(), acid1FindByName.getInhabits());
    assertEquals(acid1.getSolute(), acid1FindByName.getSolute());
  }
  
	/**
	 * Tests getters. 
	 * @throws DatabaseException
	 */
  @Test
  void testGetters() throws DatabaseException{
	 AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", "inhabit", "solute");
	 
	 assertEquals(1, acid1.getAcidID());
	 assertEquals("acid", acid1.getName());
	 assertEquals("inhabit", acid1.getInhabits());
	 assertEquals("solute", acid1.getSolute());
  }
  
  /**
   * Tests setters.
   * @throws DatabaseException
   */
  @Test
  void testSetters() throws DatabaseException{
    AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", "inhabit", "solute");
    acid1.setInhabits("new");
    acid1.setName("new");
    acid1.setSolute("new");
    
    assertEquals("new", acid1.getName());
    assertEquals("new", acid1.getInhabits());
    assertEquals("new", acid1.getSolute());
  }
  
  /**
   * Tests persist.
   * @throws DatabaseException
   */
  @Test
  void testPersist() throws DatabaseException {
    AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", "inhabit", "solute");
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
    AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", "inhabit", "solute");
    
    assertTrue(acid1.delete());
  }
}
