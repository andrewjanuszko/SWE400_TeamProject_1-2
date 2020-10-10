package datasource;

class ChemicalTDGTest extends ChemicalTDGRDSTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	protected ChemicalTableDataGateway getSingletonInstance() {
		return ChemicalTableDataGatewayRDS.getSingletonInstance();
	}

}
