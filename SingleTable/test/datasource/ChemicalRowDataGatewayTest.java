package datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dataENUM.ChemicalEnum;

class ChemicalRowDataGatewayTest extends DatabaseTest {

	/**
	 * Test the creation of a table and inserting into it.
	 * @throws DatabaseException
	 */
	@Test
	void testCreateAndInsert() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon");
		chemicalTable.findByName("UnknownChemical");
		assertEquals(ChemicalEnum.CHEMICAL.getChemicalType(), chemicalTable.getType());
		assertEquals("UnknownChemical", chemicalTable.getName());
		assertEquals("The Moon", chemicalTable.getInhabits());
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011);
		chemicalTable.findByAtomicNumber(6);
		assertEquals(ChemicalEnum.ELEMENT.getChemicalType(), chemicalTable.getType());
		assertEquals("Carbon", chemicalTable.getName());
		assertEquals("Earth", chemicalTable.getInhabits());
		assertEquals(6, chemicalTable.getAtomicNumber());
		assertEquals(12.011, chemicalTable.getAtomicMass(), 0.001);
	}
	
	/**
	 * Test if drop table works.
	 * @throws DatabaseException
	 */
	@Test
	void testDrop() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011);
		
		chemicalTable.findByName("Carbon");
		assertEquals("Carbon", chemicalTable.getName());
		assertEquals(12.011, chemicalTable.getAtomicMass(), 0.001);
		
		chemicalTable = new ChemicalRowDataGatewayRDS();

		try {
			chemicalTable.findByName("Carbon");
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
		chemicalTable.findByName("UnknownChemical");
		chemicalTable.delete();
		
		try {
			chemicalTable.findByName("UnknownChemical");
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
		
		chemicalTable.findByName("Carbon");
		assertEquals("Carbon", chemicalTable.getName());
		assertEquals(12.011, chemicalTable.getAtomicMass(), 0.001);
		
		chemicalTable.setName("Carbon-13");
		chemicalTable.setAtomicMass(13.003);
		chemicalTable.update();
		
		chemicalTable.findByName("Carbon-13");
		assertEquals("Carbon-13", chemicalTable.getName());
		assertEquals(13.003, chemicalTable.getAtomicMass(), 0.001);
	}
	
	@Test
	void testGetAllBases() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(4, "Sucrose", "Earth", -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(4, "Potassium Hydroxide", "Earth", -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011);
		ArrayList<Integer> bases = chemicalTable.findByType(4);
		chemicalTable = new ChemicalRowDataGatewayRDS(bases.get(0).intValue());
		assertEquals("Sucrose", chemicalTable.getName());
		chemicalTable = new ChemicalRowDataGatewayRDS(bases.get(1).intValue());
		assertEquals("Potassium Hydroxide", chemicalTable.getName());
	}

}