package datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dataDTO.ChemicalDTO;
import dataENUM.ChemicalEnum;

class ChemicalRowDataGatewayTest extends DatabaseTest {
	

	ChemicalRowDataGatewayRDS chemicalTable;
	
	public void createTable() throws DatabaseException {
		chemicalTable = new ChemicalRowDataGatewayRDS();
	}
	
	public void fillTable() throws DatabaseException {
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011);
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Hydrogen", "The Moon", 1, 1.008);
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Oxygen", "Mercury", 8, 15.999);
		chemicalTable = new ChemicalRowDataGatewayRDS(4, "Sucrose", "Earth", 1);
		chemicalTable = new ChemicalRowDataGatewayRDS(4, "Potassium Hydroxide", "The Moon", 1);
		chemicalTable = new ChemicalRowDataGatewayRDS(4, "Lithium Hydroxide", "Mars", 2);
		chemicalTable = new ChemicalRowDataGatewayRDS(5, "Hydrochloric Acid", "Earth", 3);
		chemicalTable = new ChemicalRowDataGatewayRDS(5, "Sulphuric Acid", "The Moon", 3);
		chemicalTable = new ChemicalRowDataGatewayRDS(5, "Nitric Acid", "Mars", 2);
		chemicalTable = new ChemicalRowDataGatewayRDS(2, "Zinc", "Mercury", 30, 65.380, 7);
		chemicalTable = new ChemicalRowDataGatewayRDS(2, "Copper", "The Moon", 29, 63.546, 8);
		chemicalTable = new ChemicalRowDataGatewayRDS(2, "Chromium", "Earth", 24, 51.996, 9);
		ArrayList<Integer> elements = new ArrayList<Integer>();
		elements.add(1);
		elements.add(2);
		elements.add(3);
		chemicalTable = new ChemicalRowDataGatewayRDS(3, "Sugar", "Mercury", elements);
	}

	@Test
	void testCreateAndFill() throws DatabaseException {
		createTable();
		fillTable();
	}
	
	/**
	 * Test the creation of a table and inserting into it.
	 * @throws DatabaseException
	 */
	@Test
	void testCreateAndInsert() throws DatabaseException {
//		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
//		chemicalTable = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon");
//		ArrayList<ChemicalDTO> results = gateway.fetchByName("UnknownChemical");
//		assertEquals("UnknownChemical", results.get(0).getName());
		
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		chemicalTable = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon");
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		assertEquals(1, chemicalTable.getID());
		assertEquals(0, chemicalTable.getType());
		assertEquals("UnknownChemical", chemicalTable.getName());
		assertEquals("The Moon", chemicalTable.getInhabits());
	}
	
	/**
	 * Test if drop table works.
	 * @throws DatabaseException
	 */
	@Test
	void testDrop() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011);
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		assertEquals(1, chemicalTable.getID());
		assertEquals(1, chemicalTable.getType());
		assertEquals("Carbon", chemicalTable.getName());
		assertEquals(6, chemicalTable.getAtomicNumber());
		assertEquals(12.011, chemicalTable.getAtomicMass(), 0.001);
		
		chemicalTable = new ChemicalRowDataGatewayRDS();

		try {
			chemicalTable = new ChemicalRowDataGatewayRDS(1);
			fail();
		} catch(DatabaseException e) {
			assertTrue(true);
		}					
	}
	
	/**
	 * Test dropping a chemical from the table.
	 * @throws DatabaseException
	 */
	@Test
	void testDelete() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon");
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		chemicalTable.delete();
		
		try {
			chemicalTable = new ChemicalRowDataGatewayRDS(1);
			fail();
		} catch(DatabaseException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test updating an entry in the database.
	 * @throws DatabaseException
	 */
	@Test
	void testUpdate() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011);
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		assertEquals("Carbon", chemicalTable.getName());
		assertEquals(12.011, chemicalTable.getAtomicMass(), 0.001);
		
		chemicalTable.setName("Carbon-13");
		chemicalTable.setAtomicMass(13.003);
		chemicalTable.update();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		assertEquals("Carbon-13", chemicalTable.getName());
		assertEquals(13.003, chemicalTable.getAtomicMass(), 0.001);
	}
	
//	@Test
//	void testGetAllBases() throws DatabaseException {
//		
//		
//
//		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011);
//		
//		ArrayList<ChemicalDTO> results = gateway.fetchBases();
//		assertFalse(results.isEmpty());
//		for (ChemicalDTO base : results) {
//			assertEquals(4, base.getType());
//		}
//
//	}
	
	@Test
	void createMetal() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		chemicalTable = new ChemicalRowDataGatewayRDS(5, "acid", "Earth", 6);
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		chemicalTable = new ChemicalRowDataGatewayRDS(2, "Iron", "Earth", 1, 2.0, 1);
	}

}