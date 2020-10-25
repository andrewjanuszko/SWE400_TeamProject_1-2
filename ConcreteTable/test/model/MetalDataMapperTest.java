package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datadto.MetalDTO;
import datasource.AcidRowDataGatewayRDS;
import datasource.DatabaseException;
import datasource.MetalRowDataGatewayRDS;

class MetalDataMapperTest {

  @BeforeEach
  void setup() throws DatabaseException {
    MetalDataMapper.metalMap.clear();
    AcidDataMapper.acidMap.clear();
    MetalRowDataGatewayRDS.dropTable();
    AcidRowDataGatewayRDS.dropTable();
    AcidRowDataGatewayRDS.createTable();
    MetalRowDataGatewayRDS.createTable();
  }

  @Test
  void testCreate() throws DomainModelException {
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name", 1.0, 1, 1.0, 1.0);

    Metal metal2 = mapper.read(metal1.getID());

    assertEquals(metal1.getID(), metal2.getID());
    assertEquals(metal1.getName(), metal2.getName());
    assertEquals(metal1.getInventory(), metal2.getInventory());
    assertEquals(metal1.getAtomicNumber(), metal2.getAtomicNumber());
    assertEquals(metal1.getAtomicMass(), metal2.getAtomicMass());
    assertEquals(metal1.getAcidAmount(), metal2.getAcidAmount());
  }

  @Test
  void testDelete() throws DomainModelException {
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name", 1.0, 1, 1, 1.0);

    mapper.delete(metal1);

    assertEquals(null, mapper.read(metal1.getID()));
  }

  @Test
  void testUpdate() throws DomainModelException {
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name", 1.0, 1, 1.0, 1.0);
    metal1.setName("new name");
    mapper.update(metal1);
    Metal metal1Copy = mapper.read(metal1.getID());
    assertEquals(metal1.getID(), metal1Copy.getID());
  }

  @Test
  void testDTOListToAcidList() throws DomainModelException {
    List<MetalDTO> dtoList = new ArrayList<MetalDTO>();

    int id = 12;
    String name = "name";
    double inventory = 1.0;
    int atomicNumber = 1;
    double atomicMass = 1.0;
    double acidAmount = 1.0;
    int dissolvedBy = 12;

    MetalDTO dto = new MetalDTO(id, name, inventory, atomicNumber, atomicMass, acidAmount, dissolvedBy);
    Metal metal = new Metal(id, name, inventory, atomicNumber, atomicMass, acidAmount);
    dtoList.add(dto);

    assertEquals(dto.getMetalID(), metal.getID());
  }

  @Test
  void testGetAll() throws DomainModelException {
    MetalDataMapper mapper = new MetalDataMapper();
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    List<Metal> result = mapper.getAll();

    assertEquals(metal1.getID(), result.get(0).getID());
  }
  
  @Test
  void testFilterByDissolvedBy() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();
    Metal metal1 = mapper.create("metal", 1.0, 1, 1.0, 1.0);
    List<Metal> metalList = new ArrayList<Metal>();
    metalList.add(metal1);
    Acid acid1 = acidMapper.create("acid", 1.0, metalList, 1);
    
    
    List<Metal> result = mapper.filterByDissolvedBy(acid1.getID());
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByNameLike() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    
    List<Metal> result = mapper.filterByNameLike(metal1.getName());
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByInventory() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    
    List<Metal> result = mapper.filterByInventory(metal1.getInventory());
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByInventoryBetween() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    
    List<Metal> result = mapper.filterByInventoryBetween(metal1.getInventory(), metal1.getInventory());
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByAtomicNumber() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    
    List<Metal> result = mapper.filterByAtomicNumber(metal1.getAtomicNumber());
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByAtomicNumberBetween() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    
    List<Metal> result = mapper.filterByAtomicNumberBetween(metal1.getAtomicNumber(), metal1.getAtomicNumber());
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByAtomicMass() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    
    List<Metal> result = mapper.filterByAtomicMass(metal1.getAtomicMass());
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByAtomicMassBetween() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    
    List<Metal> result = mapper.filterByAtomicMassBetween(metal1.getAtomicMass(), metal1.getAtomicMass());
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByAcidAmount() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    
    List<Metal> result = mapper.filterByAcidAmount(metal1.getAcidAmount());
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByAcidAmountBetween() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    
    List<Metal> result = mapper.filterByAcidAmountBetween(metal1.getAcidAmount()-1, metal1.getAcidAmount()+1);
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByPartOfCompound() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);
    //TODO fix?
    List<Metal> result = mapper.filterByPartOfCompound(12);
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByLowInventory() throws DomainModelException{
    MetalDataMapper mapper = new MetalDataMapper();
    AcidDataMapper acidMapper = new AcidDataMapper();

    Acid acid1 = acidMapper.create("name", 1.0, new ArrayList<Metal>(), 1);
    Metal metal1 = mapper.create("name1", 1.0, 1, 1.0, 1.0);  //inventory of 1.0 is considered low
    
    List<Metal> result = mapper.filterByLowInventory();
    assertEquals(metal1.getID(), result.get(0).getID());
    
  }
}
