package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByAcidAmountCommand implements MetalFilterCommandInterface {
  
  private double acidAmount;
  
  public MetalFilterByAcidAmountCommand(double acidAmount) {
    this.acidAmount = acidAmount;
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByAcidAmount(acidAmount);
  }

}
