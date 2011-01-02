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
import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.evaluator.Evaluator;
import abid.password.evaluator.JavascriptEvaluator;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeParameter;

/**
 * Creates a Ceasar cipher type password.
 * 
 * e.g. pass[shift{1}] or pass[shift{month}]
 * 
 * @author Abid
 * 
 */
public class ShiftPassword extends MutablePassword {

  // public String PASSWORD_TYPE = getClass().getSimpleName();

  public static final String PASSWORD_TYPE = "shift";

  public ShiftPassword(String password) {
    super(password);
  }

  public ShiftPassword(String text, MutableBlock block) {
    super(text, block);
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
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    // parser needs to be customisable
    Evaluator parsable = new JavascriptEvaluator();
    try {
      String evaluation = parsable.evaluateExpression(getExpression(), TimeParameter.getValues());
      
      int shiftBy = Integer.valueOf(evaluation);
      String shiftedPassword = getShiftedPassword(shiftBy);
      return shiftedPassword.equals(confirmPassword);
    } catch (ParseException e) {
      throw new PasswordException(e);
    }
  }

  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  public static MutableBlock createMutableBlock(TimeParameter timeType) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, timeType.getTextField());
    return block;
  }

  public static MutableBlock createMutableBlock(int shiftValue) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, shiftValue);
    return block;
  }

  public static MutablePassword createPassword(String text, TimeParameter timeType) {
    MutableBlock block = createMutableBlock(timeType);
    return new ShiftPassword(text, block);
  }

  public static MutablePassword createPassword(String text, int shiftValue) {
    MutableBlock block = createMutableBlock(shiftValue);
    return new ShiftPassword(text, block);
  }
}
