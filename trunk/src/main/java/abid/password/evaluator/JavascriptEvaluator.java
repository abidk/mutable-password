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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import abid.password.MutablePassword;
import abid.password.parameters.TimeType;

/**
 * This will take a expression and evaluate it.
 * 
 * TODO This will need to be made configurable once we have other paramters
 * other than time.
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

  public String evaluateExpression(MutablePassword password) throws ParseException {
    return evaluateExpression(password);
  }

  @Override
  public String evaluateExpression(String expression) throws ParseException {
    try {
      // set time parameters
      // might have other parameters so this bit will need to be made
      // configurable
      for (TimeType timePassword : TimeType.values()) {
        engine.put(timePassword.getType(), timePassword.getCalendarValue());
      }

      // evaluate and get result
      String result = String.valueOf(engine.eval(expression));
      return result;
    } catch (ScriptException e) {
      throw new ParseException(e);
    }
  }

  public static void main(String[] args) {
    JavascriptEvaluator evaluator = new JavascriptEvaluator();
    try {
      String result = evaluator.evaluateExpression("hour>=0&&hour<=21");
      System.out.println(result);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

}