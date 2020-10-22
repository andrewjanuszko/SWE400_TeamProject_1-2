package command;

import mappers.MetalDataMapper;
import model.Command;
import model.DomainModelException;
import model.Metal;
import model.MetalDataMapperInterface;
import reports.ReportObserverConnector;
import reports.ValidEntryReport;

public class CreateNewMetalCommand implements Command {

  Metal metal;

  public CreateNewMetalCommand(Metal metal) {
    this.metal = metal;
  }

  @Override
  public void execute() {
    MetalDataMapperInterface metalMapper = new MetalDataMapper();
    try {
      if(metal.getAtomicNumber() > metal.getAtomicMass()) {
        ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(false));
        throw new DomainModelException("Atomic Number cannot be more than Atomic Mass");
      } else {
      
        ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(true));
        metalMapper.create(metal.getName(), metal.getInventory(), metal.getAtomicNumber(), metal.getAtomicMass(),
            metal.getAcidAmount());
      }
    } catch (DomainModelException e) {
      ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(false));
      e.printStackTrace();
    } catch (Exception e) {
      ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(false));
      e.printStackTrace();
    }
  }

}
