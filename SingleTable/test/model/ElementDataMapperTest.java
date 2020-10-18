package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGatewayRDS;

class ElementDataMapperTest {

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    ChemicalRowDataGatewayRDS.createTable();
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
    ChemicalRowDataGatewayRDS.dropTable();
  }

  @Test
  void testCreate() throws DomainModelException {
    Element carbon = new ElementDataMapper().create("Carbon", 50, 6, 12.0096);
    System.out.println(carbon.getID() + " : " + carbon.getName());
    assertEquals("Carbon", carbon.getName());
    assertEquals(50, carbon.getInventory());
    assertEquals(6, carbon.getAtomicNumber());
    assertEquals(12.0096, carbon.getAtomicMass(), 0.0001);
  }
  
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

}
