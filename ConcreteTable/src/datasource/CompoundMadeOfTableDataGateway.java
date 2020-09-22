package datasource;

import java.util.List;

import datadto.CompoundMadeOfDTO;

public interface CompoundMadeOfTableDataGateway {
	
	public List<CompoundMadeOfDTO> getCompoundMadeOf(int compoundID);
	
	public List<CompoundMadeOfDTO> getCompoundMadeOf(String name);
}
