package datasource;

import java.util.ArrayList;
import java.util.Hashtable;
import dataDTO.CompoundMadeFromElementRecordDTO;

public class CompoundMadeFromElementTableDataGatewayMock implements CompoundMadeFromElementTableDataGateway {

	private static CompoundMadeFromElementTableDataGateway singletonInstance;
	
	/**
	 * 
	 * @return
	 */
	public static synchronized CompoundMadeFromElementTableDataGateway getSingleton() {
		if (singletonInstance.equals(null)) {
			singletonInstance = new CompoundMadeFromElementTableDataGatewayMock();
		}
		return singletonInstance;
	}
	
	/**
	 * Build with mock data for testing.
	 */
	private CompoundMadeFromElementTableDataGatewayMock() {
		resetData();
	}
	
	private Hashtable<Key, ArrayList<CompoundMadeFromElementRecordDTO>> data;
	
	private class Key {
		
		private long compoundID;
		private long elementID;
		
		/**
		 * 
		 * @param compoundID
		 * @param elementID
		 */
		public Key(long compoundID, long elementID) {
			this.compoundID = compoundID;
			this.elementID = elementID;
		}
		
	}
	
	@Override
	public void createTable() throws DatabaseException {
		
	}

	@Override
	public void createRow(long compoundID, long elementID) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRow(long compoundID, long elementID) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetData() {
		// TODO Auto-generated method stub
		
	}

}
