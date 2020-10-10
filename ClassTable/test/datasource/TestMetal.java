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
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS(1, 55, "chemicalname1", "inhabits1");
    
    MetalRowDataGateway metalGet = new MetalRowDataGatewayRDS(1);

    assertEquals("chemicalname1", metalGet.getName());
  }

  @Test
  static void testGetInhabits() {    
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");
    
    MetalRowDataGateway metalGet = new MetalRowDataGatewayRDS(1);

    // Test
    assertEquals("inhabits1", metalGet.getInhabits());
  }

  @Test
  static void testGetDissolvedBy() {    
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");

    MetalRowDataGateway metalGet = new MetalRowDataGatewayRDS(1);
    // Test
    assertEquals(15, metalGet.getDissolvedBy());
  }

  @Test
  static void testGetSet() {    
    MetalRowDataGateway metal1 = new MetalRowDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");
    MetalRowDataGateway metal2 = new MetalRowDataGatewayRDS(2, 15, "chemicalname2", "inhabits2");
    
    List<MetalRowDataGatewayRDS> metalGet = metal2.findSet(15);
    
    assertEquals("chemicalname1", metalGet.get(0).getName());
    assertEquals("chemicalname2", metalGet.get(1).getName());
  }
  
  static void testAll() {
    testGetName();
    testGetInhabits();
    testGetDissolvedBy();
    testGetSet(); 
  }
}
