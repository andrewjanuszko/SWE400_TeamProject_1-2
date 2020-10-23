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

public class LowInventoryCommand implements Command {
  String fileoutput;
  List<String> input = new ArrayList<>();

  public LowInventoryCommand(String fileoutput) throws DomainModelException {
    this.fileoutput = fileoutput;

    getInput();
  }

  private void getInput() throws DomainModelException {
    List<Acid> acids = new AcidDataMapper().filterByLowInventory();
    List<Base> bases = new BaseDataMapper().filterByLowInventory(40);
    List<Element> elements = yes();

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

  private List<Element> yes() throws DomainModelException {
    try {
      List<Element> elements = new ArrayList<>();
      List<Compound> compounds = new CompoundDataMapper().getAll();

      for (Compound c : compounds) {
        int madeOfSum = 0;
        List<Element> madeOf = c.getMadeOf();
        for (Element e : madeOf) {
          madeOfSum += e.getInventory();
        }

        if (c.getInventory() >= madeOfSum) {
          for (Element e : madeOf) {
            if (e.getInventory() > 20) {
              elements.add(e);
            }
          }
        }
      }

      return elements;

    } catch (DomainModelException e) {
      throw new DomainModelException("yes()", e);
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
