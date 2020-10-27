package command.metal;

import java.util.List;

import model.DomainModelException;
import model.Metal;
import model.MetalDataMapper;

public class MetalFilterByNameCommand implements MetalFilterCommandInterface {
  
  private String name;
  
  public MetalFilterByNameCommand(String name) {
    this.name = name;
  }

  @Override
  public List<Metal> execute() throws DomainModelException {
    return new MetalDataMapper().filterByNameLike(name);
  }

}
