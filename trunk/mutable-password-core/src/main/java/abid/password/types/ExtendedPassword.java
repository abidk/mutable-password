/**
 * Copyright 2012 Abid Khalil
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
 * Concatenates a parameter at the end of the static password.
 * 
 * e.g. pass[extend{year}] = pass2009
 * 
 * @author Abid
 * 
 */
public class ExtendedPassword extends MutablePassword {

  /** Password type name. */
  public static final String PASSWORD_TYPE = "extend";

  /**
   * Password is separated into the text and mutable block. The mutable block is
   * split into the password type and expression.
   * 
   * @param password
   */
  public ExtendedPassword(String password) {
    super(password);
  }

  /**
   * Takes the text and mutable block as separate objects. The mutable block is
   * split into the password type and expression.
   * 
   * @param text
   * @param block
   */
  public ExtendedPassword(String text, MutableBlock block) {
    super(text, block);
  }

  @Override
  public String getEvaluatedPassword() throws EvaluationException {
    ExpressionEvaluator expressionEvaluator = getEvaluator();
    Map<String, Parameter> parameters = ParameterRegistery.getParameters();
    String evaluation = expressionEvaluator.evaluate(getExpression(), parameters);
    String evaluatedPassword = getText() + evaluation;
    // System.out.println( "==>" + evaluatedPassword);
    return evaluatedPassword;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    try {
      String evaluatedPassword = getEvaluatedPassword();
      return evaluatedPassword.equals(confirmPassword);
    } catch (EvaluationException e) {
      throw new PasswordException(e);
    }
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

}
