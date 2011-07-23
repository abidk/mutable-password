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

package abid.password.evaluator;

import java.util.Map;

import junit.framework.TestCase;
import abid.password.parameters.Parameter;
import abid.password.parameters.ParameterFactory;

public class JavascriptEvaluatorTest extends TestCase {

  public void testScript() throws ParseException {
    JavascriptEvaluator evaluator = new JavascriptEvaluator();
    Map<String, Parameter> parameters = ParameterFactory.getAllParamterData();
    evaluator.evaluateExpression("2009+2009.2", parameters);
  }
  
  public void testScriptException( ) {
    JavascriptEvaluator evaluator = new JavascriptEvaluator();
    String result = null;
    try {
      Map<String, Parameter> parameters = ParameterFactory.getAllParamterData();
      result = evaluator.evaluateExpression("2009(2009.2", parameters);
    } catch (ParseException e) {
      //e.printStackTrace();
    }
    assertNull(result);
  }
  
  public void testNonEscapedExpression()  {
    JavascriptEvaluator evaluator = new JavascriptEvaluator();
    Map<String, Parameter> parameters = ParameterFactory.getAllParamterData();
    try {
      evaluator.evaluateExpression("a", parameters);
    } catch (ParseException e) {
      return;
    }
    assert false;
  }
  
  public void testEscapedExpression()  {
    JavascriptEvaluator evaluator = new JavascriptEvaluator();
    Map<String, Parameter> parameters = ParameterFactory.getAllParamterData();
    try {
      evaluator.evaluateExpression("'a'", parameters);
    } catch (ParseException e) {
      assert false;
    }
  }

}
