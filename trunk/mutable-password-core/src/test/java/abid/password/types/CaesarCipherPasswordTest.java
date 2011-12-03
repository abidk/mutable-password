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

import static org.junit.Assert.*;

import org.junit.Test;

import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.StatefulMutablePassword;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeParameter;

public class CaesarCipherPasswordTest {

  @Test
  public void passwordFactoryShouldReturnCorrectPasswordType() throws PasswordInstantiationException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("pass");
    Password unknownPassword = PasswordFactory.getInstance(password.getPassword());
    assertEquals(CaesarCipherPassword.class, unknownPassword.getClass());
    assertEquals(CaesarCipherPassword.PASSWORD_TYPE, ((StatefulMutablePassword) unknownPassword).getType());
  }

  @Test
  public void confirmPasswordShouldValidateInputCorrectly() throws PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("z");
    password.setState(1);
    assertTrue(password.confirmPassword("a"));
    assertFalse(password.confirmPassword("b"));

    // test state value
    password = CaesarCipherPassword.createPassword("z");
    password.setState(2);
    assertTrue(password.confirmPassword("b"));
    assertFalse(password.confirmPassword("a"));

    // test lowercase letters
    password = CaesarCipherPassword.createPassword("zyxwvutsrqponmlkjihgfedcba");
    password.setState(1);
    assertTrue(password.confirmPassword("azyxwvutsrqponmlkjihgfedcb"));
    assertFalse(password.confirmPassword("zyxwvutsrqponmlkjihgfedcba"));

    // test single character
    password = CaesarCipherPassword.createPassword("f");
    password.setState(1);
    assertTrue(password.confirmPassword("g"));
    assertFalse(password.confirmPassword("f"));

    password = CaesarCipherPassword.createPassword("^^");
    password.setState(1);
    assertTrue(password.confirmPassword("^^"));
    assertFalse(password.confirmPassword("^"));
    
    password = CaesarCipherPassword.createPassword(" ");
    password.setState(1);
    assertTrue(password.confirmPassword(" "));
    assertFalse(password.confirmPassword("  "));
    
    // test digits
    password = CaesarCipherPassword.createPassword("6abcdefghijklm6nopqrstuvwxyz6");
    password.setState(1);
    assertTrue(password.confirmPassword("6bcdefghijklmn6opqrstuvwxyza6"));
    assertFalse(password.confirmPassword("6abcdefghijklm6nopqrstuvwxyz6"));

    // test symbols
    password = CaesarCipherPassword.createPassword("6abcdefghijklm^nopqrstuvwxyz6");
    password.setState(1);
    assertTrue(password.confirmPassword("6bcdefghijklmn^opqrstuvwxyza6"));
    assertFalse(password.confirmPassword("6abcdefghijklm^nopqrstuvwxyz6"));

    // uppercase
    password = CaesarCipherPassword.createPassword("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    password.setState(1);
    assertTrue(password.confirmPassword("BCDEFGHIJKLMNOPQRSTUVWXYZA"));
    assertFalse(password.confirmPassword("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

    password = CaesarCipherPassword.createPassword("6ABCDEFGHIJKL6MNOPQRSTUVWXYZ6");
    password.setState(1);
    assertTrue(password.confirmPassword("6BCDEFGHIJKLM6NOPQRSTUVWXYZA6"));
    assertFalse(password.confirmPassword("6ABCDEFGHIJKL6MNOPQRSTUVWXYZ6"));

    password = CaesarCipherPassword.createPassword("AbCdEfGhIjKlMnOpQrStUvWxYz");
    password.setState(1);
    assertTrue(password.confirmPassword("BcDeFgHiJkLmNoPqRsTuVwXyZa"));
    assertFalse(password.confirmPassword("AbCdEfGhIjKlMnOpQrStUvWxYz"));

    password = CaesarCipherPassword.createPassword("6z6");
    password.setState(1);
    assertTrue(password.confirmPassword("6a6"));
    assertFalse(password.confirmPassword("6z6"));

    password = CaesarCipherPassword.createPassword("^AbCdEfGhIjKl^MnOpQrStUvWxYz^");
    password.setState(1);
    assertTrue(password.confirmPassword("^BcDeFgHiJkLm^NoPqRsTuVwXyZa^"));
    assertFalse(password.confirmPassword("^AbCdEfGhIjKl^MnOpQrStUvWxYz^"));

    password = CaesarCipherPassword.createPassword("6AbCdEfGhIjKl^MnOpQrStUvWxYz6");
    password.setState(1);
    assertTrue(password.confirmPassword("6BcDeFgHiJkLm^NoPqRsTuVwXyZa6"));
    assertFalse(password.confirmPassword("6AbCdEfGhIjKl^MnOpQrStUvWxYz6"));
    
    password = CaesarCipherPassword.createPassword("ab^id");
    password.setState(1);
    assertTrue(password.confirmPassword("bc^je"));
    assertFalse(password.confirmPassword("ab^id"));
  }

  @Test
  public void getEvaluatedPasswordShouldReturnCorrectValue() throws ParseException, PasswordException {
    StatefulMutablePassword password = CaesarCipherPassword.createPassword("z");
    password.setState(1);
    assertEquals("a", password.getEvaluatedPassword());

    // test state value
    password = CaesarCipherPassword.createPassword("z");
    password.setState(2);
    assertEquals("b", password.getEvaluatedPassword());

    // test lowercase letters
    password = CaesarCipherPassword.createPassword("zyxwvutsrqponmlkjihgfedcba");
    password.setState(1);
    assertEquals("azyxwvutsrqponmlkjihgfedcb", password.getEvaluatedPassword());

    // test single character
    password = CaesarCipherPassword.createPassword("f");
    password.setState(1);
    assertEquals("g", password.getEvaluatedPassword());

    // test digits
    password = CaesarCipherPassword.createPassword("6abcdefghijklm6nopqrstuvwxyz6");
    password.setState(1);
    assertEquals("6bcdefghijklmn6opqrstuvwxyza6", password.getEvaluatedPassword());

    // test symbols
    password = CaesarCipherPassword.createPassword("6abcdefghijklm^nopqrstuvwxyz6");
    password.setState(1);
    assertEquals("6bcdefghijklmn^opqrstuvwxyza6", password.getEvaluatedPassword());
  }

  @Test
  public void testShiftPasswordUsingTime() throws PasswordException {
    TimeParameter timeType = TimeParameter.DAY_OF_WEEK;
    CaesarCipherPassword shiftPassword = (CaesarCipherPassword) CaesarCipherPassword.createPassword("abid", timeType);
    String shiftedPassword = shiftPassword.getShiftedPassword(timeType.getCalendarValue());
    assertTrue(shiftPassword.confirmPassword(shiftedPassword));
    assertFalse(shiftPassword.confirmPassword("abid"));
  }

  @Test(expected = PasswordException.class)
  public void confirmPasswordShouldThrowPasswordExceptionWhenExpressionIsMalformed() throws PasswordException {
    MutablePassword password = new CaesarCipherPassword("ab^id") {
      public String getEvaluatedPassword() throws ParseException {
        throw new ParseException(new Exception("exception"));
      };
    };
    password.confirmPassword("something");
  }
}
