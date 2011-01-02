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
import abid.password.parameters.TimeParameter;
import abid.password.types.ExtendedTimeLockPassword;
import abid.password.types.PasswordFactory;

public class ExtendedTimeLockPasswordTest extends TestCase {

  public void testPasswordObject() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    Password password = ExtendedTimeLockPassword.createPassword("abid", TimeParameter.YEAR, TimeParameter.HOUR, 0, 24);
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());

    // System.out.println(password.toString());
    assertEquals(ExtendedTimeLockPassword.PASSWORD_TYPE, ((MutablePassword) unknownPassword).getType());
  }

  public void testPassword() throws PasswordException {

    Password comboPassword = ExtendedTimeLockPassword.createPassword("abid", TimeParameter.YEAR, TimeParameter.HOUR, 0, 24);
    assertEquals(true, comboPassword.confirmPassword("abid" + (TimeParameter.YEAR).getCalendarValue()));
  }

  public void testExpressionLength() {
    Password pass = new ExtendedTimeLockPassword("abid[extendedTimeLock{year}]");
    try {
      pass.confirmPassword("abid" + (TimeParameter.YEAR).getCalendarValue());
    } catch (PasswordException e) {
      return;
    }
    fail("Should not get here");
  }

  public void testWrongPasswordText() throws PasswordException {
    Password comboPassword = ExtendedTimeLockPassword.createPassword("abid", TimeParameter.YEAR, TimeParameter.HOUR, 0, 24);
    assertEquals(false, comboPassword.confirmPassword("abid2" + (TimeParameter.YEAR).getCalendarValue()));
  }

  public void testWrongPasswordTime() throws PasswordException {
    Password comboPassword = ExtendedTimeLockPassword.createPassword("abid", TimeParameter.YEAR, TimeParameter.HOUR, 0, 24);
    assertEquals(false, comboPassword.confirmPassword("abid" + 2009));
  }

  public void testWrongPasswordLock() throws PasswordException {
    Password comboPassword = ExtendedTimeLockPassword.createPassword("abid", TimeParameter.YEAR, TimeParameter.HOUR, -1, -1);
    assertEquals(false, comboPassword.confirmPassword("abid" + (TimeParameter.YEAR).getCalendarValue()));
  }

  public void testExtendedException() {
    Password pass = new ExtendedTimeLockPassword("abid[extendedTimeLock{year((+((year}]");
    try {
      pass.confirmPassword("abid" + (TimeParameter.YEAR).getCalendarValue());
    } catch (PasswordException e) {
      return;
    }

    fail("Should not reach this!");
  }

  public void testTimeLockException() {
    MutableBlock mutableBlock = NewExtendedTimeLockPassword.createMutableBlock(TimeParameter.YEAR, TimeParameter.HOUR, "a", "a");
    Password comboPassword = new ExtendedTimeLockPassword("abid", mutableBlock);
    try {
      comboPassword.confirmPassword("abid" + (TimeParameter.YEAR).getCalendarValue());
    } catch (PasswordException e) {
      return;
    }

    fail("Should not reach this!");
  }
}