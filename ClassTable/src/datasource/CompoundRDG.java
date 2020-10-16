package datasource;

import java.util.List;

public interface CompoundRDG {
  public CompoundDTO create(int id, List<Integer> madeOf, String name, double inventory);
  public CompoundDTO read(int id);
  public CompoundDTO update(CompoundDTO compound);
  public CompoundDTO delete(int id); 
}
