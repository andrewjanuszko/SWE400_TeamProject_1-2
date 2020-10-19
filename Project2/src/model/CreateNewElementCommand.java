package model;

import mappers.ElementDataMapper;
import reports.ReportObserverConnector;
import reports.ValidEntryReport;

public class CreateNewElementCommand implements Command {

  Element element;

  public CreateNewElementCommand(Element element) {
    this.element = element;
  }

  @Override
  public void execute()  {
    ElementDataMapperInterface elementMapper = new ElementDataMapper();
    try {
      if (element.getAtomicMass() < element.getAtomicNumber()) {
        ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(false));
        throw new DomainModelException("Atomic Number cannot be more than Atomic Mass");
      } else if (element.getName().contains(" ")) {
        ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(false));
        throw new DomainModelException("Element Names cannot contain a space");
      } else {
        ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(true));
        elementMapper.create(element.getName(), element.getInventory(), element.getAtomicNumber(),
            element.getAtomicMass());
      }
    } catch (Exception e) {
      ReportObserverConnector.getSingleton().sendReport(new ValidEntryReport(false));
      e.printStackTrace();
    } 
  }

}
