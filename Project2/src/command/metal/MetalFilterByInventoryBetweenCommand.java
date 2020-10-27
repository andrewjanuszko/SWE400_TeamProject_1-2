package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByInventoryBetweenCommand implements MetalFilterCommandInterface {
  
  private double min;
  private double max;
  
  public MetalFilterByInventoryBetweenCommand(double min, double max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByInventoryBetween(min, max);
  }

}
