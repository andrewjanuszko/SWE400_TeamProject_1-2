package mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datadto.AcidDTO;
import datasource.AcidRowDataGatewayRDS;
import datasource.DatabaseException;
import datasource.MetalRowDataGatewayRDS;
import model.Acid;
import model.AcidDataMapper;
import model.DomainModelException;
import model.Metal;

class AcidDataMapperTest {

  @BeforeEach
  void setup() throws DatabaseException{
    MetalRowDataGatewayRDS.dropTable();
    AcidRowDataGatewayRDS.createTable();
    MetalRowDataGatewayRDS.createTable();
  }
  
  /**
   * Tests creating an acid.
   * @throws DomainModelException
   */
  @Test
  void testCreate() throws DomainModelException {
    AcidDataMapper mapper = new AcidDataMapper();
    Acid acid1 = mapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Acid acid2 = mapper.read(acid1.getID());
    
    assertEquals(acid1.getID(), acid2.getID());
    assertEquals(acid1.getName(), acid2.getName());
    assertEquals(acid1.getInventory(), acid2.getInventory());
    assertEquals(acid1.getDissolves(),acid2.getDissolves());
    assertEquals(acid1.getSolute(), acid2.getSolute());
  }
  
  /**
   * Tests deleting an acid.
   * @throws DomainModelException
   */
  @Test
  void testDelete() throws DomainModelException {
    AcidDataMapper mapper = new AcidDataMapper();
    Acid acid1 = mapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    mapper.delete(acid1);
    assertEquals(null, mapper.read(acid1.getID()));
  }
  
  /**
   * Tests updataing an acid.
   * @throws DomainModelException
   */
  @Test
  void testUpdate() throws DomainModelException {
    AcidDataMapper mapper = new AcidDataMapper();
    Acid acid1 = mapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    acid1.setName("new name");
    mapper.update(acid1);
    
    Acid acid1Copy = mapper.read(acid1.getID());
    
    assertEquals(acid1.getID(), acid1Copy.getID());
    assertEquals(acid1.getName(), acid1Copy.getName());
    assertEquals(acid1.getInventory(), acid1Copy.getInventory());
    assertEquals(acid1.getDissolves(),acid1Copy.getDissolves());
    assertEquals(acid1.getSolute(), acid1Copy.getSolute());
  }
  
  @Test
  void testDTOListToAcidList() throws DomainModelException {
    List<AcidDTO> dtoList = new ArrayList<AcidDTO>();
  
    int id = 12;
    String name = "name";
    double inventory = 1.0;
    List<Metal> dissolves = new ArrayList<Metal>();
    int solute = 1;
    
    AcidDTO dto = new AcidDTO(id, name, inventory, solute);
    Acid acid = new Acid(id, name, inventory, dissolves, solute);
    
    dtoList.add(dto);
    
    assertEquals(dto.getAcidID(), acid.getID());
    assertEquals(dto.getName(), acid.getName());
    assertEquals(dto.getInventory(), acid.getInventory());
    assertEquals(dto.getSoluteID(), acid.getSolute());
    
  }
  
  
  
}
