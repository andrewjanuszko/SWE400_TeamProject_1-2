package datasource;

class ChemicalTableDataGatewayTest extends ChemicalTableDataGatewayRDSTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	protected ChemicalTableDataGateway getSingletonInstance() {
		return ChemicalTableDataGatewayRDS.getSingletonInstance();
	}

}
