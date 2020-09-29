package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AcidRowDataGatewayRDSTest {
	 
  @Test
  void testGetters() throws DatabaseException{
	 AcidRowDataGateway acid1 = new AcidRowDataGatewayRDS(1, "acid", "inhabit", "solute");
	 
	 assertEquals(1, acid1.getAcidID());
	 assertEquals("acid", acid1.getName());
	 assertEquals("inhabit", acid1.getInhabits());
	 assertEquals("solute", acid1.getSolute());
  }

}
