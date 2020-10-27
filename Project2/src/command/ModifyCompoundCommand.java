package command;

import model.Compound;
import model.CompoundDataMapper;
import model.CompoundDataMapperInterface;
import model.DomainModelException;

public class ModifyCompoundCommand implements Command {
  private Compound compound;

  public ModifyCompoundCommand(Compound compound) {
    this.compound = compound;
  }

  @Override
  public void execute() {
    CompoundDataMapperInterface compoundMapper = new CompoundDataMapper();

    try {
      compoundMapper.update(compound);
    } catch (DomainModelException e) {
      //
    }
  }

}
