package datasource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CompoundMadeFromElementTableDataGatewayTest extends CompoundMadeFromElementTableDataGatewayRDSTest {

	/**
	 * Gets a singleton.
	 */
	@Override
	protected CompoundMadeFromElementTableDataGateway getSingletonInstance() {
		return CompoundMadeFromElementTableDataGatewayRDS.getSingletonInstance();
	}

}
