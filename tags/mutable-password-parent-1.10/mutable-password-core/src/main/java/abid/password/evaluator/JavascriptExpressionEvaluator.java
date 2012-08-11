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
package abid.password.evaluator;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import abid.password.parameters.Parameter;

/**
 * Javascript implementation to evaluate expression.
 * 
 * Note: This uses Java version 6 JavaScript features, to make this compatible with
 * Java version 5, remove this class and use beanshell instead.
 * 
 * @author Abid
 */
public class JavascriptExpressionEvaluator implements ExpressionEvaluator {

  private ScriptEngine engine;

  /**
   * Initialise the built-in Javascript engine.
   */
  public JavascriptExpressionEvaluator() {
    ScriptEngineManager manager = new ScriptEngineManager();
    engine = manager.getEngineByName("js");
  }

  @Override
  public String evaluate(String expression, Map<String, Parameter> parameters) throws EvaluationException {
    try {
      for (Map.Entry<String, Parameter> e : parameters.entrySet()) {
        String key = e.getKey();
        Parameter parameter = e.getValue();
        engine.put(key, parameter.getValue());
      }

      String result = String.valueOf(engine.eval(expression));
      return removeEmptyDecimalPoint(result);
    } catch (ScriptException e) {
      throw new EvaluationException(e);
    }
  }

  /*
   * Removes the '.0' when two integers are added.
   */
  private String removeEmptyDecimalPoint(String value) {
    return value.replace(".0", "");
  }
}
