package command.metal;

import java.util.List;

import model.Metal;
import model.DomainModelException;

public interface MetalFilterCommandInterface {
  
  /**
   * Execute method for Commands.
   */
  public abstract List<Metal> execute() throws DomainModelException;

}
