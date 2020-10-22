package command;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mappers.AcidDataMapper;
import mappers.BaseDataMapper;
import mappers.CompoundDataMapper;
import mappers.ElementDataMapper;
import mappers.MetalDataMapper;
import model.Acid;
import model.Base;
import model.Compound;
import model.Element;
import model.Metal;

public class LowInventoryCommand implements Command {
  String fileoutput;
  List<String> input = new ArrayList<>();

  public LowInventoryCommand(String fileoutput) {
    this.fileoutput = fileoutput;

    getInput();
  }

  private void getInput() {
    List<Acid> acids = new AcidDataMapper().filterByLowInventory();
    List<Base> bases = new BaseDataMapper().filterByLowInventory();
    List<Compound> compounds = new CompoundDataMapper().filterByLowInventory();
    List<Element> elements = new ElementDataMapper().filterByLowInventory();
    List<Metal> metals = new MetalDataMapper().filterByLowInventory();

    for (Acid a : acids) {
      input.add("Acid\t id: " + a.getID() + ", name: " + a.getName() + ", inventory: " + a.getInventory());
    }
    for (Base b : bases) {
      input.add("Base\t id: " + b.getID() + ", name: " + b.getName() + ", inventory: " + b.getInventory());
    }
    for (Compound c : compounds) {
      input.add("Compound\t id: " + c.getID() + ", name: " + c.getName() + ", inventory: " + c.getInventory());
    }
    for (Element e : elements) {
      input.add("Element\t id: " + e.getID() + ", name: " + e.getName() + ", inventory: " + e.getInventory());
    }
    for (Metal m : metals) {
      input.add("Metals\t id: " + m.getID() + ", name: " + m.getName() + ", inventory: " + m.getInventory());
    }
  }

  @Override
  public void execute() {
    if (writeInput()) {

    } else {

    }
    // Write to file
    writeInput();
    // Report

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
