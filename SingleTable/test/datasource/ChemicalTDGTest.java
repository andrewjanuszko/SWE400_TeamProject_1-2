package datasource;

class ChemicalTDGTest extends ChemicalTDGRDSTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	protected ChemicalTDG getSingletonInstance() {
		return ChemicalTDGRDS.getSingletonInstance();
	}

}
