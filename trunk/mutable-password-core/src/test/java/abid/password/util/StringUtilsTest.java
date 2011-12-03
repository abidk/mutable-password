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
package abid.password.util;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

import org.junit.Test;

public class StringUtilsTest {

  @Test
  public void containsOnlyNumbersShouldReturnCorrectValue() {
    assertTrue(StringUtils.containsOnlyNumbers("123"));
    assertFalse(StringUtils.containsOnlyNumbers(" "));
    assertFalse(StringUtils.containsOnlyNumbers(null));
    assertFalse(StringUtils.containsOnlyNumbers(" 123"));
    assertFalse(StringUtils.containsOnlyNumbers("123 "));
    assertFalse(StringUtils.containsOnlyNumbers(" 123 "));
    assertFalse(StringUtils.containsOnlyNumbers("123a"));
  }

  @Test
  public void convertStreamToStringShouldConvertStreamToString() throws IOException {
    String inputText = "test";
    InputStream in = null;
    try {
      in = new ByteArrayInputStream(inputText.getBytes());
      String convertedStr = StringUtils.convertStreamToString(in);
      assertEquals(inputText, convertedStr);
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  @Test
  public void testPrivateConstructor() throws Exception {
    final Class<?> cls = StringUtils.class;
    final Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    c.newInstance((Object[]) null);
  }
}
