package datasource;

import dataENUM.ChemicalEnum;

public class PopulateDatabase {

  @SuppressWarnings("unused")
  public static void main(String[] args) throws DatabaseException {
    ChemicalRowDataGateway.createTable();
    ElementCompoundTableDataGateway.getSingletonInstance().createTable();
    
    /**
     * Insert Elements into the table.
     */
    ChemicalRowDataGateway hydrogen = new ChemicalRowDataGateway(ChemicalEnum.ELEMENT.getIntValue(), "Hydrogen", 3.14, 1, 1.008, 0, 0, 0);
    ChemicalRowDataGateway helium = new ChemicalRowDataGateway(ChemicalEnum.ELEMENT.getIntValue(), "Helium", 6.28, 2, 4.003, 0, 0, 0);
    ChemicalRowDataGateway carbon = new ChemicalRowDataGateway(ChemicalEnum.ELEMENT.getIntValue(), "Carbon", 9.42, 6, 12.011, 0, 0, 0);
    ChemicalRowDataGateway nitrogen = new ChemicalRowDataGateway(ChemicalEnum.ELEMENT.getIntValue(), "Nitrogen", 12.56, 7, 14.007, 0, 0, 0);
    ChemicalRowDataGateway oxygen = new ChemicalRowDataGateway(ChemicalEnum.ELEMENT.getIntValue(), "Oxygen", 15.70, 8, 15.999, 0, 0, 0);
    ChemicalRowDataGateway chlorine = new ChemicalRowDataGateway(ChemicalEnum.ELEMENT.getIntValue(), "Chlorine", 18.84, 17, 35.45, 0, 0, 0);
    
    /**
     * Insert Metals into the table.
     */
    ChemicalRowDataGateway sodium = new ChemicalRowDataGateway(ChemicalEnum.METAL.getIntValue(), "Sodium", 21.98, 11, 22.990, 0, 0, 0);
    ChemicalRowDataGateway iron = new ChemicalRowDataGateway(ChemicalEnum.METAL.getIntValue(), "Iron", 25.12, 26, 55.938, 0, 0, 0);
    ChemicalRowDataGateway copper = new ChemicalRowDataGateway(ChemicalEnum.METAL.getIntValue(), "Copper", 28.26, 29, 63.546, 0, 0, 0);
    ChemicalRowDataGateway mercury = new ChemicalRowDataGateway(ChemicalEnum.METAL.getIntValue(), "Mercury", 31.40, 80, 200.59, 0, 0, 0);
    ChemicalRowDataGateway zinc = new ChemicalRowDataGateway(ChemicalEnum.METAL.getIntValue(), "Zinc", 34.54, 30, 65.38, 0, 0, 0);
    ChemicalRowDataGateway silver = new ChemicalRowDataGateway(ChemicalEnum.METAL.getIntValue(), "Silver", 37.68, 47, 107.87, 0, 0, 0);
    
    /**
     * Insert Compounds into the table.
     */
    ChemicalRowDataGateway water = new ChemicalRowDataGateway(ChemicalEnum.COMPOUND.getIntValue(), "Water", 0, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway carbonMonoxide = new ChemicalRowDataGateway(ChemicalEnum.COMPOUND.getIntValue(), "Carbon Monoxide", 0, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway sodiumChloride = new ChemicalRowDataGateway(ChemicalEnum.COMPOUND.getIntValue(), "Sodium Chloride", 0, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway sucrose = new ChemicalRowDataGateway(ChemicalEnum.COMPOUND.getIntValue(), "Sucrose", 0, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway silverChloride = new ChemicalRowDataGateway(ChemicalEnum.COMPOUND.getIntValue(), "Silver Chloride", 0, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway nitrate = new ChemicalRowDataGateway(ChemicalEnum.COMPOUND.getIntValue(), "Nitrate", 0, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway sodiumBicarbonate = new ChemicalRowDataGateway(ChemicalEnum.COMPOUND.getIntValue(), "Sodium Bicarbonate", 0, 0, 0, 0, 0, 0);
    
    /**
     * Insert Acids into the table.
     */
    ChemicalRowDataGateway hydrochloricAcid = new ChemicalRowDataGateway(ChemicalEnum.ACID.getIntValue(), "Hydrochloric Acid", 10, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway nitricAcid = new ChemicalRowDataGateway(ChemicalEnum.ACID.getIntValue(), "Nitric Acid", 20, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway sulfuricAcid = new ChemicalRowDataGateway(ChemicalEnum.ACID.getIntValue(), "Sulfuric Acid", 30, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway carbonicAcid = new ChemicalRowDataGateway(ChemicalEnum.ACID.getIntValue(), "Carbonic Acid", 40, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway formicAcid = new ChemicalRowDataGateway(ChemicalEnum.ACID.getIntValue(), "Formic Acid", 10, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway citricAcid = new ChemicalRowDataGateway(ChemicalEnum.ACID.getIntValue(), "Citric Acid", 10, 0, 0, 0, 0, 0);
    
    /**
     * Insert Bases into the table.
     */
    ChemicalRowDataGateway potassiumHydroxide = new ChemicalRowDataGateway(ChemicalEnum.BASE.getIntValue(), "Potassium Hydroxide", 10, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway sodiumHydroxide = new ChemicalRowDataGateway(ChemicalEnum.BASE.getIntValue(), "Sodium Hydroxide", 70, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway calciumHydroxide = new ChemicalRowDataGateway(ChemicalEnum.BASE.getIntValue(), "Calcium Hydroxide", 18, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway lithiumHydroxide = new ChemicalRowDataGateway(ChemicalEnum.BASE.getIntValue(), "Lithium Hydroxide", 30, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway bariumHydroxide = new ChemicalRowDataGateway(ChemicalEnum.BASE.getIntValue(), "Barium Hydroxide", 10, 0, 0, 0, 0, 0);
    ChemicalRowDataGateway strontiumHydroxide = new ChemicalRowDataGateway(ChemicalEnum.BASE.getIntValue(), "Strontium Hydroxide",20, 0, 0, 0, 0, 0);
  }
  
}
