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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.PasswordFactory;
import abid.password.evaluator.Evaluator;
import abid.password.evaluator.JavascriptEvaluator;
import abid.password.evaluator.ParseException;
import abid.password.parameters.ParameterFactory;
import abid.password.parameters.TimeType;

/**
 * 
 * Makes it possible to combine extended mutable password with the timelock.
 * 
 * @author Abid
 * 
 */
public class ExtendedTimeLockPassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "extendedTimeLock";

  private List<MutablePassword> mutablePasswordTypes = new ArrayList<MutablePassword>();

  public ExtendedTimeLockPassword(String password) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    super(password);
    parseExpression();
  }

  private void parseExpression() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
    String expression = getExpression();

    int startTag = -1;

    for (int x = 0; x < expression.length(); x++) {
      if (expression.charAt(x) == MutableBlock.MUTABLE_BLOCK_START_TAG) {
        startTag = x;
      }

      if (expression.charAt(x) == MutableBlock.MUTABLE_BLOCK_END_TAG) {
        if (startTag != -1) {
          MutableBlock mutableBlock = new MutableBlock(expression.substring(startTag, x + 1));
          MutablePassword password = (MutablePassword) PasswordFactory.getInstance(mutableBlock.toString());
          mutablePasswordTypes.add(password);
          startTag = -1;
        }
      }
    }
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    try {
      // check if passwords match first
      for (MutablePassword password : mutablePasswordTypes) {
        Evaluator parsable = new JavascriptEvaluator();

        String evaluation = parsable.evaluateExpression(password.getExpression(), ParameterFactory.getAllParamterData());

        if (password.getType().equals(ExtendedPassword.PASSWORD_TYPE)) {
          String evaluatedPassword = getText() + evaluation;

          if (!evaluatedPassword.equals(confirmPassword)) {
            return false;
          }
        } else if (password.getType().equals(TimeLockPassword.PASSWORD_TYPE)) {
          if (!evaluation.equalsIgnoreCase("true")) {
            return false;
          }
        }

      }
    } catch (ParseException e) {
      throw new PasswordException(e);
    }
    return true;
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  public static MutableBlock createMutableBlock(TimeType extendedTimeValue, TimeType lockTimeType, int lockStartTime, int lockEndTime) {
    MutableBlock extendedMutableBlock = ExtendedPassword.createMutableBlock(extendedTimeValue);
    MutableBlock timeLockMutableBlock = TimeLockPassword.createMutableBlock(lockTimeType, lockStartTime, lockEndTime);

    String expression = extendedMutableBlock.toString() + timeLockMutableBlock.toString();
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, expression);
    return block;
  }

  public static MutablePassword createPassword(String text, TimeType extendedTimeValue, TimeType lockTimeType, int lockStartTime, int lockEndTime)
      throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    MutableBlock block = createMutableBlock(extendedTimeValue, lockTimeType, lockStartTime, lockEndTime);

    String mutablePassword = text + block;
    return new ExtendedTimeLockPassword(mutablePassword);
  }

}
