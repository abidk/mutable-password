package abid.password;

import junit.framework.TestCase;

public class PasswordExceptionTest extends TestCase {

  private String exceptionMsg = "some exception";

  public void testPasswordException() {
    try {
      throw new PasswordException(new Exception(exceptionMsg));
    } catch (Exception e) {
      assertEquals(exceptionMsg, e.getCause().getMessage());
    }

    try {
      throw new PasswordException("some text", new Exception(exceptionMsg));
    } catch (Exception e) {
      assertEquals("some text", e.getMessage());
      assertEquals(exceptionMsg, e.getCause().getMessage());
    }

  }
}
