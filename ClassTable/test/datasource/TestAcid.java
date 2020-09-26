package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

/**
 * TODO: Test update to nonexistant id
 * TODO: Test delete
 * TODO: Test changing to invalid ids
 * @author Isabella
 *
 */
class TestAcid {
  
  @Test
  void testGetName() throws SQLException, DatabaseException {
    AcidRowDataGateway initialize = new AcidRowDataGatewayRDS(),
        acid1 = new AcidRowDataGatewayRDS(1, 2, "acidname1", "acidihabits1"),
        acid2 = new AcidRowDataGatewayRDS(2, 4, "acidname2", "acidihabits2"),
        acid3 = new AcidRowDataGatewayRDS(3, 6, "acidname3", "acidihabits3"),
        acid1_fetch = new AcidRowDataGatewayRDS(1), acid2_fetch = new AcidRowDataGatewayRDS(2),
        acid3_fetch = new AcidRowDataGatewayRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals("acidname1", acid1.getName());
    assertEquals("acidname2", acid2.getName());
    assertEquals("acidname3", acid3.getName());

    // Testing to see if new gateways can properly fetch
    assertEquals("acidname1", acid1_fetch.getName());
    assertEquals("acidname2", acid2_fetch.getName());
    assertEquals("acidname3", acid3_fetch.getName());

    // Testing to see if we can change existing gateway to a new id
    acid1.fetch(2);
    assertEquals("acidname2", acid1.getName());
    acid2.fetch(3);
    assertEquals("acidname3", acid2.getName());
    acid3.fetch(1);
    assertEquals("acidname1", acid3.getName());

    initialize.dropAllTables();
  }
  
  @Test
  void testGetInhabits() throws SQLException, DatabaseException {
    AcidRowDataGateway initialize = new AcidRowDataGatewayRDS(),
        acid1 = new AcidRowDataGatewayRDS(1, 2, "acidname1", "acidihabits1"),
        acid2 = new AcidRowDataGatewayRDS(2, 4, "acidname2", "acidihabits2"),
        acid3 = new AcidRowDataGatewayRDS(3, 6, "acidname3", "acidihabits3"),
        acid1_fetch = new AcidRowDataGatewayRDS(1), acid2_fetch = new AcidRowDataGatewayRDS(2),
        acid3_fetch = new AcidRowDataGatewayRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals("acidihabits1", acid1.getInhabits());
    assertEquals("acidihabits2", acid2.getInhabits());
    assertEquals("acidihabits3", acid3.getInhabits());

    // Testing to see if new gateways can properly fetch
    assertEquals("acidihabits1", acid1_fetch.getInhabits());
    assertEquals("acidihabits2", acid2_fetch.getInhabits());
    assertEquals("acidihabits3", acid3_fetch.getInhabits());

    // Testing to see if we can change existing gateway to a new id
    acid1.fetch(2);
    assertEquals("acidihabits2", acid1.getInhabits());
    acid2.fetch(3);
    assertEquals("acidihabits3", acid2.getInhabits());
    acid3.fetch(1);
    assertEquals("acidihabits1", acid3.getInhabits());

    initialize.dropAllTables();
  }
  
  @Test
  void testGetSolute() throws SQLException, DatabaseException {
    AcidRowDataGateway initialize = new AcidRowDataGatewayRDS(),
        acid1 = new AcidRowDataGatewayRDS(1, 2, "acidname1", "acidihabits1"),
        acid2 = new AcidRowDataGatewayRDS(2, 4, "acidname2", "acidihabits2"),
        acid3 = new AcidRowDataGatewayRDS(3, 6, "acidname3", "acidihabits3"),
        acid1_fetch = new AcidRowDataGatewayRDS(1), acid2_fetch = new AcidRowDataGatewayRDS(2),
        acid3_fetch = new AcidRowDataGatewayRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals(2, acid1.getSolute());
    assertEquals(4, acid2.getSolute());
    assertEquals(6, acid3.getSolute());

    // Testing to see if new gateways can properly fetch
    assertEquals(2, acid1_fetch.getSolute());
    assertEquals(4, acid2_fetch.getSolute());
    assertEquals(6, acid3_fetch.getSolute());

    // Testing to see if we can change existing gateway to a new id
    acid1.fetch(2);
    assertEquals(4, acid1.getSolute());
    acid2.fetch(3);
    assertEquals(6, acid2.getSolute());
    acid3.fetch(1);
    assertEquals(2, acid3.getSolute());

    initialize.dropAllTables();
  }
  
  @Test
  void testDelete() {
    AcidRowDataGateway initialize = new AcidRowDataGatewayRDS(),
        acid = new AcidRowDataGatewayRDS(1, 2, "chemname1", "cheminhabits1");
    
    // Ensure it has been added
    assertEquals("chemname1", acid.getName());
    assertEquals("cheminhabits1", acid.getInhabits());
    
    acid.delete();
    
    try { 
      acid.fetch(1);
      fail("");
    } catch(DatabaseException | SQLException e) {
      assertTrue(true); 
    }
  }
  
  @Test
  void testUpdate() {
    AcidRowDataGateway initialize = new AcidRowDataGatewayRDS(),
        acid = new AcidRowDataGatewayRDS(1, 2, "acidname1", "acidinhabits1");
    
    // Test solute
    assertEquals(2, acid.getSolute());
    acid.setSolute(3);
    acid.update();
    assertEquals(3, acid.getSolute());
    
    // Test name
    assertEquals("acidname1", acid.getName());
    acid.setName("acidname2");
    acid.update();
    assertEquals("acidname2", acid.getName());
    
    // Test inhabits
    assertEquals("acidinhabits1", acid.getInhabits());
    acid.setInhabits("acidinhabits2");
    acid.update();
    assertEquals("acidinhabits2", acid.getInhabits());
    
    initialize.dropAllTables();
  }
}
