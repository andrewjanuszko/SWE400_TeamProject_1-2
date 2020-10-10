package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompoundRowDataGatewayRDSTest extends DatabaseTest{
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
  /**
   * Tests constructors.
   * @throws DatacompoundException
   */
  @Test
  void testConstructors() throws DatabaseException{
    CompoundRowDataGateway compound1 = new CompoundRowDataGatewayRDS(1, "compound", "inhabit");
    CompoundRowDataGateway compound1FindByID = new CompoundRowDataGatewayRDS(1);
    CompoundRowDataGateway compound1FindByName = new CompoundRowDataGatewayRDS("compound");
    
    assertEquals(compound1.getCompoundID(), compound1FindByID.getCompoundID());
    assertEquals(compound1.getName(), compound1FindByID.getName());
    assertEquals(compound1.getInhabits(), compound1FindByID.getInhabits());
        
    assertEquals(compound1.getCompoundID(), compound1FindByName.getCompoundID());
    assertEquals(compound1.getName(), compound1FindByName.getName());
    assertEquals(compound1.getInhabits(), compound1FindByName.getInhabits());
  }

  /**
   * Tests getters. 
   * @throws DatacompoundException
   */
  @Test
  void testGetters() throws DatabaseException{
   CompoundRowDataGateway compound1 = new CompoundRowDataGatewayRDS(1, "compound", "inhabit");
   
   assertEquals(1, compound1.getCompoundID());
   assertEquals("compound", compound1.getName());
   assertEquals("inhabit", compound1.getInhabits());
  }
  
  /**
   * Tests setters.
   * @throws DatacompoundException
   */
  @Test
  void testSetters() throws DatabaseException{
    CompoundRowDataGateway compound1 = new CompoundRowDataGatewayRDS(1, "compound", "inhabit");
    compound1.setInhabits("new");
    compound1.setName("new");
    
    assertEquals("new", compound1.getName());
    assertEquals("new", compound1.getInhabits());
  }
  
  /**
   * Tests persist.
   * @throws DatacompoundException
   */
  @Test
  void testPersist() throws DatabaseException {
    CompoundRowDataGateway compound1 = new CompoundRowDataGatewayRDS(1, "compound", "inhabit");
    compound1.setName("newName");
    compound1.persist();
    compound1 = null;
    
    CompoundRowDataGateway compound1Copy = new CompoundRowDataGatewayRDS(1);
    assertEquals("newName", compound1Copy.getName());
  }
  
  /**
   * Tests delete.
   * @throws DatacompoundException
   */
  @Test
  void testDelete() throws DatabaseException {
    CompoundRowDataGateway compound1 = new CompoundRowDataGatewayRDS(1, "compound", "inhabit");
    
    assertTrue(compound1.delete());
  }

}
