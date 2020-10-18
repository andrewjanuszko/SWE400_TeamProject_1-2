package datasource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataDTO.ElementCompoundDTO;

public abstract class CompoundElementTDGRDSTest extends DatabaseTest {

  protected ElementCompoundTableDataGateway gateway;

  /**
   * Gets a singleton.
   */
  protected abstract ElementCompoundTableDataGateway getSingletonInstance();

  /**
   * Fills the database with entries to test on.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @SuppressWarnings("unused")
  @BeforeEach
  void fillDatabase() throws DatabaseException {
    ChemicalRowDataGatewayRDS.createTable();
    gateway = getSingletonInstance();
    // Inserting 3 elements to make up the compounds
    ChemicalRowDataGatewayRDS carbon = new ChemicalRowDataGatewayRDS(1, "Carbon", 1.0, 6, 12.011, 0, 0, 0);
    ChemicalRowDataGatewayRDS oxygen = new ChemicalRowDataGatewayRDS(1, "Oxygen", 20.0, 8, 15.999, 0, 0, 0);
    ChemicalRowDataGatewayRDS hydrogen = new ChemicalRowDataGatewayRDS(1, "Hydrogen", 12.2, 1, 1.007, 0, 0, 0);
    // Inserting 2 compounds
    ChemicalRowDataGatewayRDS water = new ChemicalRowDataGatewayRDS(3, "Water", 2, 0, 0, 0, 0, 0);
    ChemicalRowDataGatewayRDS carbonMonoxide = new ChemicalRowDataGatewayRDS(3, "Carbon Monoxide", 12, 0, 0, 0, 0, 0);
    // water is made of hydrogen and oxygen
    gateway.createRow(4, 2);
    gateway.createRow(4, 3);
    // carbon monoxide is made of carbon and oxygen
    gateway.createRow(5, 1);
    gateway.createRow(5, 2);
    // NOTE: might want to make a createRow() that takes in an array
  }

  /**
   * resets the singleton after every test
   * 
   * @throws DatabaseException
   */
  @AfterEach
  void resetTable() throws DatabaseException {
	gateway.resetData();
	ChemicalRowDataGatewayRDS.dropTable();
  }

  /**
   * Tests if the gateway is a singleton.
   */
  @Test
  public void isASingleton() {
    ElementCompoundTableDataGateway x = getSingletonInstance();
    ElementCompoundTableDataGateway y = getSingletonInstance();
    assertSame(x, y);
    assertNotNull(x);
  }

  /**
   * USES ALTERNATE IMPLEMENTATION Tests the ability to find a compound by giving
   * an element it is made up of
   * 
   * @throws DatabaseException
   */
  @Test
  void testGetCompoundsFromElementID() throws DatabaseException {
    // finds compounds the are oxygen in them
    ElementCompoundDTO element = gateway.findCompoundsByElementID(2);
    // checks to make sure that both are gotten and that they are correct
    assertEquals(2, element.getRelations().size());
    assertEquals(4, element.getRelations().get(0).getID());
    assertEquals(5, element.getRelations().get(1).getID());
  }

  /**
   * USES ALTERNATE IMPLEMENTATION Tests the ability to find a compound by giving
   * an compound it is made up of
   * 
   * @throws DatabaseException
   */
  @Test
  void testGetElementsFromCompoundID() throws DatabaseException {
    // finds carbon Monoxide
    ElementCompoundDTO compounds = gateway.findElementsByCompoundID(5);
    // checks to make sure that both are gotten and that they are correct
    assertEquals(2, compounds.getRelations().size());
    assertEquals(1, compounds.getRelations().get(0).getID());
    assertEquals(2, compounds.getRelations().get(1).getID());
    // NOTE: because it is made of two elements it has to rows this might be a
    // problem
  }

  /**
   * Tests the ability to update a row.
   * 
   * @throws DatabaseException
   */
  @Test
  void testUpdateCompoundCompoundDTO() throws DatabaseException {
    ElementCompoundDTO compound = gateway.findElementsByCompoundID(5);

    assertEquals(5, compound.getID());
    assertEquals(1, compound.getRelations().get(0).getID());
    assertEquals(2, compound.getRelations().get(1).getID());

    gateway.updateRow(5, 1, 5, 3);

    compound = gateway.findElementsByCompoundID(5);

    assertEquals(5, compound.getID());
    assertEquals(3, compound.getRelations().get(0).getID());
    assertEquals(2, compound.getRelations().get(1).getID());
  }

  /**
   * Tests the ability to update a row.
   * 
   * @throws DatabaseException
   */
  @Test
  void testUpdateCompoundElementInCompoundsDTO() throws DatabaseException {
    ElementCompoundDTO element = gateway.findCompoundsByElementID(2);

    assertEquals(2, element.getID());
    assertEquals(2, element.getRelations().size());
    assertEquals(4, element.getRelations().get(0).getID());
    assertEquals(5, element.getRelations().get(1).getID());

    gateway.updateRow(4, 2, 4, 1);

    element = gateway.findCompoundsByElementID(2);
    assertEquals(1, element.getRelations().size());
    assertEquals(2, element.getID());
    assertEquals(5, element.getRelations().get(0).getID());

  }

  /**
   * Tests the ability to delete a row.
   * 
   * @throws DatabaseException
   */
  @Test
  void testDelete() throws DatabaseException {
    ElementCompoundDTO element = gateway.findCompoundsByElementID(2);

    assertEquals(2, element.getID());
    assertEquals(2, element.getRelations().size());
    assertEquals(4, element.getRelations().get(0).getID());
    assertEquals(5, element.getRelations().get(1).getID());

    gateway.delete(4, 2);

    element = gateway.findCompoundsByElementID(2);
    assertEquals(1, element.getRelations().size());
    assertEquals(2, element.getID());
    assertEquals(5, element.getRelations().get(0).getID());
  }
}
