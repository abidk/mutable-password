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

import java.util.Map;

import abid.password.PasswordException;
import abid.password.StatefulMutablePassword;
import abid.password.evaluator.EvaluationException;
import abid.password.evaluator.ExpressionEvaluator;
import abid.password.parameters.Parameter;
import abid.password.parameters.ParameterRegistery;

/**
 * Basically, a Caesar cipher.
 * 
 * Evaluates the password based on a state value, the state can be set before
 * evaluating the password.
 * 
 * @author Abid
 * 
 */
public class CaesarCipherPassword extends StatefulMutablePassword {

  // public String PASSWORD_TYPE = getClass().getSimpleName();
  /** Password type name. */
  public static final String PASSWORD_TYPE = "caesarCipher";

  /**
   * Constructs a Caesar Cipher Password object with the password argument.
   * 
   * @param password
   */
  public CaesarCipherPassword(String password) {
    super(password);
  }

  /**
   * Shift the alphabet (A-Z) by the argument value.
   * 
   * @param shiftBy
   * @return shifted value
   */
  public String getShiftedPassword(int shiftBy) {
    StringBuilder shiftPassword = new StringBuilder();
    for (char character : getText().toCharArray()) {
      if (character >= 65 && character <= 90) {
        // deal with upper case letters
        char shiftedChar = (char) ((((character - 65) + shiftBy) % 26) + 65);
        shiftPassword.append(shiftedChar);
      } else if (character >= 97 && character <= 122) {
        // deal with lower case letters
        char shiftedChar = (char) ((((character - 97) + shiftBy) % 26) + 97);
        shiftPassword.append(shiftedChar);
      } else {
        // it's not an alphabet so do nothing
        shiftPassword.append(character);
      }
    }
    return shiftPassword.toString();
  }

  @Override
  public String getEvaluatedPassword() throws EvaluationException {
    String expression = getExpression();
    int shiftBy = 0;
    if (expression.equalsIgnoreCase("state")) {
      shiftBy = getState();
    } else {
      ExpressionEvaluator expressionEvaluator = getEvaluator();
      Map<String, Parameter> parameters = ParameterRegistery.getParameters();
      String evaluation = expressionEvaluator.evaluate(expression, parameters);
      shiftBy = Integer.valueOf(evaluation);
    }
    return getShiftedPassword(shiftBy);
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    try {
      String evaluatedPassword = getEvaluatedPassword();
      return evaluatedPassword.equals(confirmPassword);
    } catch (EvaluationException e) {
      throw new PasswordException(e);
    }
  }

  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

}
