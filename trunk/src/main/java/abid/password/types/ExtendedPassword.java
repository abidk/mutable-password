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

import abid.password.MutablePassword;
import abid.password.evaluator.Evaluator;
import abid.password.evaluator.JavascriptEvaluator;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeType;
/**
 * Allows you to concatenate the time at the end of the static password. 
 * 
 * e.g. pass[time{year}] = pass2009
 * 
 * @author Abid
 *
 */
public class ExtendedPassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "extend";

  public ExtendedPassword(String password) {
    super(password);
  }

  @Override
  public boolean confirmPassword(String confirmPassword) {
    // parser needs to be customisable
    Evaluator parsable = new JavascriptEvaluator();
    try {
      String evaluation = parsable.evaluateExpression(getExpression(), TimeType.getValues() );
      
      if (evaluation != null) {
        String evaluatedPassword = getText() + evaluation;
        //System.out.println( "==>" + evaluatedPassword);
        return evaluatedPassword.equals(confirmPassword);
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

  public static MutablePassword createPassword(String text, TimeType timeValue) {
    return createPassword(text, timeValue.getTextField());
  }

  public static MutablePassword createPassword(String text, String expression) {
    String mutablePassword = text + "[" + ExtendedPassword.PASSWORD_TYPE + "{" + expression + "}]";
    return new ExtendedPassword(mutablePassword);
  }

}
