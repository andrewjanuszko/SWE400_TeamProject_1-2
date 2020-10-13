package datasource;

/**
 * Simple runner to run all class table tests.
 * 
 * @author Isabella
 *
 */
public class Runner {

  public static void main(String[] args) {
    database.ClassTableInitializer.dropTables();
    database.ClassTableInitializer.createTables();

    try {
      TestAll.testAllTests();
      System.out.println("Tests ran successfully");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
