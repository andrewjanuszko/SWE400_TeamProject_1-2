package datasource;

/**
 * Interface for a ChemicalRowDataGateway
 * @author andrewjanuszko
 *
 */
public interface ChemicalRowDataGateway {
	
	/**
	 * @param type - the type of the chemical.
	 */
	void setType(int type);
	
	/**
	 * @param name - the name of the Chemical.
	 */
	void setName(String name);
	
	/**
	 * @param habitat - the location where the Chemical is found.
	 */
	void setHabitat(String inhabits);
	
	/**
	 * @param atomicNumber - the atomic number of the Chemical.
	 */
	void setAtomicNumber(int atomicNumber);
	
	/**
	 * @param atomicMass - the atomic mass of the Chemical.
	 */
	void setAtomicMass(double atomicMass);
	
	/**
	 * @param acidID - the ID of the acid that a metal can be dissolved by.
	 */
	void setDissolvedBy(long acidID);
	
	/**
	 * @param chemicalID - the ID of the solute.
	 */
	void setSolute(long chemicalID);
	
	/**
	 * @return the id of the chemical
	 */
	long getID();
	
	/**
	 * @return the type of the chemical.
	 */
	int getType();
	
	/**
	 * @return the name of the Chemical.
	 */
	String getName();
	
	/**
	 * @return the habitat of the Chemical.
	 */
	String getHabitat();
	
	/**
	 * @return the atomic number of the Chemical.
	 */
	int getAtomicNumber();
	
	/**
	 * @return the atomic mass of the Chemical.
	 */
	double getAtomicMass();
	
	/**
	 * @return the acidID that a Chemical is dissolved by.
	 */
	long getDissolvedBy();
	
	/**
	 * @return the chemicalID of the solute.
	 */
	long getSolute();
	
	/**
	 * Store information into the database.
	 * @throws DatabaseException if failure to persist data.
	 */
	void persistData() throws DatabaseException;

	/**
	 * Used in testing.
	 */
	void resetData();

	/**
	 * Delete this instance of chemical.
	 * @throws DatabaseException when it fails to delete a chemical.
	 */
	void deleteInstance() throws DatabaseException;

	
}