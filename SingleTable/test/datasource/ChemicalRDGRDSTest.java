package datasource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class ChemicalRDGRDSTest extends DatabaseTest {

  /**
   * Test the creation of a table and inserting into it.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  public void testCreateAndInsert() throws DatabaseException {
    ChemicalRowDataGatewayRDS.createTable();

    ChemicalRowDataGatewayRDS unknown = new ChemicalRowDataGatewayRDS(0, "Unknown", 123.0, 0, 0, 0, 0, 0);

    assertEquals(1, unknown.getChemicalID());
    assertEquals(0, unknown.getType());
    assertEquals("Unknown", unknown.getName());
    assertEquals(123.0, unknown.getInventory(), 0.001);
    assertEquals(0, unknown.getAtomicNumber());
    assertEquals(0, unknown.getAtomicMass(), 0.001);
    assertEquals(0, unknown.getDissolvedBy());
    assertEquals(0, unknown.getMoles(), 0.001);
    assertEquals(0, unknown.getSolute());

    ChemicalRowDataGatewayRDS funky = new ChemicalRowDataGatewayRDS(1, "Funky", 9.0, 17, 9.810, 0, 0, 0);

    assertEquals(2, funky.getChemicalID());
    assertEquals(1, funky.getType());
    assertEquals("Funky", funky.getName());
    assertEquals(9.0, funky.getInventory());
    assertEquals(17, funky.getAtomicNumber());
    assertEquals(9.810, funky.getAtomicMass(), 0.001);
    assertEquals(0, funky.getDissolvedBy());
    assertEquals(0, funky.getMoles(), 0.001);
    assertEquals(0, funky.getSolute());

    ChemicalRowDataGatewayRDS.dropTable();
  }

  /**
   * Test the insertion of duplicate entries.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  public void testDuplicateEntry() throws DatabaseException {
    ChemicalRowDataGatewayRDS.createTable();

    ChemicalRowDataGatewayRDS unknown = new ChemicalRowDataGatewayRDS(0, "Unknown", 123.0, 0, 0, 0, 0, 0);

    assertEquals(1, unknown.getChemicalID());
    assertEquals(0, unknown.getType());
    assertEquals("Unknown", unknown.getName());
    assertEquals(123.0, unknown.getInventory(), 0.001);
    assertEquals(0, unknown.getAtomicNumber());
    assertEquals(0, unknown.getAtomicMass(), 0.001);
    assertEquals(0, unknown.getDissolvedBy());
    assertEquals(0, unknown.getMoles(), 0.001);
    assertEquals(0, unknown.getSolute());

    try {
      @SuppressWarnings("unused")
      ChemicalRowDataGatewayRDS unknown2 = new ChemicalRowDataGatewayRDS(0, "Unknown", 123.0, 0, 0, 0, 0, 0);
      fail();
    } catch (DatabaseException e) {
      assertEquals("Failed to insert into 'Chemical' table.", e.getSimpleDescription());
      ChemicalRowDataGatewayRDS.dropTable();
    }
  }

  /**
   * Test if drop table works.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  public void testDropTable() throws DatabaseException {
    ChemicalRowDataGatewayRDS.createTable();

    ChemicalRowDataGatewayRDS carbon = new ChemicalRowDataGatewayRDS(1, "Carbon", 1.0, 6, 12.011, 0, 0, 0);

    assertEquals(1, carbon.getChemicalID());
    assertEquals(1, carbon.getType());
    assertEquals("Carbon", carbon.getName());
    assertEquals(1, carbon.getInventory(), 0.001);
    assertEquals(6, carbon.getAtomicNumber());
    assertEquals(12.011, carbon.getAtomicMass(), 0.001);
    assertEquals(0, carbon.getDissolvedBy());
    assertEquals(0, carbon.getMoles(), 0.001);
    assertEquals(0, carbon.getSolute());

    ChemicalRowDataGatewayRDS.dropTable();

    try {
      @SuppressWarnings("unused")
      ChemicalRowDataGatewayRDS funky = new ChemicalRowDataGatewayRDS(1, "Funky", 9.0, 17, 9.810, 0, 0, 0);
      fail();
    } catch (DatabaseException e) {
      assertEquals("Failed to insert into 'Chemical' table.", e.getSimpleDescription());
    }
  }

  /**
   * Test dropping a chemical from the table.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  public void testDeleteChemical() throws DatabaseException {

    ChemicalRowDataGatewayRDS.dropTable();
    ChemicalRowDataGatewayRDS.createTable();

    ChemicalRowDataGatewayRDS unknown = new ChemicalRowDataGatewayRDS(0, "Unknown", 123.0, 0, 0, 0, 0, 0);

    assertEquals(1, unknown.getChemicalID());
    assertEquals(0, unknown.getType());
    assertEquals("Unknown", unknown.getName());
    assertEquals(123.0, unknown.getInventory(), 0.001);
    assertEquals(0, unknown.getAtomicNumber());
    assertEquals(0, unknown.getAtomicMass(), 0.001);
    assertEquals(0, unknown.getDissolvedBy());
    assertEquals(0, unknown.getMoles(), 0.001);
    assertEquals(0, unknown.getSolute());

    unknown.delete();

    try {
      @SuppressWarnings("unused")
      ChemicalRowDataGatewayRDS unknown2 = new ChemicalRowDataGatewayRDS(1);
      fail();
    } catch (DatabaseException e) {
      assertEquals("Could not find Chemical with ID 1.", e.getSimpleDescription());
      ChemicalRowDataGatewayRDS.dropTable();
    }
  }

  /**
   * Test updating an entry in the database.
   * 
   * @throws DatabaseException when things go wrong.
   */
  @Test
  public void testUpdate() throws DatabaseException {
    ChemicalRowDataGatewayRDS.createTable();

    ChemicalRowDataGatewayRDS carbon = new ChemicalRowDataGatewayRDS(1, "Carbon", 1.0, 6, 12.011, 0, 0, 0);

    assertEquals(1, carbon.getChemicalID());
    assertEquals(1, carbon.getType());
    assertEquals("Carbon", carbon.getName());
    assertEquals(1, carbon.getInventory(), 0.001);
    assertEquals(6, carbon.getAtomicNumber());
    assertEquals(12.011, carbon.getAtomicMass(), 0.001);
    assertEquals(0, carbon.getDissolvedBy());
    assertEquals(0, carbon.getMoles(), 0.001);
    assertEquals(0, carbon.getSolute());

    carbon.setName("Carbon-13");
    carbon.setInventory(12.345);
    carbon.setAtomicMass(13.003);
    carbon.update();

    ChemicalRowDataGatewayRDS carbon13 = new ChemicalRowDataGatewayRDS(1);

    assertEquals(1, carbon.getChemicalID());
    assertEquals(1, carbon.getType());
    assertEquals("Carbon-13", carbon13.getName());
    assertEquals(12.345, carbon.getInventory(), 0.001);
    assertEquals(6, carbon.getAtomicNumber());
    assertEquals(13.003, carbon13.getAtomicMass(), 0.001);
    assertEquals(0, carbon.getDissolvedBy());
    assertEquals(0, carbon.getMoles(), 0.001);
    assertEquals(0, carbon.getSolute());

    ChemicalRowDataGatewayRDS.dropTable();
  }

}