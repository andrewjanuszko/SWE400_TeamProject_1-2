package datasource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class ChemicalRDGRDSTest extends DatabaseTest {
	
	/**
	 * Test the creation of a table and inserting into it.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	public void testCreateAndInsert() throws DatabaseException {
		ChemicalRowDataGatewayRDS.createTable();
		
		ChemicalRowDataGatewayRDS unknownChemical = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon", 0, 0, 0, 0);
		
		assertEquals(1, unknownChemical.getChemicalID());
		assertEquals(0, unknownChemical.getType());
		assertEquals("UnknownChemical", unknownChemical.getName());
		assertEquals("The Moon", unknownChemical.getInhabits());
		assertEquals(0, unknownChemical.getAtomicNumber());
		assertEquals(0, unknownChemical.getAtomicMass(), 0.001);
		assertEquals(0, unknownChemical.getDissolvedBy());
		assertEquals(0, unknownChemical.getSolute());
		
		ChemicalRowDataGatewayRDS funkyChemical = new ChemicalRowDataGatewayRDS(1, "FunkyChemical", "Mars", 17, 9.810, 0, 0);
		
		assertEquals(2, funkyChemical.getChemicalID());
		assertEquals(1, funkyChemical.getType());
		assertEquals("FunkyChemical", funkyChemical.getName());
		assertEquals("Mars", funkyChemical.getInhabits());
		assertEquals(17, funkyChemical.getAtomicNumber());
		assertEquals(9.810, funkyChemical.getAtomicMass(), 0.001);
		assertEquals(0, funkyChemical.getDissolvedBy());
		assertEquals(0, funkyChemical.getSolute());
		
		ChemicalRowDataGatewayRDS.dropTable();
	}
	
	/**
	 * Test the insertion of duplicate entries.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	public void testDuplicateEntry() throws DatabaseException {
		ChemicalRowDataGatewayRDS.createTable();
		
		ChemicalRowDataGatewayRDS unknownChemical = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon", 0, 0, 0, 0);
		
		assertEquals(1, unknownChemical.getChemicalID());
		assertEquals(0, unknownChemical.getType());
		assertEquals("UnknownChemical", unknownChemical.getName());
		assertEquals("The Moon", unknownChemical.getInhabits());
		assertEquals(0, unknownChemical.getAtomicNumber());
		assertEquals(0, unknownChemical.getAtomicMass(), 0.001);
		assertEquals(0, unknownChemical.getDissolvedBy());
		assertEquals(0, unknownChemical.getSolute());
		
		try {
			ChemicalRowDataGatewayRDS unknownChemical2 = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon", 0, 0, 0, 0);
			assertEquals("UnknownChemical", unknownChemical2.getName());
			fail();
		} catch(DatabaseException e) {
			assertTrue(true);
			ChemicalRowDataGatewayRDS.dropTable();
		}	
	}
	
	/**
	 * Test if drop table works.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	public void testDropTable() throws DatabaseException {
		ChemicalRowDataGatewayRDS.createTable();
		
		ChemicalRowDataGatewayRDS carbon = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011, 0, 0);
		
		assertEquals(1, carbon.getChemicalID());
		assertEquals(1, carbon.getType());
		assertEquals("Carbon", carbon.getName());
		assertEquals(6, carbon.getAtomicNumber());
		assertEquals(12.011, carbon.getAtomicMass(), 0.001);
		assertEquals(0, carbon.getDissolvedBy());
		assertEquals(0, carbon.getSolute());
		
		ChemicalRowDataGatewayRDS.dropTable();

		try {
			ChemicalRowDataGatewayRDS funkyChemical = new ChemicalRowDataGatewayRDS(1, "FunkyChemical", "Mars", 17, 9.810, 0, 0);
			assertEquals("FunkyChemical", funkyChemical.getName());
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
	public void testDeleteChemical() throws DatabaseException {
		ChemicalRowDataGatewayRDS.createTable();
		
		ChemicalRowDataGatewayRDS unknownChemical = new ChemicalRowDataGatewayRDS(0, "UnknownChemical", "The Moon", 0, 0, 0, 0);
		
		assertEquals(1, unknownChemical.getChemicalID());
		assertEquals(0, unknownChemical.getType());
		assertEquals("UnknownChemical", unknownChemical.getName());
		assertEquals("The Moon", unknownChemical.getInhabits());
		assertEquals(0, unknownChemical.getAtomicNumber());
		assertEquals(0, unknownChemical.getAtomicMass(), 0.001);
		assertEquals(0, unknownChemical.getDissolvedBy());
		assertEquals(0, unknownChemical.getSolute());
		
		unknownChemical.delete();
		
		try {
			ChemicalRowDataGatewayRDS unknownChemicalSecond = new ChemicalRowDataGatewayRDS(1);
			assertEquals("UnknownChemical", unknownChemicalSecond.getName());
			fail();
		} catch(DatabaseException e) {
			assertTrue(true);
			ChemicalRowDataGatewayRDS.dropTable();
		}
	}
	
	/**
	 * Test updating an entry in the database.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	public void testUpdate() throws DatabaseException {
		ChemicalRowDataGatewayRDS.createTable();
		
		ChemicalRowDataGatewayRDS carbon = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011, 0, 0);
		
		assertEquals(1, carbon.getChemicalID());
		assertEquals(1, carbon.getType());
		assertEquals("Carbon", carbon.getName());
		assertEquals(6, carbon.getAtomicNumber());
		assertEquals(12.011, carbon.getAtomicMass(), 0.001);
		assertEquals(0, carbon.getDissolvedBy());
		assertEquals(0, carbon.getSolute());
		
		carbon.setName("Carbon-13");
		carbon.setAtomicMass(13.003);
		carbon.update();
		
		ChemicalRowDataGatewayRDS carbon13 = new ChemicalRowDataGatewayRDS(1);
		
		assertEquals(1, carbon13.getChemicalID());
		assertEquals(1, carbon13.getType());
		assertEquals("Carbon-13", carbon13.getName());
		assertEquals(13.003, carbon13.getAtomicMass(), 0.001);
		assertEquals(0, carbon13.getDissolvedBy());
		assertEquals(0, carbon13.getSolute());
		
		ChemicalRowDataGatewayRDS.dropTable();
	}

}