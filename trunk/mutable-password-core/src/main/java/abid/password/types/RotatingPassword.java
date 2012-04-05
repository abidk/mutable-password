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

package abid.password.types;

import abid.password.StatefulMutablePassword;

/**
 * Rotates the mutable part of the password using the state.
 * 
 * The state will need to be stored somewhere and set when the mutable password
 * is initialised.
 * 
 * @author Abid
 * 
 */
public class RotatingPassword extends StatefulMutablePassword {

  /** How many characters to rotate. */
  public static final int ROTATING_WIDTH = 1;
  /** Password type name. */
  public static final String PASSWORD_TYPE = "rotating";

  /**
   * Takes the String password, which is then separated into the text and
   * mutable block. The mutable block is split into the password type and
   * expression.
   * 
   * @param password
   */
  public RotatingPassword(String password) {
    super(password);
  }

  @Override
  public String getEvaluatedPassword() {
    String expression = getExpression();
    int normalisedState = normaliseState(expression.length(), getState());
    String subString = expression.substring(normalisedState, normalisedState + ROTATING_WIDTH);
    String evaluatedPassword = getText() + subString;
    return evaluatedPassword;
  }

  private int normaliseState(int maxLength, int currentState) {
    if (currentState < 0) {
      currentState *= -1;
    }
    return currentState % maxLength;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) {
    String evaluatedPassword = getEvaluatedPassword();
    if (evaluatedPassword.equals(confirmPassword)) {
      return true;
    }
    return false;
  }

  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

}
