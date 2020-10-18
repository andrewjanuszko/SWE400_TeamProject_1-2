package model;

import mappers.BaseDataMapper;

public class CreateNewBaseCommand implements Command {

  Base base;
  public CreateNewBaseCommand(Base base) {
    this.base = base;
  }

  @Override
  public void execute() {
    BaseDataMapperInterface baseMapper = new BaseDataMapper();
    try {
      baseMapper.create(base.getName(), base.getInventory(), base.getSolute());
    } catch (DomainModelException e) {
      e.printStackTrace();
    }
    
  }

}
