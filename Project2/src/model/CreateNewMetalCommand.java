package model;

import mappers.MetalDataMapper;

public class CreateNewMetalCommand implements Command {

  Metal metal;

  public CreateNewMetalCommand(Metal metal) {
    this.metal = metal;
  }

  @Override
  public void execute() {
    MetalDataMapperInterface metalMapper = new MetalDataMapper();
    try {
      metalMapper.create(metal.getName(), metal.getInventory(), metal.getAtomicNumber(), metal.getAtomicMass(),
          metal.getAcidAmount());
    } catch (DomainModelException e) {
      e.printStackTrace();
    }

  }

}
