package datasource;

public class CompoundMadeFromElementTableDataGatewayTest extends CompoundMadeFromElementTableDataGatewayRDSTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	protected CompoundMadeFromElementTableDataGateway getSingletonInstance() {
		return CompoundMadeFromElementTableDataGatewayRDS.getSingletonInstance();
	}

}
