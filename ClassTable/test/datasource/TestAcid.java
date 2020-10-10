package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Isabella Boone
 * 
 */
class TestAcid extends DatabaseTest {
  
  @Test
  void testGetName() throws SQLException, DatabaseException {
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();
    acid.dropAllTables();
    acid.createTable();
    
    AcidRowDataGateway
        acid1 = new AcidRowDataGatewayRDS(99, 2, "acidname1", "acidihabits1"),
        acid2 = new AcidRowDataGatewayRDS(8, 4, "acidname2", "acidihabits2"),
        acid3 = new AcidRowDataGatewayRDS(63, 6, "acidname3", "acidihabits3"),
        acid1_fetch = new AcidRowDataGatewayRDS(99), acid2_fetch = new AcidRowDataGatewayRDS(8),
        acid3_fetch = new AcidRowDataGatewayRDS(63);

    // Tests
    assertEquals("acidname1", acid1_fetch.getName());
    assertEquals("acidname2", acid2_fetch.getName());
    assertEquals("acidname3", acid3_fetch.getName());

    // Drop acid and chemical tables to get rid of anything we added to the database.
    acid.dropAllTables();
  }
  
  @Test
  void testGetInhabits() throws SQLException, DatabaseException {
    ChemicalRowDataGateway resetChem = new ChemicalRowDataGatewayRDS();
    AcidRowDataGateway resetAcid = new AcidRowDataGatewayRDS(),
        acid1 = new AcidRowDataGatewayRDS(56, 2, "acidname1", "acidihabits1"),
        acid2 = new AcidRowDataGatewayRDS(22, 4, "acidname2", "acidihabits2"),
        acid3 = new AcidRowDataGatewayRDS(38, 6, "acidname3", "acidihabits3"),
        acid1_fetch = new AcidRowDataGatewayRDS(56), acid2_fetch = new AcidRowDataGatewayRDS(22),
        acid3_fetch = new AcidRowDataGatewayRDS(38);

    // Tests
    assertEquals("acidihabits1", acid1_fetch.getInhabits());
    assertEquals("acidihabits2", acid2_fetch.getInhabits());
    assertEquals("acidihabits3", acid3_fetch.getInhabits());

    // Drop acid and chemical tables to get rid of anything we added to the database.
    resetAcid.dropAllTables();
  }
  
  @Test
  void testGetSolute() throws SQLException, DatabaseException {
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();
    acid.dropAllTables();
    acid.createTable();
    AcidRowDataGateway
        acid1 = new AcidRowDataGatewayRDS(1, 2, "acidname1", "acidihabits1"),
        acid2 = new AcidRowDataGatewayRDS(2, 4, "acidname2", "acidihabits2"),
        acid3 = new AcidRowDataGatewayRDS(3, 6, "acidname3", "acidihabits3"),
        acid1_fetch = new AcidRowDataGatewayRDS(1), acid2_fetch = new AcidRowDataGatewayRDS(2),
        acid3_fetch = new AcidRowDataGatewayRDS(3);

    // Test
    assertEquals(2, acid1_fetch.getSolute());
    assertEquals(4, acid2_fetch.getSolute());
    assertEquals(6, acid3_fetch.getSolute());

    acid.dropAllTables();
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
      acid = new AcidRowDataGatewayRDS(1);
      fail("");
    } catch(DatabaseException | SQLException e) {
      assertTrue(true); 
    }
  }
  
  @Test
  void testUpdate() throws SQLException, DatabaseException {
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();
    acid.dropAllTables();
    acid.createTable();
    
    AcidRowDataGateway
        acid_setter = new AcidRowDataGatewayRDS(1, 2, "acidname1", "acidinhabits1"),
        acid_getter = new AcidRowDataGatewayRDS(1);
    
    // Test solute
    assertEquals(2, acid_getter.getSolute());
    acid_setter.setSolute(3);
    acid_setter.update();
    acid_getter = new AcidRowDataGatewayRDS(1);
    assertEquals(3, acid_getter.getSolute());
    
    // Test name
    assertEquals("acidname1", acid_getter.getName());
    acid_setter.setName("acidname2");
    acid_setter.update();
    acid_getter = new AcidRowDataGatewayRDS(1);
    assertEquals("acidname2", acid_getter.getName());
    
    // Test inhabits
    assertEquals("acidinhabits1", acid_getter.getInhabits());
    acid_setter.setInhabits("acidinhabits2");
    acid_setter.update();
    acid_getter = new AcidRowDataGatewayRDS(1);
    assertEquals("acidinhabits2", acid_getter.getInhabits());
    
    acid.dropAllTables();
  }
  
  @Test
  void testGetSet() {
    AcidRowDataGateway createAcid = new AcidRowDataGatewayRDS();
    createAcid.dropAllTables();
    ChemicalRowDataGateway createChemical = new ChemicalRowDataGatewayRDS();
    createAcid.createTable();
    AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");
    AcidRowDataGateway acid2 = new AcidRowDataGatewayRDS(2, 15, "chemicalname2", "inhabits2");
    
    AcidRowDataGateway getter = new AcidRowDataGatewayRDS();
    List<AcidRowDataGatewayRDS> acidGet = getter.findSet(15);
    
    assertEquals("chemicalname1", acidGet.get(0).getName());
    assertEquals("chemicalname2", acidGet.get(1).getName());
    
    AcidRowDataGateway acid4 = new AcidRowDataGatewayRDS(4, 32, "chemicalname4", "inhabits4");
    AcidRowDataGateway acid6 = new AcidRowDataGatewayRDS(6, 32, "chemicalname6", "inhabits6");
    
    acidGet = getter.findSet(32);
    
    assertEquals("chemicalname4", acidGet.get(0).getName());
    assertEquals("chemicalname6", acidGet.get(1).getName());

  }
}
