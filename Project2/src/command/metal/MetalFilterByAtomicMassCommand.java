package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByAtomicMassCommand implements MetalFilterCommandInterface {
  
  private double atomicMass;
  
  public MetalFilterByAtomicMassCommand(double atomicMass) {
    this.atomicMass = atomicMass;
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByAtomicMass(atomicMass);
  }

}
