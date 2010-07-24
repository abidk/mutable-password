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

package abid.password.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import junit.framework.TestCase;
import abid.password.MutablePassword;
import abid.password.types.PasswordFactory;

public class PasswordFactoryTest extends TestCase {

  public void testPasswordList() {
    List<Class<? extends MutablePassword>> passwordLst = PasswordFactory.getMutablePasswordList();
    assertNotNull(passwordLst);
  }

  @SuppressWarnings("unused")
  public void testParameterConstruct() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
    final Class<?> cls = PasswordFactory.class;
    final Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    final Object n = c.newInstance((Object[]) null);
  }

}
