package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByAtomicMassBetweenCommand implements MetalFilterCommandInterface {
  
  private double min;
  private double max;
  
  public MetalFilterByAtomicMassBetweenCommand(double min, double max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByAtomicMassBetween(min, max);
  }

}
