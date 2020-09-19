package datasource;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGateway;

class TestChemical extends DatabaseTest {

  @Test
  void testInsert() {
    ChemicalRowDataGateway chemical = new ChemicalRowDataGateway(); 
    chemical.createTableChemcial();
    
    chemical.insertChemical(1, "flub", "mars");
    
    assertEquals("flub", chemical.getName(1));
  }

}
