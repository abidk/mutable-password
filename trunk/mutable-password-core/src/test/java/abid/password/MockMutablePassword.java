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
package abid.password;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.evaluator.EvaluationException;

public class MockMutablePassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "test";

  public MockMutablePassword(String password) {
    super(password);
  }

  public MockMutablePassword(String text, MutableBlock mutableBlock) {
    super(text, mutableBlock);
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  @Override
  public String getEvaluatedPassword() throws EvaluationException {
    String evaluatedPassword = getText() + "1";
    return evaluatedPassword;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) throws PasswordException {
    try {
      String evaluatedPassword = getEvaluatedPassword();
      return confirmPassword.equals(evaluatedPassword);
    } catch (EvaluationException e) {
      throw new PasswordException(e);
    }
  }
}