package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestBase {

  @Test
  void testGetName() throws SQLException, DatabaseException {
    BaseRowDataGateway initialize = new BaseRowDataGatewayRDS();
    
    initialize.dropAllTables();
    
    BaseRowDataGateway
        base1 = new BaseRowDataGatewayRDS(1, 2, "basename1", "baseinhabits1"),
        base2 = new BaseRowDataGatewayRDS(2, 4, "basename2", "baseinhabits2"),
        base3 = new BaseRowDataGatewayRDS(3, 6, "basename3", "baseinhabits3"),
        base1_fetch = new BaseRowDataGatewayRDS(1), base2_fetch = new BaseRowDataGatewayRDS(2),
        base3_fetch = new BaseRowDataGatewayRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals("basename1", base1.getName());
    assertEquals("basename2", base2.getName());
    assertEquals("basename3", base3.getName());

    // Testing to see if new gateways can properly fetch
    assertEquals("basename1", base1_fetch.getName());
    assertEquals("basename2", base2_fetch.getName());
    assertEquals("basename3", base3_fetch.getName());

    // Testing to see if we can change existing gateway to a new id
    base1.fetch(2);
    assertEquals("basename2", base1.getName());
    base2.fetch(3);
    assertEquals("basename3", base2.getName());
    base3.fetch(1);
    assertEquals("basename1", base3.getName());

    initialize.dropAllTables();
    
  }
  
  @Test
  void testGetInhabits() throws SQLException, DatabaseException {
    BaseRowDataGateway initialize = new BaseRowDataGatewayRDS();
    
    initialize.dropAllTables();
    
    BaseRowDataGateway
        base1 = new BaseRowDataGatewayRDS(1, 2, "basename1", "baseihabits1"),
        base2 = new BaseRowDataGatewayRDS(2, 4, "basename2", "baseihabits2"),
        base3 = new BaseRowDataGatewayRDS(3, 6, "basename3", "baseihabits3"),
        base1_fetch = new BaseRowDataGatewayRDS(1), base2_fetch = new BaseRowDataGatewayRDS(2),
        base3_fetch = new BaseRowDataGatewayRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals("baseihabits1", base1.getInhabits());
    assertEquals("baseihabits2", base2.getInhabits());
    assertEquals("baseihabits3", base3.getInhabits());

    // Testing to see if new gateways can properly fetch
    assertEquals("baseihabits1", base1_fetch.getInhabits());
    assertEquals("baseihabits2", base2_fetch.getInhabits());
    assertEquals("baseihabits3", base3_fetch.getInhabits());

    // Testing to see if we can change existing gateway to a new id
    base1.fetch(2);
    assertEquals("baseihabits2", base1.getInhabits());
    base2.fetch(3);
    assertEquals("baseihabits3", base2.getInhabits());
    base3.fetch(1);
    assertEquals("baseihabits1", base3.getInhabits());

    initialize.dropAllTables();
  }
  
  @Test
  void testGetSolute() throws SQLException, DatabaseException {
    BaseRowDataGateway initialize = new BaseRowDataGatewayRDS();
    
    initialize.dropAllTables();
    
    BaseRowDataGateway
        base1 = new BaseRowDataGatewayRDS(1, 2, "basename1", "baseihabits1"),
        base2 = new BaseRowDataGatewayRDS(2, 4, "basename2", "baseihabits2"),
        base3 = new BaseRowDataGatewayRDS(3, 6, "basename3", "baseihabits3"),
        base1_fetch = new BaseRowDataGatewayRDS(1), base2_fetch = new BaseRowDataGatewayRDS(2),
        base3_fetch = new BaseRowDataGatewayRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals(2, base1.getSolute());
    assertEquals(4, base2.getSolute());
    assertEquals(6, base3.getSolute());

    // Testing to see if new gateways can properly fetch
    assertEquals(2, base1_fetch.getSolute());
    assertEquals(4, base2_fetch.getSolute());
    assertEquals(6, base3_fetch.getSolute());

    // Testing to see if we can change existing gateway to a new id
    base1.fetch(2);
    assertEquals(4, base1.getSolute());
    base2.fetch(3);
    assertEquals(6, base2.getSolute());
    base3.fetch(1);
    assertEquals(2, base3.getSolute());

    initialize.dropAllTables();
  }
  
  @Test
  void testUpdate() {
    BaseRowDataGateway initialize = new BaseRowDataGatewayRDS();
    
    initialize.dropAllTables();
    
    BaseRowDataGateway base = new BaseRowDataGatewayRDS(1, 2, "basename1", "baseinhabits1");
    
    // Test solute
    assertEquals(2, base.getSolute());
    base.setSolute(3);
    base.update();
    assertEquals(3, base.getSolute());
    
    // Test name
    assertEquals("basename1", base.getName());
    base.setName("basename2");
    base.update();
    assertEquals("basename2", base.getName());
    
    // Test inhabits
    assertEquals("baseinhabits1", base.getInhabits());
    base.setInhabits("baseinhabits2");
    base.update();
    assertEquals("baseinhabits2", base.getInhabits());
    
    initialize.dropAllTables();
  }
  
  @Test
  void testDelete() {
    BaseRowDataGateway initialize = new BaseRowDataGatewayRDS(),
        base = new BaseRowDataGatewayRDS(1, 2, "chemname1", "cheminhabits1");
    
    // Ensure it has been added
    assertEquals("chemname1", base.getName());
    assertEquals("cheminhabits1", base.getInhabits());
    
    base.delete();
    
    try { 
      base.fetch(1);
      fail("");
    } catch(DatabaseException | SQLException e) {
      assertTrue(true); 
    }
  }
  
  
  @Test
  void testGetSet() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
    BaseRowDataGateway base1 = new BaseRowDataGatewayRDS(1, 15, "acidname1", "acidinhabits1");
    BaseRowDataGatewayRDS base2 = new BaseRowDataGatewayRDS(2, 15, "acidname2", "acidinhabits2");
    
    List<BaseRowDataGatewayRDS> acidGet = base2.findSet(15);
    
    assertEquals("acidname1", acidGet.get(0).getName());
    assertEquals("acidname2", acidGet.get(1).getName());
  }
  
}
