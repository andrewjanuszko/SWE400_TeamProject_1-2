package datasource;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGateway;

class TestChemical extends DatabaseTest {

  @Test
  void testInsert() {
    ChemicalRowDataGateway chemical = new ChemicalRowDataGatewayRDS(); 
    chemical.createTableChemcial();
    
    chemical.insert(1, "flub", "mars");
    
    // Test that name was inserted properly
    assertEquals("flub", chemical.getName(1));
    
    // Test that inhabits was inserted properly
    assertEquals("mars", chemical.getInhabits(1));
  }
  
  @Test
  void getName() {
    ChemicalRowDataGateway chemical = new ChemicalRowDataGatewayRDS(); 
    chemical.createTableChemcial();
    
    // Inserts
    chemical.insert(15, "monopoly", "camel");
    chemical.insert(10, "hand sanitizer", "phone");
    chemical.insert(99, "battery", "september");
    
    // Tests
    assertEquals("monopoly", chemical.getName(15));
    assertEquals("hand sanitizer", chemical.getName(10));
    assertEquals("battery", chemical.getName(99));
  }
  
  @Test
  void getInhabits() {
    ChemicalRowDataGateway chemical = new ChemicalRowDataGatewayRDS(); 
    chemical.createTableChemcial();
    
    // Inserts
    chemical.insert(15, "monopoly", "camel");
    chemical.insert(10, "hand sanitizer", "phone");
    chemical.insert(99, "battery", "september");
    
    // Tests
    assertEquals("camel", chemical.getInhabits(15));
    assertEquals("phone", chemical.getInhabits(10));
    assertEquals("september", chemical.getInhabits(99));
  }

}
