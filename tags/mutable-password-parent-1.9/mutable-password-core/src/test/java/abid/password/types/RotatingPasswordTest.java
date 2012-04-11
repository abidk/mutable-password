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
package abid.password.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.StatefulMutablePassword;

public class RotatingPasswordTest {

  @Test
  public void getPasswordTypeShouldReturnCorrectType() {
    MutablePassword password = createPassword("pass", "123456");
    assertEquals(RotatingPassword.PASSWORD_TYPE, password.getPasswordType());
  }

  @Test
  public void confirmPasswordShouldValidateCorrectly() throws PasswordException {
    StatefulMutablePassword password = createPassword("pass", "123456");
    assertEquals(0, password.getState());
    assertTrue(password.confirmPassword("pass1"));

    password = createPassword("pass", "123456");
    password.setState(0);
    assertTrue(password.confirmPassword("pass1"));

    password = createPassword("pass", "123456");
    password.setState(1);
    assertTrue(password.confirmPassword("pass2"));

    password = createPassword("pass", "123456");
    password.setState(5);
    assertTrue(password.confirmPassword("pass6"));

    password = createPassword("pass", "123456");
    password.setState(-1);
    assertTrue(password.confirmPassword("pass2"));

    password = createPassword("pass", "123456");
    password.setState(-5);
    assertTrue(password.confirmPassword("pass6"));

    // state is bigger than password length, should still loop correctly
    password = createPassword("pass", "123456");
    password.setState(12);
    assertTrue(password.confirmPassword("pass1"));

    password = createPassword("pass", "123456");
    password.setState(0);
    assertFalse(password.confirmPassword("pass99"));

    password = createPassword("pass", "123456");
    password.setState(0);
    assertFalse(password.confirmPassword("somethingElse1"));

    // TODO Passing null will be treated as a string value. Do we want this????
    password = createPassword("pass", null);
    password.setState(0);
    assertTrue(password.confirmPassword("passn"));
  }

  private StatefulMutablePassword createPassword(String text, String mutableText) {
    return new RotatingPasswordBuilder().createPassword(text, mutableText);
  }

}
