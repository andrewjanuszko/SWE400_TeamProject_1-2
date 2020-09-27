package datasource;

class ChemicalTableDataGatewayRDSTest extends ChemicalTableDataGatewayTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	protected ChemicalTableDataGateway getSingletonInstance() {
		return ChemicalTableDataGatewayRDS.getSingletonInstance();
	}

}
