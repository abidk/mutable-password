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
import abid.password.PasswordFactory;
import abid.password.parameters.StockMarketType;
import abid.password.parameters.TimeType;
import abid.password.types.ExtendedPassword;

public class ExtendedPasswordTest extends TestCase {

  public void testPasswordObject() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    TimeType timeType = TimeType.YEAR;
    MutablePassword password = ExtendedPassword.createPassword("abid", timeType);
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());

    assertEquals(ExtendedPassword.PASSWORD_TYPE, ((MutablePassword) unknownPassword).getType());
  }

  public void testExtendedPassword() throws PasswordException {
    TimeType timeType = TimeType.YEAR;
    Password dynamicPassword = ExtendedPassword.createPassword("abid", timeType);

    String confirmPassword = "abid" + timeType.getCalendarValue();
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abida" + timeType.getCalendarValue();
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testExtendedPassword2() throws PasswordException {
    TimeType timeType = TimeType.YEAR;
    Password dynamicPassword = ExtendedPassword.createPassword("abid", "year+year");

    String confirmPassword = "abid" + (timeType.getCalendarValue() + timeType.getCalendarValue());
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid" + timeType.getCalendarValue();
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testExtendedPassword3() throws PasswordException {
    TimeType timeType = TimeType.YEAR;
    Password dynamicPassword = ExtendedPassword.createPassword("abid", "year+1.5");

    String confirmPassword = "abid" + (timeType.getCalendarValue() + 1.5);
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid" + timeType.getCalendarValue();
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testExtendedPasswordStockMarketParameter() throws PasswordException {
    StockMarketType stock = StockMarketType.FTSE100;
    Password dynamicPassword = ExtendedPassword.createPassword("abid", stock.getMarket());

    //System.out.println(dynamicPassword.getPassword());

    String confirmPassword = "abid" + stock.getIndexValue();
    //System.out.println(confirmPassword);
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }
}
