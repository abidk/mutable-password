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
import abid.password.evaluator.Evaluator;
import abid.password.evaluator.ParseException;
import abid.password.parameters.Parameter;
import abid.password.parameters.ParameterFactory;
import abid.password.parameters.TimeParameter;

/**
 * Makes it possible to combine extended mutable password with the timelock.
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

  /**
   * Takes the text and mutable block as separate objects. The mutable block is
   * split into the password type and expression.
   * 
   * @param text
   * @param block
   */
  public ExtendedTimeLockPassword(String text, MutableBlock block) {
    super(text, block);
  }

  @Override
  public String getEvaluatedPassword() throws ParseException {
    String[] expressions = getExpression().split(",");
    String extendTimeExpression = expressions[0];
    Evaluator evaluator = getEvaluator();
    Map<String, Parameter> parameters = ParameterFactory.getAllParamterData();
    String extendEvaluation = evaluator.evaluateExpression(extendTimeExpression, parameters);
    String evaluatedPassword = getText() + extendEvaluation;
    return evaluatedPassword;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    String[] expressions = getExpression().split(",");
    if (expressions.length < 2) {
      throw new PasswordException("The expression for 'ExtendTimeLockPassword' is incorrect.");
    }

    try {
      String evaluatedPassword = getEvaluatedPassword();
      // check if passwords match first
      if (!evaluatedPassword.equals(confirmPassword)) {
        return false;
      }

      // check the time expression is correct
      String lockExpression = expressions[1];
      Evaluator evaluator = getEvaluator();
      String eval = evaluator.evaluateExpression(lockExpression, ParameterFactory.getAllParamterData());
      if (!"true".equalsIgnoreCase(eval)) {
        return false;
      }
      return true;
    } catch (ParseException e) {
      throw new PasswordException(e);
    }
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  /**
   * Creates a mutable block based on the input values.
   * 
   * @param extendedTimeValue
   * @param lockTimeType
   * @param lockStartTime
   * @param lockEndTime
   * @return mutable block
   */
  public static MutableBlock createMutableBlock(TimeParameter extendedTimeValue, 
      TimeParameter lockTimeType, int lockStartTime, int lockEndTime) {
    String extendExpression = extendedTimeValue.getTextField();
    String lockExpression = lockTimeType.getTextField() + ">=" + lockStartTime 
       + "&&" + lockTimeType.getTextField() + "<=" + lockEndTime;
    String expression = extendExpression + "," + lockExpression;
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, expression);
    return block;
  }

  /**
   * Create the mutable password based on the input values.
   * 
   * @param text
   * @param extendedTimeValue
   * @param lockTimeType
   * @param lockStartTime
   * @param lockEndTime
   * @return mutable password
   */
  public static MutablePassword createPassword(String text, TimeParameter extendedTimeValue, 
      TimeParameter lockTimeType, int lockStartTime, int lockEndTime) {
    MutableBlock block = createMutableBlock(extendedTimeValue, lockTimeType,
        lockStartTime, lockEndTime);

    String mutablePassword = text + block;
    return new ExtendedTimeLockPassword(mutablePassword);
  }

}
