package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByDissolvedByCommand implements MetalFilterCommandInterface {
  
  private int acidID;
  
  public MetalFilterByDissolvedByCommand(int acidID) {
    this.acidID = acidID;
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByDissolvedBy(acidID);
  }

}
