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
package abid.password;

/**
 * Allows this abstract class to be extended to allow the extension of further
 * mutable passwords.
 * 
 * @author Abid
 * 
 */
public abstract class StatefulMutablePassword extends MutablePassword {

  private int state;

  /**
   * Constructs password as a stateful mutable password object.
   * 
   * @param password
   */
  public StatefulMutablePassword(String password) {
    super(password);
  }

  /**
   * Returns the state, which can then be used when evaluating the password.
   * 
   * @return state
   */
  public int getState() {
    return state;
  }

  /**
   * Set the state of the password.
   * 
   * @param state
   */
  public void setState(int state) {
    this.state = state;
  }
}
