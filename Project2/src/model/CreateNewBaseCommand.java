package model;

import mappers.BaseDataMapper;
import reports.ReportObserverConnector;
import reports.ValidEntryReport;

public class CreateNewBaseCommand implements Command {

  Base base;
  public CreateNewBaseCommand(Base base) {
    this.base = base;
  }

  @Override
  public void execute() {
    BaseDataMapperInterface baseMapper = new BaseDataMapper();
    try {
      baseMapper.create(base.getName(), base.getInventory(), base.getSolute());
    } catch (DomainModelException e) {
      ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(false));
      e.printStackTrace();
    }
    ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(true));
  }

}
