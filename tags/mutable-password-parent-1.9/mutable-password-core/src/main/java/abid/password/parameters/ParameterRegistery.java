/**
 * Copyright 2012 Abid Khalil
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
 * Register and unregister parameters.
 * 
 * @author Abid
 */
public class ParameterRegistery {

  private static final Map<String, Parameter> parameters = new HashMap<String, Parameter>();

  private ParameterRegistery() {
  }

  /**
   * Register parameter to be used for password evaluations. Existing parameters
   * are not overriden, and must be removed first.
   * 
   * @param key
   * @param parameter
   * @return parameter added or not
   */
  public static boolean registerParameter(String key, Parameter parameter) {
    if (!parameters.containsKey(key)) {
      parameters.put(key, parameter);
      return true;
    }
    return false;
  }

  /**
   * Unregister parameter.
   * 
   * @param key
   * @return if parameter was removed
   */
  public static boolean unregisterParameter(String key) {
    if (parameters.containsKey(key)) {
      parameters.remove(key);
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
   * Returns registered parameters with updated values.
   * 
   * @return parameters
   */
  public static Map<String, Parameter> getParameters() {
    Map<String, Parameter> params = new HashMap<String, Parameter>();
    // get the latest 'refreshed' parameter values
    params.putAll(TimeParameter.getParameters());
    params.putAll(ZodiacParameter.getParameters());

    // add any parameters the user may have added
    params.putAll(parameters);
    return params;
  }

}
