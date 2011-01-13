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

public class ExtendedPasswordTest extends TestCase {

  public void testPasswordType() {
    MutablePassword password = ExtendedPassword.createPassword("abid", "year(year");
    assertEquals("extend", password.getPasswordType());
  }

  public void testPasswordObject() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    TimeParameter timeType = TimeParameter.YEAR;
    MutablePassword password = ExtendedPassword.createPassword("abid", timeType);
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());

    assertEquals(ExtendedPassword.PASSWORD_TYPE, ((MutablePassword) unknownPassword).getType());
  }

  public void testExtendedPassword() throws PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("abid", timeType);

    String confirmPassword = "abid" + timeType.getCalendarValue();
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "abida" + timeType.getCalendarValue();
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testEvaluatedPassword() throws ParseException, PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    String passwordText = "abid";
    MutablePassword password = ExtendedPassword.createPassword(passwordText, timeType);

    // test the evaluated password
    assertEquals(true, password.confirmPassword(password.getEvaluatedPassword()));
  }

  public void testExtendedPassword2() throws PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("abid", "year+year");

    String confirmPassword = "abid" + (timeType.getCalendarValue() + timeType.getCalendarValue());
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "abid" + timeType.getCalendarValue();
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  public void testExtendedPassword3() throws PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("abid", "year+1.5");

    String confirmPassword = "abid" + (timeType.getCalendarValue() + 1.5);
    assertEquals(true, password.confirmPassword(confirmPassword));

    String wrongPassword = "abid" + timeType.getCalendarValue();
    assertEquals(false, password.confirmPassword(wrongPassword));
  }

  /*
   * This method is well dodgy. Yahoo will throw a http response 99 sometimes.
   */
  /*
   * public void testExtendedPasswordStockMarketParameter() throws
   * PasswordException { try { StockMarketParameter stock =
   * StockMarketParameter.FTSE100; Password password =
   * ExtendedPassword.createPassword("abid", stock.getMarket());
   * 
   * // System.out.println(dynamicPassword.getPassword());
   * 
   * String confirmPassword = "abid" + stock.getIndexValue(); //
   * System.out.println(confirmPassword); assertEquals(true,
   * password.confirmPassword(confirmPassword));
   * 
   * String wrongPassword = "abid"; assertEquals(false,
   * password.confirmPassword(wrongPassword)); } catch (Exception e) {
   * e.printStackTrace(); } }
   */

  public void testMutableBlock() {
    MutableBlock mutableBlock = ExtendedPassword.createMutableBlock(TimeParameter.HOUR);
    assertEquals(TimeParameter.HOUR.getTextField(), mutableBlock.getExpression());
    assertEquals(ExtendedPassword.PASSWORD_TYPE, mutableBlock.getType());
  }

  public void testMutableBlockConstruction() throws PasswordException {
    MutableBlock mutableBlock = ExtendedPassword.createMutableBlock(TimeParameter.HOUR);
    ExtendedPassword extendedPassword = new ExtendedPassword("abid", mutableBlock);

    String confirmPassword = "abid" + TimeParameter.HOUR.getCalendarValue();
    assertEquals(true, extendedPassword.confirmPassword(confirmPassword));
  }

  public void testNonAlphbecticalWrongPassword() throws PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("ab^id", timeType);

    String confirmPassword = "abid" + timeType.getCalendarValue();
    assertEquals(false, password.confirmPassword(confirmPassword));
  }

  public void testNonAlphbecticalCorrectPassword() throws PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("ab^id", timeType);

    String confirmPassword = "ab^id" + timeType.getCalendarValue();
    assertEquals(true, password.confirmPassword(confirmPassword));
  }

  public void testPasswordException() {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("abid", "year(year");

    String confirmPassword = "abid" + (timeType.getCalendarValue() + timeType.getCalendarValue());
    boolean isAuthenticated = false;
    try {
      isAuthenticated = password.confirmPassword(confirmPassword);
    } catch (PasswordException e) {
    }
    assertEquals(false, isAuthenticated);
  }

  public void testStringOutput() {
    Password password = ExtendedPassword.createPassword("abid", "year(year");
    System.out.println(password.toString());
  }
}
