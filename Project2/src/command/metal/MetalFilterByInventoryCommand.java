package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByInventoryCommand implements MetalFilterCommandInterface {
  
  private double inventory;
  
  public MetalFilterByInventoryCommand(double inventory) {
    this.inventory = inventory;
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByInventory(inventory);
  }

}
