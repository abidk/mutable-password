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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.evaluator.EvaluationException;
import abid.password.parameters.TimeParameter;

public class TimeLockPasswordTest {

  @Test
  public void confirmPasswordShouldValidatePasswordCorrectly() throws PasswordException {
    MutablePassword password = createPassword("pass1", TimeParameter.HOUR, 0, 24);
    assertTrue(password.confirmPassword("pass1"));
    assertFalse(password.confirmPassword("pass123"));

    password = createPassword("pass1", TimeParameter.HOUR, -2, -1);
    assertFalse(password.confirmPassword("pass1"));
    assertFalse(password.confirmPassword("pass123"));

    password = createPassword("pass1", TimeParameter.DAY_OF_WEEK, Calendar.SUNDAY, Calendar.SATURDAY);
    assertTrue(password.confirmPassword("pass1"));
    assertFalse(password.confirmPassword("pass123"));
  }

  @Test
  public void getEvaluatedPasswordShouldReturnCorrectValue() throws EvaluationException, PasswordException {
    MutablePassword password = createPassword("pass1", TimeParameter.HOUR, 0, 24);
    assertEquals("pass1", password.getEvaluatedPassword());

    password = createPassword("pass1", TimeParameter.HOUR, -2, -1);
    assertEquals("pass1", password.getEvaluatedPassword());
  }

  @Test(expected = PasswordException.class)
  public void confirmPasswordPasswordShouldThrowExceptionWhenExpressionIsMalformed() throws PasswordException {
    MutableBlock mutableBlock = new TimeLockPasswordBuilder().createMutableBlock(TimeParameter.HOUR, 1, 2);
    TimeLockPassword password = new TimeLockPassword("pass", mutableBlock) {
      @Override
      protected boolean isPasswordLocked() throws EvaluationException {
        throw new EvaluationException(new Exception("some exception"));
      }
    };
    password.confirmPassword("pass");
  }

  private MutablePassword createPassword(String text, TimeParameter timeType, int start, int end) {
    return new TimeLockPasswordBuilder().createPassword(text, timeType, start, end);
  }

}
