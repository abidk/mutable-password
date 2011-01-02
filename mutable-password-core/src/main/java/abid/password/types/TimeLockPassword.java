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

package abid.password.types;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.evaluator.Evaluator;
import abid.password.evaluator.JavascriptEvaluator;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeParameter;

/**
 * Creates a password which can only be used at a specific time.
 * 
 * e.g. pass[timelock{minute, 1, 25}] - password only works between 1 and 25
 * minutes
 * 
 * @author Abid
 * 
 */
public class TimeLockPassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "timelock";

  public TimeLockPassword(String password) {
    super(password);
  }

  public TimeLockPassword(String text, MutableBlock block) {
    super(text, block);
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    String passwordText = getText();
    // check if passwords match first
    if (confirmPassword.equals(passwordText)) {
      Evaluator parsable = new JavascriptEvaluator();
      try {
        String evaluation = parsable.evaluateExpression(getExpression(), TimeParameter.getValues());
        return evaluation.equalsIgnoreCase("true");
      } catch (ParseException e) {
        throw new PasswordException(e);
      }
    }
    return false;
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  public static MutableBlock createMutableBlock(TimeParameter timeType, int start, int end) {
    String type = timeType.getTextField();
    String expression = type + ">=" + start + "&&" + type + "<=" + end;
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, expression);
    return block;
  }

  public static MutablePassword createPassword(String text, TimeParameter timeType, int start, int end) {
    MutableBlock block = createMutableBlock(timeType, start, end);
    return new TimeLockPassword(text, block);
  }

}
