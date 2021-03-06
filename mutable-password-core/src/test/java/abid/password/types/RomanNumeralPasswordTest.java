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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.evaluator.EvaluationException;
import abid.password.parameters.TimeParameter;

public class RomanNumeralPasswordTest {

  @Test
  public void getPasswordTypeShouldReturnCorrectType() {
    MutablePassword password = createPassword("pass", TimeParameter.YEAR);
    assertEquals("romanNumeral", password.getPasswordType());
  }

  @Test
  public void confirmPasswordShouldValidatePasswordCorrectly() throws PasswordException {
    Password password = createPassword("pass", 1000);
    assertTrue(password.confirmPassword("passM"));

    password = createPassword("pass", 900);
    assertTrue(password.confirmPassword("passCM"));

    password = createPassword("pass", 500);
    assertTrue(password.confirmPassword("passD"));

    password = createPassword("pass", 400);
    assertTrue(password.confirmPassword("passCD"));

    password = createPassword("pass", 100);
    assertTrue(password.confirmPassword("passC"));

    password = createPassword("pass", 90);
    assertTrue(password.confirmPassword("passXC"));

    password = createPassword("pass", 10);
    assertTrue(password.confirmPassword("passX"));

    password = createPassword("pass", 9);
    assertTrue(password.confirmPassword("passIX"));

    password = createPassword("pass", 5);
    assertTrue(password.confirmPassword("passV"));

    password = createPassword("pass", 4);
    assertTrue(password.confirmPassword("passIV"));

    password = createPassword("pass", 1);
    assertTrue(password.confirmPassword("passI"));

    password = createPassword("pass", 0);
    assertTrue(password.confirmPassword("pass"));

    password = createPassword("pass", 0);
    assertTrue(password.confirmPassword("pass"));
  }

  @Test
  public void getEvaluatedPasswordShouldReturnCorrectEvaluation() throws EvaluationException {
    MutablePassword password = createPassword("pass", TimeParameter.YEAR);
    assertNotNull(password.getEvaluatedPassword());

    password = createPassword("pass", 1000);
    assertEquals("passM", password.getEvaluatedPassword());

    password = createPassword("pass", 900);
    assertEquals("passCM", password.getEvaluatedPassword());

    password = createPassword("pass", 500);
    assertEquals("passD", password.getEvaluatedPassword());

    password = createPassword("pass", 400);
    assertEquals("passCD", password.getEvaluatedPassword());

    password = createPassword("pass", 100);
    assertEquals("passC", password.getEvaluatedPassword());

    password = createPassword("pass", 90);
    assertEquals("passXC", password.getEvaluatedPassword());

    password = createPassword("pass", 10);
    assertEquals("passX", password.getEvaluatedPassword());

    password = createPassword("pass", 9);
    assertEquals("passIX", password.getEvaluatedPassword());

    password = createPassword("pass", 5);
    assertEquals("passV", password.getEvaluatedPassword());

    password = createPassword("pass", 4);
    assertEquals("passIV", password.getEvaluatedPassword());

    password = createPassword("pass", 1);
    assertEquals("passI", password.getEvaluatedPassword());

    password = createPassword("pass", 0);
    assertEquals("pass", password.getEvaluatedPassword());
  }

  @Test(expected = PasswordException.class)
  public void confirmPasswordPasswordShouldThrowExceptionWhenExpressionIsMalformed() throws PasswordException {
    MutablePassword password = new RomanNumeralPassword("pass[romanNumeral{a}]");
    password.confirmPassword("pass");
  }

  private MutablePassword createPassword(String text, int value) {
    return new RomanNumeralPasswordBuilder().createPassword(text, value);
  }

  private MutablePassword createPassword(String text, TimeParameter timeValue) {
    return new RomanNumeralPasswordBuilder().createPassword(text, timeValue);
  }

}
