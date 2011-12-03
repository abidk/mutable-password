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

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import abid.password.parameters.Parameter;
import abid.password.parameters.ParameterFactory;

public class JavascriptEvaluatorTest {

  private JavascriptEvaluator evaluator;
  private Map<String, Parameter> parameters;

  @Before
  public void setUp() {
    evaluator = new JavascriptEvaluator();
    parameters = ParameterFactory.getAllParamterData();
  }

  @Test
  public void evaluateExpressionShouldReturnCorrectResult() throws ParseException {
    assertEquals("4018.2", evaluator.evaluateExpression("2009+2009.2", parameters));
    assertEquals("1", evaluator.evaluateExpression("(2+2+2)/6", parameters));
  }

  @Test(expected = ParseException.class)
  public void evaluateExpressionShouldThrowExceptionWhenExpressionIsMalformed() throws ParseException {
    evaluator.evaluateExpression("2009(2009.2", parameters);
  }

  @Test(expected = ParseException.class)
  public void evaluateExpressionShouldThrowExceptionWhenExpressionIsNonEscaped() throws ParseException {
    evaluator.evaluateExpression("a", parameters);
  }

  @Test
  public void evaluateExpressionShouldEvaluateExpressionWhenExpressionIsEscaped() throws ParseException {
    assertEquals("a", evaluator.evaluateExpression("'a'", parameters) );
  }

}
