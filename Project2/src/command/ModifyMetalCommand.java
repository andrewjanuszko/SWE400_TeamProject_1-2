package command;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;
import model.MetalDataMapperInterface;

public class ModifyMetalCommand implements Command {
  private Metal metal; 
  
  public ModifyMetalCommand(Metal metal) {
    this.metal = metal; 
  }
  
  @Override
  public void execute() {
    MetalDataMapperInterface metalMapper = new MetalDataMapper();
    
    try {
      metalMapper.update(metal);
    } catch( DomainModelException e) {
      ///
    }
  }
}
