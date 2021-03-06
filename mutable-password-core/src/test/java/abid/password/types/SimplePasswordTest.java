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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import abid.password.PasswordParseException;
import abid.password.Password;
import abid.password.PasswordException;

public class SimplePasswordTest {

  @Test
  public void confirmPasswordShouldValidatePasswordCorrectly() throws PasswordParseException, PasswordException {
    Password password = new SimplePassword("pass1");
    assertTrue(password.confirmPassword("pass1"));
    assertFalse(password.confirmPassword("pass123"));

    password = new SimplePassword("pass[");
    assertTrue(password.confirmPassword("pass["));
    assertFalse(password.confirmPassword("pass"));

    password = new SimplePassword("pass[]");
    assertTrue(password.confirmPassword("pass[]"));
    assertFalse(password.confirmPassword("pass"));

    password = new SimplePassword("pass[{]");
    assertTrue(password.confirmPassword("pass[{]"));
    assertFalse(password.confirmPassword("pass"));

    password = new SimplePassword("ab[id}]");
    assertTrue(password.confirmPassword("ab[id}]"));
    assertFalse(password.confirmPassword("bcje"));
  }
}
