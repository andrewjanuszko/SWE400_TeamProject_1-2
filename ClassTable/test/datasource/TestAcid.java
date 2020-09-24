package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAcid {
  
  // Implement after metal is properly implemented (?)
  @Test
  void testGetSolute() {
    fail("Not yet implemented");
  }
  
  @Test
  void testGetName() {
    AcidRowDataGateway insertAcid1, insertAcid2, insertAcid3;
    
    
    insertAcid1 = new AcidRowDataGatewayRDS(1, 2, "acidname1", "acidihabits1");
    insertAcid2 = new AcidRowDataGatewayRDS(2, 4, "acidname2", "acidihabits2");
    insertAcid3 = new AcidRowDataGatewayRDS(3, 6, "acidname3", "acidihabits3");
    
    
    
    
  }
  
//  @Test
//  void testGetName() {
//    // Create acid row data gateway
//    AcidRowDataGateway acid = new AcidRowDataGatewayRDS(); 
//    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
//    
//    // Create acid, chemical tables
//    acid.createTableAcid();
//    chem.createTableChemcial();
//    
//    // Insert acid
//    acid.insert(1, 15, "chemicalname1", "inhabits1");
//    acid.insert(2, 50, "chemicalname2", "inhabits2");
//    acid.insert(3, 7, "chemicalname3", "inhabits3");
//    
//    // Test
//    assertEquals("chemicalname1", acid.getName(1));
//    assertEquals("chemicalname2", acid.getName(2));
//    assertEquals("chemicalname3", acid.getName(3));
//   }
  
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
    acid.insert(1, 15, "chemicalname1", "inhabits1");
    acid.insert(2, 50, "chemicalname2", "inhabits2");
    acid.insert(3, 7, "chemicalname3", "inhabits3");
    
    // Test
    assertEquals("inhabits1", acid.getInhabits(1));
    assertEquals("inhabits2", acid.getInhabits(2));
    assertEquals("inhabits3", acid.getInhabits(3));
  }

}
