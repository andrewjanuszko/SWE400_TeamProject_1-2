package datasource;

public class CompoundElementTDGTest extends CompoundElementTDGRDSTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	protected ElementCompoundTableDataGateway getSingletonInstance() {
		return ElementCompoundTableDataGateway.getSingletonInstance();
	}

}
