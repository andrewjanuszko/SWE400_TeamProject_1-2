
package mappers;

import java.util.HashMap;
import java.util.Map;

import model.Acid;

/**
 * @author jloga
 *
 */
public abstract class AcidIdentityMap {
  private static Map<Integer, Acid> acid = new HashMap<Integer, Acid>();
  public static void addAcid(Acid arg) {
    acid.put(arg.getID(), arg);
  }
  
  public static Acid getAcid(int key) {
    return acid.get(key);
  }
}
