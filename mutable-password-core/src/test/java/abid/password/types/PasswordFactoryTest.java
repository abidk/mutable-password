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

import java.lang.reflect.Constructor;

import org.junit.Test;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.evaluator.ParseException;
import abid.password.parameters.TimeParameter;

public class PasswordFactoryTest {

  @Test
  public void getInstanceShouldReturnSimplePasswordWhenPasswordNotRegistered() throws PasswordInstantiationException {
    Password password = PasswordFactory.getInstance(buildMockPassword("something").getPassword());
    assertEquals(SimplePassword.class, password.getClass());
  }

  @Test
  public void registerPasswordShouldRegisterNewPassword() throws PasswordInstantiationException {
    PasswordFactory.registerPasswordType(MockMutablePassword.class);
    Password password = PasswordFactory.getInstance(buildMockPassword("something").getPassword());
    assertEquals(MockMutablePassword.class, password.getClass());
  }

  @Test
  public void getPasswordTypesShouldReturnRegisteredPasswordTypes() {
    PasswordFactory.registerPasswordType(MockMutablePassword.class);
    assertTrue(PasswordFactory.getPasswordTypes().contains(MockMutablePassword.class));
  }

  @Test
  public void testSingletonClass() throws Exception {
    final Class<?> cls = PasswordFactory.class;
    final Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    c.newInstance((Object[]) null);
  }

  @Test
  public void passwordFactoryShouldReturnCorrectPasswordTypes() throws PasswordInstantiationException {
    Password password = PasswordFactory.getInstance(TimeLockPassword.createPassword("abid", TimeParameter.HOUR, 0, 24).getPassword());
    assertEquals(TimeLockPassword.class, password.getClass());

    password = PasswordFactory.getInstance(ExtendedPassword.createPassword("abid", TimeParameter.HOUR).getPassword());
    assertEquals(ExtendedPassword.class, password.getClass());

    password = PasswordFactory.getInstance(CaesarCipherPassword.createPassword("abid").getPassword());
    assertEquals(CaesarCipherPassword.class, password.getClass());

    password = PasswordFactory.getInstance(new SimplePassword("abid").getPassword());
    assertEquals(SimplePassword.class, password.getClass());

    password = PasswordFactory.getInstance(RotatingPassword.createPassword("abid", "1234").getPassword());
    assertEquals(RotatingPassword.class, password.getClass());

    password = PasswordFactory.getInstance(RomanNumeralPassword.createPassword("abid", 1234).getPassword());
    assertEquals(RomanNumeralPassword.class, password.getClass());
  }

  private MutablePassword buildMockPassword(String text) {
    MutableBlock block = new MutableBlock(MockMutablePassword.PASSWORD_TYPE, "1");
    return new MockMutablePassword(text, block);
  }

  public static class MockMutablePassword extends MutablePassword {

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
    public String getEvaluatedPassword() throws ParseException {
      String evaluatedPassword = getText() + "1";
      return evaluatedPassword;
    }

    @Override
    public boolean confirmPassword(String confirmPassword) throws PasswordException {
      try {
        String evaluatedPassword = getEvaluatedPassword();
        return confirmPassword.equals(evaluatedPassword);
      } catch (ParseException e) {
        throw new PasswordException(e);
      }
    }
  }

}
