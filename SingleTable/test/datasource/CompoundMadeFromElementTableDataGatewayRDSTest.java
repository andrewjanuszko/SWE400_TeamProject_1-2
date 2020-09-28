package datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataDTO.CompoundMadeFromElementDTO;

public abstract class CompoundMadeFromElementTableDataGatewayRDSTest extends DatabaseTest {
	
	protected CompoundMadeFromElementTableDataGateway gateway;
	
	/**
	 * Gets a singleton.
	 */
	protected abstract CompoundMadeFromElementTableDataGateway getSingletonInstance();
	

	/**
	 * Fills the database with entries to test on.
	 * @throws DatabaseException when things go wrong.
	 */
	@BeforeEach
	void fillDatabase() throws DatabaseException {
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		gateway = getSingletonInstance();
		//Inserting 3 elements to make up the compounds
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011, -1, -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Oxygen", "Earth", 8, 15.999, -1, -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Hydrogen", "Earth", 1, 1.007, -1, -1);
		//Inserting 2 compounds
		chemicalTable = new ChemicalRowDataGatewayRDS(3, "Water", "Earth", -1, -1, -1, -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(3, "Carbon Monoxide", "Earth", -1, -1, -1, -1);
		// water is made of hydrogen and oxygen
		gateway.createRow(4, 2);
		gateway.createRow(4, 3);
		// carbon monoxide is made of carbon and oxygen
		gateway.createRow(5, 1);
		gateway.createRow(5, 2);
		// NOTE: might want to make a createRow() that takes in an array
	}

//	@AfterEach
//	void resetTable() throws DatabaseException {
//		gateway.resetData();
//	}
	
	@Test
	public void isASingleton()
	{
		CompoundMadeFromElementTableDataGateway x = getSingletonInstance();
		CompoundMadeFromElementTableDataGateway y = getSingletonInstance();
		assertSame(x, y);
		assertNotNull(x);
	}

	/**
	 * Tests the  to find a compound by giving an element it is made up of 
	 * @throws DatabaseException
	 */
	@Test
	void testGetCompoundsFromElementID() throws DatabaseException {
		// finds compounds the are oxygen in them
		ArrayList<CompoundMadeFromElementDTO> compounds = gateway.findCompoundsByElementID(2);
		// checks to make sure that both are gotten and that they are correct
		assertEquals(2, compounds.size());
		assertEquals(4, compounds.get(0).getCompoundId());
		assertEquals(5, compounds.get(1).getCompoundId());
	}

	@Test
	void testGetCompoundFromCompoundID() throws DatabaseException {
		// finds carbon Monoxide
		ArrayList<CompoundMadeFromElementDTO> compounds = gateway.findElementsByCompoundID(5);
		// checks to make sure that both are gotten and that they are correct
		assertEquals(2, compounds.size());
		assertEquals(5, compounds.get(0).getCompoundId());
		assertEquals(5, compounds.get(1).getCompoundId());
		// NOTE: because it is made of two elements it has to rows this might be a problem
	}
	

	
}
