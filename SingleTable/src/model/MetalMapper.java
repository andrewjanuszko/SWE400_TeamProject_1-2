package model;

import java.util.ArrayList;

import dataDTO.ChemicalDTO;
import datasource.ChemicalTableDataGatewayRDS;

public class MetalMapper implements MetalDataMapperInterface {
  
  private ChemicalTableDataGatewayRDS chemicalTableDataGateway;

  @Override
  public void create(Metal metal) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Metal read(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(Metal metal) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(Metal metal) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public ArrayList<Metal> getAll() {
    return convertToMetal(chemicalTableDataGateway.getMetals().executeQuery());
  }

  @Override
  public ArrayList<Metal> filterByWildCardName(String wildCardName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Metal> filterByInventory(double inventory) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Metal> filterByInventoryRange(double min, double max) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Metal> filterByAtomicNumber(int atomicNumber) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Metal> filterByAtomicMass(double atomicMass) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Metal> filterByAtomicMassRange(double min, double max) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Metal> filterByAcidRequired(double acidRequired) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Metal> filterByAcidRequiredRange(double min, double max) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Metal> filterByDissolvedBy(int acidID) {
    // TODO Auto-generated method stub
    return null;
  }
  
  private ArrayList<Metal> convertToMetal(ArrayList<ChemicalDTO> metals) {
    ArrayList<Metal> converted = new ArrayList<Metal>();
    for(ChemicalDTO metal : metals) {
      converted.add(new Metal(metal.getChemicalID(), metal.getName(), metal.getInventory(), metal.getAtomicNumber(), metal.getAtomicMass(), metal.getMoles()));
    }
    return converted;
  }

}
