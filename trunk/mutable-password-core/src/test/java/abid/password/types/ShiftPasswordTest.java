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
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeParameter;
import abid.password.types.PasswordFactory;
import abid.password.types.ShiftPassword;

public class ShiftPasswordTest extends TestCase {

  public void testPasswordObject() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    MutablePassword password = ShiftPassword.createPassword("abid", 1);
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());

    assertEquals(ShiftPassword.PASSWORD_TYPE, ((MutablePassword) unknownPassword).getType());
  }

  public void testShiftPassword() throws PasswordException {
    Password dynamicPassword = ShiftPassword.createPassword("abid", 1);

    String confirmPassword = "bcje";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testEvaluatedPassword() throws ParseException, PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    String passwordText = "abid";
    MutablePassword password = ShiftPassword.createPassword(passwordText, timeType);

    // test the evaluated password
    assertEquals(true, password.confirmPassword(password.getEvaluatedPassword()));
  }

  public void testLowerCasePassword() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("abcdefghijklmnopqrstuvwxyz", 1);

    String confirmPassword = "bcdefghijklmnopqrstuvwxyza";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abcdefghijklmnopqrstuvwxyz";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testLowerCaseNumberCombination() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("6abcdefghijklm6nopqrstuvwxyz6", 1);

    String confirmPassword = "6bcdefghijklmn6opqrstuvwxyza6";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "6abcdefghijklm6nopqrstuvwxyz6";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testLowerCaseSymbolNumberCombination() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("6abcdefghijklm^nopqrstuvwxyz6", 1);

    String confirmPassword = "6bcdefghijklmn^opqrstuvwxyza6";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "6abcdefghijklm^nopqrstuvwxyz6";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testUpperCasePassword() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1);

    String confirmPassword = "BCDEFGHIJKLMNOPQRSTUVWXYZA";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testUpperCaseNumberCombination() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("6ABCDEFGHIJKL6MNOPQRSTUVWXYZ6", 1);

    String confirmPassword = "6BCDEFGHIJKLM6NOPQRSTUVWXYZA6";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "6ABCDEFGHIJKL6MNOPQRSTUVWXYZ6";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testUppercaseLowercaseCombo() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("AbCdEfGhIjKlMnOpQrStUvWxYz", 1);

    String confirmPassword = "BcDeFgHiJkLmNoPqRsTuVwXyZa";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "AbCdEfGhIjKlMnOpQrStUvWxYz";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testLowercaseUppercase() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", 1);

    String confirmPassword = "bcdefghijklmnopqrstuvwxyzaBCDEFGHIJKLMNOPQRSTUVWXYZA";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testLowercasePassword() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("az", 1);

    String confirmPassword = "ba";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "az";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testLowercasePassword2() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("6z6", 1);

    String confirmPassword = "6a6";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "6z6";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testUppercaseLowercaseSymbolCombo() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("^AbCdEfGhIjKl^MnOpQrStUvWxYz^", 1);

    String confirmPassword = "^BcDeFgHiJkLm^NoPqRsTuVwXyZa^";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "^AbCdEfGhIjKl^MnOpQrStUvWxYz^";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testUppercaseLowercaseNumberSymbolCombo() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("6AbCdEfGhIjKl^MnOpQrStUvWxYz6", 1);

    String confirmPassword = "6BcDeFgHiJkLm^NoPqRsTuVwXyZa6";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "6AbCdEfGhIjKl^MnOpQrStUvWxYz6";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testShiftNonAlphabeticalCorrectPassword() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("ab^id", 1);

    String confirmPassword = "bc^je";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));
  }

  public void testShiftNonAlphabeticalWrongPassword() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("ab^id", 1);

    String wrongPassword = "ab^id";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testShiftPasswordUsingTime() throws PasswordException {
    // shift password every hour
    TimeParameter timeType = TimeParameter.DAY_OF_WEEK;
    ShiftPassword shiftPassword = (ShiftPassword) ShiftPassword.createPassword("abid", timeType);

    String shiftedPassword = shiftPassword.getShiftedPassword(timeType.getCalendarValue());
    // System.out.println("shift by: " + timeType.getCalendarValue() +
    // " shifted value: " + shiftedPassword);
    assertEquals(true, shiftPassword.confirmPassword(shiftedPassword));

    String wrongPassword = "abid";
    assertEquals(false, shiftPassword.confirmPassword(wrongPassword));
  }

  public void testPasswordException() {
    String passwordText = "abid";
    MutableBlock mutableBlock = NewShiftPassword.createMutableBlock("a");
    MutablePassword password = new ShiftPassword(passwordText, mutableBlock);

    String wrongPassword = "abid";
    try {
      password.confirmPassword(wrongPassword);
    } catch (PasswordException e) {
      return;
    }
    fail("Should not read this!");
  }

}
