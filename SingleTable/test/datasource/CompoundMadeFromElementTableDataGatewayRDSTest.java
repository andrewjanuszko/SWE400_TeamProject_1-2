package datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
		gateway = getSingletonInstance();
		ChemicalRowDataGatewayRDS chemicalTable = new ChemicalRowDataGatewayRDS();
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Carbon", "Earth", 6, 12.011, -1, -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Oxygen", "Earth", 8, 15.999, -1, -1);
		chemicalTable = new ChemicalRowDataGatewayRDS(1, "Hydrogen", "Earth", 1, 1.007, -1, -1);
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
	
	@Test
	void testGetCompoundsFromElement() throws DatabaseException {
		ArrayList<CompoundMadeFromElementDTO> compounds = gateway.findCompoundsByElementID(4);
		CompoundMadeFromElementDTO cmp1 = compounds.get(0);
		assertEquals(4, cmp1.getCompoundId());
	}



	
}
