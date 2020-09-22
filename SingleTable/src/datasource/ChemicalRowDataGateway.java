package datasource;

/**
 * Interface for a ChemicalRowDataGateway
 * @author andrewjanuszko
 *
 */
public interface ChemicalRowDataGateway {
	
	/**
	 * Drops a Chemical table if it already exists, then creates a new empty one.
	 * @throws DatabaseException
	 */
	public void createTableChemical() throws DatabaseException;
	
	/**
	 * 
	 * @param chemicalID the ID of the chemical.
	 * @return the type of the chemical.
	 * @throws DatabaseException
	 */
	public int getType(int chemicalID) throws DatabaseException;
	
	/**
	 * 
	 * @param chemicalID the ID of the chemical.
	 * @return the name of the chemical.
	 * @throws DatabaseException
	 */
	public String getName(int chemicalID) throws DatabaseException;
	
	/**
	 * 
	 * @param chemicalID the ID of the chemical.
	 * @return the habitat of the chemical.
	 * @throws DatabaseException
	 */
	public String getInhabits(int chemicalID) throws DatabaseException;
	
	/**
	 * 
	 * @param chemicalID the ID of the chemical.
	 * @return the atomic number of the chemical.
	 * @throws DatabaseException
	 */
	public int getAtomicNumber(int chemicalID) throws DatabaseException;
	
	/**
	 * 
	 * @param chemicalID the ID of the chemical.
	 * @return the atomic mass of the chemical.
	 * @throws DatabaseException
	 */
	public double getAtomicMass(int chemicalID) throws DatabaseException;
	
	/**
	 * 
	 * @param chemicalID the ID of the chemical.
	 * @return the acid ID that dissolves a chemical.
	 * @throws DatabaseException
	 */
	public int getDissolvedBy(int chemicalID) throws DatabaseException;
	
	/**
	 * 
	 * @param chemicalID the ID of the chemical.
	 * @return the solute ID of that chemical.
	 * @throws DatabaseException
	 */
	public int getSolute(int chemicalID) throws DatabaseException;
	
	/**
	 * 
	 * @param chemicalID the ID of the chemical.
	 * @param name the name of the chemical.
	 * @param inhabits the habitat of the chemical.
	 * @param atomicNumber the atomic number of the chemical.
	 * @param atomicMass the atomic mass of the chemical.
	 * @param dissolvedBy the acid ID that dissolves a chemical.
	 * @param solute the solute ID of that chemical.
	 * @throws DatabaseException
	 */
	public void insert(int chemicalID, String name, String inhabits, int atomicNumber, double atomicMass, int dissolvedBy, int solute) throws DatabaseException;
	
}