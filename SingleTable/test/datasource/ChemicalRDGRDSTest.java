package datasource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class ChemicalRowDataGatewayRDSTest extends DatabaseTest {
	
	/**
	 * Test the creation of a table and inserting into it.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testCreateAndInsert() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon", -1, -1.0, -1, -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		
		assertEquals(1, chemicalTable.getChemicalID());
		assertEquals(0, chemicalTable.getType());
		assertEquals("UnknownChemical", chemicalTable.getName());
		assertEquals("The Moon", chemicalTable.getInhabits());
		assertEquals(0, chemicalTable.getAtomicNumber());
		assertEquals(0, chemicalTable.getAtomicMass(), 0.001);
		assertEquals(0, chemicalTable.getDissolvedBy());
		assertEquals(0, chemicalTable.getSolute());
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "FunkyChemical", "Mars", 17, 9.810, -1, -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(2);
		
		assertEquals(2, chemicalTable.getChemicalID());
		assertEquals(1, chemicalTable.getType());
		assertEquals("FunkyChemical", chemicalTable.getName());
		assertEquals("Mars", chemicalTable.getInhabits());
		assertEquals(17, chemicalTable.getAtomicNumber());
		assertEquals(9.810, chemicalTable.getAtomicMass(), 0.001);
		assertEquals(0, chemicalTable.getDissolvedBy());
		assertEquals(0, chemicalTable.getSolute());
	}
	
	/**
	 * Test if drop table works.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testDropTable() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011, -1, -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		
		assertEquals(1, chemicalTable.getChemicalID());
		assertEquals(1, chemicalTable.getType());
		assertEquals("Carbon", chemicalTable.getName());
		assertEquals(6, chemicalTable.getAtomicNumber());
		assertEquals(12.011, chemicalTable.getAtomicMass(), 0.001);
		assertEquals(0, chemicalTable.getDissolvedBy());
		assertEquals(0, chemicalTable.getSolute());
		
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
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testDeleteChemical() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon", -1, -1.0, -1, -1);
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
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testUpdate() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011, -1, -1);		
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		
		assertEquals(1, chemicalTable.getChemicalID());
		assertEquals(1, chemicalTable.getType());
		assertEquals("Carbon", chemicalTable.getName());
		assertEquals(6, chemicalTable.getAtomicNumber());
		assertEquals(12.011, chemicalTable.getAtomicMass(), 0.001);
		assertEquals(0, chemicalTable.getDissolvedBy());
		assertEquals(0, chemicalTable.getSolute());
		
		chemicalTable.setName("Carbon-13");
		chemicalTable.setAtomicMass(13.003);
		chemicalTable.update();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		
		assertEquals(1, chemicalTable.getChemicalID());
		assertEquals(1, chemicalTable.getType());
		assertEquals("Carbon-13", chemicalTable.getName());
		assertEquals(13.003, chemicalTable.getAtomicMass(), 0.001);
		assertEquals(0, chemicalTable.getDissolvedBy());
		assertEquals(0, chemicalTable.getSolute());
	}

}