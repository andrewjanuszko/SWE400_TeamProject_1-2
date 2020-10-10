package datasource;

public class Runner {

  public static void main(String[] args) {
    database.ClassTableInitializer.dropTables();
    database.ClassTableInitializer.createTables();
    
    TestAll.testAllTests();
  }

}
