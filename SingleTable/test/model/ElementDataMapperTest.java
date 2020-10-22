package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import datasource.ChemicalRowDataGateway;
import datasource.DatabaseException;
import datasource.ElementCompoundTableDataGatewayInterface;
import datasource.ElementCompoundTableDataGateway;

public class ElementDataMapperTest {

  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testCreate() throws DomainModelException {
    Element carbon = new ElementDataMapper().read(3);
    assertEquals("Carbon", carbon.getName());
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
    Element nitrogen = new ElementDataMapper().read(4);
    assertEquals("Nitrogen", nitrogen.getName());
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testUpdate() throws DomainModelException {
    try {
      Element iron = new ElementDataMapper().read(8);
      assertEquals("Iron", iron.getName());
      
      iron.setName("Wacky Iron");
      
      new ElementDataMapper().update(iron);
      
      iron = new ElementDataMapper().read(iron.getID());
      
      assertEquals("Wacky Iron", iron.getName());
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
      Element hydrogen = new ElementDataMapper().read(1);
      assertEquals("Hydrogen", hydrogen.getName());
      
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
    List<Element> elements = new ElementDataMapper().getAll();
    assertEquals(12, elements.size());
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testNameLike() throws DomainModelException {
    List<Element> elements = new ElementDataMapper().filterByNameLike("Carbon");
    assertEquals(1, elements.size());
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testInventorySpecificAndRange() throws DomainModelException {
    List<Element> elements = new ElementDataMapper().filterByInventory(3.14);
    assertEquals(1, elements.size());
    
    elements = new ElementDataMapper().filterByInventoryBetween(6, 13);
    assertEquals(3, elements.size());
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testAtomicNumberSpecificAndRange() throws DomainModelException {
    List<Element> elements = new ElementDataMapper().filterByAtomicNumber(6);
    assertEquals(1, elements.size());
    
    elements = new ElementDataMapper().filterByAtomicNumberBetween(6, 8);
    assertEquals(3, elements.size());
  }
  
  /**
   * 
   * @throws DomainModelException
   */
  @Test
  public void testAtomicMassSpecificAndRange() throws DomainModelException {
    List<Element> elements = new ElementDataMapper().filterByAtomicMass(14.007);
    assertEquals(1, elements.size());
    
    elements = new ElementDataMapper().filterByAtomicMassBetween(0, 20);
    assertEquals(5, elements.size());
  }
  
  @Test
  public void testElementsInCompound() throws DomainModelException {
    List<Element> madeOf = new ArrayList<>();
    madeOf.add(new ElementDataMapper().create("Hydrogen",34, 1, 1.008));
    madeOf.add(new ElementDataMapper().create("Oxygen", 70, 8, 15.999));
    Compound water = new CompoundDataMapper().create("Water", 90, madeOf);
    List<Element> inWater = new ElementDataMapper().filterByPartOfCompound(water.getID());
    assertEquals(2, inWater.size());
  }


}
