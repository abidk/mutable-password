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
 * Generic wrapper to capture any exceptions thrown whilst executing the mutable
 * password.
 * 
 * @author Abid
 */
public class PasswordException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Input any useful message with the exception.
   * 
   * @param message
   */
  public PasswordException(String message) {
    super(message);
  }

  /**
   * Wrap any exception.
   * 
   * @param cause
   */
  public PasswordException(Throwable cause) {
    super(cause);
  }

  /**
   * Wrap message and exceptions.
   * 
   * @param message
   * @param cause
   */
  public PasswordException(String message, Throwable cause) {
    super(message, cause);
  }

}
