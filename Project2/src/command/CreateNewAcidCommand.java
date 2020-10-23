package command;

import model.Acid;
import model.AcidDataMapper;
import model.AcidDataMapperInterface;
import model.DomainModelException;
import reports.ReportObserverConnector;
import reports.ValidEntryReport;

public class CreateNewAcidCommand implements Command {

  Acid acid;
  public CreateNewAcidCommand(Acid acid) {
    this.acid = acid;
  }

  @Override
  public void execute() {
   AcidDataMapperInterface acidMapper = new AcidDataMapper();
   try {
    acidMapper.create(acid.getName(), acid.getInventory(), acid.getDissolves(), acid.getSolute());
    
  } catch (DomainModelException e) {
    ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(false));
    e.printStackTrace();
  }
   ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(true));
  }

}
