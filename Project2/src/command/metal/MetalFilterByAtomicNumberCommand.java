package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByAtomicNumberCommand implements MetalFilterCommandInterface {
  
  private int atomicNumber;

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByAtomicNumber(atomicNumber);
  }

}
