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
import abid.password.MutableBlock;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.StatefulMutablePassword;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeParameter;

public class CaesarCipherPasswordTest extends TestCase {

  public void testPasswordObject() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("abid");
    password.setState(1);
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());

    assertEquals(CaesarCipherPassword.PASSWORD_TYPE, ((StatefulMutablePassword) unknownPassword).getType());
  }

  public void testShiftPassword() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("abid");
    password.setState(1);

    String confirmPassword = "bcje";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "abid";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testEvaluatedPassword() throws ParseException, PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    String passwordText = "abid";
    StatefulMutablePassword password = CaesarCipherPassword.createPassword(passwordText, timeType);

    // test the evaluated password
    assertEquals(true, password.confirmPassword(password.getEvaluatedPassword()));
  }

  public void testLowerCasePassword() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("z");
    password.setState(2);

    String confirmPassword = "b";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "a";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testLowerCasePassword2() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("zyxwvutsrqponmlkjihgfedcba");
    password.setState(1);

    String confirmPassword = "azyxwvutsrqponmlkjihgfedcb";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "zyxwvutsrqponmlkjihgfedcba";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testLowerCaseOneCharPass() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("f");
    password.setState(1);

    String confirmPassword = "g";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "f";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testLowerCaseNumberCombination() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("6abcdefghijklm6nopqrstuvwxyz6");
    password.setState(1);

    String confirmPassword = "6bcdefghijklmn6opqrstuvwxyza6";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "6abcdefghijklm6nopqrstuvwxyz6";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testLowerCaseSymbolNumberCombination() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("6abcdefghijklm^nopqrstuvwxyz6");
    password.setState(1);

    String confirmPassword = "6bcdefghijklmn^opqrstuvwxyza6";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "6abcdefghijklm^nopqrstuvwxyz6";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testUpperCasePassword() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    password.setState(1);

    String confirmPassword = "BCDEFGHIJKLMNOPQRSTUVWXYZA";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testUpperCaseNumberCombination() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("6ABCDEFGHIJKL6MNOPQRSTUVWXYZ6");
    password.setState(1);

    String confirmPassword = "6BCDEFGHIJKLM6NOPQRSTUVWXYZA6";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "6ABCDEFGHIJKL6MNOPQRSTUVWXYZ6";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testUppercaseLowercaseCombo() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("AbCdEfGhIjKlMnOpQrStUvWxYz");
    password.setState(1);

    String confirmPassword = "BcDeFgHiJkLmNoPqRsTuVwXyZa";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "AbCdEfGhIjKlMnOpQrStUvWxYz";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testLowercaseUppercase() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    password.setState(1);

    String confirmPassword = "bcdefghijklmnopqrstuvwxyzaBCDEFGHIJKLMNOPQRSTUVWXYZA";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testLowercasePassword() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("az");
    password.setState(1);

    String confirmPassword = "ba";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "az";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testLowercasePassword2() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("6z6");
    password.setState(1);

    String confirmPassword = "6a6";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "6z6";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testUppercaseLowercaseSymbolCombo() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("^AbCdEfGhIjKl^MnOpQrStUvWxYz^");
    password.setState(1);

    String confirmPassword = "^BcDeFgHiJkLm^NoPqRsTuVwXyZa^";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "^AbCdEfGhIjKl^MnOpQrStUvWxYz^";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testUppercaseLowercaseNumberSymbolCombo() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("6AbCdEfGhIjKl^MnOpQrStUvWxYz6");
    password.setState(1);

    String confirmPassword = "6BcDeFgHiJkLm^NoPqRsTuVwXyZa6";
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "6AbCdEfGhIjKl^MnOpQrStUvWxYz6";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testShiftNonAlphabeticalCorrectPassword() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("ab^id");
    password.setState(1);

    String confirmPassword = "bc^je";
    assertEquals(true, password.confirmPassword(confirmPassword));
  }

  public void testShiftNonAlphabeticalWrongPassword() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("ab^id");
    password.setState(1);

    String wrongPassword = "ab^id";
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testShiftPasswordUsingTime() throws PasswordException {
    // shift password every hour
    TimeParameter timeType = TimeParameter.DAY_OF_WEEK;
    CaesarCipherPassword shiftPassword = (CaesarCipherPassword) CaesarCipherPassword.createPassword("abid", timeType);

    String shiftedPassword = shiftPassword.getShiftedPassword(timeType.getCalendarValue());
    // System.out.println("shift by: " + timeType.getCalendarValue() +
    // " shifted value: " + shiftedPassword);
    assertEquals(true, shiftPassword.confirmPassword(shiftedPassword));

    String wrongPassword = "abid";
    assertEquals(false, shiftPassword.confirmPassword(wrongPassword));
  }

  public void testPasswordException() {
    String passwordText = "abid";
    MutableBlock mutableBlock = NewCaesarCipherPassword.createMutableBlock("a");
    CaesarCipherPassword password = new CaesarCipherPassword(passwordText + mutableBlock);

    String wrongPassword = "abid";
    try {
      password.confirmPassword(wrongPassword);
    } catch (PasswordException e) {
      return;
    }
    fail("Should not read this!");
  }

}