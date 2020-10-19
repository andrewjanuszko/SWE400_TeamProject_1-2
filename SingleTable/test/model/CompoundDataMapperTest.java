package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGatewayRDS;
import datasource.ElementCompoundTableDataGatewayRDS;

class CompoundDataMapperTest {
  
  private static ElementCompoundTableDataGatewayRDS elementCompoundTableDataGateway;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    ChemicalRowDataGatewayRDS.createTable();
    elementCompoundTableDataGateway.createTable();
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
    elementCompoundTableDataGateway.dropTable();
    ChemicalRowDataGatewayRDS.dropTable();
  }

  @Test
  void test() {
    fail("Not yet implemented");
  }

}
