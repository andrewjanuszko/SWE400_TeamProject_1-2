package datasource;

public class ChemicalTableDataGatewayRDSTest extends ChemicalTableDataGatewayTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	public ChemicalTableDataGatewayInterface getGateway() {
		return ChemicalTableDataGateway.getSingletonInstance();
	}

}
