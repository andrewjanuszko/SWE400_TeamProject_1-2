package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBase {

  @Test
  void testGetName() {
    BaseRowDataGateway initialize = new BaseRowDataGatewayRDS(),
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
  
  
  
  
  
  
//  /**
//   * Test insert
//   */
//  @Test
//  void testInsert() {
//    // Create base row data gateway
//    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
//    BaseRowDataGateway base = new BaseRowDataGatewayRDS(); 
//    
//    // Create base table
//    chem.createTableChemcial();
//    base.createTableBase();
//    
//    // Insert base
//    base.insert(1, 42, "hydrogen peroxide", "baking soda");
//    
//    // Test
//    assertEquals("hydrogen peroxide", base.getName(1));
//    assertEquals(42, base.getSoluteId(1));
//    assertEquals("baking soda", base.getInhabits(1));
//  }
//  
//  // Implement after metal is properly implemented (?)
//  @Test
//  void testGetSolute() {
//    fail("Not yet implemented");
//  }
//  
//  @Test
//  void testGetName() {
//    // Create base row data gateway
//    BaseRowDataGateway base = new BaseRowDataGatewayRDS(); 
//    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
//    
//    // Create base, chemical tables
//    base.createTableBase();
//    chem.createTableChemcial();
//    
//    // Insert base
//    base.insert(1, 15, "chemicalname1", "inhabits1");
//    base.insert(2, 50, "chemicalname2", "inhabits2");
//    base.insert(3, 7, "chemicalname3", "inhabits3");
//    
//    // Test
//    assertEquals("chemicalname1", base.getName(1));
//    assertEquals("chemicalname2", base.getName(2));
//    assertEquals("chemicalname3", base.getName(3));
//   }
//  
//  @Test
//  void testGetInhabits() {
// // Create base row data gateway
//    AcidRowDataGateway base = new AcidRowDataGatewayRDS(); 
//    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
//    
//    // Create base, chemical tables
//    base.createTableAcid();
//    chem.createTableChemcial();
//    
//    // Insert base
//    base.insert(1, 15, "chemicalname1", "inhabits1");
//    base.insert(2, 50, "chemicalname2", "inhabits2");
//    base.insert(3, 7, "chemicalname3", "inhabits3");
//    
//    // Test
//    assertEquals("inhabits1", base.getInhabits(1));
//    assertEquals("inhabits2", base.getInhabits(2));
//    assertEquals("inhabits3", base.getInhabits(3));
//  }
}
