package datasource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class ChemicalRowDataGatewayTest extends DatabaseTest {
	
	/**
	 * Test the creation of a table and inserting into it.
	 * @throws DatabaseException
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
		assertEquals(-1, chemicalTable.getAtomicNumber());
		assertEquals(-1.0, chemicalTable.getAtomicMass(), 0.001);
		assertEquals(-1, chemicalTable.getDissolvedBy());
		assertEquals(-1, chemicalTable.getSolute());
	}
	
	/**
	 * Test if drop table works.
	 * @throws DatabaseException
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
		assertEquals(-1, chemicalTable.getDissolvedBy());
		assertEquals(-1, chemicalTable.getSolute());
		
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
	 * @throws DatabaseException
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
		assertEquals(-1, chemicalTable.getDissolvedBy());
		assertEquals(-1, chemicalTable.getSolute());
		
		chemicalTable.setName("Carbon-13");
		chemicalTable.setAtomicMass(13.003);
		chemicalTable.update();
		
		chemicalTable = new ChemicalRowDataGatewayRDS(1);
		
		assertEquals(1, chemicalTable.getChemicalID());
		assertEquals(1, chemicalTable.getType());
		assertEquals("Carbon-13", chemicalTable.getName());
		assertEquals(13.003, chemicalTable.getAtomicMass(), 0.001);
		assertEquals(-1, chemicalTable.getDissolvedBy());
		assertEquals(-1, chemicalTable.getSolute());
	}

}