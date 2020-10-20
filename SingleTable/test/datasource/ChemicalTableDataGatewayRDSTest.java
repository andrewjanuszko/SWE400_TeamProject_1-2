package datasource;

public class ChemicalTableDataGatewayRDSTest extends ChemicalTableDataGatewayTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	public ChemicalTableDataGateway getGateway() {
		return ChemicalTableDataGatewayRDS.getSingletonInstance();
	}

}
