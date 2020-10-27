package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByAtomicNumberBetweenCommand implements MetalFilterCommandInterface {
  
  private int min;
  private int max;
  
  public MetalFilterByAtomicNumberBetweenCommand(int min, int max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByAtomicMassBetween(min, max);
  }

}
