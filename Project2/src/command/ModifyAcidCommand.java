package command;

import model.Acid;
import model.AcidDataMapper;
import model.AcidDataMapperInterface;
import model.DomainModelException;

public class ModifyAcidCommand implements Command {
  private Acid acid; 
  
  /**
   * Update Acid id with values inside acid object
   */
  public ModifyAcidCommand(Acid acid) { 
    this.acid = acid; 
  }
  
  @Override
  public void execute() {
    AcidDataMapperInterface acidMapper = new AcidDataMapper(); 
    try {
      acidMapper.update(acid);
    } catch (DomainModelException e ) {
//      
    }
  }
}
