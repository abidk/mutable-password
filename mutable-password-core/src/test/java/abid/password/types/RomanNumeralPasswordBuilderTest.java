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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import abid.password.MutablePassword;
import abid.password.PasswordException;
import abid.password.parameters.TimeParameter;

public class RomanNumeralPasswordBuilderTest {

  private RomanNumeralPasswordBuilder builder;

  @Before
  public void setUp() {
    builder = new RomanNumeralPasswordBuilder();
  }

  @Test
  public void createPasswordShouldBuildPasswordAsExpected() throws PasswordException {
    MutablePassword password = builder.createPassword("pass", 900);
    assertEquals("pass[romanNumeral{900}]", password.getPassword());

    password = builder.createPassword("pass", TimeParameter.YEAR);
    assertEquals("pass[romanNumeral{year}]", password.getPassword());
  }

}