package model;

public class CreateNewAcidCommand implements Command {

  Acid acid;
  public CreateNewAcidCommand(Acid acid) {
    this.acid = acid;
  }

  @Override
  public void execute() {
   AcidDataMapperInterface acidMapper = new AcidDataMapper();
   try {
    acidMapper.create(acid.getName(), acid.getInventory(), acid.getDissolves(), acid.getSolute());
  } catch (DomainModelException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
    
  }

}
