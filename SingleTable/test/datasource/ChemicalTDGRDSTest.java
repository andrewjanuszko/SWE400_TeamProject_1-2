package datasource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataDTO.ChemicalDTO;

public abstract class ChemicalTDGRDSTest extends DatabaseTest {

	protected ChemicalTableDataGateway gateway = getSingletonInstance();
	
	protected abstract ChemicalTableDataGateway getSingletonInstance();
	
	/**
	 * Fills the database with entries to test on.
	 * @throws DatabaseException when things go wrong.
	 */
	@SuppressWarnings("unused")
	@BeforeEach
	public void fillDatabase() throws DatabaseException {
		ChemicalRowDataGatewayRDS.createTable();
		ChemicalRowDataGatewayRDS aquaRegia = new ChemicalRowDataGatewayRDS(5, "Aqua Regia", 123.4, 0, 0, 0, 0, 0);
		ChemicalRowDataGatewayRDS gold = new ChemicalRowDataGatewayRDS(2, "Gold", 923, 0, 0, 1, 170.0, 0);
		ChemicalRowDataGatewayRDS platinum = new ChemicalRowDataGatewayRDS(2, "Platinum", 923, 0, 0, 1, 17.0, 0);
		ChemicalRowDataGatewayRDS palladium = new ChemicalRowDataGatewayRDS(2, "Palladium", 923, 0, 0, 1, 17.0, 0);
	}
	
	/**
	 * 
	 * @throws DatabaseException
	 */
	@AfterEach
	public void dropDatabase() throws DatabaseException {
		ChemicalRowDataGatewayRDS.dropTable();
	}

