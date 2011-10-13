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
import abid.password.parameters.ZodiacParameter;
import abid.password.parameters.ZodiacParameter.Zodiac;

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
    assertTrue(password.confirmPassword(confirmPassword));

    String wrongPassword = "abida" + timeType.getCalendarValue();
    assertFalse(password.confirmPassword(wrongPassword));
  }

  public void testEvaluatedPassword() throws ParseException, PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    String passwordText = "abid";
    MutablePassword password = ExtendedPassword.createPassword(passwordText, timeType);

    // test the evaluated password
    assertTrue(password.confirmPassword(password.getEvaluatedPassword()));
  }

  public void testExtendedPassword2() throws PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("abid", "year+year");

    String confirmPassword = "abid" + (timeType.getCalendarValue() + timeType.getCalendarValue());
    assertTrue(password.confirmPassword(confirmPassword));

    String wrongPassword = "abid" + timeType.getCalendarValue();
    assertFalse(password.confirmPassword(wrongPassword));
  }

  public void testAlgebraExtendedPassword() throws ParseException {
    String algebra = "((year+year)*2)-10";
    MutablePassword algebraPassword = ExtendedPassword.createPassword("pAss", algebra);
    //System.out.println(algebraPassword.getEvaluatedPassword());
    assertNotNull(algebraPassword.getEvaluatedPassword());
  }
  
  public void testAlgebraPasswordWithZodiacParameter() throws ParseException {
    String algebra = "(year-10)+zodiac";
    MutablePassword algebraPassword = ExtendedPassword.createPassword("pAss", algebra);
    //System.out.println(algebraPassword.getEvaluatedPassword());
    assertNotNull(algebraPassword.getEvaluatedPassword());
  }

  public void testAlgebraPasswordThatChangesHourly() throws ParseException {
    String x = TimeParameter.YEAR.getTextField();
    String y = TimeParameter.HOUR.getTextField();
    String algebra = x + "-" + y;
    MutablePassword algebraPassword = ExtendedPassword.createPassword("pAss", algebra);
    //System.out.println(algebraPassword.getEvaluatedPassword());
    assertNotNull(algebraPassword.getEvaluatedPassword());
  }

  public void testPasswordWithZodiacParameter() throws PasswordException, ParseException {
    MutablePassword password = ExtendedPassword.createPassword("abid", ZodiacParameter.ZODIAC.getParameter());
    //System.out.println("Password: " + password.getPassword());
    //System.out.println("Evaluated: " + password.getEvaluatedPassword());

    Zodiac todayZodiac = ZodiacParameter.getTodaysZodiac();
    String confirmPassword = "abid" + todayZodiac.getSign();
    //System.out.println("Confirm: " + confirmPassword);
    assertTrue(password.confirmPassword(confirmPassword));
  }

  public void testExtendedPassword3() throws PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("abid", "year+1.5");

    String confirmPassword = "abid" + (timeType.getCalendarValue() + 1.5);
    assertTrue(password.confirmPassword(confirmPassword));

    String wrongPassword = "abid" + timeType.getCalendarValue();
    assertFalse(password.confirmPassword(wrongPassword));
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
    assertTrue(extendedPassword.confirmPassword(confirmPassword));
  }

  public void testNonAlphbecticalWrongPassword() throws PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("ab^id", timeType);

    String confirmPassword = "abid" + timeType.getCalendarValue();
    assertFalse(password.confirmPassword(confirmPassword));
  }

  public void testNonAlphbecticalCorrectPassword() throws PasswordException {
    TimeParameter timeType = TimeParameter.YEAR;
    Password password = ExtendedPassword.createPassword("ab^id", timeType);

    String confirmPassword = "ab^id" + timeType.getCalendarValue();
    assertTrue(password.confirmPassword(confirmPassword));
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
    assertFalse(isAuthenticated);
  }

  public void testPasswordString() throws ParseException {
    Password password = ExtendedPassword.createPassword("abid", "year+year");
    assertNotNull(password.getPassword());

    MutablePassword mutablePassword = (MutablePassword) password;
    assertNotNull(mutablePassword.getPassword());
    assertNotNull(mutablePassword.getEvaluatedPassword());
  }

  public void testBrokenPasswordStringThrowsException() {
    Password password = ExtendedPassword.createPassword("abid", "year(year");
    assertNotNull(password.getPassword());

    MutablePassword mutablePassword = (MutablePassword) password;
    assertNotNull(mutablePassword.getPassword());

    try {
      assertNotNull(mutablePassword.getEvaluatedPassword());
    } catch (ParseException e) {
      // e.printStackTrace();
      return;
    }
    fail("Should not reach here");
  }
}
