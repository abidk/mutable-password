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

import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.evaluator.EvaluationException;
import abid.password.parameters.TimeParameter;

public class ExtendedTimeLockPasswordTest {

  @Test
  public void testEvaluatedPassword() throws PasswordException, EvaluationException {
    MutablePassword password = createPassword("pass", TimeParameter.YEAR, TimeParameter.HOUR, 0, 24);
    assertTrue(password.confirmPassword(password.getEvaluatedPassword()));
  }

  @Test
  public void confirmPasswordShouldValidateCorrectly() throws PasswordException {
    Password password = createPassword("pass", TimeParameter.YEAR, TimeParameter.HOUR, 0, 24);
    assertTrue(password.confirmPassword("pass" + (TimeParameter.YEAR).getCalendarValue()));
    assertFalse(password.confirmPassword("pass2" + (TimeParameter.YEAR).getCalendarValue()));
    assertFalse(password.confirmPassword("pass"));
    assertFalse(password.confirmPassword("pass2009"));

    password = createPassword("pass", TimeParameter.YEAR, TimeParameter.HOUR, -1, -1);
    assertFalse(password.confirmPassword("pass" + (TimeParameter.YEAR).getCalendarValue()));
    assertFalse(password.confirmPassword("pass"));
  }

  @Test(expected = PasswordException.class)
  public void confirmPasswordShouldThrowExceptionWhenExpressionIsBadlyFormed() throws PasswordException {
    Password pass = new ExtendedTimeLockPassword("password[extendedTimeLock{year((+((year}]");
    pass.confirmPassword("password" + (TimeParameter.YEAR).getCalendarValue());
  }

  private MutablePassword createPassword(String text, TimeParameter timeParam, TimeParameter lockTimeType, int lockStartTime, int lockEndTime) {
    return new ExtendedTimeLockPasswordBuilder().createPassword(text, timeParam, lockTimeType, lockStartTime, lockEndTime);
  }
}
