package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGateway;

class TestChemical extends DatabaseTest {
  
  @Test
  void testGetName() throws SQLException, DatabaseException {
    ChemicalRowDataGateway initialize = new ChemicalRowDataGatewayRDS(),
        chem1 = new ChemicalRowDataGatewayRDS(1, "chemname1", "cheminhabits1"),
        chem2 = new ChemicalRowDataGatewayRDS(2, "chemname2", "cheminhabits2"),
        chem3 = new ChemicalRowDataGatewayRDS(3, "chemname3", "cheminhabits3"),
        chem1_fetch = new ChemicalRowDataGatewayRDS(1), chem2_fetch = new ChemicalRowDataGatewayRDS(2),
        chem3_fetch = new ChemicalRowDataGatewayRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals("chemname1", chem1.getName());
    assertEquals("chemname2", chem2.getName());
    assertEquals("chemname3", chem3.getName());

    // Testing to see if new gateways can properly fetch
    assertEquals("chemname1", chem1_fetch.getName());
    assertEquals("chemname2", chem2_fetch.getName());
    assertEquals("chemname3", chem3_fetch.getName());

    // Testing to see if we can change existing gateway to a new id
    chem1.fetch(2);
    assertEquals("chemname2", chem1.getName());
    chem2.fetch(3);
    assertEquals("chemname3", chem2.getName());
    chem3.fetch(1);
    assertEquals("chemname1", chem3.getName());

    initialize.dropTable();
  }
  
  @Test
  void testGetInhabits() throws SQLException, DatabaseException {
    ChemicalRowDataGateway initialize = new ChemicalRowDataGatewayRDS(),
        chem1 = new ChemicalRowDataGatewayRDS(1, "chemname1", "cheminhabits1"),
        chem2 = new ChemicalRowDataGatewayRDS(2, "chemname2", "cheminhabits2"),
        chem3 = new ChemicalRowDataGatewayRDS(3, "chemname3", "cheminhabits3"),
        chem1_fetch = new ChemicalRowDataGatewayRDS(1), chem2_fetch = new ChemicalRowDataGatewayRDS(2),
        chem3_fetch = new ChemicalRowDataGatewayRDS(3);

    // Testing to see if they still hold values after adding
    assertEquals("cheminhabits1", chem1.getInhabits());
    assertEquals("cheminhabits2", chem2.getInhabits());
    assertEquals("cheminhabits3", chem3.getInhabits());

    // Testing to see if new gateways can properly fetch
    assertEquals("cheminhabits1", chem1_fetch.getInhabits());
    assertEquals("cheminhabits2", chem2_fetch.getInhabits());
    assertEquals("cheminhabits3", chem3_fetch.getInhabits());

    // Testing to see if we can change existing gateway to a new id
    chem1.fetch(2);
    assertEquals("cheminhabits2", chem1.getInhabits());
    chem2.fetch(3);
    assertEquals("cheminhabits3", chem2.getInhabits());
    chem3.fetch(1);
    assertEquals("cheminhabits1", chem3.getInhabits());

    initialize.dropTable();
  }
  
  @Test
  void testUpdate() {
    ChemicalRowDataGateway initialize = new ChemicalRowDataGatewayRDS(),
        chem = new ChemicalRowDataGatewayRDS(1, "chemname1", "cheminhabits1");
    
    // Test name
    assertEquals("chemname1", chem.getName());
    chem.setName("chemname2");
    chem.update();
    assertEquals("chemname2", chem.getName());
    
    // Test inhabits
    assertEquals("cheminhabits1", chem.getInhabits());
    chem.setInhabits("cheminhabits2");
    chem.update();
    assertEquals("cheminhabits2", chem.getInhabits());
    
    initialize.dropTable();
  }
  
  @Test
  void testDelete() {
    ChemicalRowDataGateway initialize = new ChemicalRowDataGatewayRDS(),
        chem = new ChemicalRowDataGatewayRDS(1, "chemname1", "cheminhabits1");
    
    // Ensure it has been added
    assertEquals("chemname1", chem.getName());
    assertEquals("cheminhabits1", chem.getInhabits());
    
    chem.delete();
    
    try { 
      chem.fetch(1);
      fail("");
    } catch(DatabaseException | SQLException e) {
      assertTrue(true); 
    }
  }
  
}
