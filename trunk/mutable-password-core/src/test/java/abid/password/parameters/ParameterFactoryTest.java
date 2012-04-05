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

import org.junit.Test;

public class ParameterFactoryTest {

  @Test
  public void addParameterShouldOnlyAddAParameterWhenItdoesNotExistAlready() {
    assertTrue(ParameterRegister.registerParameter("test", new Parameter(1)));
    assertFalse(ParameterRegister.registerParameter("test", new Parameter(1)));
    assertFalse(ParameterRegister.registerParameter("test", new Parameter(2)));
  }

  @Test
  public void getParameterShouldReturnCorrectValue() {
    ParameterRegister.registerParameter("param2", new Parameter(1));
    assertEquals(1, ParameterRegister.getParameter("param2").getValue());
  }

  @Test
  public void getAllParamterDataShouldReturnParameters() {
    ParameterRegister.registerParameter("params", new Parameter(1));
    assertEquals(1, ParameterRegister.getParameters().get("params").getValue());
  }

  @Test
  public void removeParameterShouldRemoveParamterWhenItExists() {
    ParameterRegister.registerParameter("param3", new Parameter(1));

    assertTrue(ParameterRegister.unregisterParameter("param3"));
    assertFalse(ParameterRegister.unregisterParameter("param3"));
    assertFalse(ParameterRegister.unregisterParameter("madeUp"));
  }

  @Test
  public void testSingletonConstruct() throws Exception {
    final Class<?> cls = ParameterRegister.class;
    final Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    c.newInstance((Object[]) null);
  }

}
