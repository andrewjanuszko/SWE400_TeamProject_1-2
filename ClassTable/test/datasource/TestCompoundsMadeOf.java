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
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", "inhabits");
    ElementRDG element2 = new ElementRDGRDS(23, 44, 6, "element2", "inhabits");
    ElementRDG element3 = new ElementRDGRDS(61, 22, 2, "element3", "inhabits");
    ElementRDG element4 = new ElementRDGRDS(11, 5, 1, "element4", "inhabits");
    ElementRDG element5 = new ElementRDGRDS(14, 12, 2, "element5", "inhabits");
    ElementRDG element6 = new ElementRDGRDS(16, 15, 3, "element6", "inhabits");

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
        "inhabits1");
    CompoundsMadeOfTDG compound2 = new CompoundsMadeOfTDGRDS(2, madeOf2, "chemicalname2",
        "inhabits2");
    CompoundsMadeOfTDG compound3 = new CompoundsMadeOfTDGRDS(3, madeOf3, "chemicalname3",
        "inhabits2");

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
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", "inhabits");
    ElementRDG element2 = new ElementRDGRDS(23, 44, 6, "element2", "inhabits");
    ElementRDG element3 = new ElementRDGRDS(61, 22, 2, "element3", "inhabits");
    ElementRDG element4 = new ElementRDGRDS(11, 5, 1, "element4", "inhabits");
    ElementRDG element5 = new ElementRDGRDS(14, 12, 2, "element5", "inhabits");
    ElementRDG element6 = new ElementRDGRDS(16, 15, 3, "element6", "inhabits");

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
        "inhabits1");
    CompoundsMadeOfTDG compound2 = new CompoundsMadeOfTDGRDS(2, madeOf2, "chemicalname2",
        "inhabits2");
    CompoundsMadeOfTDG compound3 = new CompoundsMadeOfTDGRDS(3, madeOf3, "chemicalname3",
        "inhabits3");

    // compound getters
    CompoundsMadeOfTDG compoundsGet1 = new CompoundsMadeOfTDGRDS(1);
    CompoundsMadeOfTDG compoundsGet2 = new CompoundsMadeOfTDGRDS(2);
    CompoundsMadeOfTDG compoundsGet3 = new CompoundsMadeOfTDGRDS(3);

    assertEquals("inhabits1", compoundsGet1.getInhabits());
    assertEquals("inhabits2", compoundsGet2.getInhabits());
    assertEquals("inhabits3", compoundsGet3.getInhabits());

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
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", "inhabits");
    ElementRDG element2 = new ElementRDGRDS(23, 44, 6, "element2", "inhabits");

    List<Integer> madeOf = new ArrayList<Integer>();
    madeOf.add(55);
    madeOf.add(23);

    //initialize compounds
    CompoundsMadeOfTDG compounds = new CompoundsMadeOfTDGRDS(1, madeOf, "chemicalname1",
        "inhabits1");

    List<Integer> list = compounds.findSetElementId(1);
    List<Integer> expected = new ArrayList<>();
    expected.add(55);
    expected.add(23);
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
    ElementRDG element1 = new ElementRDGRDS(55, 12, 5, "element1", "inhabits");
    ElementRDG element2 = new ElementRDGRDS(23, 44, 6, "element2", "inhabits");

    List<Integer> madeOf1 = new ArrayList<Integer>();
    madeOf1.add(55);
    List<Integer> madeOf2 = new ArrayList<Integer>();
    madeOf2.add(23);

    //initialize compounds
    CompoundsMadeOfTDG compound1 = new CompoundsMadeOfTDGRDS(1, madeOf1, "chemicalname1",
        "inhabits1"), compound2 = new CompoundsMadeOfTDGRDS(2, madeOf2, "chemicalname2", "inhabits2");

    // Compound1
    List<Integer> list1 = compound1.findSetElementId(1);
    List<Integer> expected1 = new ArrayList<>();
    expected1.add(55);
    assertEquals(expected1, list1);

    // Compound2
    List<Integer> list2 = compound1.findSetElementId(2);
    List<Integer> expected2 = new ArrayList<>();
    expected2.add(23);
    assertEquals(expected2, list2);

  }
}
