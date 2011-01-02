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
import abid.password.parameters.ParameterFactory;
import abid.password.parameters.TimeParameter;

/**
 * 
 * Makes it possible to combine extended mutable password with the timelock.
 * 
 * @author Abid
 * 
 */
public class ExtendedTimeLockPassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "extendedTimeLock";

  public ExtendedTimeLockPassword(String password) {
    super(password);
  }
  
  public ExtendedTimeLockPassword(String text, MutableBlock block) {
    super(text, block);
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    String[] expressions = getExpression().split(",");

    if (expressions.length < 2) {
      throw new PasswordException("The expression for 'ExtendTimeLockPassword' is incorrect.");
    }

    String extendTimeExpression = expressions[0];
    String lockExpression = expressions[1];

    try {
      // check if passwords match first
      Evaluator parsable = new JavascriptEvaluator();

      // check the extend bit is correct
      String extendEvaluation = parsable.evaluateExpression(extendTimeExpression, ParameterFactory.getAllParamterData());
      String evaluatedPassword = getText() + extendEvaluation;
      if (!evaluatedPassword.equals(confirmPassword)) {
        return false;
      }

      // check the time expression is correct
      String timeEvaluation = parsable.evaluateExpression(lockExpression, ParameterFactory.getAllParamterData());
      if (!timeEvaluation.equalsIgnoreCase("true")) {
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

  public static MutableBlock createMutableBlock(TimeParameter extendedTimeValue, TimeParameter lockTimeType, int lockStartTime, int lockEndTime) {
    String extendExpression = extendedTimeValue.getTextField();
    String lockExpression = lockTimeType.getTextField() + ">=" + lockStartTime + "&&" + lockTimeType.getTextField() + "<=" + lockEndTime;
    String expression = extendExpression + "," + lockExpression;
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, expression);
    return block;
  }

  public static MutablePassword createPassword(String text, TimeParameter extendedTimeValue, TimeParameter lockTimeType, int lockStartTime, int lockEndTime) {
    MutableBlock block = createMutableBlock(extendedTimeValue, lockTimeType, lockStartTime, lockEndTime);

    String mutablePassword = text + block;
    return new ExtendedTimeLockPassword(mutablePassword);
  }

}
