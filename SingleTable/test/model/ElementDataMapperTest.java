package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGatewayRDS;

class ElementDataMapperTest {
  
  /**
   * 
   * @throws Exception
   */
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    ChemicalRowDataGatewayRDS.createTable();
  }

  /**
   * 
   * @throws Exception
   */
  @AfterAll
  static void tearDownAfterClass() throws Exception {
    ChemicalRowDataGatewayRDS.dropTable();
  }

  /**
   * 
   * @throws DomainModelException
   */
  @Test
  void testCreate() throws DomainModelException {
    Element carbon = new ElementDataMapper().create("Carbon", 50, 6, 12.0096);
    System.out.println(carbon.getID() + " : " + carbon.getName());
    assertEquals("Carbon", carbon.getName());
    assertEquals(50, carbon.getInventory());
    assertEquals(6, carbon.getAtomicNumber());
    assertEquals(12.0096, carbon.getAtomicMass(), 0.0001);
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  void testCreateDuplicate() throws DomainModelException {
    try {
      Element oxygen = new ElementDataMapper().create("Oxygen", 70, 8, 15.999);
      System.out.println(oxygen.getID() + " : " + oxygen.getName());
      assertEquals("Oxygen", oxygen.getName());
      assertEquals(70, oxygen.getInventory());
      assertEquals(8, oxygen.getAtomicNumber());
      assertEquals(15.999, oxygen.getAtomicMass(), 0.0001);
      
      Element oxygen2 = new ElementDataMapper().create("Oxygen", 70, 8, 15.999);
      System.out.println(oxygen2.getID() + " : " + oxygen2.getName());
      fail();
    } catch (DomainModelException e) {
      assertTrue(true);
    }
  }
  
  @Test
  void testRead() throws DomainModelException {
    Element nitrogen = new ElementDataMapper().create("Nitrogen", 75, 7, 14.0067);
    System.out.println(nitrogen.getID() + " : " + nitrogen.getName());
    assertEquals("Nitrogen", nitrogen.getName());
    assertEquals(75, nitrogen.getInventory());
    assertEquals(7, nitrogen.getAtomicNumber());
    assertEquals(14.0067, nitrogen.getAtomicMass(), 0.0001);
    
    Element nitrogen2 = new ElementDataMapper().read(nitrogen.getID());
    assertEquals("Nitrogen", nitrogen2.getName());
    assertEquals(75, nitrogen2.getInventory());
    assertEquals(7, nitrogen2.getAtomicNumber());
    assertEquals(14.0067, nitrogen2.getAtomicMass(), 0.0001);
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  void testDelete() throws DomainModelException {
    try {
      Element hydrogen = new ElementDataMapper().create("Hydrogen",34, 1, 1.008);
      System.out.println(hydrogen.getID() + " : " + hydrogen.getName());
      assertEquals("Hydrogen", hydrogen.getName());
      assertEquals(34, hydrogen.getInventory());
      assertEquals(1, hydrogen.getAtomicNumber());
      assertEquals(1.008, hydrogen.getAtomicMass(), 0.0001);
      
      new ElementDataMapper().delete(hydrogen);
      
      Element readHydrogen = new ElementDataMapper().read(hydrogen.getID());
      System.out.println(readHydrogen.getID() + " : " + readHydrogen.getName());
      fail();
    } catch (DomainModelException e) {
      assertTrue(true);
    }
  }

}