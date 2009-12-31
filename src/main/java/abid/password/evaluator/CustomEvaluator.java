/* Copyright 2009 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package abid.password.evaluator;

import java.util.Map;

@Deprecated
public class CustomEvaluator implements Evaluator {

  public static final char ADD = '+';
  public static final char MINUS = '-';
  public static final char MULTIPLY = '*';
  public static final char DIVIDE = '/';
  public static final char MOD = '%';
  public static final char POWER = '^';

  private String algorithm;

  public CustomEvaluator(String algorithm) {
    this.algorithm = algorithm;
  }

  public int getResult(int xValue, int yValue) {
    int result = 0;
    char sign = 0;
    for (char characters : algorithm.toCharArray()) {
      if (characters == 'x') {
        result = evaluate(sign, xValue, result);
      } else if (characters == 'y') {
        result = evaluate(sign, yValue, result);
      } else if (characters == ADD) {
        sign = ADD;
      } else if (characters == MINUS) {
        sign = MINUS;
      } else if (characters == MULTIPLY) {
        sign = MULTIPLY;
      } else if (characters == DIVIDE) {
        sign = DIVIDE;
      } else if (characters == MOD) {
        sign = MOD;
      } else if (characters == POWER) {
        sign = POWER;
      }
    }
    return result;
  }

  private int evaluate(char sign, int value, int result) {
    if (sign == ADD) {
      return result += value;
    } else if (sign == MINUS) {
      return result -= value;
    } else if (sign == MULTIPLY) {
      return result *= value;
    } else if (sign == DIVIDE) {
      return result /= value;
    } else if (sign == MOD) {
      return result %= value;
    }
    return value;
  }

  @Override
  public String evaluateExpression(String expression, Map<String, Number> map) throws ParseException {
    return null;
  }


}
