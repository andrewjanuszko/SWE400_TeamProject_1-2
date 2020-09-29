package datasource;

import dataDTO.ChemicalDTO;
import java.util.ArrayList;

/**
 * Table Data Gateway for Chemical.
 * @author andrewjanuszko
 */
public interface ChemicalTDG {

	/**
	 * Fetch all Chemicals.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchAll() throws DatabaseException;
	
	/**
	 * Fetch all Elements.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchElements() throws DatabaseException;
	
	/**
	 * Fetch all Metals.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchMetals() throws DatabaseException;
	
	/**
	 * Fetch all Compounds.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchCompounds() throws DatabaseException;
	
	/**
	 * Fetch all Bases.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchBases() throws DatabaseException;
	
	/**
	 * Fetch all Acids.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchAcids() throws DatabaseException;
	
	/**
	 * Fetch Chemicals by name.
	 * @param name of the Chemical.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchByName(String name) throws DatabaseException;
	
	/**
	 * Fetch Chemicals by habitat.
	 * @param habitat of the Chemical.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchByHabitat(String habitat) throws DatabaseException;
	
	/**
	 * Fetch Elements by atomic number.
	 * @param atomicNumber of the Element.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchByAtomicNumber(int atomicNumber) throws DatabaseException;
	
	/**
	 * Fetch Elements in a given mass range.
	 * @param min mass of the Element.
	 * @param max mass of the Element.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchByAtomicMassRange(double min, double max) throws DatabaseException;
	
	/**
	 * Fetch all Metals dissolved by an Acid
	 * @param acidID of the Acid.
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchByDissolvedBy(int acidID) throws DatabaseException;
	
	/**
	 * Fetch all Bases and Acids by solute.
	 * @param chemicalID of the Chemical. 
	 * @return an ArrayList of ChemicalDTOs.
	 * @throws DatabaseException when things go wrong.
	 */
	public ArrayList<ChemicalDTO> fetchBySolute(int chemicalID) throws DatabaseException;

}
