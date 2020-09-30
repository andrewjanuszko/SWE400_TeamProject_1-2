package datasource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class ChemicalRDGRDSTest extends DatabaseTest {
	
	/**
	 * Test the creation of a table and inserting into it.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testCreateAndInsert() throws DatabaseException {
		ChemicalRDGRDS chemicalGateway = new ChemicalRDGRDS();
		chemicalGateway.createTable();
		
		chemicalGateway = new ChemicalRDGRDS(0, "UnknownChemical", "The Moon", -1, -1.0, -1, -1);
		chemicalGateway = new ChemicalRDGRDS(1);
		
		assertEquals(1, chemicalGateway.getChemicalID());
		assertEquals(0, chemicalGateway.getType());
		assertEquals("UnknownChemical", chemicalGateway.getName());
		assertEquals("The Moon", chemicalGateway.getInhabits());
		assertEquals(0, chemicalGateway.getAtomicNumber());
		assertEquals(0, chemicalGateway.getAtomicMass(), 0.001);
		assertEquals(0, chemicalGateway.getDissolvedBy());
		assertEquals(0, chemicalGateway.getSolute());
		
		chemicalGateway = new ChemicalRDGRDS(1, "FunkyChemical", "Mars", 17, 9.810, -1, -1);
		chemicalGateway = new ChemicalRDGRDS(2);
		
		assertEquals(2, chemicalGateway.getChemicalID());
		assertEquals(1, chemicalGateway.getType());
		assertEquals("FunkyChemical", chemicalGateway.getName());
		assertEquals("Mars", chemicalGateway.getInhabits());
		assertEquals(17, chemicalGateway.getAtomicNumber());
		assertEquals(9.810, chemicalGateway.getAtomicMass(), 0.001);
		assertEquals(0, chemicalGateway.getDissolvedBy());
		assertEquals(0, chemicalGateway.getSolute());
		
		chemicalGateway.dropTable();
	}
	
	/**
	 * Test if drop table works.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testDropTable() throws DatabaseException {
		ChemicalRDGRDS chemicalGateway = new ChemicalRDGRDS();
		chemicalGateway.createTable();
		
		chemicalGateway = new ChemicalRDGRDS(1, "Carbon", "Earth", 6, 12.011, -1, -1);
		chemicalGateway = new ChemicalRDGRDS(1);
		
		assertEquals(1, chemicalGateway.getChemicalID());
		assertEquals(1, chemicalGateway.getType());
		assertEquals("Carbon", chemicalGateway.getName());
		assertEquals(6, chemicalGateway.getAtomicNumber());
		assertEquals(12.011, chemicalGateway.getAtomicMass(), 0.001);
		assertEquals(0, chemicalGateway.getDissolvedBy());
		assertEquals(0, chemicalGateway.getSolute());
		
		chemicalGateway.dropTable();

		try {
			chemicalGateway = new ChemicalRDGRDS(1);
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
		
		ChemicalRDGRDS chemicalGateway = new ChemicalRDGRDS();
		chemicalGateway.createTable();
		
		chemicalGateway = new ChemicalRDGRDS(0, "UnknownChemical", "The Moon", -1, -1.0, -1, -1);
		chemicalGateway = new ChemicalRDGRDS(1);
		
		chemicalGateway.delete();
		
		try {
			chemicalGateway = new ChemicalRDGRDS(1);
			chemicalGateway.dropTable();
			fail();
		} catch(DatabaseException e) {
			assertTrue(true);
			chemicalGateway.dropTable();

		}
	}
	
	/**
	 * Test updating an entry in the database.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testUpdate() throws DatabaseException {
		ChemicalRDGRDS chemicalGateway = new ChemicalRDGRDS();
		chemicalGateway.createTable();
		
		chemicalGateway = new ChemicalRDGRDS(1, "Carbon", "Earth", 6, 12.011, -1, -1);		
		chemicalGateway = new ChemicalRDGRDS(1);
		
		assertEquals(1, chemicalGateway.getChemicalID());
		assertEquals(1, chemicalGateway.getType());
		assertEquals("Carbon", chemicalGateway.getName());
		assertEquals(6, chemicalGateway.getAtomicNumber());
		assertEquals(12.011, chemicalGateway.getAtomicMass(), 0.001);
		assertEquals(0, chemicalGateway.getDissolvedBy());
		assertEquals(0, chemicalGateway.getSolute());
		
		chemicalGateway.setName("Carbon-13");
		chemicalGateway.setAtomicMass(13.003);
		chemicalGateway.update();
		
		chemicalGateway = new ChemicalRDGRDS(1);
		
		assertEquals(1, chemicalGateway.getChemicalID());
		assertEquals(1, chemicalGateway.getType());
		assertEquals("Carbon-13", chemicalGateway.getName());
		assertEquals(13.003, chemicalGateway.getAtomicMass(), 0.001);
		assertEquals(0, chemicalGateway.getDissolvedBy());
		assertEquals(0, chemicalGateway.getSolute());
		
		chemicalGateway.dropTable();
	}

}