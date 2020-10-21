package datasource;

import dataENUM.ChemicalEnum;

public class PopulateDatabase {

  public static void main(String[] args) throws DatabaseException {
    ElementCompoundTableDataGatewayRDS.getSingletonInstance().createTable();
    ChemicalRowDataGatewayRDS.createTable();
    
    ChemicalRowDataGatewayRDS hydrogen = new ChemicalRowDataGatewayRDS(ChemicalEnum.ELEMENT.getIntValue(), "Hydrogen", 3.14, 1, 1.008, 0, 0, 0);
    ChemicalRowDataGatewayRDS helium = new ChemicalRowDataGatewayRDS(ChemicalEnum.ELEMENT.getIntValue(), "Hydrogen", 3.14, 1, 1.008, 0, 0, 0);
    
  }
  
}
