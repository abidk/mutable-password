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
 * This the most simplistic password object and allows you to confirm a
 * password.
 * 
 * @author Abid
 * 
 */
public abstract class Password {

  /**
   * Confirm password validity.
   * 
   * @param confirmPassword
   * @return if the password matches the confirm password parameter
   * @throws PasswordException
   */
  public abstract boolean confirmPassword(String confirmPassword) throws PasswordException;

  private String password;

  /**
   * Stores the password and provides the ability to confirm a password.
   * 
   * @param password
   */
  public Password(String password) {
    this.password = password;
  }

  /**
   * Returns the password.
   * 
   * @return password
   */
  public String getPassword() {
    return password;
  }
}
