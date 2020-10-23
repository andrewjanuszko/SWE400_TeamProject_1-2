package command;

import java.util.ArrayList;
import java.util.List;

import model.AcidDataMapper;
import model.Acid;
import model.AcidDataMapperInterface;
import model.DomainModelException;
import reports.FilterAcidReport;
import reports.ReportObserverConnector;

public class FilterAcidCommand implements Command {
  String[] filter;

  public FilterAcidCommand(String filter) {    
    this.filter = filter.split("-");
    
  }

  @Override
  public void execute() {
    AcidDataMapperInterface acidMapper = new AcidDataMapper();
    List<Acid> acids = new ArrayList<>();
    try {
      switch (filter[0]) {
      case ("1"): {
        System.out.println("name");
        String name = filter[1];
        
        acids = acidMapper.filterByNameLike(name);
      }
      case ("2"): {
        System.out.println("solute");
        
        acids = acidMapper.filterBySolute(Integer.parseInt(filter[1]));
      }
      case ("3"): {
        System.out.println("inventory");
        
        acids = acidMapper.filterByInventory(Integer.parseInt(filter[1]));
      }
      case("4"): {
        System.out.println("inventory range");
        
        acids = acidMapper.filterByInventoryBetween(Integer.parseInt(filter[1]), Integer.parseInt(filter[2]));
      }
      default: {
        acids = acidMapper.getAll();
      }
      }
    } catch (DomainModelException e) {
      e.printStackTrace();
    }
    
    ReportObserverConnector.getSingleton().sendReport(new FilterAcidReport(acids));
    
  }

}