	/**
	 * Test pulling everything out of the table.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchAll() throws DatabaseException {
		
		ArrayList<ChemicalDTO> lowAcids = gateway.filterAcidsWithLowInventory();
		assertEquals(1, lowAcids.size());
		for (ChemicalDTO lowAcid : lowAcids) {
          System.out.println(lowAcid.getName());
        }
	}
	
	/**
	 * Test pulling elements out of the table.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchElements() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchElements();
		assertEquals(8, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(5, chem1.getChemicalID());
		assertEquals(1, chem1.getType());
		assertEquals("Carbon", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(6, chem1.getAtomicNumber());
		assertEquals(12.011, chem1.getAtomicMass(), 0.001);
		assertEquals(0, chem1.getDissolvedBy());
		assertEquals(0, chem1.getSolute());
	}
	
	/**
	 * Test pulling metals out of the table.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchMetals() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchMetals();
		assertEquals(4, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(9, chem1.getChemicalID());
		assertEquals(2, chem1.getType());
		assertEquals("Copper", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(29, chem1.getAtomicNumber());
		assertEquals(63.546, chem1.getAtomicMass(), 0.001);
		assertEquals(10, chem1.getDissolvedBy());
		assertEquals(0, chem1.getSolute());
	}
	
	/**
	 * Test pulling compounds out of the table.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchCompounds() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchCompounds();
		assertEquals(4, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(13, chem1.getChemicalID());
		assertEquals(3, chem1.getType());
		assertEquals("Glucose", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(0, chem1.getAtomicNumber());
		assertEquals(0, chem1.getAtomicMass(), 0.001);
		assertEquals(0, chem1.getDissolvedBy());
		assertEquals(0, chem1.getSolute());
	}
	
	/**
	 * Test pulling bases out of the table.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchBases() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchBases();
		assertEquals(4, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(17, chem1.getChemicalID());
		assertEquals(4, chem1.getType());
		assertEquals("Potassium hydroxide", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(0, chem1.getAtomicNumber());
		assertEquals(0, chem1.getAtomicMass(), 0.001);
		assertEquals(0, chem1.getDissolvedBy());
		assertEquals(50, chem1.getSolute());
	}
	
	/**
	 * Test pulling acids out of the table.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchAcids() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchAcids();
		assertEquals(4, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(21, chem1.getChemicalID());
		assertEquals(5, chem1.getType());
		assertEquals("Hydrochloric acid", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(0, chem1.getAtomicNumber());
		assertEquals(0, chem1.getAtomicMass(), 0.001);
		assertEquals(0, chem1.getDissolvedBy());
		assertEquals(50, chem1.getSolute());
	}
	
	/**
	 * Test finding things by name.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchByName() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchByName("Oxygen");
		assertEquals(1, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(7, chem1.getChemicalID());
		assertEquals(1, chem1.getType());
		assertEquals("Oxygen", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(8, chem1.getAtomicNumber());
		assertEquals(15.999, chem1.getAtomicMass(), 0.001);
		assertEquals(0, chem1.getDissolvedBy());
		assertEquals(0, chem1.getSolute());
	}
	
	/**
	 * Test finding things by habitat.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchByHabitat() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchByHabitat("Mars");
		assertEquals(1, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(1, chem1.getChemicalID());
		assertEquals(0, chem1.getType());
		assertEquals("FunkyChemical", chem1.getName());
		assertEquals("Mars", chem1.getInhabits());
		assertEquals(0, chem1.getAtomicNumber());
		assertEquals(0, chem1.getAtomicMass(), 0.001);
		assertEquals(0, chem1.getDissolvedBy());
		assertEquals(0, chem1.getSolute());
	}
	
	/**
	 * Test finding things by atomic number.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchByAtomicNumber() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchByAtomicNumber(79);
		assertEquals(1, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(12, chem1.getChemicalID());
		assertEquals(2, chem1.getType());
		assertEquals("Gold", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(79, chem1.getAtomicNumber());
		assertEquals(196.966, chem1.getAtomicMass(), 0.001);
		assertEquals(40, chem1.getDissolvedBy());
		assertEquals(0, chem1.getSolute());
	}
	
	/**
	 * Test finding things by atomic mass.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchByAtomicMassRange() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchByAtomicMassRange(60.0, 70.0);
		assertEquals(2, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(9, chem1.getChemicalID());
		assertEquals(2, chem1.getType());
		assertEquals("Copper", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(29, chem1.getAtomicNumber());
		assertEquals(63.546, chem1.getAtomicMass(), 0.001);
		assertEquals(10, chem1.getDissolvedBy());
		assertEquals(0, chem1.getSolute());
		chem1 = chemicals.get(1);
		assertEquals(11, chem1.getChemicalID());
		assertEquals(2, chem1.getType());
		assertEquals("Zinc", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(30, chem1.getAtomicNumber());
		assertEquals(65.380, chem1.getAtomicMass(), 0.001);
		assertEquals(30, chem1.getDissolvedBy());
		assertEquals(0, chem1.getSolute());
	}
	
	/**
	 * Test finding things by dissolved by.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchByDissolvedBy() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchByDissolvedBy(10);
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(9, chem1.getChemicalID());
		assertEquals(2, chem1.getType());
		assertEquals("Copper", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(29, chem1.getAtomicNumber());
		assertEquals(63.546, chem1.getAtomicMass(), 0.001);
		assertEquals(10, chem1.getDissolvedBy());
		assertEquals(0, chem1.getSolute());
	}
	
	/**
	 * Test finding things by solute.
	 * @throws DatabaseException when things go wrong.
	 */
	@Test
	void testFetchBySolute() throws DatabaseException {
		ArrayList<ChemicalDTO> chemicals = gateway.fetchBySolute(50);
		assertEquals(2, chemicals.size());
		
		ChemicalDTO chem1 = chemicals.get(0);
		assertEquals(17, chem1.getChemicalID());
		assertEquals(4, chem1.getType());
		assertEquals("Potassium hydroxide", chem1.getName());
		assertEquals("Earth", chem1.getInhabits());
		assertEquals(0, chem1.getAtomicNumber());
		assertEquals(0, chem1.getAtomicMass(), 0.001);
		assertEquals(0, chem1.getDissolvedBy());
		assertEquals(50, chem1.getSolute());
	}

}
