package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestMetal {
  

  @Test
  void testGetName() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS();
    
    chem.createTableChemcial();
    metal.createTableMetal();
    
    metal.insert(1, 55, "chemicalname1", "inhabits1");
    metal.insert(2, 2, "chemicalname2", "inhabits2");
    
    assertEquals("chemicalname1", metal.getName(1));
    assertEquals("chemicalname2", metal.getName(2));  
  }
  
  @Test
  void testGetInhabits() {
    // Create row data gateways
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS();
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();
    
    // Create tables
    chem.createTableChemcial();
    acid.createTableAcid(); 
    metal.createTableMetal();
    
    // Insert metal
    metal.insert(1, 15, "chemicalname1", "inhabits1");
    metal.insert(2, 50, "chemicalname2", "inhabits2");
    metal.insert(3, 7, "chemicalname3", "inhabits3");
    
    // Test
    assertEquals("inhabits1", metal.getInhabits(1));
    assertEquals("inhabits2", metal.getInhabits(2));
    assertEquals("inhabits3", metal.getInhabits(3));
  }
  
  @Test
  void testGetDissolvedBy() {
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS();
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(); 
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();
    
    // Create tables
    chem.createTableChemcial();
    acid.createTableAcid(); 
    metal.createTableMetal();
    
    // Insert metal
    metal.insert(1, 15, "chemicalname1", "inhabits1");
    metal.insert(2, 50, "chemicalname2", "inhabits2");
    metal.insert(3, 7, "chemicalname3", "inhabits3");
    
    // Test
    assertEquals(15, metal.getDissolvedBy(1));
    assertEquals(50, metal.getDissolvedBy(2));
    assertEquals(7, metal.getDissolvedBy(3));
  }
  
}
