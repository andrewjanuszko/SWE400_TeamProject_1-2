package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CompoundMadeOfTableDataGatewayRDSTest extends DatabaseTest{

	@SuppressWarnings("unused")
  @Test
	void testAddRelation() throws DatabaseException {
		CompoundRowDataGateway c1 = new CompoundRowDataGatewayRDS("compound", 1.0);
		ElementRowDataGateway e1 = new ElementRowDataGatewayRDS("element", 1.0, 5, 10.0);
		//TODO write tests
		

		
	}

}
