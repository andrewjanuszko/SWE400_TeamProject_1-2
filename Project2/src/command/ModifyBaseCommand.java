package command;

import model.Base;
import model.BaseDataMapper;
import model.BaseDataMapperInterface;
import model.DomainModelException;

public class ModifyBaseCommand implements Command{

  private Base base; 
  
  public ModifyBaseCommand(Base base) {
    this.base = base; 
  }
  
  @Override
  public void execute() {
    BaseDataMapperInterface baseMapper = new BaseDataMapper(); 
    try {
      baseMapper.update(base); 
    } catch(DomainModelException e) {
      //
    }
  }
}
