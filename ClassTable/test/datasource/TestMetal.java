package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

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
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();
    
    MetalRowDataGateway metalGet = new MetalRowDataGatewayRDS(1);

    // Test
    assertEquals("inhabits1", metalGet.getInhabits());
  }

  @Test
  void testGetDissolvedBy() {
    MetalRowDataGateway metal = new MetalRowDataGatewayRDS(1, 15, "chemicalname1", "inhabits1");
    ChemicalRowDataGateway chem = new ChemicalRowDataGatewayRDS();
    AcidRowDataGateway acid = new AcidRowDataGatewayRDS();

    MetalRowDataGateway metalGet = new MetalRowDataGatewayRDS(1);
    // Test
    assertEquals(15, metalGet.getDissolvedBy());
  }

}
