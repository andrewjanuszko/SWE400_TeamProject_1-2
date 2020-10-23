package command;

import java.util.ArrayList;
import java.util.List;

import model.CompoundDataMapper;
import model.Compound;
import model.CompoundDataMapperInterface;
import model.DomainModelException;
import reports.FilterCompoundReport;
import reports.ReportObserverConnector;

public class FilterCompoundCommand implements Command {
  String[] filter;

  public FilterCompoundCommand(String filter) {    
    this.filter = filter.split("-");
    
  }

  @Override
  public void execute() {
    CompoundDataMapperInterface compoundMapper = new CompoundDataMapper();
    List<Compound> compound = new ArrayList<>();
    
    try {
      /**
       * 1 = filter by name
       * 2 = filter by inventory
       * 3 = filter by inventory range
       * 4 = filter by madeof
       */
      switch (filter[0]) {
      case ("1"): {
        System.out.println("name");
        String name = filter[1];
        
        compound = compoundMapper.filterByNameLike(name);
      }
      case ("2"): {
        System.out.println("inventory");
        
        compound = compoundMapper.filterByInventory(Integer.parseInt(filter[1]));
      }
      case("3"): {
        System.out.println("inventory range");
        
        compound = compoundMapper.filterByInventoryBetween(Integer.parseInt(filter[1]), Integer.parseInt(filter[2]));
      }
      case("4"): {
        System.out.println("madeof");

        compound = compoundMapper.filterByMadeOf(Integer.parseInt(filter[1]));
      }
      default: 
        System.out.println("getall");
        
        compound = compoundMapper.getAll();

      }
    } catch (DomainModelException e) {
      e.printStackTrace();
    }
    
    ReportObserverConnector.getSingleton().sendReport(new FilterCompoundReport(compound));
    
  }
}
