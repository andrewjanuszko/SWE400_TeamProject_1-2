package datasource;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestMetal {

  @Test
  void testGetName() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS(1, 55, "chemicalname1", "inhabits1");
    
    MetalRowDataGateway metalGet = new MetalRowDataGatewayRDS(1);

    assertEquals("chemicalname1", metalGet.getName());
  }

  @Test
  void testGetInhabits() {
    // Create row data gateways
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");
    
    MetalRowDataGateway metalGet = new MetalRowDataGatewayRDS(1);

    // Test
    assertEquals("inhabits1", metalGet.getInhabits());
  }

  @Test
  void testGetDissolvedBy() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");

    MetalRowDataGateway metalGet = new MetalRowDataGatewayRDS(1);
    // Test
    assertEquals(15, metalGet.getDissolvedBy());
  }

  @Test
  void testGetSet() {
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();
    MetalRowDataGateway emptyMetal = new MetalRowDataGatewayRDS();
    MetalRowDataGateway metal1 = new MetalRowDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");
    MetalRowDataGatewayRDS metal2 = new MetalRowDataGatewayRDS(2, 15, "chemicalname2", "inhabits2");
    
    List<MetalRowDataGatewayRDS> metalGet = metal2.findSet(15);
    
    assertEquals("chemicalname1", metalGet.get(0).getName());
    assertEquals("chemicalname2", metalGet.get(1).getName());
  }
}
