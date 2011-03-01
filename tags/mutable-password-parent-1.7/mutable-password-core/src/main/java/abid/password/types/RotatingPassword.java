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

import abid.password.MutableBlock;
import abid.password.StatefulMutablePassword;

/**
 * Password rotates the mutable part of the password according to the state.
 * 
 * The state will need to be stored somewhere and set when the mutable password
 * is initialised.
 * 
 * @author Abid
 * 
 */
public class RotatingPassword extends StatefulMutablePassword {

  public static final int ROTATING_WIDTH = 1;
  public static final String PASSWORD_TYPE = "rotating";

  public RotatingPassword(String password) {
    super(password);
  }

  @Override
  public String getEvaluatedPassword() {
    String expression = getExpression();
    int normalisedState = normaliseState(expression.length(), getState());
    String subString = expression.substring(normalisedState, (normalisedState + ROTATING_WIDTH));
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

  public static MutableBlock createMutableBlock(String mutableText) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, mutableText);
    return block;
  }

  public static StatefulMutablePassword createPassword(String text, String mutableText) {
    MutableBlock block = createMutableBlock(mutableText);

    String mutablePassword = text + block;
    return new RotatingPassword(mutablePassword);
  }

}
