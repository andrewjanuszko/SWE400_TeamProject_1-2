package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CompoundMadeOfTableDataGatewayRDSTest extends DatabaseTest{

	@SuppressWarnings("unused")
  @Test
	void testAddRelation() throws DatabaseException {
		CompoundRowDataGateway c1 = new CompoundRowDataGatewayRDS(12, "compound", 1.0);
		ElementRowDataGateway e1 = new ElementRowDataGatewayRDS(10, "element", 1.0, 5, 10.0);
		
		CompoundMadeOfTableDataGateway cmf = new CompoundMadeOfTableDataGatewayRDS(12);

		cmf.addElementToCompound(10);
		cmf = null;
		
		cmf = new CompoundMadeOfTableDataGatewayRDS(12);
		cmf.getCompoundMadeOf().forEach(x -> assertEquals(10, x.getElementID()));
	}

}
