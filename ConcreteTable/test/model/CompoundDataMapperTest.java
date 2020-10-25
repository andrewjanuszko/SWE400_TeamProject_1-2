package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datadto.BaseDTO;
import datadto.CompoundDTO;
import datasource.CompoundMadeOfTableDataGatewayRDS;
import datasource.CompoundRowDataGatewayRDS;
import datasource.DatabaseException;
import datasource.ElementRowDataGatewayRDS;

class CompoundDataMapperTest {

  @BeforeEach
  void setup() throws DatabaseException {
    CompoundDataMapper.compoundMap.clear();
    ElementDataMapper.elementMap.clear();
    CompoundMadeOfTableDataGatewayRDS.dropTable();
    ElementRowDataGatewayRDS.createTable();
    CompoundRowDataGatewayRDS.createTable();
    CompoundMadeOfTableDataGatewayRDS.createTable();
  }
  
  @Test
  void testCreate() throws DomainModelException {
    CompoundDataMapper compoundMapper = new CompoundDataMapper();
    Compound compound1 = compoundMapper.create("name", 1.0, new ArrayList<Element>());
    Compound compound2 = compoundMapper.read(compound1.getID());

    assertEquals(compound1.getID(), compound2.getID());
    assertEquals(compound1.getName(), compound2.getName());
    assertEquals(compound1.getInventory(), compound2.getInventory());
    assertEquals(compound1.getMadeOf(), compound2.getMadeOf());
  }
  
  /**
   * Tests deleting an compound.
   * @throws DomainModelException
   */
  @Test
  void testDelete() throws DomainModelException {
    CompoundDataMapper compoundMapper = new CompoundDataMapper();
    Compound compound1 = compoundMapper.create("name", 1.0, new ArrayList<Element>());
    compoundMapper.delete(compound1);
    assertEquals(null, compoundMapper.read(compound1.getID()));
  }
  
  /**
   * Tests updataing an compound.
   * @throws DomainModelException
   */
  @Test
  void testUpdate() throws DomainModelException {
    CompoundDataMapper compoundMapper = new CompoundDataMapper();
    Compound compound1 = compoundMapper.create("name", 1.0, new ArrayList<Element>());
    compound1.setName("new name");
    compoundMapper.update(compound1);
    
    Compound compound1Copy = compoundMapper.read(compound1.getID());
    
    assertEquals(compound1.getID(),compound1Copy.getID());
    assertEquals(compound1.getName(), compound1Copy.getName());
    assertEquals(compound1.getInventory(), compound1Copy.getInventory());
    assertEquals(compound1.getMadeOf(), compound1Copy.getMadeOf());
  }
  
  @Test
  void testDTOListToCompoundList() throws DomainModelException {
    List<CompoundDTO> dtoList = new ArrayList<CompoundDTO>();
  
    int id = 12;
    String name = "name";
    double inventory = 1.0;
    
    CompoundDTO dto = new CompoundDTO(id, name, inventory);
    Compound compound = new Compound(id, name, inventory, new ArrayList<Element>());
    
    dtoList.add(dto);
    
    assertEquals(dto.getCompoundID(), compound.getID());
    assertEquals(dto.getName(), compound.getName());
    assertEquals(dto.getInventory(), compound.getInventory());
  }
  
  @Test
  void testGetAll() throws DomainModelException {
    CompoundDataMapper compoundMapper = new CompoundDataMapper();    
    Compound compound1 = compoundMapper.create("name", 1.0, new ArrayList<Element>());
    
    List<Compound> result = compoundMapper.getAll();
    assertEquals(compound1.getID(), result.get(0).getID());
  }
  
  @Test
  void testFilterByNameLike() throws DomainModelException{
    CompoundDataMapper compoundMapper = new CompoundDataMapper();
    Compound compound1 = compoundMapper.create("name", 1.0, new ArrayList<Element>());
        
    List<Compound> result = compoundMapper.filterByNameLike(compound1.getName());
    assertEquals(compound1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByInventory() throws DomainModelException{
    CompoundDataMapper compoundMapper = new CompoundDataMapper();   
    Compound compound1 = compoundMapper.create("name", 1.0, new ArrayList<Element>());
    
    List<Compound> result = compoundMapper.filterByInventory(compound1.getInventory());
    assertEquals(compound1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByInventoryBetween() throws DomainModelException{
    CompoundDataMapper compoundMapper = new CompoundDataMapper();  
    Compound compound1 = compoundMapper.create("name", 1.0, new ArrayList<Element>());
    
    List<Compound> result = compoundMapper.filterByInventoryBetween(compound1.getInventory(), compound1.getInventory());
    assertEquals(compound1.getID(), result.get(0).getID());
    
  }
  
  @Test
  void testFilterByMadeOf() throws DomainModelException{
    CompoundDataMapper mapper = new CompoundDataMapper();  
    ElementDataMapper eMapper = new ElementDataMapper();
    Element element1 = eMapper.create("name", 1.0, 1, 1.0);
    List<Element> eList = new ArrayList<Element>();
    eList.add(element1);
    Compound compound1 = mapper.create("name1", 1.0, eList);
    
    List<Compound> result = mapper.filterByMadeOf(element1.getID());
    assertEquals(compound1.getID(), result.get(0).getID());
    
  }
}
