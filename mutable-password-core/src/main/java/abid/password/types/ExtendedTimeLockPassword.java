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

import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.evaluator.EvaluationException;
import abid.password.evaluator.ExpressionEvaluator;
import abid.password.parameters.Parameter;
import abid.password.parameters.ParameterRegistery;

/**
 * Combines Extended Mutable Password with Time Lock Password.
 * 
 * @author Abid
 * 
 */
public class ExtendedTimeLockPassword extends MutablePassword {

  /** Password type name. */
  public static final String PASSWORD_TYPE = "extendedTimeLock";

  /**
   * Takes the String password, which is then separated into the text and
   * mutable block. The mutable block is split into the password type and
   * expression.
   * 
   * @param password
   */
  public ExtendedTimeLockPassword(String password) {
    super(password);
  }

  @Override
  public String getEvaluatedPassword() throws EvaluationException {
    String[] expressions = getExpression().split(",");
    String extendTimeExpression = expressions[0];
    ExpressionEvaluator expressionEvaluator = getEvaluator();
    Map<String, Parameter> parameters = ParameterRegistery.getParameters();
    String extendEvaluation = expressionEvaluator.evaluate(extendTimeExpression, parameters);
    String evaluatedPassword = getText() + extendEvaluation;
    return evaluatedPassword;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    try {
      String evaluatedPassword = getEvaluatedPassword();
      // check if passwords match first
      if (!evaluatedPassword.equals(confirmPassword)) {
        return false;
      }

      // check the time expression is correct
      String[] expressions = getExpression().split(",");
      String lockExpression = expressions[1];
      ExpressionEvaluator expressionEvaluator = getEvaluator();
      String eval = expressionEvaluator.evaluate(lockExpression, ParameterRegistery.getParameters());
      if (!"true".equalsIgnoreCase(eval)) {
        return false;
      }
      return true;
    } catch (EvaluationException e) {
      throw new PasswordException(e);
    }
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

}
