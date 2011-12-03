/**
 * Copyright 2011 Abid Khalil
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

import java.util.HashMap;
import java.util.Map;

/**
 * Combines all the parameter data into one Hashmap.
 * 
 * TODO use reflection to automatically add types.
 * 
 * @author Abid
 */
public class ParameterFactory {

  private static final Map<String, Parameter> parameters = new HashMap<String, Parameter>();

  private ParameterFactory() {
  }

  /**
   * This method returns the latest parameter values for time, stock market, and
   * any parameters added by the user.
   * 
   * @return parameters
   */
  public static Map<String, Parameter> getAllParamterData() {
    Map<String, Parameter> params = new HashMap<String, Parameter>();
    // get the latest 'refreshed' parameter values
    params.putAll(TimeParameter.getParameters());
    params.putAll(ZodiacParameter.getParameters());

    // add any parameters the user may have added
    params.putAll(parameters);
    return params;
  }

  /**
   * Adds a parameter to be used for password evaluations. Existing parameters
   * are not overriden, and must be removed first.
   * 
   * @param key
   * @param parameter
   * @return parameter added or not
   */
  public static boolean addParameter(String key, Parameter parameter) {
    if (!parameters.containsKey(key)) {
      parameters.put(key, parameter);
      return true;
    }
    return false;
  }

  /**
   * Will return the key object or null.
   * 
   * @param key
   * @return Parameter
   */
  public static Parameter getParameter(String key) {
    return parameters.get(key);
  }

  /**
   * Adds all the parameter to be used for password evaluations. This will
   * override existing values.
   * 
   */
  public static void addAllParameters(Map<String, Parameter> newParams) {
    parameters.putAll(newParams);
  }

  /**
   * Removes the parameter.
   * 
   * @param key
   * @return if parameter was removed
   */
  public static boolean removeParameter(String key) {
    if (parameters.containsKey(key)) {
      parameters.remove(key);
      return true;
    }
    return false;
  }
}
