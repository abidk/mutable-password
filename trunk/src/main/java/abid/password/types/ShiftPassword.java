/* Copyright 2009 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package abid.password.types;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.evaluator.Evaluator;
import abid.password.evaluator.JavascriptEvaluator;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeType;

/**
 * Creates a Ceasar cipher type password. 
 * 
 * e.g. pass[shift{1}] or pass[shift{month}]
 * 
 * @author Abid
 * 
 */
public class ShiftPassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "shift";

  public ShiftPassword(String password) {
    super(password);
  }

  private String getShiftedPassword(int shiftBy) {
    String shiftPassword = "";
    for (char character : getText().toCharArray()) {
      if (character >= 65 && character <= 90) {
        // deal with upper case letters
        char shiftedChar = (char) ((((character - 65) + shiftBy) % 26) + 65);
        shiftPassword += shiftedChar;
      } else if (character >= 97 && character <= 122) {
        // deal with lower case letters
        char shiftedChar = (char) ((((character - 97) + shiftBy) % 26) + 97);
        shiftPassword += shiftedChar;
      } else {
        // it's not an alphabet so do nothing
        shiftPassword += character;
      }
    }
    return shiftPassword;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) {
    // parser needs to be customisable
    Evaluator parsable = new JavascriptEvaluator();
    try {
      String evaluation = parsable.evaluateExpression(getExpression());

      if (evaluation != null) {
        // need to convert this into float then get int value, otherwise we get
        // the value 1.0
        int shiftBy = Float.valueOf(evaluation).intValue();

        String shiftedPassword = getShiftedPassword(shiftBy);
        return shiftedPassword.equals(confirmPassword);
      }

    } catch (ParseException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  public static MutablePassword createPassword(String text, TimeType timeType) {
    MutableBlock block = new MutableBlock(text, PASSWORD_TYPE, timeType.getType());
    return new ShiftPassword(block.toString());
  }

  public static MutablePassword createPassword(String text, int shiftValue) {
    MutableBlock block = new MutableBlock(text, PASSWORD_TYPE, shiftValue);
    return new ShiftPassword(block.toString());
  }

}
