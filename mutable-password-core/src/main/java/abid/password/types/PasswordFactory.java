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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.Password;

/**
 * Method which allows you to select the password type object by passing it a
 * password.
 * 
 * @author Abid
 * 
 */
public class PasswordFactory {

  private static List<Class<? extends MutablePassword>> mutablePasswords = new ArrayList<Class<? extends MutablePassword>>();

  static {
    // add the existing types
    mutablePasswords.add(ShiftPassword.class);
    mutablePasswords.add(TimeLockPassword.class);
    mutablePasswords.add(ExtendedPassword.class);
    mutablePasswords.add(ExtendedTimeLockPassword.class);
    mutablePasswords.add(RomanNumeralPassword.class);
    mutablePasswords.add(RotatingPassword.class);
  }

  private PasswordFactory() {

  }

  /**
   * Takes a password string and returns a suitable mutable password type
   * object.
   * 
   * @param password
   * @return The password type object
   * @throws IllegalArgumentException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   * @throws SecurityException
   * @throws NoSuchMethodException
   */
  public static Password getInstance(String password) throws IllegalArgumentException, InstantiationException, IllegalAccessException,
      InvocationTargetException, SecurityException, NoSuchMethodException {
    MutableBlock block = new MutableBlock(password);

    Class<?>[] argTypes = { String.class };
    Object[] arglist = { password };

    if (block.getType() != null) {
      for (Class<?> cls : mutablePasswords) {
        Constructor<?> ct = cls.getConstructor(argTypes);
        MutablePassword mutatingPassword = (MutablePassword) ct.newInstance(arglist);
        if (block.getType().equals(mutatingPassword.getPasswordType())) {
          return mutatingPassword;
        }
      }
    }
    return new SimplePassword(password);
  }

  /**
   * Allows you to insert other mutable password types.
   * 
   * @param passwordClass
   */
  public static boolean addMutablePassword(Class<? extends MutablePassword> passwordClass) {
    if (!mutablePasswords.contains(passwordClass)) {
      return mutablePasswords.add(passwordClass);
    }
    return false;
  }

  /**
   * Allows you to remove mutable password types.
   * 
   * @param passwordClass
   */
  public static boolean removeMutablePassword(Class<? extends MutablePassword> passwordClass) {
    return mutablePasswords.remove(passwordClass);
  }

  /**
   * Returns a list of all the mutable passwords.
   * 
   * @return list of mutable password classes.
   */
  public static List<Class<? extends MutablePassword>> getMutablePasswordList() {
    return mutablePasswords;
  }
}
