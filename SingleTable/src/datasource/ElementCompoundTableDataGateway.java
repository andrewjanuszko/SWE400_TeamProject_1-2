package datasource;

import dataDTO.ElementCompoundDTO;

/**
 * A table data gateway.
 * @author andrewjanuszko
 */
public interface ElementCompoundTableDataGateway {
  
  public void createTable() throws DatabaseException;
  
  public void dropTable() throws DatabaseException;
  
  public void create(int compoundID, int elementID) throws DatabaseException;
  
  public void update(int oldCompoundID, int oldElementID, int newCompoundID, int newElementID) throws DatabaseException;
  
  public void delete(int compoundID, int elementID) throws DatabaseException;
  
  public ElementCompoundDTO readElementsFromCompound(int compoundID) throws DatabaseException;
  
  public ElementCompoundDTO readCompoundsWithElement(int elementID) throws DatabaseException;
  
//	/**
//	 * Updates a row in a table.
//	 * @param compoundID the id of the compound.
//	 * @param elementID the id of the element.
//	 * @throws DatabaseException when we cannot connect to the database.
//	 */
//	public void updateRow(int oldCompoundID, int oldElementID, int newCompoundID, int newElementID) throws DatabaseException;
//	
//	/**
//	 * Create a row.
//	 * @param compoundID
//	 * @param elementID
//	 * @throws DatabaseException
//	 */
//	public void createRow(int compoundID, int elementID) throws DatabaseException;
//	
//	/**
//	 * resets the data for testing.
//	 * @throws DatabaseException
//	 */
//	public void resetData() throws DatabaseException;
//	
//	/**
//	 * Delete a row from the TDG.
//	 * @param compoundID
//	 * @param elementID
//	 * @throws DatabaseException
//	 */
//	public void delete(int compoundID, int elementID) throws DatabaseException;
//	
//	/**
//	 * 
//	 * @param elementID
//	 * @return
//	 * @throws DatabaseException
//	 */
//	public ElementCompoundDTO findCompoundsByElementID(int elementID) throws DatabaseException;
//	
//	/**
//	 * 
//	 * @param compoundID
//	 * @return
//	 * @throws DatabaseException
//	 */
//	public ElementCompoundDTO findElementsByCompoundID(int compoundID) throws DatabaseException;
}
