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

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class PasswordExceptionTest {

  @Test
  public void testPasswordException() {
    PasswordException exception = new PasswordException("some exception");
    assertThat(exception).hasMessage("some exception");

    exception = new PasswordException(new Exception("some cause exception"));
    assertThat(exception.getCause()).hasMessage("some cause exception");

    exception = new PasswordException("some text", new Exception("some cause exception"));
    assertThat(exception).hasMessage("some text");
    assertThat(exception.getCause()).hasMessage("some cause exception");
  }
}
