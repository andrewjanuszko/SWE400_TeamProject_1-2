package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGateway;

class TestChemical extends DatabaseTest {
  
  /**
   * Test getName method of ChemicalRowDataGateway
   * @throws SQLException
   * @throws DatabaseException
   */
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

    initialize.dropTable();
  }
  
  /**
   * Test getInhabits of ChemicalRowDataGateway
   * @throws SQLException
   * @throws DatabaseException
   */
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

    initialize.dropTable();
  }
  
  /**
   * Test the update function of ChemicalRowDataGateway
   */
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
  
  /**
   * Test the delete method of ChemicalRowDataGateway
   */
  @Test
  void testDelete() {
    ChemicalRowDataGateway initialize = new ChemicalRowDataGatewayRDS(),
        chem = new ChemicalRowDataGatewayRDS(1, "chemname1", "cheminhabits1");
    
    // Ensure it has been added
    assertEquals("chemname1", chem.getName());
    assertEquals("cheminhabits1", chem.getInhabits());
    
    chem.delete();
    
    try { 
      chem = new ChemicalRowDataGatewayRDS(1);
      fail("");
    } catch(DatabaseException | SQLException e) {
      assertTrue(true); 
    }
  }
  
}
