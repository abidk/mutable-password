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
import abid.password.parameters.ParameterFactory;
import abid.password.parameters.TimeParameter;

/**
 * Allows you to concatenate a parameter at the end of the static password.
 * 
 * e.g. pass[extend{year}] = pass2009
 * 
 * @author Abid
 * 
 */
public class ExtendedPassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "extend";

  public ExtendedPassword(String password) {
    super(password);
  }

  public ExtendedPassword(String text, MutableBlock block) {
    super(text, block);
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    // parser needs to be customisable
    Evaluator parsable = new JavascriptEvaluator();
    try {
      String evaluation = parsable.evaluateExpression(getExpression(), ParameterFactory.getAllParamterData());

      String evaluatedPassword = getText() + evaluation;
      // System.out.println( "==>" + evaluatedPassword);
      return evaluatedPassword.equals(confirmPassword);
    } catch (ParseException e) {
      throw new PasswordException(e);
    }
  }

  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  public static MutableBlock createMutableBlock(String expression) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, expression);
    return block;
  }

  public static MutableBlock createMutableBlock(TimeParameter timeValue) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, timeValue.getTextField());
    return block;
  }

  public static MutablePassword createPassword(String text, TimeParameter timeValue) {
    return createPassword(text, timeValue.getTextField());
  }

  public static MutablePassword createPassword(String text, String expression) {
    MutableBlock block = createMutableBlock(expression);
    String mutablePassword = text + block;
    return new ExtendedPassword(mutablePassword);
  }

}
