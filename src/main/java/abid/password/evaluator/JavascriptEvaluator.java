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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import abid.password.parameters.TimeType;

/**
 * This will take a expression and evaluate it.
 * 
 * 
 * @author Abid
 * 
 */
public class JavascriptEvaluator implements Evaluator {

  private ScriptEngine engine;

  public JavascriptEvaluator() {
    ScriptEngineManager manager = new ScriptEngineManager();
    engine = manager.getEngineByName("js");
  }

  @Override
  public String evaluateExpression(String expression, Map<String, Integer> map) throws ParseException {
    try {
      for (String key : map.keySet()) {
        Integer value = map.get(key);
        // System.out.println( value + " " + key );
        engine.put(key, value);
      }

      // evaluate and get result
      String result = String.valueOf(engine.eval(expression));
      // System.out.println(fixDecimalOutput(result));
      return fixDecimalOutput(result);
    } catch (ScriptException e) {
      throw new ParseException(e);
    }
  }

  /*
   * This fixes the problem where you add two integers and you get a float which
   * ends with '.0'
   * 
   */
  private String fixDecimalOutput(String evaluation) {
    return evaluation.replace(".0", "");
  }

  public static void main(String[] args) {
    JavascriptEvaluator evaluator = new JavascriptEvaluator();
    try {
      String result = evaluator.evaluateExpression("2009+2009.2", TimeType.getValues());
      System.out.println(result);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}