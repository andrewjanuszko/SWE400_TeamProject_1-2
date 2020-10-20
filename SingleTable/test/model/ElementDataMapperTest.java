package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import datasource.ChemicalRowDataGatewayRDS;
import datasource.DatabaseException;

public class ElementDataMapperTest {
  
  /**
   * 
   * @throws Exception
   */
  @BeforeEach
  public void beforeEach() throws DatabaseException {
    ChemicalRowDataGatewayRDS.createTable();
  }

  /**
   * 
   * @throws Exception
   */
  @AfterEach
  public void afterEach() throws DatabaseException {
    ChemicalRowDataGatewayRDS.dropTable();
  }

  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testCreate() throws DomainModelException {
    Element carbon = new ElementDataMapper().create("Carbon", 50, 6, 12.0096);
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
  public void testCreateDuplicate() throws DomainModelException {
    try {
      Element oxygen = new ElementDataMapper().create("Oxygen", 70, 8, 15.999);
      assertEquals("Oxygen", oxygen.getName());
      assertEquals(70, oxygen.getInventory());
      assertEquals(8, oxygen.getAtomicNumber());
      assertEquals(15.999, oxygen.getAtomicMass(), 0.0001);
      
      Element oxygen2 = new ElementDataMapper().create("Oxygen", 70, 8, 15.999);
      assertEquals("Oxygen", oxygen2.getName());
      fail();
    } catch (DomainModelException e) {
      assertTrue(true);
    }
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testRead() throws DomainModelException {
    Element nitrogen = new ElementDataMapper().create("Nitrogen", 75, 7, 14.0067);
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
  public void testUpdate() throws DomainModelException {
    try {
      Element lead = new ElementDataMapper().create("Lead", 621, 82, 207.2);
      assertEquals("Lead", lead.getName());
      assertEquals(621, lead.getInventory());
      assertEquals(82, lead.getAtomicNumber());
      assertEquals(207.2, lead.getAtomicMass(), 0.001);
      
      lead.setName("Lead-207");
      lead.setInventory(90210);
      lead.setAtomicMass(206.976);
      
      new ElementDataMapper().update(lead);
      
      lead = new ElementDataMapper().read(lead.getID());
      
      assertEquals("Lead-207", lead.getName());
      assertEquals(90210, lead.getInventory());
      assertEquals(82, lead.getAtomicNumber());
      assertEquals(206.976, lead.getAtomicMass(), 0.001);
    } catch (DomainModelException e) {
      System.out.println(e);
      fail();
    }
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testDelete() throws DomainModelException {
    try {
      Element hydrogen = new ElementDataMapper().create("Hydrogen",34, 1, 1.008);
      assertEquals("Hydrogen", hydrogen.getName());
      assertEquals(34, hydrogen.getInventory());
      assertEquals(1, hydrogen.getAtomicNumber());
      assertEquals(1.008, hydrogen.getAtomicMass(), 0.0001);
      
      new ElementDataMapper().delete(hydrogen);
      
      Element readHydrogen = new ElementDataMapper().read(hydrogen.getID());
      assertEquals("Hydrogen", readHydrogen.getName());
      fail();
    } catch (DomainModelException e) {
      assertTrue(true);
    }
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testGetAll() throws DomainModelException {
    Element hydrogen = new ElementDataMapper().create("Hydrogen",34, 1, 1.008);
    assertEquals("Hydrogen", hydrogen.getName());
    Element lead = new ElementDataMapper().create("Lead", 621, 82, 207.2);
    assertEquals("Lead", lead.getName());
    Element nitrogen = new ElementDataMapper().create("Nitrogen", 75, 7, 14.0067);
    assertEquals("Nitrogen", nitrogen.getName());
    Element oxygen = new ElementDataMapper().create("Oxygen", 70, 8, 15.999);
    assertEquals("Oxygen", oxygen.getName());
    Element carbon = new ElementDataMapper().create("Carbon", 50, 6, 12.0096);
    assertEquals("Carbon", carbon.getName());
    
    List<Element> elements = new ElementDataMapper().getAll();
    assertEquals(5, elements.size());
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testNameLike() throws DomainModelException {
    Element hydrogen = new ElementDataMapper().create("Hydrogen",34, 1, 1.008);
    assertEquals("Hydrogen", hydrogen.getName());
    Element lead = new ElementDataMapper().create("Lead", 621, 82, 207.2);
    assertEquals("Lead", lead.getName());
    Element nitrogen = new ElementDataMapper().create("Nitrogen", 75, 7, 14.0067);
    assertEquals("Nitrogen", nitrogen.getName());
    Element oxygen = new ElementDataMapper().create("Oxygen", 70, 8, 15.999);
    assertEquals("Oxygen", oxygen.getName());
    Element carbon = new ElementDataMapper().create("Carbon", 50, 6, 12.0096);
    assertEquals("Carbon", carbon.getName());
    Element carbon13 = new ElementDataMapper().create("Carbon-13", 10, 6, 13.0033);
    assertEquals("Carbon-13", carbon13.getName());
    
    List<Element> elements = new ElementDataMapper().filterByNameLike("Carbon");
    assertEquals(2, elements.size());
  }
  
  @Test
  public void testInventorySpecificAndRange() throws DomainModelException {
    Element hydrogen = new ElementDataMapper().create("Hydrogen",34, 1, 1.008);
    assertEquals("Hydrogen", hydrogen.getName());
    Element lead = new ElementDataMapper().create("Lead", 621, 82, 207.2);
    assertEquals("Lead", lead.getName());
    Element nitrogen = new ElementDataMapper().create("Nitrogen", 75, 7, 14.0067);
    assertEquals("Nitrogen", nitrogen.getName());
    Element oxygen = new ElementDataMapper().create("Oxygen", 70, 8, 15.999);
    assertEquals("Oxygen", oxygen.getName());
    Element carbon = new ElementDataMapper().create("Carbon", 50, 6, 12.0096);
    assertEquals("Carbon", carbon.getName());
    Element carbon13 = new ElementDataMapper().create("Carbon-13", 10, 6, 13.0033);
    assertEquals("Carbon-13", carbon13.getName());
    
    List<Element> elements = new ElementDataMapper().filterByInventory(621);
    assertEquals(1, elements.size());
    
    elements = new ElementDataMapper().filterByInventoryBetween(70, 75);
    assertEquals(2, elements.size());
    
  }

}
