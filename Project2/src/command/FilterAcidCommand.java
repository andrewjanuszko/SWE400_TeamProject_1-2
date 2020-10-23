package command;

import java.util.ArrayList;
import java.util.List;

import model.Acid;
import model.AcidDataMapper;
import model.AcidDataMapperInterface;
import model.DomainModelException;
import reports.FilterAcidReport;
import reports.ReportObserverConnector;
import reports.ValidEntryReport;

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
        
        acids = acidMapper.filterByWildCardName(name);
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
        
        acids = acidMapper.filterByInventoryRange(Integer.parseInt(filter[1]), Integer.parseInt(filter[2]));
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
