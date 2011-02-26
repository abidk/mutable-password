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

import junit.framework.TestCase;
import abid.password.types.RotatingPassword;

public class StatefulMutablePasswordTest extends TestCase {

  public void testState() {
    StatefulMutablePassword password = RotatingPassword.createPassword("pass", "123456");
    assertEquals(0, password.getState());
    int state = 99;
    password.setState(state);
    assertEquals(state, password.getState());
  }

}
