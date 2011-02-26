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

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import junit.framework.TestCase;
import abid.password.evaluator.Evaluator;
import abid.password.evaluator.JavascriptEvaluator;
import abid.password.evaluator.ParseException;
import abid.password.parameters.Parameter;
import abid.password.types.ExtendedPassword;

public class MutablePasswordTest extends TestCase {

  public void testNullMutableBlock() {
    String passwordText = "pass";
    MutablePassword password = new ExtendedPassword(passwordText, null);
    assertNull(password.getExpression());
    assertNull(password.getType());
    assertNull(password.getMutableBlock());
    assertEquals(passwordText, password.getText());
  }

  public void testBrokenMutableExpression() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    ExtendedPassword password = new ExtendedPassword("abidextendedTimeLock{year}]");
    assertNull(password.getExpression());
    assertNull(password.getType());
    assertNull(password.getMutableBlock());
    assertNull(password.getText());
  }

  public void testNewEvaluator() {
    String passwordText = "pass";
    MutablePassword password = new ExtendedPassword(passwordText, null);
    // check the default evaluator
    assertEquals(JavascriptEvaluator.class, password.getEvaluator().getClass());
    // set new evaluator
    password.setEvaluator(new NewEvalutator());
    assertEquals(NewEvalutator.class, password.getEvaluator().getClass());
  }

  public class NewEvalutator implements Evaluator {

    @Override
    public String evaluateExpression(String expression, Map<String, Parameter> map) throws ParseException {
      return null;
    }
  }

}
