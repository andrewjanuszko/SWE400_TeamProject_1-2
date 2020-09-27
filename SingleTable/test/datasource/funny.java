package datasource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dataDTO.ChemicalDTO;

abstract class ChemicalTableDataGatewayTest extends DatabaseTest {
	
	protected ChemicalTableDataGateway gateway;
	
	public abstract ChemicalTableDataGatewayRDS getSingletonInstance();

	@Test
	void testGetAllChemicals() throws DatabaseException {
		gateway = getSingletonInstance();
		ArrayList<ChemicalDTO> results = gateway.fetchAll();
		System.out.println(results.toString());
	}

}
