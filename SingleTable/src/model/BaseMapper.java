package model;

import java.util.ArrayList;

import dataDTO.ChemicalDTO;
import datasource.ChemicalRowDataGatewayRDS;
import datasource.ChemicalTableDataGatewayRDS;
import datasource.DatabaseException;

public class BaseMapper implements BaseDataMapperInterface {

	private ChemicalTableDataGatewayRDS chemicalTDG;
	private ArrayList<ChemicalDTO> chemicalDTOs = new ArrayList<ChemicalDTO>();
	 
	@Override
	public void create(Base base) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Base read(int id) {
		Base base = null;
		try {
			ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(id);
			base = new Base(row.getChemicalID(), row.getName(), row.getInventory(), row.getSolute());
		}catch (DatabaseException e) {
		      e.printStackTrace();
	    }
	    return base;
	}

	@Override
	public void update(Base base) {
		try {
			ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(base.getID());
			row.setName(base.getName());
			row.setInventory(base.getInventory());
			row.setSolute(base.getSolute());
			row.update();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Base base) {
		try {
			ChemicalRowDataGatewayRDS row = new ChemicalRowDataGatewayRDS(base.getID());
			row.delete();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Base> getAll() {
		try {
			chemicalDTOs = chemicalTDG.getElements().executeQuery();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}

	@Override
	public ArrayList<Base> filterByWildCardName(String wildCardName) {
		try {
			chemicalDTOs = chemicalTDG.getBases().filterByWildCardName(wildCardName).executeQuery();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}

	@Override
	public ArrayList<Base> filterByInventory(double inventory) {
		try {
			chemicalDTOs = chemicalTDG.executeFindBasesWithLowInventory();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}

	@Override
	public ArrayList<Base> filterByInventoryRange(double min, double max) {
		try {
			chemicalDTOs = chemicalTDG.getBases().filterByInventoryRange(min, max).executeQuery();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}

	@Override
	public ArrayList<Base> filterBySolute(int chemicalID) {
		try {
			chemicalDTOs = chemicalTDG.getBases().filterBySolute(chemicalID).executeQuery();
		} catch (DatabaseException e) {
		      e.printStackTrace();
		}
		return convertFromChemicalDTO(chemicalDTOs);
	}
	
	/**
	 * Converts an ArrayList of ChemicalDTOs from the DB to Base objects
	 * @param chemicalDTOs
	 * @return
	 */
	private ArrayList<Base> convertFromChemicalDTO(ArrayList<ChemicalDTO> chemicalDTOs) {
		ArrayList<Base> bases = new ArrayList<Base>();
		for (ChemicalDTO chemicalDTO : chemicalDTOs ) {
			bases.add(new Base(chemicalDTO.getChemicalID(), chemicalDTO.getName(),
					chemicalDTO.getInventory(), chemicalDTO.getSolute()));
		}
		chemicalDTOs = null;
		return bases;
	}

}
