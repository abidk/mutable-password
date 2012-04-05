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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.evaluator.EvaluationException;
import abid.password.evaluator.ExpressionEvaluator;
import abid.password.parameters.Parameter;
import abid.password.parameters.TimeParameter;
import abid.password.parameters.ZodiacParameter;

public class ExtendedPasswordTest {

  @Test
  public void getTypeShouldReturnExtendedPasswordType() throws PasswordException, EvaluationException {
    MutablePassword password = createPassword("pass1", TimeParameter.YEAR);
    assertEquals(ExtendedPassword.PASSWORD_TYPE, password.getType());
  }

  @Test
  public void confirmPasswordShouldValidatePasswordCorrectly() throws PasswordException, EvaluationException {
    MutablePassword password = createPassword("pass1", TimeParameter.YEAR);
    assertTrue(password.confirmPassword("pass1" + TimeParameter.YEAR.getCalendarValue()));
    assertFalse(password.confirmPassword("pass1a" + TimeParameter.YEAR.getCalendarValue()));

    password = createPassword("pass1", "year+year");
    assertTrue(password.confirmPassword("pass1" + (TimeParameter.YEAR.getCalendarValue() + TimeParameter.YEAR.getCalendarValue())));
    assertFalse(password.confirmPassword("pass1" + TimeParameter.YEAR.getCalendarValue()));

    password = createPassword("pass", "((year+year)*2)-10");
    assertTrue(password.confirmPassword("pass" + ((TimeParameter.YEAR.getCalendarValue() + TimeParameter.YEAR.getCalendarValue()) * 2 - 10)));

    password = createPassword("pass", "zodiac");
    assertTrue(password.confirmPassword("pass" + ZodiacParameter.getTodaysZodiac().getSign()));
    assertFalse(password.confirmPassword("pass"));

    password = createPassword("ab^id", TimeParameter.YEAR);
    assertTrue(password.confirmPassword("ab^id" + TimeParameter.YEAR.getCalendarValue()));
    assertFalse(password.confirmPassword("abid" + TimeParameter.YEAR.getCalendarValue()));

    password = new ExtendedPassword("abid", new ExtendedPasswordBuilder().createMutableBlock(TimeParameter.HOUR));
    assertTrue(password.confirmPassword("abid" + TimeParameter.HOUR.getCalendarValue()));

    password = createPassword("ab^id", TimeParameter.YEAR);
    String confirmPassword = "ab^id" + TimeParameter.YEAR.getCalendarValue();
    assertTrue(password.confirmPassword(confirmPassword));
  }

  @Test
  public void getEvaluatedPasswordShouldReturnCorrectEvaluation() throws EvaluationException {
    MutablePassword password = createPassword("pass", TimeParameter.YEAR.getTextField() + "-" + TimeParameter.YEAR.getTextField());
    assertEquals("pass0", password.getEvaluatedPassword());

    password = createPassword("pass", "(year-year)+10");
    assertEquals("pass10", password.getEvaluatedPassword());
  }

  @Test
  public void createMutableBlockShouldReturnCorrectParameters() {
    MutableBlock mutableBlock = new ExtendedPasswordBuilder().createMutableBlock(TimeParameter.HOUR);
    assertEquals(TimeParameter.HOUR.getTextField(), mutableBlock.getExpression());
    assertEquals(ExtendedPassword.PASSWORD_TYPE, mutableBlock.getType());
  }

  @Test(expected = PasswordException.class)
  public void confirmPasswordShouldThrowExceptionWhenExpressionIsMalformed() throws PasswordException {
    Password password = createPassword("pass", "year(year");
    password.confirmPassword("pass");
  }

  @Test(expected = EvaluationException.class)
  public void getEvaluatedPasswordShouldThrowExceptionWhenExpressionIsMalformed() throws EvaluationException {
    MutablePassword password = createPassword("pass", "year(year");
    password.getEvaluatedPassword();
  }

  @Test
  public void badlyFormedExpressionShouldParseCorrectly() {
    MutablePassword password = new ExtendedPassword("pass", null);
    assertNull(password.getExpression());
    assertNull(password.getType());
    assertNull(password.getMutableBlock());
    assertEquals("pass", password.getText());

    password = new ExtendedPassword("abidextendedTimeLock{year}]");
    assertNull(password.getExpression());
    assertNull(password.getType());
    assertNull(password.getMutableBlock());
    assertNull(password.getText());
  }

  @Test
  public void setEvaluatorShouldReturnCorrectSetEvaluator() {
    MutablePassword password = new ExtendedPassword("pass", null);
    password.setEvaluator(new NewEvalutator());
    assertEquals(NewEvalutator.class, password.getEvaluator().getClass());
  }

  public class NewEvalutator implements ExpressionEvaluator {

    @Override
    public String evaluate(String expression, Map<String, Parameter> map) throws EvaluationException {
      return null;
    }
  }

  private MutablePassword createPassword(String text, String expression) {
    return new ExtendedPasswordBuilder().createPassword(text, expression);
  }

  private MutablePassword createPassword(String text, TimeParameter timeValue) {
    return new ExtendedPasswordBuilder().createPassword(text, timeValue);
  }
}
