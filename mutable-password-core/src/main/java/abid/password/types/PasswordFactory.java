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
import java.util.ArrayList;
import java.util.List;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.Password;

/**
 * Factory class to convert a password String into a Password object.
 * 
 * @author Abid
 * 
 */
public class PasswordFactory {

  private static List<Class<? extends MutablePassword>> passwordTypes = new ArrayList<Class<? extends MutablePassword>>();

  static {
    // register internal types
    passwordTypes.add(CaesarCipherPassword.class);
    passwordTypes.add(TimeLockPassword.class);
    passwordTypes.add(ExtendedPassword.class);
    passwordTypes.add(ExtendedTimeLockPassword.class);
    passwordTypes.add(RomanNumeralPassword.class);
    passwordTypes.add(RotatingPassword.class);
  }

  private PasswordFactory() {
  }

  /**
   * Input password String and return a password object.
   * 
   * @param password
   * @return Password object
   * @throws PasswordInstantiationException
   */
  public static Password getInstance(String password) throws PasswordInstantiationException {
    MutableBlock block = new MutableBlock(password);
    try {
      if (block.getType() != null) {
        Class<?>[] argTypes = { String.class };
        Object[] argObjects = { password };
        for (Class<?> cls : passwordTypes) {
          Constructor<?> ct = cls.getConstructor(argTypes);
          MutablePassword mutatingPassword = (MutablePassword) ct.newInstance(argObjects);
          if (block.getType().equals(mutatingPassword.getPasswordType())) {
            return mutatingPassword;
          }
        }
      }
    } catch (Exception e) {
      throw new PasswordInstantiationException("Unable to construct password object.", e);
    }
    // if a type cannot be found then it must be a simple password
    return new SimplePassword(password);
  }

  /**
   * Register a new mutable password type.
   * 
   * @param passwordClass
   * @return password added or not
   */
  public static boolean registerPasswordType(
      Class<? extends MutablePassword> passwordClass) {
    if (!passwordTypes.contains(passwordClass)) {
      return passwordTypes.add(passwordClass);
    }
    return false;
  }

  /**
   * Remove mutable password type.
   * 
   * @param passwordClass
   * @return password removed or not
   */
  public static boolean unregisterPasswordType(
      Class<? extends MutablePassword> passwordClass) {
    return passwordTypes.remove(passwordClass);
  }

  /**
   * Returns registered mutable passwords types.
   * 
   * @return mutable password type list
   */
  public static List<Class<? extends MutablePassword>> getPasswordTypes() {
    return passwordTypes;
  }
}
