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

import abid.password.MutableBlock;
import abid.password.PasswordException;
import abid.password.StatefulMutablePassword;
import abid.password.evaluator.Evaluator;
import abid.password.evaluator.ParseException;
import abid.password.parameters.Parameter;
import abid.password.parameters.ParameterFactory;
import abid.password.parameters.TimeParameter;

/**
 * Basically, a Caesar cipher.
 * 
 * You can give it a time value e.g. pass[shift{month}] or you can create a
 * password with a state, the state can be specified later.
 * 
 * @author Abid
 * 
 */
public class CaesarCipherPassword extends StatefulMutablePassword {

  // public String PASSWORD_TYPE = getClass().getSimpleName();
  /** Password type name. */
  public static final String PASSWORD_TYPE = "caesarCipher";

  /**
   * Takes the String password, which is then separated into the text and
   * mutable block. The mutable block is split into the password type and
   * expression.
   * 
   * @param password
   */
  public CaesarCipherPassword(String password) {
    super(password);
  }

  /**
   * This will shift the alphabet (A-Z) by the argument value.
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
  public String getEvaluatedPassword() throws ParseException {
    String expression = getExpression();
    int shiftBy = 0;
    if (expression.equalsIgnoreCase("state")) {
      shiftBy = getState();
    } else {
      Evaluator evaluator = getEvaluator();
      Map<String, Parameter> parameters = ParameterFactory.getAllParamterData();
      String evaluation = evaluator.evaluateExpression(expression, parameters);
      shiftBy = Integer.valueOf(evaluation);
    }
    return getShiftedPassword(shiftBy);
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    try {
      String evaluatedPassword = getEvaluatedPassword();
      return evaluatedPassword.equals(confirmPassword);
    } catch (ParseException e) {
      throw new PasswordException(e);
    }
  }

  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  /**
   * Creates a mutable block based on the input values.
   * 
   * @param timeType
   * @return mutable block
   */
  public static MutableBlock createMutableBlock(TimeParameter timeType) {
    return createMutableBlock(timeType.getTextField());
  }

  /**
   * Creates a mutable block based on the input values.
   * 
   * @param expression
   * @return mutable block
   */
  public static MutableBlock createMutableBlock(String expression) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, expression);
    return block;
  }

  /**
   * Creates a state based mutable password based on the input values.
   * 
   * @param text
   * @param timeType
   * @return mutable password with state
   */
  public static StatefulMutablePassword createPassword(String text, TimeParameter timeType) {
    MutableBlock block = createMutableBlock(timeType);
    return new CaesarCipherPassword(text + block);
  }

  /**
   * Creates a state based mutable password based on the input values.
   * 
   * @param text
   * @return mutable password with state
   */
  public static StatefulMutablePassword createPassword(String text) {
    MutableBlock block = createMutableBlock("state");
    return new CaesarCipherPassword(text + block);
  }

}
