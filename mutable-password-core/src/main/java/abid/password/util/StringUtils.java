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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Additional String manipulation methods.
 * 
 * @author Abid
 */
public class StringUtils {

  private StringUtils() {
  }

  /**
   * Checks the string input to see if it contains numbers only.
   * 
   * @param textInput
   * @return true if it input contains numerals only
   */
  public static boolean containsOnlyNumbers(String textInput) {
    if (textInput == null) {
      return false;
    }
    for (char c : textInput.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Takes an InputStream and converts into into a String value.
   * 
   * @param is
   * @return string value of stream
   * @throws IOException
   */
  public static String convertStreamToString(InputStream is) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();

    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    return sb.toString();
  }
}
