package model;

import java.util.ArrayList;

import dataDTO.ChemicalDTO;
import datasource.ChemicalRowDataGatewayRDS;
import datasource.ChemicalTableDataGatewayRDS;
import datasource.DatabaseException;

public class AcidMapper implements AcidDataMapperInterface  {

	private ChemicalTableDataGatewayRDS chemicalTDG;
	private ArrayList<ChemicalDTO> chemicalDTOs = new ArrayList<ChemicalDTO>();
	
	@Override
	public void create(Acid acid) {
		// TODO Auto-generated method stub
	}

	@Override
	public Acid read(int id) {
		Acid acid = null;
		try {
			ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(id);
			acid = new Acid(row.getChemicalID(), row.getName(), row.getInventory(), /*RETURN LATER*/null, row.getSolute());
		}catch (DatabaseException e) {
		      e.printStackTrace();
	    }
	    return acid;
	}

	@Override
	public void update(Acid acid) {
		try {
			ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(acid.getID());
			row.setName(acid.getName());
			row.setInventory(acid.getInventory());
			row.setSolute(acid.getSolute());
			row.update();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Acid acid) {
		try {
			ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(acid.getID());
			row.delete();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Acid> getAll() {
		try {
			chemicalDTOs = chemicalTDG.getAcids().executeQuery();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}

	@Override
	public ArrayList<Acid> filterByWildCardName(String wildCardName) {
		try {
			chemicalDTOs = chemicalTDG.getAcids().filterByWildCardName(wildCardName).executeQuery();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}

	@Override
	public ArrayList<Acid> filterByInventory(double inventory) {
		try {
			chemicalDTOs = chemicalTDG.filterByInventoryValue(inventory).executeQuery();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}

	@Override
	public ArrayList<Acid> filterByInventoryRange(double min, double max) {
		try {
			chemicalDTOs = chemicalTDG.getAcids().filterByInventoryRange(min, max).executeQuery();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}

	@Override
	public ArrayList<Acid> filterBySolute(int chemicalID) {
		try {
			chemicalDTOs = chemicalTDG.getAcids().filterBySolute(chemicalID).executeQuery();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}
	
	/**
	 * Converts an ArrayList of ChemicalDTOs from the DB to acid objects
	 * @param chemicalDTOs
	 * @return
	 */
	private ArrayList<Acid> convertFromChemicalDTO(ArrayList<ChemicalDTO> chemicalDTOs) {
		ArrayList<Acid> acids = new ArrayList<Acid>();
		for (ChemicalDTO chemicalDTO : chemicalDTOs ) {
			acids.add(new Acid(chemicalDTO.getChemicalID(), chemicalDTO.getName(), chemicalDTO.getInventory(),/*RETURN LATE*/ null, chemicalDTO.getSolute()));
		}
		chemicalDTOs = null;
		return acids;
	}

}
