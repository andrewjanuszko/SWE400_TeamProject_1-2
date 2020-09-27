package datasource;

import java.util.ArrayList;
import dataDTO.ChemicalDTO;
import dataDTO.CompoundMadeFromElementRecordDTO;

/**
 * A table data gateway.
 * @author andrewjanuszko
 */
public interface CompoundMadeFromElementTableDataGateway {
	
	/**
	 * Updates a row in a table
	 * @param compoundID the id of the compound.
	 * @param elementID the id of the element.
	 * @throws DatabaseException when we cannot connect to the database.
	 */
	void updateRow(int compoundID, int elementID) throws DatabaseException;
	
	/**
	 * Get all elements that are in a given compound.
	 * @param compoundID the compound.
	 * @return a list of all elements in that compound.
	 * @throws DatabaseException if the compound does not exist.
	 */
	ArrayList<CompoundMadeFromElementRecordDTO> findElementsByCompoundID(int compoundID) throws DatabaseException;
	
	/**
	 * Get all compounds made of a given element.
	 * @param elementID the element.
	 * @return a list of all compounds made from that element.
	 * @throws DatabaseException if the element does not exist.
	 */
	ArrayList<CompoundMadeFromElementRecordDTO> findCompoundsByElementID(int elementID) throws DatabaseException;
}
