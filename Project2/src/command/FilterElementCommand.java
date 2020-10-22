package command;

import java.util.ArrayList;
import java.util.List;

import mappers.ElementDataMapper;
import model.Element;
import model.ElementDataMapperInterface;
import model.DomainModelException;
import reports.FilterElementReport;
import reports.ReportObserverConnector;

public class FilterElementCommand implements Command {
  String[] filter;

  public FilterElementCommand(String filter) {    
    this.filter = filter.split("-");
    
  }

  @Override
  public void execute() {
    ElementDataMapperInterface elementMapper = new ElementDataMapper();
    List<Element> element = new ArrayList<>();
    
    try {
      /**
       * 1 = filter by name
       * 2 = filter by inventory
       * 3 = filter by inventory range
       * 4 = filter by atomicnumber
       * 5 = filter by atomicmass
       * 6 = filter by atomicmassrange
       * 7 = filter by partofcompound
       */
      switch (filter[0]) {
      case ("1"): {
        System.out.println("name");
        String name = filter[1];
        
        element = elementMapper.filterByWildCardName(name);
      }
      case ("2"): {
        System.out.println("inventory");
        
        element = elementMapper.filterByInventory(Integer.parseInt(filter[1]));
      }
      case("3"): {
        System.out.println("inventory range");
        
        element = elementMapper.filterByInventoryRange(Integer.parseInt(filter[1]), Integer.parseInt(filter[2]));
      }
      case("4"): {
        System.out.println("atomic number");

        element = elementMapper.filterByAtomicNumber(Integer.parseInt(filter[1]));
      }
      case("5"): {
        System.out.println("atomic mass");

        element = elementMapper.filterByAtomicMass(Double.parseDouble(filter[1]));
      }
      case("6"): {
        System.out.println("atomic mass range");

        element = elementMapper.filterByAtomicMassRange(Double.parseDouble(filter[1]), Double.parseDouble(filter[2]));
      }
      case("7"): {
        System.out.println("part of compound");

        element = elementMapper.filterByPartOfCompound(Integer.parseInt(filter[1]));
      }
      default: 
        System.out.println("getall");
        
        element = elementMapper.getAll();

      }
    } catch (DomainModelException e) {
      e.printStackTrace();
    }
    
    ReportObserverConnector.getSingleton().sendReport(new FilterElementReport(element));
    
  }
}
