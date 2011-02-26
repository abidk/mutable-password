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
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeParameter;

public class RomanNumeralPasswordTest extends TestCase {

  public void testPasswordType() {
    MutablePassword password = RomanNumeralPassword.createPassword("pass", TimeParameter.YEAR);
    assertEquals("romanNumeral", password.getPasswordType());
  }

  public void testPasswordFactory() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    MutablePassword password = RomanNumeralPassword.createPassword("pass", TimeParameter.YEAR);
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());
    assertEquals(RomanNumeralPassword.PASSWORD_TYPE, ((MutablePassword) unknownPassword).getType());
  }

  public void testPasswordEvaluation() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("pass", TimeParameter.YEAR);
    assertNotNull(password.getEvaluatedPassword());
  }

  public void testRomanNumeralM() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "1000");
    assertEquals("-M", password.getEvaluatedPassword());
  }

  public void testRomanNumeralCM() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "900");
    assertEquals("-CM", password.getEvaluatedPassword());
  }

  public void testRomanNumeralD() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "500");
    assertEquals("-D", password.getEvaluatedPassword());
  }

  public void testRomanNumeralCD() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "400");
    assertEquals("-CD", password.getEvaluatedPassword());
  }

  public void testRomanNumeralC() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "100");
    assertEquals("-C", password.getEvaluatedPassword());
  }

  public void testRomanNumeralXC() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "90");
    assertEquals("-XC", password.getEvaluatedPassword());
  }

  public void testRomanNumeralX() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "10");
    assertEquals("-X", password.getEvaluatedPassword());
  }

  public void testRomanNumeralIX() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "9");
    assertEquals("-IX", password.getEvaluatedPassword());
  }

  public void testRomanNumeralV() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "5");
    assertEquals("-V", password.getEvaluatedPassword());
  }

  public void testRomanNumeralIV() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "4");
    assertEquals("-IV", password.getEvaluatedPassword());
  }

  public void testRomanNumeralI() throws ParseException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "1");
    assertEquals("-I", password.getEvaluatedPassword());
  }

  public void testPasswordContainsNoRomanNumeral() throws PasswordException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "0");
    assertTrue(password.confirmPassword("-"));
  }

  public void testPasswordContainsNoRomanNumeral2() throws PasswordException {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "'a'");
    assertTrue(password.confirmPassword("-a"));
  }

  public void testUneascapedExpression() {
    MutablePassword password = RomanNumeralPassword.createPassword("-", "a");
    try {
      assertTrue(password.confirmPassword("-a"));
    } catch (PasswordException e) {
      return;
    }
    assert false;
  }
}
