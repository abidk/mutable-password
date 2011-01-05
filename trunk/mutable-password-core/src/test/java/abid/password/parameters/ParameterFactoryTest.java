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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class ParameterFactoryTest extends TestCase {

  private String key = "test";
  private Integer value = 1;

  public void testAddParameter() {
    boolean added = ParameterFactory.addParameter(key, value);
    assertEquals(true, added);

    Map<String, Number> params = ParameterFactory.getAllParamterData();
    Integer paramValue = (Integer) params.get(key);
    assertEquals(value, paramValue);

    boolean removed = ParameterFactory.removeParameter(key);
    assertEquals(true, removed);
  }

  public void testAlreadyExistsParameter() {
    boolean added = ParameterFactory.addParameter(key, value);
    assertEquals(true, added);

    boolean newAdded = ParameterFactory.addParameter(key, value);
    assertEquals(false, newAdded);
  }

  public void testAddAllParameters() {
    int value = 99;
    Map<String, Number> newParams = new HashMap<String, Number>();
    newParams.put("newKey", value);
    ParameterFactory.addAllParameters(newParams);

    Map<String, Number> params = ParameterFactory.getAllParamterData();
    int paramValue = (Integer) params.get("newKey");
    assertEquals(value, paramValue);
  }

  public void testRemoveNonExistentParameter() {
    boolean removed = ParameterFactory.removeParameter("madeUp");
    assertEquals(false, removed);
  }
  
  @SuppressWarnings("unused")
  public void testParameterConstruct() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
    final Class<?> cls = ParameterFactory.class;
    final Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    final Object n = c.newInstance((Object[]) null);
  }

}
