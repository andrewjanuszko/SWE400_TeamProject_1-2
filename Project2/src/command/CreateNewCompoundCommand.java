package command;

import mappers.CompoundDataMapper;
import model.Command;
import model.Compound;
import model.CompoundDataMapperInterface;
import model.DomainModelException;
import reports.ReportObserverConnector;
import reports.ValidEntryReport;

public class CreateNewCompoundCommand implements Command {

  Compound compound;

  public CreateNewCompoundCommand(Compound compound) {
    this.compound = compound;
  }

  @Override
  public void execute() {
    CompoundDataMapperInterface compoundMapper = new CompoundDataMapper();
    try {
      compoundMapper.create(compound.getName(), compound.getInventory(), compound.getMadeOf());
    } catch (DomainModelException e) {
      ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(false));
      e.printStackTrace();
    }
    ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(true));
  }

}
