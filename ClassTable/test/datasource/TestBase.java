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
class TestBase extends DatabaseTest {

  @Test
  void testGetName() throws SQLException, DatabaseException {
    BaseRDG base = new BaseRDGRDS();
    ChemicalRDG chem = new ChemicalRDGRDS();
    
    base.dropAllTables();
    chem.createTable();
    base.createTable();
    
    BaseRDG
        base1 = new BaseRDGRDS(1, 2, "basename1", "baseinhabits1"),
        base2 = new BaseRDGRDS(2, 4, "basename2", "baseinhabits2"),
        base3 = new BaseRDGRDS(3, 6, "basename3", "baseinhabits3"),
        base1_fetch = new BaseRDGRDS(1), base2_fetch = new BaseRDGRDS(2),
        base3_fetch = new BaseRDGRDS(3);

    // Testing to see if new gateways can properly fetch
    assertEquals("basename1", base1_fetch.getName());
    assertEquals("basename2", base2_fetch.getName());
    assertEquals("basename3", base3_fetch.getName());

    base.dropAllTables();
  }
  
  @Test
  void testGetInhabits() throws SQLException, DatabaseException {
    BaseRDG initialize = new BaseRDGRDS(),
        base1 = new BaseRDGRDS(1, 2, "basename1", "baseihabits1"),
        base2 = new BaseRDGRDS(2, 4, "basename2", "baseihabits2"),
        base3 = new BaseRDGRDS(3, 6, "basename3", "baseihabits3"),
        base1_fetch = new BaseRDGRDS(1), base2_fetch = new BaseRDGRDS(2),
        base3_fetch = new BaseRDGRDS(3);

    // Testing to see if new gateways can properly fetch
    assertEquals("baseihabits1", base1_fetch.getInhabits());
    assertEquals("baseihabits2", base2_fetch.getInhabits());
    assertEquals("baseihabits3", base3_fetch.getInhabits());

    initialize.dropAllTables();
  }
  
  @Test
  void testGetSolute() throws SQLException, DatabaseException {
    BaseRDG initialize = new BaseRDGRDS(),
        base1 = new BaseRDGRDS(1, 2, "basename1", "baseihabits1"),
        base2 = new BaseRDGRDS(2, 4, "basename2", "baseihabits2"),
        base3 = new BaseRDGRDS(3, 6, "basename3", "baseihabits3"),
        base1_fetch = new BaseRDGRDS(1), base2_fetch = new BaseRDGRDS(2),
        base3_fetch = new BaseRDGRDS(3);

    // Testing to see if new gateways can properly fetch
    assertEquals(2, base1_fetch.getSolute());
    assertEquals(4, base2_fetch.getSolute());
    assertEquals(6, base3_fetch.getSolute());

    initialize.dropAllTables();
  }
  
  @Test
  void testUpdate() throws SQLException, DatabaseException {
    BaseRDG initialize = new BaseRDGRDS();
    initialize.dropAllTables();
    
    BaseRDG
        base_setter = new BaseRDGRDS(1, 2, "basename1", "baseinhabits1"),
        base_getter = new BaseRDGRDS(1);
    
    // Test solute
    assertEquals(2, base_getter.getSolute());
    base_setter.setSolute(3);
    base_setter.update();
    base_getter = new BaseRDGRDS(1);
    assertEquals(3, base_getter.getSolute());
    
    // Test name
    assertEquals("basename1", base_getter.getName());
    base_setter.setName("basename2");
    base_setter.update();
    base_getter = new BaseRDGRDS(1);
    assertEquals("basename2", base_getter.getName());
    
    // Test inhabits
    assertEquals("baseinhabits1", base_getter.getInhabits());
    base_setter.setInhabits("baseinhabits2");
    base_setter.update();
    base_getter = new BaseRDGRDS(1);
    assertEquals("baseinhabits2", base_getter.getInhabits());
    
    initialize.dropAllTables();
  }
  
  @Test
  void testDelete() {
    BaseRDG createBase = new BaseRDGRDS(),
        base = new BaseRDGRDS(1, 2, "chemname1", "cheminhabits1");
    
    // Ensure it has been added 
    assertEquals("chemname1", base.getName());
    assertEquals("cheminhabits1", base.getInhabits());
    
    base.delete();
    
    try { 
      base = new BaseRDGRDS(1);
      fail("");
    } catch(DatabaseException | SQLException e) {
      assertTrue(true); 
    }
    
    createBase.dropAllTables();
  }
  
  @Test
  void testGetSet() {
    BaseRDG createBase = new BaseRDGRDS();
    createBase.dropAllTables();
    ChemicalRDG createChemical = new ChemicalRDGRDS();
    BaseRDG acid1 = new BaseRDGRDS(1, 15, "chemicalname1", "inhabits1");
    BaseRDG acid2 = new BaseRDGRDS(2, 15, "chemicalname2", "inhabits2");
    
    BaseRDG getter = new BaseRDGRDS();
    List<BaseRDGRDS> acidGet = getter.findSet(15);
    
    assertEquals("chemicalname1", acidGet.get(0).getName());
    assertEquals("chemicalname2", acidGet.get(1).getName());
    
    BaseRDG acid4 = new BaseRDGRDS(4, 32, "chemicalname4", "inhabits4");
    BaseRDG acid6 = new BaseRDGRDS(6, 32, "chemicalname6", "inhabits6");
    
    acidGet = getter.findSet(32);
    
    assertEquals("chemicalname4", acidGet.get(0).getName());
    assertEquals("chemicalname6", acidGet.get(1).getName());

    createBase.dropAllTables();
  }
}
