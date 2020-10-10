package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Isabella Boone
 * 
 */
class TestAcid extends DatabaseTest {

  @Test
  static void testGetName() throws SQLException, DatabaseException {
    AcidRowDataGateway
        acid1 = new AcidRowDataGatewayRDS(1), 
        acid2 = new AcidRowDataGatewayRDS(2),
        acid3 = new AcidRowDataGatewayRDS(3),
        acid4 = new AcidRowDataGatewayRDS(4);
        

    // Tests
    assertEquals("acidname1", acid1.getName());
    assertEquals("acidname2", acid2.getName());
    assertEquals("acidname3", acid3.getName());
    assertEquals("acidname4", acid4.getName());
  }

  @Test
  static void testGetInhabits() throws SQLException, DatabaseException {
    AcidRowDataGateway 
        acid1 = new AcidRowDataGatewayRDS(1), 
        acid2 = new AcidRowDataGatewayRDS(2),
        acid3 = new AcidRowDataGatewayRDS(3),
        acid4 = new AcidRowDataGatewayRDS(4); 

    // Tests
    assertEquals("acidinhabits1", acid1.getInhabits());
    assertEquals("acidinhabits2", acid2.getInhabits());
    assertEquals("acidinhabits3", acid3.getInhabits());
    assertEquals("acidinhabits4", acid4.getInhabits());
  }

  @Test
  static void testGetSolute() throws SQLException, DatabaseException {
    AcidRowDataGateway 
        acid1 = new AcidRowDataGatewayRDS(1), 
        acid2 = new AcidRowDataGatewayRDS(2),
        acid3 = new AcidRowDataGatewayRDS(3),
        acid4 = new AcidRowDataGatewayRDS(4);

    // Test
    assertEquals(51, acid1.getSolute());
    assertEquals(52, acid2.getSolute());
    assertEquals(53, acid3.getSolute());
    assertEquals(54, acid4.getSolute());
  }

  @Test
  static void testDelete() {
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS(9, 59, "acidname1", "acidinhabits1");

    // Ensure it has been added
    assertEquals("acidname1", acid.getName());
    assertEquals("acidinhabits1", acid.getInhabits());
    assertEquals(59, acid.getSolute());

    acid.delete();

    try {
      acid = new AcidRowDataGatewayRDS(9);
      fail("");
    } catch (DatabaseException | SQLException e) {
      assertTrue(true);
    }
  }

  @Test
  static void testUpdate() throws SQLException, DatabaseException {
    AcidRowDataGateway acid_setter = new AcidRowDataGatewayRDS(9, 59, "acidname9", "acidinhabits9"),
        acid_getter = new AcidRowDataGatewayRDS(9);
    
    assertEquals("acidname9", acid_getter.getName());
    assertEquals("acidinhabits9", acid_getter.getInhabits());
    assertEquals(59, acid_getter.getSolute());
    
    acid_setter.setName("acidname6");
    acid_setter.setInhabits("acidinhabits6");
    acid_setter.setSolute(56);
    acid_setter.update();
    
    acid_getter = new AcidRowDataGatewayRDS(9); 
    
    assertEquals("acidname6", acid_getter.getName());
    assertEquals("acidinhabits6", acid_getter.getInhabits());
    assertEquals(56, acid_getter.getSolute());
    
    acid_getter.delete();
  }

  @Test
  static void testGetSet() {
    AcidRowDataGateway getter = new AcidRowDataGatewayRDS();
    List<AcidRowDataGatewayRDS> acidGet = getter.findSet(55); 
    
    assertEquals("acidname5", acidGet.get(0).getName());
    assertEquals("acidname6", acidGet.get(1).getName());
  }

  static void testAll() {
    try {
      insertAcids(); 
      testGetName();
      testGetInhabits();
      testGetSolute();
      testDelete();
      testUpdate();
      testGetSet(); 
    } catch (SQLException | DatabaseException e) {
      e.printStackTrace();
    }
  }

  private static void insertAcids() {
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS(1, 51, "acidname1", "acidinhabits1");
    acid = new AcidRowDataGatewayRDS(2, 52, "acidname2", "acidinhabits2");
    acid = new AcidRowDataGatewayRDS(3, 53, "acidname3", "acidinhabits3");
    acid = new AcidRowDataGatewayRDS(4, 54, "acidname4", "acidinhabits4");
    acid = new AcidRowDataGatewayRDS(5, 55, "acidname5", "acidinhabits5");
    acid = new AcidRowDataGatewayRDS(6, 55, "acidname6", "acidinhabits6");
    
  }
}
