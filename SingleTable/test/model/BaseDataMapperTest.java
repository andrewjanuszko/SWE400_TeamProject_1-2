package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class BaseDataMapperTest {

  @Test
  void testCreate() throws DomainModelException {
	  Base lithiumHydroxide = new BaseDataMapper().read(29);
	  assertEquals("Lithium Hydroxide", lithiumHydroxide.getName()); 
  }
  
  @Test
  void testCreateDuplicate() throws DomainModelException{
	  try {
		  Base calciumHydroxide2 = new BaseDataMapper().create("Calcium Hydroxide", 52.3, 0);
	      assertEquals("Calcium Hydroxide", calciumHydroxide2.getName());
	      fail();
	    } catch (DomainModelException e) {
	      assertTrue(true);
	    }
  }
  
  @Test
  void testRead() throws DomainModelException{
	  Base potassiumHydroxide =  new BaseDataMapper().read(2);
	  assertEquals("Potassium Hydroxide", potassiumHydroxide.getName());
	  assertEquals(47.0, potassiumHydroxide.getInventory());
	  assertEquals(0, potassiumHydroxide.getSolute());
  }
  
  @Test
  void testUpdate() throws DomainModelException {
	  Base unknownBase = new BaseDataMapper().create("???", 50, 0);
	  unknownBase.setInventory(45);
	  unknownBase.setName("Basic Base");
	  unknownBase.setSolute(9);
	  new BaseDataMapper().update(unknownBase);
	  unknownBase = new BaseDataMapper().read(unknownBase.getID());
	  
	  assertEquals("Basic Base", unknownBase.getName());
	  assertEquals(45, unknownBase.getInventory());
	  assertEquals(9,unknownBase.getSolute());
  }
  
  @Test
  void testDelete() throws DomainModelException {
	  try {
		  Base calciumHydroxide = new BaseDataMapper().read(1);
		  assertEquals("Calcium Hydroxide", calciumHydroxide.getName());
		  new BaseDataMapper().delete(calciumHydroxide);
		  calciumHydroxide = new BaseDataMapper().read(1);
		  fail();
	    } catch (DomainModelException e) {
	      assertTrue(true);
	    }
  }
  
  @Test
  void testGetAll() throws DomainModelException {
	  List<Base> bases = new BaseDataMapper().getAll();
	  assertEquals(3, bases.size());
  }
  
  @Test
  void testNameLike() throws DomainModelException {
	  List<Base> bases = new BaseDataMapper().filterByNameLike("Potassium Hydroxide");
	  assertEquals(1, bases.size());
	  assertEquals("Potassium Hydroxide", bases.get(0).getName());
	  assertEquals(47.0, bases.get(0).getInventory());
	  assertEquals(0, bases.get(0).getSolute());
	  bases = new BaseDataMapper().filterByNameLike("xide");
	  assertEquals(3, bases.size());
  }
 
  @Test
  void testInventory() throws DomainModelException {
	  List<Base> bases = new BaseDataMapper().filterByInventory(52.3);
	  assertEquals(1, bases.size());
	  assertEquals("Calcium Hydroxide", bases.get(0).getName());
	  
	  bases = new BaseDataMapper().filterByInventoryBetween(0, 50);
	  assertEquals(2, bases.size());
	  assertEquals("Potassium Hydroxide", bases.get(0).getName());
	  assertEquals("Rubidium Hydroxide", bases.get(1).getName());
  }
  
  @Test
  void testSolute() throws DomainModelException {
	  List<Base> bases = new BaseDataMapper().filterBySolute(0);
	  assertEquals(3, bases.size());
	  assertEquals("Calcium Hydroxide", bases.get(0).getName());
	  assertEquals("Potassium Hydroxide", bases.get(1).getName());
	  assertEquals("Rubidium Hydroxide", bases.get(2).getName());
  }
  
  @Test
  void testLowInventory() throws DomainModelException {
	  List<Base> bases = new BaseDataMapper().filterByLowInventory();
	  assertEquals(1, bases.size());
	  assertEquals("Rubidium Hydroxide", bases.get(0).getName());
  }
}
