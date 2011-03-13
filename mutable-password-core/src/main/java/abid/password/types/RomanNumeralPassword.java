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
import abid.password.util.StringUtils;

/**
 * Replaces the number value with Roman numerals.
 * 
 * @author Abid
 */
public class RomanNumeralPassword extends MutablePassword {

  /** Password type name. */
  public static final String PASSWORD_TYPE = "romanNumeral";

  /**
   * Takes the String password, which is then separated into the text and
   * mutable block. The mutable block is split into the password type and
   * expression.
   * 
   * @param password
   */
  public RomanNumeralPassword(String password) {
    super(password);
  }

  @Override
  public String getEvaluatedPassword() throws ParseException {
    Evaluator evaluator = getEvaluator();
    Map<String, Parameter> parameters = ParameterFactory.getAllParamterData();
    String evaluation = evaluator.evaluateExpression(getExpression(), parameters);

    String romanValue = evaluation;
    if (StringUtils.containsOnlyNumbers(evaluation)) {
      long value = Long.parseLong(evaluation);
      romanValue = getRoman(value);
    }
    String evaluatedPassword = getText() + romanValue;
    return evaluatedPassword;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    try {
      String evaluatedPassword = getEvaluatedPassword();
      return evaluatedPassword.equals(confirmPassword);
    } catch (ParseException e) {
      throw new PasswordException(e);
    }
  }

  private String getRoman(long value) {
    StringBuilder romanNum = new StringBuilder();
    while (value >= 1000) {
      value -= 1000;
      romanNum.append("M");
    }
    while (value >= 900) {
      value -= 900;
      romanNum.append("CM");
    }
    while (value >= 500) {
      value -= 500;
      romanNum.append("D");
    }
    while (value >= 400) {
      value -= 400;
      romanNum.append("CD");
    }
    while (value >= 100) {
      value -= 100;
      romanNum.append("C");
    }
    while (value >= 90) {
      value -= 90;
      romanNum.append("XC");
    }
    while (value >= 10) {
      value -= 10;
      romanNum.append("X");
    }
    while (value >= 9) {
      value -= 9;
      romanNum.append("IX");
    }
    while (value >= 5) {
      value -= 5;
      romanNum.append("V");
    }
    while (value >= 4) {
      value -= 4;
      romanNum.append("IV");
    }
    while (value >= 1) {
      value -= 1;
      romanNum.append("I");
    }
    return romanNum.toString();
  }

  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  /**
   * Create the mutable password based on the input values.
   * 
   * @param text
   * @param timeValue
   * @return mutable password
   */
  public static MutablePassword createPassword(String text, TimeParameter timeValue) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, timeValue.getTextField());
    String mutablePassword = text + block;
    return new RomanNumeralPassword(mutablePassword);
  }

  /**
   * Create the mutable password based on the input values.
   * 
   * @param text
   * @param value
   * @return mutable password
   */
  public static MutablePassword createPassword(String text, int value) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, String.valueOf(value));
    String mutablePassword = text + block;
    return new RomanNumeralPassword(mutablePassword);
  }
}
