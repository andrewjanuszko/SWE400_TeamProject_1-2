package command;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Acid;
import model.AcidDataMapper;
import model.Base;
import model.BaseDataMapper;
import model.Compound;
import model.CompoundDataMapper;
import model.DomainModelException;
import model.Element;
import model.ElementDataMapper;

public class LowInventoryCommand implements Command {
  String fileoutput;
  List<String> input = new ArrayList<>();

  public LowInventoryCommand(String fileoutput) throws DomainModelException {
    this.fileoutput = fileoutput;

    getInput();
  }

  /**
   * Get input
   * @throws DomainModelException
   */
  private void getInput() throws DomainModelException {
    List<Acid> acids = getLowAcids();
    List<Base> bases = new BaseDataMapper().filterByInventoryRange(0, 40);
    List<Element> elements = getLowElements();

    for (Acid a : acids) {
      input.add("Acid\t id: " + a.getID() + ", name: " + a.getName() + ", inventory: " + a.getInventory());
    }
    for (Base b : bases) {
      input.add("Base\t id: " + b.getID() + ", name: " + b.getName() + ", inventory: " + b.getInventory());
    }
    for (Element e : elements) {
      input.add("Element\t id: " + e.getID() + ", name: " + e.getName() + ", inventory: " + e.getInventory());
    }
  }

  /**
   * Get low acids
   * 
   * @return all acids that are low
   */
  private List<Acid> getLowAcids() {
    List<Acid> acids = new ArrayList<>(), allAcids = new AcidDataMapper().getAll();

    for (Acid a : allAcids) {
      if (a.getInventory() < a.getThreshold()) {
        acids.add(a);
      }
    }
    return acids;
  }

  /**
   * Get low elements
   * 
   * @return all elements that are low
   * @throws DomainModelException
   */
  private List<Element> getLowElements() throws DomainModelException {
    List<Element> returnList = new ArrayList<>();

    try {
      List<Element> elements = new ElementDataMapper().getAll();

      for (Element e : elements) {
        int compoundSize = 0;
        List<Compound> compoundz = new CompoundDataMapper().filterByMadeOf(e.getID());

        for (Compound c : compoundz) {
          compoundSize += c.getInventory();
        }

        if ((compoundSize > e.getInventory()) || (e.getInventory() < 20)) {
          returnList.add(e);
        }

      }

      return returnList;

    } catch (DomainModelException e) {
      throw new DomainModelException("getLowElements()", e);
    }
  }

  @Override
  public void execute() {
    if (writeInput()) {

    } else {

    }

  }

  private boolean writeInput() {
    try {
      FileWriter w = new FileWriter(fileoutput);
      for (String s : input) {
        w.write(s);
      }
      w.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

}
