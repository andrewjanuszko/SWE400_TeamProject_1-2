package command;

import java.util.ArrayList;
import java.util.List;

import mappers.ElementDataMapper;
import mappers.MetalDataMapper;
import model.DomainModelException;
import model.Element;
import model.ElementDataMapperInterface;
import model.Metal;
import model.MetalDataMapperInterface;
import reports.FilterElementReport;
import reports.FilterMetalReport;
import reports.ReportObserverConnector;

public class FilterMetalCommand implements Command {
  String[] filter;

  public FilterMetalCommand(String filter) {    
    this.filter = filter.split("-");
    
  }

  @Override
  public void execute() {
    MetalDataMapperInterface metalMapper = new MetalDataMapper();
    List<Metal> metal = new ArrayList<>();
    
    try {
      /**
       * 1 = filter by name
       * 2 = filter by inventory
       * 3 = filter by inventory range
       * 4 = filter by atomicnumber
       * 5 = filter by atomicmass
       * 6 = filter by atomicmassrange
       * 7 = filter by acidrequired
       * 8 = filter by acidrequiredrange
       * 9 = filter by dissolvedby
       */
      switch (filter[0]) {
      case ("1"): {
        System.out.println("name");
        String name = filter[1];
        
        metal = metalMapper.filterByWildCardName(name);
      }
      case ("2"): {
        System.out.println("inventory");
        
        metal = metalMapper.filterByInventory(Integer.parseInt(filter[1]));
      }
      case("3"): {
        System.out.println("inventory range");
        
        metal = metalMapper.filterByInventoryRange(Integer.parseInt(filter[1]), Integer.parseInt(filter[2]));
      }
      case("4"): {
        System.out.println("atomic number");

        metal = metalMapper.filterByAtomicNumber(Integer.parseInt(filter[1]));
      }
      case("5"): {
        System.out.println("atomic mass");

        metal = metalMapper.filterByAtomicMass(Double.parseDouble(filter[1]));
      }
      case("6"): {
        System.out.println("atomic mass range");

        metal = metalMapper.filterByAtomicMassRange(Double.parseDouble(filter[1]), Double.parseDouble(filter[2]));
      }
      case("7"): {
        System.out.println("acid required");

        metal = metalMapper.filterByAcidRequired(Double.parseDouble(filter[1]));
      }
      case("8"): {
        System.out.println("acid required range");

        metal = metalMapper.filterByAcidRequiredRange(Double.parseDouble(filter[1]), Double.parseDouble(filter[2]));
      }
      case("9"): {
        System.out.println("dissolved by");

        metal = metalMapper.filterByDissolvedBy(Integer.parseInt(filter[1]));
      }
      default: 
        System.out.println("getall");
        
        metal = metalMapper.getAll();

      }
    } catch (DomainModelException e) {
      e.printStackTrace();
    }
    
    ReportObserverConnector.getSingleton().sendReport(new FilterMetalReport(metal));
    
  }

}
