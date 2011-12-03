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

import java.util.Map;

import org.junit.Test;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.evaluator.Evaluator;
import abid.password.evaluator.JavascriptEvaluator;
import abid.password.evaluator.ParseException;
import abid.password.parameters.Parameter;
import abid.password.parameters.TimeParameter;
import abid.password.parameters.ZodiacParameter;

public class ExtendedPasswordTest {

  @Test
  public void passwordFactoryShouldReturnCorrectPasswordType() throws PasswordInstantiationException {
    Password unknownPassword = PasswordFactory.getInstance(ExtendedPassword.createPassword("abid", TimeParameter.YEAR).getPassword());
    assertEquals(ExtendedPassword.class, unknownPassword.getClass());
    assertEquals(ExtendedPassword.PASSWORD_TYPE, ((MutablePassword) unknownPassword).getType());
  }

  @Test
  public void confirmPasswordShouldValidatePasswordCorrectly() throws PasswordException, ParseException {
    MutablePassword password = ExtendedPassword.createPassword("pass1", TimeParameter.YEAR);
    assertTrue(password.confirmPassword("pass1" + TimeParameter.YEAR.getCalendarValue()));
    assertFalse(password.confirmPassword("pass1a" + TimeParameter.YEAR.getCalendarValue()));

    password = ExtendedPassword.createPassword("pass1", "year+year");
    assertTrue(password.confirmPassword("pass1" + (TimeParameter.YEAR.getCalendarValue() + TimeParameter.YEAR.getCalendarValue())));
    assertFalse(password.confirmPassword("pass1" + TimeParameter.YEAR.getCalendarValue()));

    password = ExtendedPassword.createPassword("pass", "((year+year)*2)-10");
    assertTrue(password
        .confirmPassword("pass" + ((TimeParameter.YEAR.getCalendarValue() + TimeParameter.YEAR.getCalendarValue()) * 2 - 10)));

    password = ExtendedPassword.createPassword("pass", "zodiac");
    assertTrue(password.confirmPassword("pass" + ZodiacParameter.getTodaysZodiac().getSign()));
    assertFalse(password.confirmPassword("pass"));

    password = ExtendedPassword.createPassword("ab^id", TimeParameter.YEAR);
    assertTrue(password.confirmPassword("ab^id" + TimeParameter.YEAR.getCalendarValue()));
    assertFalse(password.confirmPassword("abid" + TimeParameter.YEAR.getCalendarValue()));

    password = new ExtendedPassword("abid", ExtendedPassword.createMutableBlock(TimeParameter.HOUR));
    assertTrue(password.confirmPassword("abid" + TimeParameter.HOUR.getCalendarValue()));

    password = ExtendedPassword.createPassword("ab^id", TimeParameter.YEAR);
    String confirmPassword = "ab^id" + TimeParameter.YEAR.getCalendarValue();
    assertTrue(password.confirmPassword(confirmPassword));
  }

  @Test
  public void getEvaluatedPasswordShouldReturnCorrectEvaludatyion() throws ParseException {
    MutablePassword password = ExtendedPassword.createPassword("pass", TimeParameter.YEAR);
    assertEquals("pass2011", password.getEvaluatedPassword());

    password = ExtendedPassword.createPassword("pass", TimeParameter.YEAR.getTextField() + "-" + TimeParameter.YEAR.getTextField());
    assertEquals("pass0", password.getEvaluatedPassword());

    // algebra
    password = ExtendedPassword.createPassword("pass", "((year+year)*2)-10");
    assertEquals("pass8034", password.getEvaluatedPassword());

    // algebra and zodiac
    password = ExtendedPassword.createPassword("pass", "(year-10)+zodiac");
    assertEquals("pass2001Sagittarius", password.getEvaluatedPassword());
  }

  @Test
  public void createMutableBlockShouldReturnCorrectParameters() {
    MutableBlock mutableBlock = ExtendedPassword.createMutableBlock(TimeParameter.HOUR);
    assertEquals(TimeParameter.HOUR.getTextField(), mutableBlock.getExpression());
    assertEquals(ExtendedPassword.PASSWORD_TYPE, mutableBlock.getType());
  }

  @Test(expected = PasswordException.class)
  public void confirmPasswordShouldThrowExceptionWhenExpressionIsMalformed() throws PasswordException {
    Password password = ExtendedPassword.createPassword("pass", "year(year");
    password.confirmPassword("pass");
  }

  @Test(expected = ParseException.class)
  public void getEvaluatedPasswordShouldThrowExceptionWhenExpressionIsMalformed() throws ParseException {
    MutablePassword password = ExtendedPassword.createPassword("pass", "year(year");
    password.getEvaluatedPassword();
  }

  @Test
  public void testNullMutableBlock() {
    String passwordText = "pass";
    MutablePassword password = new ExtendedPassword(passwordText, null);
    assertNull(password.getExpression());
    assertNull(password.getType());
    assertNull(password.getMutableBlock());
    assertEquals(passwordText, password.getText());
  }

  @Test
  public void testBrokenMutableExpression() {
    ExtendedPassword password = new ExtendedPassword("abidextendedTimeLock{year}]");
    assertNull(password.getExpression());
    assertNull(password.getType());
    assertNull(password.getMutableBlock());
    assertNull(password.getText());
  }

  @Test
  public void testAccessorAndMutators() {
    MutablePassword password = new ExtendedPassword("pass", null);
    assertEquals(JavascriptEvaluator.class, password.getEvaluator().getClass());

    password.setEvaluator(new NewEvalutator());
    assertEquals(NewEvalutator.class, password.getEvaluator().getClass());
  }

  public class NewEvalutator implements Evaluator {

    @Override
    public String evaluateExpression(String expression, Map<String, Parameter> map) throws ParseException {
      return null;
    }
  }

}
