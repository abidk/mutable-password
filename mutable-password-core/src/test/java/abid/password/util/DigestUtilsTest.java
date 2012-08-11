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
package abid.password.util;

import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class DigestUtilsTest {

  @Test
  public void hashShouldReturnExactHash() {
    byte[] actual = DigestUtils.hash("1".getBytes(), "1".getBytes(), "SHA-256", 32);
    byte[] confirm = DigestUtils.hash("1".getBytes(), "1".getBytes(), "SHA-256", 32);
    assertThat(actual).isEqualTo(confirm);
  }

  @Test
  public void hashShouldReturnDifferentHash() {
    byte[] actual = DigestUtils.hash("1".getBytes(), "1".getBytes(), "SHA-256", 32);
    byte[] confirm = DigestUtils.hash("1".getBytes(), "2".getBytes(), "SHA-256", 32);
    assertThat(actual).isNotEqualTo(confirm);

    actual = DigestUtils.hash("1".getBytes(), "1".getBytes(), "SHA-256", 32);
    confirm = DigestUtils.hash("2".getBytes(), "1".getBytes(), "SHA-256", 32);
    assertThat(actual).isNotEqualTo(confirm);
  }

  @Test
  public void generateSaltShouldReturnSaltAtSpecifiedLength() {
    assertThat(DigestUtils.generateSalt(1)).hasSize(1);
    assertThat(DigestUtils.generateSalt(99)).hasSize(99);
  }

  @Test
  public void generateSaltShouldReturnRandomSalt() {
    byte[] actual = DigestUtils.generateSalt(99999);
    byte[] confirm = DigestUtils.generateSalt(99999);
    // there is a slim chance the same salt is generated.
    assertThat(actual).isNotEqualTo(confirm);
  }

  @Test
  public void toHexShouldReturnAHexadecimalConversion() {
    assertThat(DigestUtils.toHex("A8c,".getBytes())).isEqualTo("4138632c");
  }

  @Test
  public void hashShouldThrowRuntimeExceptionWhenWrongAlgorithmSpecified() {
    try {
      DigestUtils.hash("1".getBytes(), "1".getBytes(), "FAKE", 32);
      fail("expected exception!");
    } catch (RuntimeException e) {
      assertThat(e).hasMessage("Could not hash input with algorithm 'FAKE'.");
    }
  }
  
  @Test
  public void hashShouldThrowRuntimeExceptionWhenWrongDigestLengthSpecified() {
    try {
      DigestUtils.hash("1".getBytes(), "1".getBytes(), "MD5", 1);
      fail("expected exception!");
    } catch (RuntimeException e) {
      assertThat(e).hasMessage("Could not digest input because...");
    }
  }

  @Test
  public void testPrivateConstructor() throws Exception {
    Class<?> cls = DigestUtils.class;
    Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    c.newInstance((Object[]) null);
  }
}
