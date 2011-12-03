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

import static org.junit.Assert.*;

import org.junit.Test;

import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.StatefulMutablePassword;

public class RotatingPasswordTest {

  @Test
  public void getPasswordTypeShouldReturnCorrectType() {
    MutablePassword password = RotatingPassword.createPassword("pass", "123456");
    assertEquals(RotatingPassword.PASSWORD_TYPE, password.getPasswordType());
  }

  @Test
  public void passwordFactoryShouldReturnCorrectType() throws PasswordInstantiationException {
    MutablePassword password = RotatingPassword.createPassword("pass", "123456");
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());
    assertEquals(RotatingPassword.class, unknownPassword.getClass());
  }

  @Test
  public void confirmPasswordShouldValidateCorrectly() throws PasswordException {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    assertEquals(0, password.getState());
    assertTrue(password.confirmPassword("pass1"));

    password = RotatingPassword.createPassword("pass", "123456");
    password.setState(0);
    assertTrue(password.confirmPassword("pass1"));

    password = RotatingPassword.createPassword("pass", "123456");
    password.setState(1);
    assertTrue(password.confirmPassword("pass2"));

    password = RotatingPassword.createPassword("pass", "123456");
    password.setState(5);
    assertTrue(password.confirmPassword("pass6"));

    password = RotatingPassword.createPassword("pass", "123456");
    password.setState(-1);
    assertTrue(password.confirmPassword("pass2"));

    password = RotatingPassword.createPassword("pass", "123456");
    password.setState(-5);
    assertTrue(password.confirmPassword("pass6"));

    // state is bigger than password length, should still loop correctly
    password = RotatingPassword.createPassword("pass", "123456");
    password.setState(12);
    assertTrue(password.confirmPassword("pass1"));

    password = RotatingPassword.createPassword("pass", "123456");
    password.setState(0);
    assertFalse(password.confirmPassword("pass99"));

    password = RotatingPassword.createPassword("pass", "123456");
    password.setState(0);
    assertFalse(password.confirmPassword("somethingElse1"));

    // TODO Passing null will be treated as a string value. Do we want this????
    password = RotatingPassword.createPassword("pass", null);
    password.setState(0);
    assertTrue(password.confirmPassword("passn"));
  }

}
