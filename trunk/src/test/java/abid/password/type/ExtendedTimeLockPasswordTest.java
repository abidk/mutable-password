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
import abid.password.types.ExtendedTimeLockPassword;
import abid.password.types.PasswordFactory;

public class ExtendedTimeLockPasswordTest extends TestCase {

  public void testPasswordObject() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    Password password = ExtendedTimeLockPassword.createPassword("abid", TimeParameter.YEAR, TimeParameter.HOUR, 0, 24);
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());

    assertEquals(ExtendedTimeLockPassword.PASSWORD_TYPE, ((MutablePassword) unknownPassword).getType());
  }

  public void testPassword() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException,
      NoSuchMethodException, PasswordException {

    Password comboPassword = ExtendedTimeLockPassword.createPassword("abid", TimeParameter.YEAR, TimeParameter.HOUR, 0, 24);
    assertEquals(true, comboPassword.confirmPassword("abid" + (TimeParameter.YEAR).getCalendarValue()));
  }

}
