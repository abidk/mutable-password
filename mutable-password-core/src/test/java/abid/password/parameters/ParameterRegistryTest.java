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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class ParameterRegistryTest {

  @Test
  public void addParameterShouldOnlyAddAParameterWhenItdoesNotExistAlready() {
    assertTrue(ParameterRegistery.registerParameter("test", new Parameter(1)));
    assertFalse(ParameterRegistery.registerParameter("test", new Parameter(1)));
    assertFalse(ParameterRegistery.registerParameter("test", new Parameter(2)));
  }

  @Test
  public void getParameterShouldReturnCorrectValue() {
    ParameterRegistery.registerParameter("param2", new Parameter(1));
    assertEquals(1, ParameterRegistery.getParameter("param2").getValue());
  }

  @Test
  public void getAllParamterDataShouldReturnParameters() {
    ParameterRegistery.registerParameter("params", new Parameter(1));
    assertEquals(1, ParameterRegistery.getParameters().get("params").getValue());
  }

  @Test
  public void removeParameterShouldRemoveParamterWhenItExists() {
    ParameterRegistery.registerParameter("param3", new Parameter(1));

    assertTrue(ParameterRegistery.unregisterParameter("param3"));
    assertFalse(ParameterRegistery.unregisterParameter("param3"));
    assertFalse(ParameterRegistery.unregisterParameter("madeUp"));
  }

  @Test
  public void testSingletonConstruct() throws Exception {
    Class<?> cls = ParameterRegistery.class;
    Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    c.newInstance((Object[]) null);
  }

}
