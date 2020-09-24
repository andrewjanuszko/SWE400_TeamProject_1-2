package datasource;

import java.util.ArrayList;

/**
 * Interface for a ChemicalRowDataGateway
 * @author andrewjanuszko
 */
public interface ChemicalRowDataGateway {
	
	/**
	 * Deletes an instance of a Chemical from the table.
	 * @throws DatabaseException when chemical not found.
	 */
	public void delete() throws DatabaseException;
	
	/**
	 * Updates the chemical in the table.
	 * @throws DatabaseException when chemical not found.
	 */
	public void update() throws DatabaseException;
	
	/**
	 * Get the type of the chemical.
	 * @return the type.
	 */
	public int getType();
	
	/**
	 * Get the name of the chemical.
	 * @return the name.
	 */
	public String getName();
	
	/**
	 * Get the habitat of the chemical.
	 * @return the habitat.
	 */
	public String getInhabits();
	
	/**
	 * Get the atomic number of the chemical.
	 * @return the atomic number.
	 */
	public int getAtomicNumber();
	
	/**
	 * Get the atomic mass of the chemical.
	 * @return the atomic mass.
	 */
	public double getAtomicMass();
	
	/**
	 * Get the acid that a metal is dissolved by.
	 * @return the acid ID.
	 */
	public int getDissolvedBy();
	
	/**
	 * Get the chemical ID of the base/acid solute.
	 * @return the base/acid solute.
	 */
	public int getSolute();
	
	/**
	 * Set the type of the chemical.
	 * @param type is the type of the chemical.
	 */
	public void setType(int type);
	
	/**
	 * Set the name of the chemical.
	 * @param name is the name of the chemical.
	 */
	public void setName(String name);
	
	/**
	 * Set the habitat of the chemical.
	 * @param inhabits is the habitat of the chemical.
	 */
	public void setInhabits(String inhabits);
	
	/**
	 * Set the atomic number of the chemical.
	 * @param atomicNumber is the atomic number of the chemical.
	 */
	public void setAtomicNumber(int atomicNumber);
	
	/**
	 * Set the atomic mass of the chemical.
	 * @param atomicMass the atomic mass of the chemical.
	 */
	public void setAtomicMass(double atomicMass);

	/**
	 * Set the acid ID that a metal is dissolved by.
	 * @param dissolvedBy is the acid ID that a metal is dissolved by.
	 * @throws DatabaseException when the given ID is not an acid.
	 */
	public void setDissolvedBy(int dissolvedBy) throws DatabaseException;
	
	/**
	 * Set the chemical ID of the base/acid solute.
	 * @param solute the chemical ID of the base/acid solute.
	 */
	public void setSolute(int solute);
	
	/**
	 * 
	 * @param type
	 * @return
	 * @throws DatabaseException
	 */
	public ArrayList<Integer> findByType(int type) throws DatabaseException;
	
	/**
	 * 
	 * @param name
	 * @throws DatabaseException
	 */
	public void findByName(String name) throws DatabaseException;

	/**
	 * 
	 * @param inhabits
	 * @return
	 * @throws DatabaseException
	 */
	public ArrayList<Integer> findByHabitat(String inhabits) throws DatabaseException;
	
	/**
	 * 
	 * @param atomicNumber
	 * @throws DatabaseException
	 */
	public void findByAtomicNumber(int atomicNumber) throws DatabaseException;
	
	/**
	 * 
	 * @param atomicMass
	 * @throws DatabaseException
	 */
	public void findByAtomicMass(double atomicMass) throws DatabaseException;
	
	/**
	 * 
	 * @param dissolvedBy
	 * @return
	 * @throws DatabaseException
	 */
	public ArrayList<Integer> findByDissolvedBy(int dissolvedBy) throws DatabaseException;
	
	/**
	 * 
	 * @param solute
	 * @return
	 * @throws DatabaseException
	 */
	public ArrayList<Integer> findBySolute(int solute) throws DatabaseException;

	
	


	
	
	
}