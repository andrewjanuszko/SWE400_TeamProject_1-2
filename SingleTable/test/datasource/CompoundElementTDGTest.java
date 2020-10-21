package datasource;

public class CompoundElementTDGTest extends CompoundElementTDGRDSTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	protected ElementCompoundTableDataGatewayRDS getSingletonInstance() {
		return ElementCompoundTableDataGatewayRDS.getSingletonInstance();
	}

}
