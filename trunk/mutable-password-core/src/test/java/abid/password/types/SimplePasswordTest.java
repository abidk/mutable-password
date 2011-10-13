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

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;
import abid.password.Password;
import abid.password.PasswordException;

public class SimplePasswordTest extends TestCase {

  public void testSimplePassword() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException, PasswordException {
    String password = "abid";
    Password dynamicPassword = PasswordFactory.getInstance(password);

    String confirmPassword = "abid";
    assertTrue(dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertFalse(dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testSimplePassword2() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException, PasswordException {
    String password = "abid[";
    Password dynamicPassword = PasswordFactory.getInstance(password);

    String confirmPassword = "abid[";
    assertTrue(dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertFalse(dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testSimplePassword3() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException, PasswordException {
    Password dynamicPassword = PasswordFactory.getInstance("abid[]");

    String confirmPassword = "abid[]";
    assertTrue(dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertFalse(dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testSimplePassword4() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException, PasswordException {
    Password dynamicPassword = PasswordFactory.getInstance("abid[{]");

    String confirmPassword = "abid[{]";
    assertTrue(dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertFalse(dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testSimplePassword5() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException, PasswordException {
    Password dynamicPassword = PasswordFactory.getInstance("ab[id}]");

    String confirmPassword = "ab[id}]";
    assertTrue(dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertFalse(dynamicPassword.confirmPassword(wrongPassword));
  }
}
