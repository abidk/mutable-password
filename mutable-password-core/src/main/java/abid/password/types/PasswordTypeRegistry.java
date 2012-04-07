/**
 * Copyright 2012 Abid Khalil
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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import abid.password.MutablePassword;

/**
 * Register and unregister password types.
 * 
 * @author Abid
 */
public class PasswordTypeRegistry {

  private static final CopyOnWriteArrayList<Class<? extends MutablePassword>> passwordTypes = new CopyOnWriteArrayList<Class<? extends MutablePassword>>();

  static {
    // register internal password types
    passwordTypes.add(CaesarCipherPassword.class);
    passwordTypes.add(TimeLockPassword.class);
    passwordTypes.add(ExtendedPassword.class);
    passwordTypes.add(ExtendedTimeLockPassword.class);
    passwordTypes.add(RomanNumeralPassword.class);
    passwordTypes.add(RotatingPassword.class);
  }

  private PasswordTypeRegistry() {
  }

  /**
   * Register a new mutable password type.
   * 
   * @param cls
   *          Password Type Class
   * @return password added or not
   */
  public static boolean registerPasswordType(Class<? extends MutablePassword> cls) {
    return passwordTypes.addIfAbsent(cls);
  }

  /**
   * Remove mutable password type.
   * 
   * @param cls
   *          Password Type Class
   * @return password removed or not
   */
  public static boolean unregisterPasswordType(Class<? extends MutablePassword> cls) {
    return passwordTypes.remove(cls);
  }

  /**
   * Returns registered mutable passwords types.
   * 
   * @return Password Types
   */
  public static List<Class<? extends MutablePassword>> getPasswordTypes() {
    return passwordTypes;
  }
}
