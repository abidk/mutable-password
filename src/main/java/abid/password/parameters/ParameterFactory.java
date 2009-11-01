package abid.password.parameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Combines all the parameter data into one Hashmap.
 * 
 * TODO make customisable to use reflection to automatically add types.
 * @author Abid
 *
 */
public class ParameterFactory {

  public static Map<String, Integer> getAllParamterData() {
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.putAll(TimeType.getValues());
    map.putAll(StockMarketType.getValues());
    
    return map;
  }
}
