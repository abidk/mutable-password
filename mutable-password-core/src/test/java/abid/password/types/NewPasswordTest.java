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

import junit.framework.TestCase;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.evaluator.ParseException;

public class NewPasswordTest extends TestCase {

  public void testNewPasswordType() throws PasswordInstantiationException {
    NewPassword password = (NewPassword) NewPassword.createPassword("something");

    Password m1 = PasswordFactory.getInstance(password.getPassword());

    // class is not registered with factory so the above line will not find the
    // appropriate class.
    assertNotSame(NewPassword.class, m1.getClass());

    // The factory should return a SimplePassword object
    assertEquals(SimplePassword.class, m1.getClass());

    // System.out.println(m1.getPassword());
  }

  public void testEvaluatedPassword() throws ParseException, PasswordException {
    String passwordText = "abid";
    MutablePassword password = NewPassword.createPassword(passwordText);
    // test the evaluated password
    assertTrue(password.confirmPassword(password.getEvaluatedPassword()));
  }

  public void testRegisteredNewPasswordType() throws PasswordInstantiationException {
    // add the password to the factory
    PasswordFactory.registerPasswordType(NewPassword.class);

    NewPassword password = (NewPassword) NewPassword.createPassword("something");

    Password m1 = PasswordFactory.getInstance(password.getPassword());

    // The factory should return a NewTypePassword object
    assertEquals(NewPassword.class, m1.getClass());
  }

}
