package model;

import java.util.ArrayList;

import datasource.ChemicalRowDataGatewayRDS;
import datasource.ChemicalTableDataGatewayRDS;
import datasource.DatabaseException;

public class BaseMapper implements BaseDataMapperInterface {

	private ChemicalTableDataGatewayRDS chemicalTDG;
	 
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Base> filterByWildCardName(String wildCardName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Base> filterByInventory(double inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Base> filterByInventoryRange(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Base> filterBySolute(int chemicalID) {
		// TODO Auto-generated method stub
		return null;
	}

}
