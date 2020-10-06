package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * JUnit tests for Base Gateway
 * @author JOJO
 *
 */
class BaseRowDataGatewayRDSTest extends DatabaseTest{
  /**
   * Setup.
   * @throws DatabaseException
   */
  @BeforeEach
  void setup() throws DatabaseException{
    BaseRowDataGatewayRDS.createTable();
  }
  
  /**
   * Tests constructors.
   * @throws DatabaseException
   */
  @Test
  void testConstructors() throws DatabaseException{
    BaseRowDataGateway base1 = new BaseRowDataGatewayRDS(1, "base", "inhabit", "solute");
    BaseRowDataGateway base1FindByID = new BaseRowDataGatewayRDS(1);
    BaseRowDataGateway base1FindByName = new BaseRowDataGatewayRDS("base");
    
    assertEquals(base1.getBaseID(), base1FindByID.getBaseID());
    assertEquals(base1.getName(), base1FindByID.getName());
    assertEquals(base1.getInhabits(), base1FindByID.getInhabits());
    assertEquals(base1.getSolute(), base1FindByID.getSolute());
    
    assertEquals(base1.getBaseID(), base1FindByName.getBaseID());
    assertEquals(base1.getName(), base1FindByName.getName());
    assertEquals(base1.getInhabits(), base1FindByName.getInhabits());
    assertEquals(base1.getSolute(), base1FindByName.getSolute());
  }

  /**
   * Tests getters. 
   * @throws DatabaseException
   */
  @Test
  void testGetters() throws DatabaseException{
   BaseRowDataGateway base1 = new BaseRowDataGatewayRDS(1, "base", "inhabit", "solute");
   
   assertEquals(1, base1.getBaseID());
   assertEquals("base", base1.getName());
   assertEquals("inhabit", base1.getInhabits());
   assertEquals("solute", base1.getSolute());
  }
  
  /**
   * Tests setters.
   * @throws DatabaseException
   */
  @Test
  void testSetters() throws DatabaseException{
    BaseRowDataGateway base1 = new BaseRowDataGatewayRDS(1, "base", "inhabit", "solute");
    base1.setInhabits("new");
    base1.setName("new");
    base1.setSolute("new");
    
    assertEquals("new", base1.getName());
    assertEquals("new", base1.getInhabits());
    assertEquals("new", base1.getSolute());
  }
  
  /**
   * Tests persist.
   * @throws DatabaseException
   */
  @Test
  void testPersist() throws DatabaseException {
    BaseRowDataGateway base1 = new BaseRowDataGatewayRDS(1, "base", "inhabit", "solute");
    base1.setName("newName");
    assertTrue(base1.persist());
    base1 = null;
    
    BaseRowDataGateway base1Copy = new BaseRowDataGatewayRDS(1);
    assertEquals("newName", base1Copy.getName());
  }
  
  /**
   * Tests delete.
   * @throws DatabaseException
   */
  @Test
  void testDelete() throws DatabaseException {
    BaseRowDataGateway base1 = new BaseRowDataGatewayRDS(1, "base", "inhabit", "solute");
    
    assertTrue(base1.delete());
  }
}
