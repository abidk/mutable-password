/**
 * Copyright 2010 Abid Khalil
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

package abid.password;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import junit.framework.TestCase;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.parameters.TimeParameter;
import abid.password.types.ExtendedPassword;
import abid.password.types.PasswordFactory;
import abid.password.types.ShiftPassword;
import abid.password.types.SimplePassword;
import abid.password.types.TimeLockPassword;

public class PasswordFactoryTest extends TestCase {

  public void testTimeLockPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {

    Password p1 = TimeLockPassword.createPassword("abid", TimeParameter.HOUR, 0, 24);
    Password m1 = PasswordFactory.getInstance(p1.getPassword());

    // check if the correct class is found
    assertEquals(p1.getClass(), m1.getClass());
    assertEquals(p1.getPassword(), m1.getPassword());
  }

  public void testExtendedPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {

    Password p2 = ExtendedPassword.createPassword("abid", TimeParameter.HOUR);
    Password m2 = PasswordFactory.getInstance(p2.getPassword());
    assertEquals(p2.getClass(), m2.getClass());
    assertEquals(p2.getPassword(), m2.getPassword());
  }

  public void testShiftPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {

    Password p3 = ShiftPassword.createPassword("abid", 1);
    Password m3 = PasswordFactory.getInstance(p3.getPassword());
    assertEquals(p3.getClass(), m3.getClass());
    assertEquals(p3.getPassword(), m3.getPassword());
  }

  public void testSimplePasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {

    Password p4 = new SimplePassword("abid");
    Password m4 = PasswordFactory.getInstance(p4.getPassword());
    assertEquals(p4.getClass(), m4.getClass());
    assertEquals(p4.getPassword(), m4.getPassword());
  }

  public void testPasswordFactory() {
    boolean added = PasswordFactory.addMutablePassword(ExtendedPassword.class);
    assertEquals(false, added);

    List<Class<? extends MutablePassword>> list = PasswordFactory.getMutablePasswordList();
    assertEquals(true, list.contains(ExtendedPassword.class));

    boolean removed = PasswordFactory.removeMutablePassword(ExtendedPassword.class);
    assertEquals(true, removed);

    list = PasswordFactory.getMutablePasswordList();
    assertEquals(false, list.contains(ExtendedPassword.class));

    boolean addedAgain = PasswordFactory.addMutablePassword(ExtendedPassword.class);
    assertEquals(true, addedAgain);

    list = PasswordFactory.getMutablePasswordList();
    assertEquals(true, list.contains(ExtendedPassword.class));
  }
}
