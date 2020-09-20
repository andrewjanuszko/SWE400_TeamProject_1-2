package datasource;

/**
 * A table data gateway.
 * @author andrewjanuszko
 */
public interface CompoundMadeFromElementTableDataGateway {

	/**
	 * Allows us to create tables for CompoundMadeOfElement
	 * @throws DatabaseException when we cannot connect to the database.
	 */
	void createTable() throws DatabaseException;
	
	/**
	 * Creates a row in the table.
	 * @param compoundID the id of the compound.
	 * @param elementID the id of the element.
	 * @throws DatabaseException when we cannot connect to the database.
	 */
	void createRow(long compoundID, long elementID) throws DatabaseException;
	
	/**
	 * Updates a row in a table
	 * @param compoundID the id of the compound.
	 * @param elementID the id of the element.
	 * @throws DatabaseException when we cannot connect to the database.
	 */
	void updateRow(long compoundID, long elementID) throws DatabaseException;
	
	/**
	 * Used for testing this table.
	 */
	void resetData();
}
