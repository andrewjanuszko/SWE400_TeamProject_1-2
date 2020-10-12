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
  void testGetName() {
    ChemicalRDG chem = new ChemicalRDGRDS();
    MetalRDG met = new MetalRDGRDS(); 
    met.dropAllTables();
    chem.createTable();
    met.createTableMetal();
    
    MetalRDG metal = new MetalRDGRDS(1, 55, "chemicalname1", "inhabits1");
    
    MetalRDG metalGet = new MetalRDGRDS(1);

    assertEquals("chemicalname1", metalGet.getName());
  }

  @Test
  void testGetInhabits() {
    // Create row data gateways
    ChemicalRDG chem = new ChemicalRDGRDS();
    MetalRDG met = new MetalRDGRDS(); 
    met.dropAllTables();
    chem.createTable();
    met.createTableMetal();
    
    MetalRDG metal = new MetalRDGRDS(1, 15, "chemicalname1", "inhabits1");
    
    MetalRDG metalGet = new MetalRDGRDS(1);

    // Test
    assertEquals("inhabits1", metalGet.getInhabits());
  }

  @Test
  void testGetDissolvedBy() {
    ChemicalRDG chem = new ChemicalRDGRDS();
    MetalRDG met = new MetalRDGRDS(); 
    met.dropAllTables();
    chem.createTable();
    met.createTableMetal();
    
    MetalRDG metal = new MetalRDGRDS(1, 15, "chemicalname1", "inhabits1");

    MetalRDG metalGet = new MetalRDGRDS(1);
    // Test
    assertEquals(15, metalGet.getDissolvedBy());
  }

  @Test
  void testGetSet() {
    ChemicalRDG chem = new ChemicalRDGRDS();
    AcidRDG acid = new AcidRDGRDS();
    MetalRDG met = new MetalRDGRDS();
    acid.dropAllTables();
    met.dropTableMetal();
    chem.createTable();
    acid.createTable();
    met.createTableMetal();
    
    MetalRDG metal1 = new MetalRDGRDS(1, 15, "chemicalname1", "inhabits1");
    MetalRDG metal2 = new MetalRDGRDS(2, 15, "chemicalname2", "inhabits2");
    
    List<MetalRDGRDS> metalGet = metal2.findSet(15);
    
    assertEquals("chemicalname1", metalGet.get(0).getName());
    assertEquals("chemicalname2", metalGet.get(1).getName());
  }
}
