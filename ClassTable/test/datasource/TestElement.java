package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author kimberlyoneill
 *
 */
class TestElement extends DatabaseTest {

  
  @Test
  void testGetAtomicNumber() {
    ChemicalRDG chem = new ChemicalRDGRDS(); 
    ElementRDG ele = new ElementRDGRDS();
    ele.dropAllTables();
    chem.createTable();
    ele.createTableElement();
    
    //initialize element
    ElementRDG element = new ElementRDGRDS(1, 55, 123, "chemicalname1", 1.0);
    
    //element getter
    ElementRDG elementGet = new ElementRDGRDS(1);
    
    assertEquals(55, elementGet.getAtomicNumber());
  }
  
  @Test
  void testGetAtomicMass() {
    ChemicalRDG chem = new ChemicalRDGRDS(); 
    ElementRDG ele = new ElementRDGRDS();
    ele.dropAllTables();
    chem.createTable();
    ele.createTableElement();
    
    ElementRDG element = new ElementRDGRDS(1, 55, 123, "chemicalname1", 1.0);
    
    ElementRDG elementGet = new ElementRDGRDS(1);
    
    assertEquals(123, elementGet.getAtomicMass(), .01);
  }
  
  @Test
  void testGetName() {
    ChemicalRDG chem = new ChemicalRDGRDS(); 
    ElementRDG ele = new ElementRDGRDS();
    ele.dropAllTables();
    chem.createTable();
    ele.createTableElement();
    
    ElementRDG element = new ElementRDGRDS(1, 55, 123, "chemicalname1", 1.0);
    
    ElementRDG elementGet = new ElementRDGRDS(1);
    
    assertEquals("chemicalname1", elementGet.getName());
  }
  
  @Test
  void testGetInhabits() {
    ChemicalRDG chem = new ChemicalRDGRDS(); 
    ElementRDG ele = new ElementRDGRDS();
    ele.dropAllTables();
    chem.createTable();
    ele.createTableElement();
    
    ElementRDG element = new ElementRDGRDS(1, 15, 18, "chemicalname1", 1.0);
    
    ElementRDG elementGet = new ElementRDGRDS(1);
    
    // Test
    assertEquals(1.0, elementGet.getInventory());
  }

}
