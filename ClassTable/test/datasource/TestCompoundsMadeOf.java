package datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author kimberlyoneill
 *
 */
class TestCompoundsMadeOf extends DatabaseTest {

  @Test
  void testGetName() {
    // create tables
    ChemicalRDG chem = new ChemicalRDGRDS(); 
    ElementRDG ele = new ElementRDGRDS();
    CompoundsMadeOfTDG com = new CompoundsMadeOfTDGRDS();
    
    chem.dropTable();
    ele.dropTableElement();
    com.dropTableCompoundMadeFromElement();
    
    chem.createTable();
    ele.createTableElement();
    com.createTableCompoundMadeFrom();

    // initialize elements
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", 1.0);
    ElementRDG element2 = new ElementRDGRDS(23, 44, 6, "element2", 1.0);
    ElementRDG element3 = new ElementRDGRDS(61, 22, 2, "element3", 1.0);
    ElementRDG element4 = new ElementRDGRDS(11, 5, 1, "element4", 1.0);
    ElementRDG element5 = new ElementRDGRDS(14, 12, 2, "element5", 1.0);
    ElementRDG element6 = new ElementRDGRDS(16, 15, 3, "element6", 1.0);

    List<Integer> madeOf1 = new ArrayList<Integer>();
    madeOf1.add(55);
    madeOf1.add(23);
    List<Integer> madeOf2 = new ArrayList<Integer>();
    madeOf1.add(61);
    madeOf1.add(11);
    List<Integer> madeOf3 = new ArrayList<Integer>();
    madeOf1.add(16);
    madeOf1.add(14);

    // initialize compounds
    CompoundsMadeOfTDG compound1 = new CompoundsMadeOfTDGRDS(1, madeOf1, "chemicalname1",
        1.0);
    CompoundsMadeOfTDG compound2 = new CompoundsMadeOfTDGRDS(2, madeOf2, "chemicalname2",
        1.0);
    CompoundsMadeOfTDG compound3 = new CompoundsMadeOfTDGRDS(3, madeOf3, "chemicalname3",
        1.0);

    // compound getters
    CompoundsMadeOfTDG compoundsGet1 = new CompoundsMadeOfTDGRDS(1);
    CompoundsMadeOfTDG compoundsGet2 = new CompoundsMadeOfTDGRDS(2);
    CompoundsMadeOfTDG compoundsGet3 = new CompoundsMadeOfTDGRDS(3);

    assertEquals("chemicalname1", compoundsGet1.getCompoundName());
    assertEquals("chemicalname2", compoundsGet2.getCompoundName());
    assertEquals("chemicalname3", compoundsGet3.getCompoundName());
  }

  @Test
  void testGetInhabits() {
    // create tables
    ChemicalRDG chem = new ChemicalRDGRDS(); 
    ElementRDG ele = new ElementRDGRDS();
    CompoundsMadeOfTDG com = new CompoundsMadeOfTDGRDS();
    
    chem.dropTable();
    ele.dropTableElement();
    com.dropTableCompoundMadeFromElement();
    
    chem.createTable();
    ele.createTableElement();
    com.createTableCompoundMadeFrom();

    // initialize elements
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", 1.0);
    ElementRDG element2 = new ElementRDGRDS(23, 44, 6, "element2", 1.0);
    ElementRDG element3 = new ElementRDGRDS(61, 22, 2, "element3", 1.0);
    ElementRDG element4 = new ElementRDGRDS(11, 5, 1, "element4", 1.0);
    ElementRDG element5 = new ElementRDGRDS(14, 12, 2, "element5", 1.0);
    ElementRDG element6 = new ElementRDGRDS(16, 15, 3, "element6", 1.0);

    List<Integer> madeOf1 = new ArrayList<Integer>();
    madeOf1.add(55);
    madeOf1.add(23);
    List<Integer> madeOf2 = new ArrayList<Integer>();
    madeOf1.add(61);
    madeOf1.add(11);
    List<Integer> madeOf3 = new ArrayList<Integer>();
    madeOf1.add(16);
    madeOf1.add(14);

    // initialize compounds
    CompoundsMadeOfTDG compound1 = new CompoundsMadeOfTDGRDS(1, madeOf1, "chemicalname1",
        1.0);
    CompoundsMadeOfTDG compound2 = new CompoundsMadeOfTDGRDS(2, madeOf2, "chemicalname2",
        1.0);
    CompoundsMadeOfTDG compound3 = new CompoundsMadeOfTDGRDS(3, madeOf3, "chemicalname3",
        1.0);

    // compound getters
    CompoundsMadeOfTDG compoundsGet1 = new CompoundsMadeOfTDGRDS(1);
    CompoundsMadeOfTDG compoundsGet2 = new CompoundsMadeOfTDGRDS(2);
    CompoundsMadeOfTDG compoundsGet3 = new CompoundsMadeOfTDGRDS(3);

    assertEquals(1.0, compoundsGet1.getInventory());
    assertEquals(1.0, compoundsGet2.getInventory());
    assertEquals(1.0, compoundsGet3.getInventory());

  }

