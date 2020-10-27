package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalGetAllCommand implements MetalFilterCommandInterface {
  
  public MetalGetAllCommand() {
    
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().getAll();
  }

}
