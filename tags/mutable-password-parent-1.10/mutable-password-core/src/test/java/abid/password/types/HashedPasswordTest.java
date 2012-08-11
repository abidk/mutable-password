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

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.evaluator.EvaluationException;

public class HashedPasswordTest {

  @Test
  public void shouldParsePassword() throws PasswordException, EvaluationException {
    MutablePassword password = createPassword("1");
    HashedPassword hashedPassword = new HashedPassword(password.getPassword());

    assertThat(hashedPassword.getText()).isEqualTo("eeca91fd439b6d5e827e8fda7fee3546f2def93508637483f6be8a2df7a4392");
    assertThat(hashedPassword.getEvaluatedPassword()).isEqualTo("eeca91fd439b6d5e827e8fda7fee3546f2def93508637483f6be8a2df7a4392");
    assertThat(hashedPassword.getMutableBlock().toString()).isEqualTo("[hashed{SHA-256,32,31}]");
    assertThat(hashedPassword.getType()).isEqualTo("hashed");
    assertThat(hashedPassword.getExpression()).isEqualTo("SHA-256,32,31");
  }

  @Test
  public void getPasswordTypeShouldReturnCorrectPasswordType() throws PasswordException, EvaluationException {
    MutablePassword password = createPassword("123");
    assertThat(password.getPasswordType()).isEqualTo("hashed");
  }

  @Test
  public void getEvaluatedPasswordShouldReturnHashedPassword() throws EvaluationException {
    MutablePassword password = createPassword("123");
    assertThat(password.getText()).isEqualTo("60f1fbbad6b32ffbdf60642f4e93d1c54563482f2fefcbde2206f47d4c4f0");
    assertThat(password.getEvaluatedPassword()).isEqualTo("60f1fbbad6b32ffbdf60642f4e93d1c54563482f2fefcbde2206f47d4c4f0");
  }

  @Test
  public void confirmPasswordShouldMatchWhenPasswordIsCorrect() throws PasswordException {
    MutablePassword password = createPassword("123");
    assertThat(password.confirmPassword("123")).isTrue();
    assertThat(password.confirmPassword("1234")).isFalse();
  }

  private MutablePassword createPassword(String text) {
    return new HashedPasswordBuilder().createPassword(text, "1".getBytes());
  }
}
