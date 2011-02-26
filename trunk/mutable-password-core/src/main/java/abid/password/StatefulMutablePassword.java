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

/**
 * Allows this abstract class to be extended to allow the extension of further
 * mutable passwords.
 * 
 * @author Abid
 * 
 */
public abstract class StatefulMutablePassword extends MutablePassword {

  private int state;

  public StatefulMutablePassword(String password) {
    super(password);
  }

  /**
   * Returns the state of the password. This allows you to store a value which
   * can be used in the password evaluation.
   * 
   * @return state
   */
  public int getState() {
    return state;
  }

  /**
   * Allows you to set the state of the password. This value can then be used
   * directly in the password evaluation.
   * 
   * @param state
   */
  public void setState(int state) {
    this.state = state;
  }
}