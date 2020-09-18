package ClassTable.test.datasource;


import org.junit.Test;

import ClassTable.src.datasource.ChemicalRowDataGateway;

class TestChemical extends DatabaseTest {

  @Test
  void testInsert() {
    ChemicalRowDataGateway chemical = new ChemicalRowDataGateway(); 
    chemical.createTableChemcial();
    
    chemical.insertChemical(1, "flub", "mars");
    
    assertEquals("flub", chemical.getName(1));
  }

}
