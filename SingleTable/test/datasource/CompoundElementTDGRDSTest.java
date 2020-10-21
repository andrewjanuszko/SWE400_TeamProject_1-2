package datasource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataDTO.ElementCompoundDTO;
import dataENUM.ChemicalEnum;

public abstract class CompoundElementTDGRDSTest extends DatabaseTest {

  protected ElementCompoundTableDataGatewayRDS gateway;

  /**
   * Gets a singleton.
   */
  protected abstract ElementCompoundTableDataGatewayRDS getSingletonInstance();

  @BeforeEach
  public void buildDB() throws DatabaseException {
    ChemicalRowDataGatewayRDS.createTable();
    gateway = getSingletonInstance();
    gateway.createTable();
  }
  
  @AfterEach
  public void deconstructDB() throws DatabaseException {
    gateway.dropTable();
    ChemicalRowDataGatewayRDS.dropTable();
  }
  
  @Test
  public void isSingleton() throws DatabaseException {
    ElementCompoundTableDataGateway gateway1 = getSingletonInstance();
    ElementCompoundTableDataGateway gateway2 = getSingletonInstance();
    assertNotNull(gateway1);
    assertNotNull(gateway2);
    assertEquals(gateway1, gateway2);
  }
  
  @Test
  public void createCompound() throws DatabaseException {
    ChemicalRowDataGatewayRDS hydrogen = new ChemicalRowDataGatewayRDS(ChemicalEnum.ELEMENT.getIntValue(), "Hydrogen", 90, 1, 1.007, 0, 0, 0);
    ChemicalRowDataGatewayRDS oxygen = new ChemicalRowDataGatewayRDS(ChemicalEnum.ELEMENT.getIntValue(), "Oxygen", 90, 8, 15.999, 0, 0, 0);
    
    ChemicalRowDataGatewayRDS hydrogenDioxide = new ChemicalRowDataGatewayRDS(ChemicalEnum.COMPOUND.getIntValue(), "Hydrogen Dioxide", 90, 0, 0, 0, 0, 0);
    
    assertEquals(1, hydrogen.getID());
    assertEquals(2, oxygen.getID());
    assertEquals(3, hydrogenDioxide.getID());
    
    gateway.create(3, 1);
    gateway.create(3, 2);
    
    ElementCompoundDTO water = gateway.readElementsFromCompound(3);
    assertEquals(2, water.getRelations().size());
  }
}
