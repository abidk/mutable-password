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

package abid.password;

import java.lang.reflect.Constructor;
import java.util.List;

import abid.password.types.PasswordTypeRegistry;
import abid.password.types.SimplePassword;

/**
 * Convert a password String into a Password object.
 * 
 * @author Abid
 * 
 */
public class MutablePasswordParser implements PasswordParser {

  /**
   * Convert password String and return a password object.
   * 
   * @param password
   *          Password String
   * @return Password object
   * @throws PasswordParseException
   */
  public Password parse(String password) throws PasswordParseException {
    MutableBlock block = new MutableBlock(password);
    if (block.getType() == null) {
      // no type then it must be a simple password
      return new SimplePassword(password);
    }

    List<Class<? extends MutablePassword>> passwordTypes = PasswordTypeRegistry.getPasswordTypes();
    return convertPasswordStringToObject(block, password, passwordTypes);
  }

  protected MutablePassword convertPasswordStringToObject(MutableBlock block, String password, List<Class<? extends MutablePassword>> passwordTypes)
      throws PasswordParseException {
    Class<?>[] argTypes = { String.class };
    Object[] argObjects = { password };
    try {
      for (Class<?> cls : passwordTypes) {
        Constructor<?> ct = cls.getConstructor(argTypes);
        MutablePassword mutatingPassword = (MutablePassword) ct.newInstance(argObjects);
        if (block.getType().equals(mutatingPassword.getPasswordType())) {
          return mutatingPassword;
        }
      }
    } catch (Exception e) {
      throw new PasswordParseException("Unable to construct password object.", e);
    }
    throw new PasswordParseException("Unable to determine password type.");
  }

}
