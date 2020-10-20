package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGatewayRDS;

class MetalDataMapperTest {

  @BeforeEach
  public void setUpBeforeClass() throws Exception {
	  ChemicalRowDataGatewayRDS.createTable();
	  Metal Iron = new MetalDataMapper().create("Iron", 50.0, 26, 55.8, 4);
	  Metal Gold = new MetalDataMapper().create("Gold", 40.0, 79, 197.0, 5);
	  Metal Copper = new MetalDataMapper().create("Copper", 34.0, 29, 63.5, 8);
  }

  @AfterEach
  public void tearDownAfterClass() throws Exception {
	 ChemicalRowDataGatewayRDS.dropTable();
  }

  @Test
  void testCreate() throws DomainModelException {
	  Metal Gallium = new MetalDataMapper().create("Gallium", 80.0, 31, 69.7, 10);
	  assertEquals("Gallium", Gallium.getName());
	  assertEquals(80.0, Gallium.getInventory());
	  assertEquals(31,Gallium.getAtomicNumber());
	  assertEquals(69.7,Gallium.getAtomicMass());
	  assertEquals(10, Gallium.getAcidAmount());
  }
  
  @Test
  void testCreateDuplicate() throws DomainModelException{
	  try {
		  Metal iron2 = new MetalDataMapper().create("Iron", 50.0, 26, 55.8, 4);
	      assertEquals("Iron", iron2.getName());
	      fail();
	    } catch (DomainModelException e) {
	      assertTrue(true);
	    }
  }
  
  @Test
  void testRead() throws DomainModelException{
	  Metal gold =  new MetalDataMapper().read(2);
	  assertEquals("Gold", gold.getName());
	  assertEquals(40.0, gold.getInventory());
	  assertEquals(79, gold.getAtomicNumber());
	  assertEquals(197.0, gold.getAtomicMass());
	  assertEquals(5, gold.getAcidAmount());
  }
  
  @Test
  void testUpdate() throws DomainModelException {
	  Metal unknownMetal = new MetalDataMapper().create("???", 100.0, 1, 2, 1);
	  unknownMetal.setName("Silver");
	  unknownMetal.setInventory(80.0);
	  unknownMetal.setAtomicNumber(47);
	  unknownMetal.setAtomicMass(107.9);
	  unknownMetal.setAcidAmount(6);
	  new MetalDataMapper().update(unknownMetal);
	  Metal silver = new MetalDataMapper().read(unknownMetal.getID());
	  assertEquals("Silver", silver.getName());
	  assertEquals(80.0, silver.getInventory());
	  assertEquals(47, silver.getAtomicNumber());
	  assertEquals(107.9, silver.getAtomicMass());
	  assertEquals(6, silver.getAcidAmount());

  }
  
  @Test
  void testDelete() throws DomainModelException {
	  try {
		  Metal Iron = new MetalDataMapper().read(1);
		  assertEquals("Iron", Iron.getName());
		  new MetalDataMapper().delete(Iron);
		  Iron = new MetalDataMapper().read(1);
		  fail();
	    } catch (DomainModelException e) {
	      assertTrue(true);
	    }
  }
  
  @Test
  void testGetAll() throws DomainModelException {
	  List<Metal> metals = new MetalDataMapper().getAll();
	  assertEquals(3, metals.size());
  }
  
  @Test
  void testNameLike() throws DomainModelException {
	  List<Metal> metals = new MetalDataMapper().filterByNameLike("Gold");
	  assertEquals(1, metals.size());
	  assertEquals("Gold", metals.get(0).getName());
	  assertEquals(40.0, metals.get(0).getInventory());
	  assertEquals(79, metals.get(0).getAtomicNumber());
	  assertEquals(197.0, metals.get(0).getAtomicMass());
	  assertEquals(5, metals.get(0).getAcidAmount());
  
	  metals = new MetalDataMapper().filterByNameLike("o");
	  assertEquals(3, metals.size()); // all metals have an 'o' in them
  }
 
  @Test
  void testInventory() throws DomainModelException {
	  List<Metal> metals = new MetalDataMapper().filterByInventory(40);
	  assertEquals(1, metals.size());
	  assertEquals("Gold", metals.get(0).getName());
	  assertEquals(40.0, metals.get(0).getInventory());
	  assertEquals(79, metals.get(0).getAtomicNumber());
	  assertEquals(197.0, metals.get(0).getAtomicMass());
	  assertEquals(5, metals.get(0).getAcidAmount());
	  
	  metals = new MetalDataMapper().filterByInventoryBetween(0, 45);
	  assertEquals(2, metals.size());
	  assertEquals("Gold", metals.get(0).getName());
	  assertEquals("Copper", metals.get(1).getName());
  }
  
  @Test
  void testAtomicNumber() throws DomainModelException {
	  List<Metal> metals = new MetalDataMapper().filterByAtomicNumber(26);
	  assertEquals(1, metals.size());
	  assertEquals("Iron", metals.get(0).getName());
	  assertEquals(50.0, metals.get(0).getInventory());
	  assertEquals(26, metals.get(0).getAtomicNumber());
	  assertEquals(55.8, metals.get(0).getAtomicMass());
	  assertEquals(4, metals.get(0).getAcidAmount());
	  
	  metals = new MetalDataMapper().filterByAtomicNumberBetween(25, 30);
	  assertEquals(2, metals.size());
	  assertEquals("Iron", metals.get(0).getName());
	  assertEquals("Copper", metals.get(1).getName());
  }
  
  @Test
  void testAtomicMass() throws DomainModelException {
	  List<Metal> metals = new MetalDataMapper().filterByAtomicMass(63.5);
	  assertEquals(1, metals.size());
	  assertEquals("Copper", metals.get(0).getName());
	  assertEquals(34.0, metals.get(0).getInventory());
	  assertEquals(29, metals.get(0).getAtomicNumber());
	  assertEquals(63.5, metals.get(0).getAtomicMass());
	  assertEquals(8, metals.get(0).getAcidAmount());
	  
	  metals = new MetalDataMapper().filterByAtomicMassBetween(0, 100);
	  assertEquals(2, metals.size());
	  assertEquals("Iron", metals.get(0).getName());
	  assertEquals("Copper", metals.get(1).getName());
  }
  
  @Test
  void testAcidAmount() throws DomainModelException {
	  List<Metal> metals = new MetalDataMapper().filterByAcidAmount(8);
	  assertEquals(1, metals.size());
	  assertEquals("Copper", metals.get(0).getName());
	  assertEquals(34.0, metals.get(0).getInventory());
	  assertEquals(29, metals.get(0).getAtomicNumber());
	  assertEquals(63.5, metals.get(0).getAtomicMass());
	  assertEquals(8, metals.get(0).getAcidAmount());
	  
	  metals = new MetalDataMapper().filterByAcidAmountBetween(5, 10);
	  assertEquals("Gold", metals.get(0).getName());
	  assertEquals("Copper", metals.get(1).getName());
  }
  
  @Test
  void testDissolvedBy() throws DomainModelException {
	 
  }
  
  @Test
  void testPartOfCompund() throws DomainModelException {
	 
  }

  @Test
  void testLowInventory() throws DomainModelException {
	  Metal gold =  new MetalDataMapper().read(2);
	  gold.setInventory(10);
	  new MetalDataMapper().update(gold);
	  
	  List<Metal> metals = new MetalDataMapper().filterByLowInventory();
	  assertEquals(1, metals.size());
	  assertEquals("Gold", metals.get(0).getName());
  }

}
