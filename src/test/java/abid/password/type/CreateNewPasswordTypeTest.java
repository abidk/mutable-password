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

import abid.password.Password;
import abid.password.types.PasswordFactory;
import abid.password.types.SimplePassword;
import junit.framework.TestCase;

public class CreateNewPasswordTypeTest extends TestCase {

  public void testNewPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    NewTypePassword password = (NewTypePassword) NewTypePassword.createPassword("something");

    Password m1 = PasswordFactory.getInstance(password.getPassword());

    // class is not registered with factory so the above line will not find the
    // appropriate class.
    assertNotSame(NewTypePassword.class, m1.getClass());
    
    // The factory should return a SimplePassword object
    assertEquals(SimplePassword.class, m1.getClass());

    //System.out.println(m1.getPassword());
  }

  public void testRegisteredNewPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    // add the password to the factory
    PasswordFactory.addMutablePassword(NewTypePassword.class);

    NewTypePassword password = (NewTypePassword) NewTypePassword.createPassword("something");

    Password m1 = PasswordFactory.getInstance(password.getPassword());
    
    // The factory should return a NewTypePassword object
    assertEquals(NewTypePassword.class, m1.getClass());
  }

}
