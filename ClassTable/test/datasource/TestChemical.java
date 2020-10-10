package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGateway;

/**
 * 
 * @author Isabella Boone
 *
 */
class TestChemical extends DatabaseTest {
  
  /**
   * Test getName method of ChemicalRowDataGateway
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  static void testGetName() throws SQLException, DatabaseException {
    ChemicalRowDataGateway 
        chem1 = new ChemicalRowDataGatewayRDS(1), 
        chem2 = new ChemicalRowDataGatewayRDS(2),
        chem3 = new ChemicalRowDataGatewayRDS(11),
        chem4 = new ChemicalRowDataGatewayRDS(12);

    assertEquals("acidname1", chem1.getName());
    assertEquals("acidname2", chem2.getName());
    assertEquals("basename1", chem3.getName());
    assertEquals("basename2", chem4.getName());
  }
  
  /**
   * Test getInhabits of ChemicalRowDataGateway
   * @throws SQLException
   * @throws DatabaseException
   */
  @Test
  static void testGetInhabits() throws SQLException, DatabaseException {
    ChemicalRowDataGateway 
        chem1 = new ChemicalRowDataGatewayRDS(1), 
        chem2 = new ChemicalRowDataGatewayRDS(2),
        chem3 = new ChemicalRowDataGatewayRDS(11),
        chem4 = new ChemicalRowDataGatewayRDS(12);

    assertEquals("acidinhabits1", chem1.getInhabits());
    assertEquals("acidinhabits2", chem2.getInhabits());
    assertEquals("baseinhabits1", chem3.getInhabits());
    assertEquals("baseinhabits2", chem4.getInhabits());
  }
  
  /**
   * Test the update function of ChemicalRowDataGateway
   * @throws DatabaseException 
   * @throws SQLException 
   */
  @Test
  static void testUpdate() throws SQLException, DatabaseException {
    ChemicalRowDataGateway chem_setter = new ChemicalRowDataGatewayRDS(9, "chemname9", "cheminhabits9"),
        chem_getter = new ChemicalRowDataGatewayRDS(9);

    assertEquals("chemname9", chem_getter.getName());
    assertEquals("cheminhabits9", chem_getter.getInhabits());
    
    chem_setter.setName("chemname6");
    chem_setter.setInhabits("cheminhabits6");
    
    chem_setter.update();
    chem_getter = new ChemicalRowDataGatewayRDS(9);
    
    assertEquals("chemname6", chem_getter.getName());
    assertEquals("cheminhabits6", chem_getter.getInhabits());
    
    chem_getter = new ChemicalRowDataGatewayRDS(9);
    chem_getter.delete();
  }
  
  /**
   * Test the delete method of ChemicalRowDataGateway
   */
  @Test
  static void testDelete() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS(9, "chemname9", "cheminhabits9");
    
    // Ensure it has been added
    assertEquals("chemname9", chem.getName());
    assertEquals("cheminhabits9", chem.getInhabits());
    
    chem.delete();
    
    try { 
      chem = new ChemicalRowDataGatewayRDS(9);
      fail("");
    } catch(DatabaseException | SQLException e) {
      assertTrue(true); 
    }
  }
  
  static void testAll() {
    try {
      testGetName();
      testGetInhabits();
      testDelete();
      testUpdate();
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }
  
  private static void insertChemicals() {
    // theoretically i should have free reign on any existing ones, like ones created for acid, base, etc
  }
  
}
