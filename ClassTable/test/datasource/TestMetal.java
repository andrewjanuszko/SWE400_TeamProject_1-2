package datasource;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author kimberlyoneill
 *
 */
class TestMetal {

  @Test
  static void testGetName() {    
    MetalRowDataGateway metalGet1 = new MetalRowDataGatewayRDS(31);
    MetalRowDataGateway metalGet2 = new MetalRowDataGatewayRDS(32);
    MetalRowDataGateway metalGet3 = new MetalRowDataGatewayRDS(33);
    MetalRowDataGateway metalGet4 = new MetalRowDataGatewayRDS(34);
    
    assertEquals("metalname1", metalGet1.getName());
    assertEquals("metalname2", metalGet2.getName());
    assertEquals("metalname3", metalGet3.getName());
    assertEquals("metalname4", metalGet4.getName());
  }

  @Test
  static void testGetInhabits() {    
    MetalRowDataGateway metalGet1 = new MetalRowDataGatewayRDS(31);
    MetalRowDataGateway metalGet2 = new MetalRowDataGatewayRDS(32);
    MetalRowDataGateway metalGet3 = new MetalRowDataGatewayRDS(33);
    MetalRowDataGateway metalGet4 = new MetalRowDataGatewayRDS(34);
    
    assertEquals("metalinhabits1", metalGet1.getInhabits());
    assertEquals("metalinhabits2", metalGet2.getInhabits());
    assertEquals("metalinhabits3", metalGet3.getInhabits());
    assertEquals("metalinhabits4", metalGet4.getInhabits());
  }

  @Test
  static void testGetDissolvedBy() {    
    MetalRowDataGateway metalGet1 = new MetalRowDataGatewayRDS(31);
    MetalRowDataGateway metalGet2 = new MetalRowDataGatewayRDS(32);
    MetalRowDataGateway metalGet3 = new MetalRowDataGatewayRDS(33);
    MetalRowDataGateway metalGet4 = new MetalRowDataGatewayRDS(34);
    
    assertEquals(1, metalGet1.getDissolvedBy());
    assertEquals(2, metalGet2.getDissolvedBy());
    assertEquals(3, metalGet3.getDissolvedBy());
    assertEquals(4, metalGet4.getDissolvedBy());
  }

  @Test
  static void testGetSet() {    
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS(); 
    List<MetalRowDataGatewayRDS> metalGet = metal.findSet(5);
    
    assertEquals("metalname5", metalGet.get(0).getName());
    assertEquals("metalname6", metalGet.get(1).getName());
  }
  
  static void testAll() {
    insertMetals();
    testGetName();
    testGetInhabits();
    testGetDissolvedBy();
    testGetSet(); 
  }
  
  private static void insertMetals() {
    MetalRowDataGateway 
    metal = new MetalRowDataGatewayRDS(31, 1, "metalname1", "metalinhabits1");
    metal = new MetalRowDataGatewayRDS(32, 2, "metalname2", "metalinhabits2");
    metal = new MetalRowDataGatewayRDS(33, 3, "metalname3", "metalinhabits3");
    metal = new MetalRowDataGatewayRDS(34, 4, "metalname4", "metalinhabits4");
    metal = new MetalRowDataGatewayRDS(35, 5, "metalname5", "metalinhabits5");
    metal = new MetalRowDataGatewayRDS(36, 5, "metalname6", "metalinhabits6");
  }
}
