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

import junit.framework.TestCase;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.types.ExtendedTimeLockPassword;
import abid.password.types.ShiftPassword;

public class MutablePasswordTest extends TestCase {
  public void testNullBlock() {
    String passwordText = "pass";
    MutablePassword password = new ShiftPassword(passwordText, null);
    assertNull(password.getExpression());
    assertNull(password.getType());
    assertEquals(passwordText, password.getText());
    assertNull(password.getMutableBlock());
  }

  public void testStartOfExpression() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    Password pass = new ExtendedTimeLockPassword("abidextendedTimeLock{year}]");
  }
}
