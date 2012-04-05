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
package abid.password;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import abid.password.MutablePasswordParser;
import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.parameters.TimeParameter;
import abid.password.types.CaesarCipherPassword;
import abid.password.types.CaesarCipherPasswordBuilder;
import abid.password.types.ExtendedPassword;
import abid.password.types.ExtendedPasswordBuilder;
import abid.password.types.ExtendedTimeLockPassword;
import abid.password.types.ExtendedTimeLockPasswordBuilder;
import abid.password.types.PasswordTypeRegistry;
import abid.password.types.RomanNumeralPassword;
import abid.password.types.RomanNumeralPasswordBuilder;
import abid.password.types.RotatingPassword;
import abid.password.types.RotatingPasswordBuilder;
import abid.password.types.SimplePassword;
import abid.password.types.TimeLockPassword;
import abid.password.types.TimeLockPasswordBuilder;

public class MutablePasswordParserTest {

  private MutablePasswordParser parser;

  @Before
  public void setUp() {
    parser = new MutablePasswordParser();
  }

  @Test
  public void parseShouldReturnSimplePasswordWhenPasswordIsNotMutable() throws PasswordParseException {
    Password password = parser.parse(new SimplePassword("abid").getPassword());
    assertEquals(SimplePassword.class, password.getClass());

    password = parser.parse("pass1");
    assertEquals(SimplePassword.class, password.getClass());

    password = parser.parse("pass[");
    assertEquals(SimplePassword.class, password.getClass());

    password = parser.parse("pass[]");
    assertEquals(SimplePassword.class, password.getClass());

    password = parser.parse("pass[{]");
    assertEquals(SimplePassword.class, password.getClass());

    password = parser.parse("ab[id}]");
    assertEquals(SimplePassword.class, password.getClass());
  }

  @Test
  public void parseShouldReturnCorrectMutablePasswordType() throws PasswordParseException {
    Password password = parser.parse(new TimeLockPasswordBuilder().createPassword("abid", TimeParameter.HOUR, 0, 24).getPassword());
    assertEquals(TimeLockPassword.class, password.getClass());

    password = parser.parse(new ExtendedPasswordBuilder().createPassword("abid", TimeParameter.HOUR).getPassword());
    assertEquals(ExtendedPassword.class, password.getClass());

    password = parser.parse(new CaesarCipherPasswordBuilder().createPassword("abid").getPassword());
    assertEquals(CaesarCipherPassword.class, password.getClass());

    password = parser.parse(new RotatingPasswordBuilder().createPassword("abid", "1234").getPassword());
    assertEquals(RotatingPassword.class, password.getClass());

    password = parser.parse(new RomanNumeralPasswordBuilder().createPassword("abid", 1234).getPassword());
    assertEquals(RomanNumeralPassword.class, password.getClass());

    password = parser.parse(new ExtendedTimeLockPasswordBuilder().createPassword("abid", TimeParameter.YEAR, TimeParameter.HOUR, 0, 24).getPassword());
    assertEquals(ExtendedTimeLockPassword.class, password.getClass());
  }

  @Test
  public void parseShouldThrowExceptionWhenPasswordTypeNotRegistered() {
    try {
      parser.parse(buildMockPassword("something").getPassword());
      fail("Expected MalformedPasswordException!");
    } catch (PasswordParseException e) {
      assertEquals(PasswordParseException.class, e.getClass());
      assertEquals("Unable to determine password type.", e.getMessage());
    }
  }

  @Test
  public void parseShouldReturnRegisteredMockMutablePasswordType() throws PasswordParseException {
    PasswordTypeRegistry.registerPasswordType(MockMutablePassword.class);

    Password password = parser.parse(buildMockPassword("something").getPassword());
    assertEquals(MockMutablePassword.class, password.getClass());
  }

  @Test(expected = PasswordParseException.class)
  public void convertPasswordStringToObjectShouldThrowExceptionWhenPasswordIsMalFormed() throws PasswordParseException {
    parser.convertPasswordStringToObject(null, null, PasswordTypeRegistry.getPasswordTypes());
  }

  private MutablePassword buildMockPassword(String text) {
    MutableBlock block = new MutableBlock(MockMutablePassword.PASSWORD_TYPE, "1");
    return new MockMutablePassword(text, block);
  }

}
