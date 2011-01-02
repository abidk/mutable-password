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

/**
 * Basic password
 * 
 * @author Abid
 * 
 */
public abstract class Password {

  private String password;

  public abstract boolean confirmPassword(String confirmPassword) throws PasswordException;

  public Password(String password) {
    // set this as the original password
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public String toString() {
    return getPassword();
  }
}