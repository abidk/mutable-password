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
import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.evaluator.EvaluationException;
import abid.password.evaluator.ExpressionEvaluator;
import abid.password.parameters.Parameter;
import abid.password.parameters.ParameterRegistery;

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

  /** Password type name. */
  public static final String PASSWORD_TYPE = "timelock";

  /**
   * Takes the String password, which is then separated into the text and
   * mutable block. The mutable block is split into the password type and
   * expression.
   * 
   * @param password
   */
  public TimeLockPassword(String password) {
    super(password);
  }

  /**
   * Takes the text and mutable block as separate objects. The mutable block is
   * split into the password type and expression.
   * 
   * @param text
   * @param block
   */
  public TimeLockPassword(String text, MutableBlock block) {
    super(text, block);
  }

  @Override
  public String getEvaluatedPassword() throws EvaluationException {
    // password does not need evaluating
    String passwordText = getText();
    return passwordText;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    String passwordText = getText();
    // check if passwords match first
    if (confirmPassword.equals(passwordText)) {
      try {
        return isPasswordLocked();
      } catch (EvaluationException e) {
        throw new PasswordException(e);
      }
    }
    return false;
  }

  protected boolean isPasswordLocked() throws EvaluationException {
    ExpressionEvaluator expressionEvaluator = getEvaluator();
    Map<String, Parameter> parameters = ParameterRegistery.getParameters();
    String result = expressionEvaluator.evaluate(getExpression(), parameters);
    return "true".equalsIgnoreCase(result);
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

}
