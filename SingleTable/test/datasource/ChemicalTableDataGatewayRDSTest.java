package datasource;

class ChemicalTableDataGatewayRDSTest extends ChemicalTableDataGatewayTest {

	@Override
	protected ChemicalTableDataGateway getSingletonInstance() {
		return ChemicalTableDataGatewayRDS.getSingletonInstance();
	}

}
