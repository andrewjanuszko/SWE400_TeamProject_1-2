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
		gateway.getSingletonInstance();
		gateway.createRow(1, 2);
		gateway.createRow(1, 3);
		gateway.createRow(1, 4);
		gateway.createRow(1, 5);
		gateway.createRow(6, 7);
		gateway.createRow(6, 8);
		gateway.createRow(6, 9);
		gateway.createRow(6, 1);
		gateway.createRow(11, 2);
		gateway.createRow(11, 3);
		gateway.createRow(11, 4);
		gateway.createRow(11, 5);
	}
	
	@Test
	void testGetCompoundsFromElement() throws DatabaseException {
		ArrayList<CompoundMadeFromElementDTO> compounds = gateway.findCompoundsByElementID(7);
		CompoundMadeFromElementDTO cmp1 = compounds.get(0);
		assertEquals(6, cmp1.getCompoundId());
	}



	
}