  @Test
  void testFindSetElementid() {
    // Drop / Create tables
    ChemicalRDG chem = new ChemicalRDGRDS(); 
    ElementRDG ele = new ElementRDGRDS();
    CompoundsMadeOfTDG com = new CompoundsMadeOfTDGRDS();
    
    chem.dropTable();
    ele.dropTableElement();
    com.dropTableCompoundMadeFromElement();
    
    chem.createTable();
    ele.createTableElement();
    com.createTableCompoundMadeFrom();
    
    // initialize elements
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", 1.0);
    ElementRDG element2 = new ElementRDGRDS(23, 44, 6, "element2", 1.0);

    List<Integer> madeOf = new ArrayList<Integer>();
    madeOf.add(55);
    madeOf.add(23);

    //initialize compounds
    CompoundsMadeOfTDG compounds = new CompoundsMadeOfTDGRDS(1, madeOf, "chemicalname1",
        1.0);

    List<CompoundDTO> list = compounds.findSetElementId(1);
    List<CompoundDTO> expected = new ArrayList<>();
    
    CompoundDTO c1 = new CompoundDTO(1, 55);
    CompoundDTO c2 = new CompoundDTO(1, 23);
    expected.add(c1);
    expected.add(c2);
    assertEquals(expected, list);
  }

  @Test
  void TestFindCompoundId() {
    //create tables
    ChemicalRDG chem = new ChemicalRDGRDS(); 
    ElementRDG ele = new ElementRDGRDS();
    CompoundsMadeOfTDG com = new CompoundsMadeOfTDGRDS();
    
    chem.dropTable();
    ele.dropTableElement();
    com.dropTableCompoundMadeFromElement();
    
    chem.createTable();
    ele.createTableElement();
    com.createTableCompoundMadeFrom();
    
    //initialize elements
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", 1.0);
    ElementRDG element2 = new ElementRDGRDS(23, 44, 6, "element2", 1.0);

    List<Integer> madeOf1 = new ArrayList<Integer>();
    madeOf1.add(55);
    List<Integer> madeOf2 = new ArrayList<Integer>();
    madeOf2.add(23);

    //initialize compounds
    CompoundsMadeOfTDG compound1 = new CompoundsMadeOfTDGRDS(1, madeOf1, "chemicalname1",
        1.0), compound2 = new CompoundsMadeOfTDGRDS(2, madeOf2, "chemicalname2", 1.0);

    // Compound1
    CompoundDTO c1 = new CompoundDTO(1, 55);
    CompoundDTO c2 = new CompoundDTO(1, 23);
    
    List<CompoundDTO> list1 = compound1.findSetElementId(1);
    List<CompoundDTO> expected1 = new ArrayList<>();
    expected1.add(c1);
    assertEquals(expected1, list1);

    // Compound2
    List<CompoundDTO> list2 = compound1.findSetElementId(2);
    List<CompoundDTO> expected2 = new ArrayList<>();
    expected2.add(c2);
    assertEquals(expected2, list2);

  }
}
