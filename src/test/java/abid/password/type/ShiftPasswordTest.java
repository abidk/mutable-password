/* Copyright 2009 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package abid.password.type;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
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

  public void testShiftPassword2() throws PasswordException {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("abid", 1);

    String confirmPassword = "bcje";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testShiftPasswordUsingTime() throws PasswordException {
    // shift password every hour
    TimeParameter timeType = TimeParameter.DAY_OF_WEEK;
    ShiftPassword shiftPassword = (ShiftPassword) ShiftPassword.createPassword("abid", timeType);

    String shiftedPassword = shiftPassword.getShiftedPassword(timeType.getCalendarValue());
    // System.out.println("shift by: " + timeType.getCalendarValue() + " shifted value: " + shiftedPassword);
    assertEquals(true, shiftPassword.confirmPassword(shiftedPassword));

    String wrongPassword = "abid";
    assertEquals(false, shiftPassword.confirmPassword(wrongPassword));
  }
}
