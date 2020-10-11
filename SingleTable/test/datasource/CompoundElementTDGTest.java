package datasource;

public class CompoundElementTDGTest extends CompoundElementTDGRDSTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	protected CompoundElementTDG getSingletonInstance() {
		return CompoundElementTDGRDS.getSingletonInstance();
	}

}
