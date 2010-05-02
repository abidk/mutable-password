package abid.password.evaluator;

import junit.framework.TestCase;
import abid.password.parameters.TimeParameter;

public class JavascriptEvaluatorTest extends TestCase {

  public void testScript() throws ParseException {
    JavascriptEvaluator evaluator = new JavascriptEvaluator();

    String result = evaluator.evaluateExpression("2009+2009.2", TimeParameter.getValues());
    System.out.println(result);
  }

}
