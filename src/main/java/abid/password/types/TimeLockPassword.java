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
 * Creates a password which can only be used at a specific time. 
 * 
 * e.g. pass[timelock{minute, 1, 25}] = pass (but only works between 1 and 25 minutes)
 * 
 * @author Abid
 *
 */
public class TimeLockPassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "timelock";

  public TimeLockPassword(String password) {
    super(password);
  }

  @Override
  public boolean confirmPassword(String confirmPassword) {
    String password = getText();
    // check if passwords match first
    if (confirmPassword.equals(password)) {
      Evaluator parsable = new JavascriptEvaluator();
      try {
        String evaluation = parsable.evaluateExpression(getExpression());
        return evaluation.equalsIgnoreCase("true");
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  // hour>=0 && hour<=22
  public static MutablePassword createPassword(String text, TimeType timeType, int start, int end) {
    String type = timeType.getType();
    String expression = type + ">=" + start + "&&" + type + "<=" + end;
    MutableBlock block = new MutableBlock(text, PASSWORD_TYPE, expression);
    return new TimeLockPassword(block.toString());
  }

}
