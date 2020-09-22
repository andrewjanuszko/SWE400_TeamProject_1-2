package datasource;

import java.util.List;

import datadto.CompoundMadeOfDTO;

public interface CompoundMadeOfTableDataGateway {
	
	public List<CompoundMadeOfDTO> getCompoundMadeOf(int compoundID);

}
