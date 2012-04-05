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

package abid.password.types;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;

import org.junit.Test;

import abid.password.MockMutablePassword;

public class PasswordTypeRegistryTest {

  @Test
  public void registerPasswordTypeShouldRegisteredPasswordType() {
    PasswordTypeRegistry.registerPasswordType(MockMutablePassword.class);
    assertTrue(PasswordTypeRegistry.getPasswordTypes().contains(MockMutablePassword.class));
  }

  @Test
  public void unregisterPasswordTypeShouldUnregisteredPasswordType() {
    PasswordTypeRegistry.registerPasswordType(MockMutablePassword.class);
    assertTrue(PasswordTypeRegistry.getPasswordTypes().contains(MockMutablePassword.class));

    PasswordTypeRegistry.unregisterPasswordType(MockMutablePassword.class);
    assertFalse(PasswordTypeRegistry.getPasswordTypes().contains(MockMutablePassword.class));
  }

  @Test
  public void getPasswordTypesShouldReturnDefaultRegisteredTypes() {
    assertTrue(PasswordTypeRegistry.getPasswordTypes().contains(CaesarCipherPassword.class));
    assertTrue(PasswordTypeRegistry.getPasswordTypes().contains(TimeLockPassword.class));
    assertTrue(PasswordTypeRegistry.getPasswordTypes().contains(ExtendedPassword.class));
    assertTrue(PasswordTypeRegistry.getPasswordTypes().contains(ExtendedTimeLockPassword.class));
    assertTrue(PasswordTypeRegistry.getPasswordTypes().contains(RomanNumeralPassword.class));
    assertTrue(PasswordTypeRegistry.getPasswordTypes().contains(RotatingPassword.class));
  }

  @Test
  public void testSingletonClass() throws Exception {
    Class<?> cls = PasswordTypeRegistry.class;
    Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    c.newInstance((Object[]) null);
  }

}
