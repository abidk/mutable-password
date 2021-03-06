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
 * Capture exception when a password string cannot be converted.
 * 
 * @author Abid Khalil
 * 
 */
public class PasswordParseException extends PasswordException {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new exception with the message.
   * 
   * @param message
   */
  public PasswordParseException(String message) {
    super(message);
  }

  /**
   * Constructs a new exception with the message and exception.
   * 
   * @param message
   * @param cause
   */
  public PasswordParseException(String message, Throwable cause) {
    super(message, cause);
  }

}
