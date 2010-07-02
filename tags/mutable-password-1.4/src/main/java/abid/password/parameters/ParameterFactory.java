/**
 * Copyright 2010 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package abid.password.parameters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Combines all the parameter data into one Hashmap.
 * 
 * TODO use reflection to automatically add types.
 * 
 * @author Abid
 * 
 */
public class ParameterFactory {

  private ParameterFactory() {
  }

  private static final Logger log = LoggerFactory.getLogger(ParameterFactory.class);
  private static Map<String, Number> parameters = new HashMap<String, Number>();

  /**
   * This method returns the latest parameter values for time, stock market, and
   * any parameters added by the user.
   * 
   * @return latest parameters
   */
  public synchronized static Map<String, Number> getAllParamterData() {
    Map<String, Number> params = new HashMap<String, Number>();
    // get the latest time parameter values
    params.putAll(TimeParameter.getValues());

    try {
      // get the latest stock market parameter values
      params.putAll(StockMarketParameter.getValues());
    } catch (IOException e) {
      log.error("Unable to add the stock market parameters, because...", e);
    }

    // add any parameters the user may have added
    params.putAll(parameters);
    return params;
  }

  public static boolean addParameter(String key, Number value) {
    if (!parameters.containsKey(key)) {
      parameters.put(key, value);
      return true;
    }
    return false;
  }

  public static void addAllParameters(Map<String, Number> newParams) {
    parameters.putAll(newParams);
  }

  public static boolean removeParameter(String key) {
    if (parameters.containsKey(key)) {
      parameters.remove(key);
      return true;
    }
    return false;
  }
}
