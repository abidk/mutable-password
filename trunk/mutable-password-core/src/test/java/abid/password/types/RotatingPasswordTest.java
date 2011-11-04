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

import junit.framework.TestCase;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.StatefulMutablePassword;

public class RotatingPasswordTest extends TestCase {

  public void testPasswordType() {
    MutablePassword password = RotatingPassword.createPassword("pass", "123456");
    assertEquals(RotatingPassword.PASSWORD_TYPE, password.getPasswordType());
  }

  public void testPasswordFactory() throws PasswordInstantiationException {
    MutablePassword password = RotatingPassword.createPassword("pass", "123456");
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());
    assertEquals(RotatingPassword.PASSWORD_TYPE, ((MutablePassword) unknownPassword).getType());
  }

  public void testPasswordWithoutState() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    assertEquals(0, password.getState());
    assertTrue(password.confirmPassword("pass1"));
  }

  public void testPasswordWithState() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    password.setState(0);
    assertTrue(password.confirmPassword("pass1"));
  }

  public void testPasswordWithState2() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    password.setState(1);
    assertTrue(password.confirmPassword("pass2"));
  }

  public void testPasswordWithState3() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    password.setState(5);
    assertTrue(password.confirmPassword("pass6"));
  }

  public void testPasswordWithNegativeState() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    password.setState(-1);
    assertTrue(password.confirmPassword("pass2"));
  }

  public void testPasswordWithNegativeState2() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    password.setState(-5);
    assertTrue(password.confirmPassword("pass6"));
  }

  public void testPasswordWithStateBiggerThanMaxLength() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    password.setState(12);
    assertTrue(password.confirmPassword("pass1"));
  }

  public void testPasswordWithWrongConfirmPassword() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    password.setState(0);
    assertFalse(password.confirmPassword("pass99"));
  }

  public void testPasswordWithWrongStaticText() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    password.setState(0);
    assertFalse(password.confirmPassword("somethingElse1"));
  }

  /**
   * Passing null will be treated as a string value. Do we want this????
   */
  public void testPasswordWithoutText() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", null);
    password.setState(0);
    assertTrue(password.confirmPassword("passn"));
  }

}
