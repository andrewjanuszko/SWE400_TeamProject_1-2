package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBase {

  /**
   * Test insert
   */
  @Test
  void testInsert() {
    // Create base row data gateway
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
    BaseRowDataGateway base = new BaseRowDataGatewayRDS(); 
    
    // Create base table
    chem.createTableChemcial();
    base.createTableBase();
    
    // Insert base
    base.insert(1, 42, "hydrogen peroxide", "baking soda");
    
    // Test
    assertEquals("hydrogen peroxide", base.getName(1));
    assertEquals(42, base.getSoluteId(1));
    assertEquals("baking soda", base.getInhabits(1));
  }
  
  // Implement after metal is properly implemented (?)
  @Test
  void testGetSolute() {
    fail("Not yet implemented");
  }
  
  @Test
  void testGetName() {
    // Create base row data gateway
    BaseRowDataGateway base = new BaseRowDataGatewayRDS(); 
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    
    // Create base, chemical tables
    base.createTableBase();
    chem.createTableChemcial();
    
    // Insert base
    base.insert(1, 15, "chemicalname1", "inhabits1");
    base.insert(2, 50, "chemicalname2", "inhabits2");
    base.insert(3, 7, "chemicalname3", "inhabits3");
    
    // Test
    assertEquals("chemicalname1", base.getName(1));
    assertEquals("chemicalname2", base.getName(2));
    assertEquals("chemicalname3", base.getName(3));
   }
  
  @Test
  void testGetInhabits() {
 // Create base row data gateway
    AcidRowDataGateway base = new AcidRowDataGatewayRDS(); 
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    
    // Create base, chemical tables
    base.createTableAcid();
    chem.createTableChemcial();
    
    // Insert base
    base.insert(1, 15, "chemicalname1", "inhabits1");
    base.insert(2, 50, "chemicalname2", "inhabits2");
    base.insert(3, 7, "chemicalname3", "inhabits3");
    
    // Test
    assertEquals("inhabits1", base.getInhabits(1));
    assertEquals("inhabits2", base.getInhabits(2));
    assertEquals("inhabits3", base.getInhabits(3));
  }
}
