package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAcid {
  
  /**
   * Test insert
   */
  @Test
  void testInsert() {
    // Create acid row data gateway
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS(); 
    
    // Create acid table
    chem.createTableChemcial();
    acid.createTableAcid();
    
    // Insert chemical first
    chem.insert(1, "hydrogen peroxide", "baking soda");
    acid.insert(1, 42);
    
    // Test
    assertEquals("hydrogen peroxide", acid.getName(1));
    assertEquals(42, acid.getSoluteId(1));
    assertEquals("baking soda", acid.getInhabits(1));
  }
  
  // Implement after metal is properly implemented (?)
  @Test
  void testGetSolute() {
    fail("Not yet implemented");
  }
  
  @Test
  void testGetName() {
    // Create acid row data gateway
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS(); 
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    
    // Create acid, chemical tables
    acid.createTableAcid();
    chem.createTableChemcial();
    
    // Insert chemical 
    chem.insert(1, "chemicalname1", "inhabits1");
    chem.insert(2, "chemicalname2", "inhabits2");
    chem.insert(3, "chemicalname3", "inhabits3");
    
    // Insert acid
    acid.insert(1, 15);
    acid.insert(2, 50);
    acid.insert(3, 7);
    
    // Test
    assertEquals("chemicalname1", acid.getName(1));
    assertEquals("chemicalname2", acid.getName(2));
    assertEquals("chemicalname3", acid.getName(3));
   }
  
  @Test
  void testGetInhabits() {
 // Create acid row data gateway
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS(); 
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    
    // Create acid, chemical tables
    acid.createTableAcid();
    chem.createTableChemcial();
    
    // Insert chemical 
    chem.insert(1, "chemicalname1", "inhabits1");
    chem.insert(2, "chemicalname2", "inhabits2");
    chem.insert(3, "chemicalname3", "inhabits3");
    
    // Insert acid
    acid.insert(1, 15);
    acid.insert(2, 50);
    acid.insert(3, 7);
    
    // Test
    assertEquals("inhabits1", acid.getInhabits(1));
    assertEquals("inhabits2", acid.getInhabits(2));
    assertEquals("inhabits3", acid.getInhabits(3));
  }

}
