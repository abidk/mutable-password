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

import java.util.Calendar;

import junit.framework.TestCase;
import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeParameter;
import abid.password.types.TimeLockPassword;

public class TimeLockPasswordTest extends TestCase {

  public void testTimeLockPassword() throws PasswordException {
    String confirmPassword = "abid";
    MutablePassword mutatingPassword = TimeLockPassword.createPassword("abid", TimeParameter.HOUR, 0, 24);
    assertEquals(true, mutatingPassword.confirmPassword(confirmPassword));

    MutablePassword mutatingPassword2 = TimeLockPassword.createPassword("abid", TimeParameter.HOUR, -2, -1);
    assertEquals(false, mutatingPassword2.confirmPassword(confirmPassword));

    String wrongPassword = "diba";
    MutablePassword mutatingPassword3 = TimeLockPassword.createPassword("abid", TimeParameter.HOUR, 0, 24);
    assertEquals(false, mutatingPassword3.confirmPassword(wrongPassword));
  }

  public void testEvaluatedPassword() throws ParseException, PasswordException {
    String passwordText = "abid";
    MutablePassword password = TimeLockPassword.createPassword(passwordText, TimeParameter.HOUR, 0, 24);

    // test the evaluated password
    assertEquals(true, password.confirmPassword(password.getEvaluatedPassword()));
  }
  
  public void testDayOfWeekPassword() throws PasswordException {
    // Calendar.SUNDAY = 1
    // Calendar.MONDAY = 2
    // Calendar.TUESDAY = 3
    // Calendar.WEDNESDAY = 4
    // Calendar.THURSDAY = 5
    // Calendar.FRIDAY = 6
    // Calendar.SATURDAY = 7
    String confirmPassword = "a";
    String wrongPassword = "wrongDayOfWeek";
    MutablePassword mutatingPassword = TimeLockPassword.createPassword(confirmPassword, TimeParameter.DAY_OF_WEEK, Calendar.SUNDAY, Calendar.SATURDAY);
    assertEquals(true, mutatingPassword.confirmPassword(confirmPassword));
    assertEquals(false, mutatingPassword.confirmPassword(wrongPassword));
  }

  public void testPasswordException() {
    String passwordText = "abid";
    String confirmPassword = "abid";
    MutableBlock mutableBlock = NewTimeLockPassword.createMutableBlock(TimeParameter.HOUR, "a", "b");
    MutablePassword mutatingPassword = new TimeLockPassword(passwordText, mutableBlock);

    try {
      mutatingPassword.confirmPassword(confirmPassword);
    } catch (PasswordException e) {
      return;
    }
    fail("Should not read this!");
  }
}
