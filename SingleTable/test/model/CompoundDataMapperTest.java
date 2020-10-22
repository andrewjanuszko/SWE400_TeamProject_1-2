package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datasource.ChemicalRowDataGateway;
import datasource.ElementCompoundTableDataGateway;

class CompoundDataMapperTest {
  
  private static ElementCompoundTableDataGateway elementCompoundTableDataGateway;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    ChemicalRowDataGateway.createTable();
    elementCompoundTableDataGateway.createTable();
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
    elementCompoundTableDataGateway.dropTable();
    ChemicalRowDataGateway.dropTable();
  }

  @Test
  void test() {
    fail("Not yet implemented");
  }

}
