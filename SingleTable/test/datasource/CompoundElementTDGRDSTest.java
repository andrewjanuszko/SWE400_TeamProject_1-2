package datasource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataDTO.ElementCompoundDTO;
import dataENUM.ChemicalEnum;

public abstract class CompoundElementTDGRDSTest extends DatabaseTest {

  protected ElementCompoundTableDataGatewayRDS gateway;

  /**
   * Gets a singleton.
   */
  protected abstract ElementCompoundTableDataGatewayRDS getSingletonInstance();

  @BeforeEach
  public void buildDB() throws DatabaseException {
    ChemicalRowDataGatewayRDS.createTable();
    gateway = getSingletonInstance();
    gateway.createTable();
  }
  
  @AfterEach
<<<<<<< HEAD
  public void deconstructDB() throws DatabaseException {
    gateway.dropTable();
    ChemicalRowDataGatewayRDS.dropTable();
=======
  void resetTable() throws DatabaseException {
	gateway.resetData();
	ChemicalRowDataGatewayRDS.dropTable();
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  }
  
  @Test
  public void isSingleton() throws DatabaseException {
    ElementCompoundTableDataGateway gateway1 = getSingletonInstance();
    ElementCompoundTableDataGateway gateway2 = getSingletonInstance();
    assertNotNull(gateway1);
    assertNotNull(gateway2);
    assertEquals(gateway1, gateway2);
  }
  
  @Test
<<<<<<< HEAD
  public void createCompound() throws DatabaseException {
    ChemicalRowDataGatewayRDS hydrogen = new ChemicalRowDataGatewayRDS(ChemicalEnum.ELEMENT.getIntValue(), "Hydrogen", 90, 1, 1.007, 0, 0, 0);
    ChemicalRowDataGatewayRDS oxygen = new ChemicalRowDataGatewayRDS(ChemicalEnum.ELEMENT.getIntValue(), "Oxygen", 90, 8, 15.999, 0, 0, 0);
    
    ChemicalRowDataGatewayRDS hydrogenDioxide = new ChemicalRowDataGatewayRDS(ChemicalEnum.COMPOUND.getIntValue(), "Hydrogen Dioxide", 90, 0, 0, 0, 0, 0);
    
    assertEquals(1, hydrogen.getID());
    assertEquals(2, oxygen.getID());
    assertEquals(3, hydrogenDioxide.getID());
    
    gateway.create(3, 1);
    gateway.create(3, 2);
    
    ElementCompoundDTO water = gateway.readElementsFromCompound(3);
    assertEquals(2, water.getRelations().size());
=======
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
>>>>>>> branch '84-single-table-data-mapper-implementations' of https://gitlab.engr.ship.edu/ko1568/swe400_project1_group6.git
  }
}
