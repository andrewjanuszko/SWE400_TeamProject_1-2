package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAcid {
  
  @Test
  void testGetName() {
    AcidRowDataGateway 
      initialize = new AcidRowDataGatewayRDS(),
      acid1 = new AcidRowDataGatewayRDS(1, 2, "acidname1", "acidihabits1"),
      acid2 = new AcidRowDataGatewayRDS(2, 4, "acidname2", "acidihabits2"),
      acid3 = new AcidRowDataGatewayRDS(3, 6, "acidname3", "acidihabits3"),
      acid1_fetch = new AcidRowDataGatewayRDS(1),
      acid2_fetch = new AcidRowDataGatewayRDS(2),
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
  void testGetInhabits() {
    AcidRowDataGateway 
    initialize = new AcidRowDataGatewayRDS(),
    acid1 = new AcidRowDataGatewayRDS(1, 2, "acidname1", "acidihabits1"),
    acid2 = new AcidRowDataGatewayRDS(2, 4, "acidname2", "acidihabits2"),
    acid3 = new AcidRowDataGatewayRDS(3, 6, "acidname3", "acidihabits3"),
    acid1_fetch = new AcidRowDataGatewayRDS(1),
    acid2_fetch = new AcidRowDataGatewayRDS(2),
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
}
