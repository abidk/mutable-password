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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ParameterFactoryTest {

  @Test
  public void addParameterShouldStoreNewParameter() {
    boolean added = ParameterFactory.addParameter("test", new Parameter(1));
    assertTrue(added);
    
    boolean newAdded = ParameterFactory.addParameter("test", new Parameter(1));
    assertFalse(newAdded);
  }

  @Test
  public void getParameterShouldReturnCorrectValue() {
    ParameterFactory.addParameter("param2", new Parameter(1));
    assertEquals(1, ParameterFactory.getParameter("param2").getValue());
  }

  @Test
  public void getAllParamterDataShouldReturnParameters() {
    ParameterFactory.addParameter("params", new Parameter(1));
    Parameter parameter = ParameterFactory.getAllParamterData().get("params");
    assertEquals(1, parameter.getValue());
  }
  
  @Test
  public void removeParameterShouldRemoveParamterFromFactory( ) {
    ParameterFactory.addParameter("param3", new Parameter(1));
    boolean removed = ParameterFactory.removeParameter("param3");
    assertTrue(removed);
    
    removed = ParameterFactory.removeParameter("madeUp");
    assertFalse(removed);
  }

  @Test
  public void addAllParametersShouldAddTypesToFactory() {
    Map<String, Parameter> newParams = new HashMap<String, Parameter>();
    newParams.put("newKey1", new Parameter(99));
    newParams.put("newKey2", new Parameter(98));
    ParameterFactory.addAllParameters(newParams);

    Map<String, Parameter> parameters = ParameterFactory.getAllParamterData();
    assertEquals(99, parameters.get("newKey1").getValue());
    assertEquals(98, parameters.get("newKey2").getValue());
  }

  @Test
  public void testParameterConstruct() throws IllegalArgumentException,
      InstantiationException, IllegalAccessException, InvocationTargetException {
    final Class<?> cls = ParameterFactory.class;
    final Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    c.newInstance((Object[]) null);
  }

}
