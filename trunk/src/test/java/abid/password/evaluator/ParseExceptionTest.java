package abid.password.evaluator;

import junit.framework.TestCase;

public class ParseExceptionTest extends TestCase {

  private String exceptionMsg = "some exception";

  public void testParseException() {
    try {
      throw new ParseException(new Exception(exceptionMsg));
    } catch (Exception e) {
      assertEquals(exceptionMsg, e.getCause().getMessage());
    }
  }
}
