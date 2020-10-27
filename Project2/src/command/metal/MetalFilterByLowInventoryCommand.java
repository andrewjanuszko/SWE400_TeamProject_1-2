package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByLowInventoryCommand implements MetalFilterCommandInterface {
  
  public MetalFilterByLowInventoryCommand() {
    
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByLowInventory();
  }

}
