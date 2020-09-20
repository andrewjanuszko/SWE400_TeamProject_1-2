package datasource;

public class CompoundMadeFromElementTableDataGatewayRDS implements CompoundMadeFromElementTableDataGateway {
	
	private static CompoundMadeFromElementTableDataGateway singletonInstance;
	
	/**
	 * Get the singleton instance of the RDS gateway.
	 * @return the singleton instance.
	 */
	public static synchronized CompoundMadeFromElementTableDataGateway getSingleton() {
		if (singletonInstance.equals(null)) { //Possible error point (.equals vs ==)
			singletonInstance = new CompoundMadeFromElementTableDataGatewayRDS();
		}
		return singletonInstance;
	}

	@Override
	public void createTable() throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRow(long compoundID, long elementID) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRelation(long compoundID, long elementID) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetData() {
		// TODO Auto-generated method stub
		
	}

}
